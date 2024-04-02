package com.chatup.back.oauth;

import java.util.Map;

public class UserInfo {

    private Map<String, Object> attributes;

    public UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }

    public String getPicture() {
        return (String) attributes.get("picture");
    }
}
