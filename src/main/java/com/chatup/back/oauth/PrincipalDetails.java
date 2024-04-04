package com.chatup.back.oauth;

import com.chatup.back.user.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class PrincipalDetails implements OAuth2User {

    private String name;
    private User user;
    private Map<String, Object> attributes;

    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
        // 임시
        this.name = (String) attributes.get("sub");
//        this.name = user.getProviderId();
    }

    public User getUser() {
        return user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<GrantedAuthority>();
        collect.add(() -> "USER");
        return collect;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
