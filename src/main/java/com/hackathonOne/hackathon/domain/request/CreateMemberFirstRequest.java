package com.hackathonOne.hackathon.domain.request;

import lombok.Data;

@Data
public class CreateMemberFirstRequest {
    private Long id;
    private String name;
    private String email;
}
