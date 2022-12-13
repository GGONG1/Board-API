package com.team9.boardapi.jwt;

import com.team9.boardapi.entity.UserRoleEnum;
import com.team9.boardapi.security.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    //AUTHORIZATION의 key값
    public static final String AUTHORIZATION_HEADER = "Authorization";
    //사용자 권한 값의 key값 - token안에 사용자 권한도 넣어줌
    public static final String AUTHORIZATION_KEY = "auth";
    //token을 만들 때 같이 앞에 붙어서 들어가는 부분
    private static final String BEARER_PREFIX = "Bearer ";
    //토큰 만료 시간
    private static final long TOKEN_TIME = 60 * 60 * 1000L;

    private final UserDetailsServiceImpl userDetailsService;

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    //DI가 이루어지고 난 후에 초기화 하는 메서드
    @PostConstruct
    public void init() {
        //base64로 인코딩이 되어 있기 때문에 secretkey값을 가지고 와서 decode하는 과정
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }
    
    //Header에서 Token을 가져오기
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        //AUTHORIZATION Key에 들어잇는 토큰값이 있는지 없는지 && Bearer로 시작을 하는지
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            //token에 연관이 되지 않는 그냥 string값인 bearer가 6글자 이고 한칸이 띄어져 있는 값을 지운다.
            return bearerToken.substring(7);
        }
        return null;
    }

    //토큰 생성 - jwt를 만드는 메서드
    public String createToken(String username, UserRoleEnum role) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(username)
                        .claim(AUTHORIZATION_KEY, role) //claim이라는 공간안에 사용자의 권한을 넣어 줄 것이고 그 권한을 가져올 때는 우리가 지정해 놓은 OAuth Key를 사용해서(파라미터) 넣어놓을 것
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME)) // setExpiration은 우리가 이 토큰을 언제까지 유효하게 가져갈 건지 지정해주는 부분
                        .setIssuedAt(date) //setIssuedAt은 이 토큰이 언제 만들어졌는지 넣어 주는 부분
                        .signWith(key, signatureAlgorithm)//실제로 위에서 Secret key를 사용해서 만든 key 객체와 그 key객체를 어떤 알고리즘을 사용해서 암호화할 것인지를 지정해주는 부분(여기서는 HS256사용)
                        .compact();
    }

    //토큰 검증
    public boolean validateToken(String token) {
    
        try {
            //setSigningKey쪽에다가 우리가 token을 만들 때 사용한 키를 넣어주고 어떠한 토큰에 검증을 할건지를 parseClaimsJws()부분에 토큰을 넣어주면 내부적으로 토큰을 검증을 해준다.
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // 인증 객체 생성
    public Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
