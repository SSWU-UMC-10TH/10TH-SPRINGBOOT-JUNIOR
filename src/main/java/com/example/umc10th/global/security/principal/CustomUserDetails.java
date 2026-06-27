package com.example.umc10th.global.security.principal;

import com.example.umc10th.domain.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한 설정이 필요 없다면 빈 리스트 반환 (필요 시 역할/권한 부여 가능)
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // DB에 저장된 암호화된 비밀번호 리턴
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // 식별자로 사용할 이메일 리턴
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}