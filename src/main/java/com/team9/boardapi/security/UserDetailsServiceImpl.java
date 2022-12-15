package com.team9.boardapi.security;

import com.team9.boardapi.entity.User;
import com.team9.boardapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        //UserDetailsImpl을 반환을 하는데 이때 생성자의 user,user에서 갖고온 username가 들어가기 때문에 DB에서 조회를 해온 username과 user객체를 담아 주면서 UserDetailsImpl를 반환을 함.
        return new UserDetailsImpl(user, user.getUsername());
    }
}
