package com.korea.project.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;


public class MemberDetail implements UserDetails, OAuth2User {

    private final Member member;
    private final Map<String,Object> attributes;


    public MemberDetail(Member member) {
        this.member = member;
        this.attributes= new HashMap<>();
    }

    //OAuth 로그인
//    public MemberDetail(Member member, Map<String, Object> attributes) {
//        this.member = member;
//        this.attributes = attributes;
//    }


    //OAuth2User의 메서드
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    //별로 안중요 안씀
    @Override
    public String getName() {
        return member.getLoginId();
    }


    //UserDetails의 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
