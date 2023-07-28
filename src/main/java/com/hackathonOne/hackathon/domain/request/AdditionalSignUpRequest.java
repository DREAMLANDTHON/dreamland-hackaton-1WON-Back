package com.hackathonOne.hackathon.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class AdditionalSignUpRequest {

    private Long id;
    private List<String> allergies;
    private List<String> specialTypes;
}
