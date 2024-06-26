package com.chatup.back.oauth;

import com.chatup.back.user.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class Oauth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        UserInfo userInfo = new UserInfo(oAuth2User.getAttributes());
        System.out.println(oAuth2User.getAttributes());
        System.out.println(oAuth2User.getName());

        // 검사
//        User user = findByProviderId();
        User user = new User();
        // 없으면 새로 생성
        System.out.println(userInfo.getEmail());
        System.out.println(userInfo.getPicture());
        System.out.println(userInfo.getProviderId());

        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }
}
