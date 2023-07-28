package com.hackathonOne.hackathon.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateMemberRequest {
    private MemberDto member;
    private List<AllergyDto> allergies;
    private List<SpecialTypeDto> specialTypes;
    private List<CanEatDto> canEats;


    public static class MemberDto {

        private String name;
    }

    public static class AllergyDto {
        private Long id;
        private String name;

        // getters and setters
    }

    public static class SpecialTypeDto {
        private Long id;
        private String name;

        // getters and setters
    }

    public static class CanEatDto {
        private Long id;
        private String name;

        // getters and setters
    }
}
