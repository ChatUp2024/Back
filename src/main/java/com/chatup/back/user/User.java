package com.chatup.back.user;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class User {

    private int id;
    private String nickname;
    private String email;
    private String picture;
    private String providerId;
    private LocalDateTime createTime;
}
