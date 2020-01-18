package com.example.todoist.responseBean;

import lombok.Data;

import javax.persistence.Column;

@Data
public class GoogleOAuth2UserInfo {
    private String id;

    private String name;

    private String email;

    private String imageUrl;

}
