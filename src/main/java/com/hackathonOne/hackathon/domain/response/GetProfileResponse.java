package com.hackathonOne.hackathon.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetProfileResponse {

    private MemberDto member;
    private List<AllergyDto> allergies;
    private List<SpecialTypeDto> specialTypes;
    private List<CanEatDto> canEats;

    @Data
    @AllArgsConstructor
    public static class MemberDto {

        private String name;
    }

    @Data
    @AllArgsConstructor
    public static class AllergyDto {
        private Long id;
        private String name;

        // getters and setters
    }

    @Data
    @AllArgsConstructor
    public static class SpecialTypeDto {
        private Long id;
        private String name;

        // getters and setters
    }

    @Data
    @AllArgsConstructor
    public static class CanEatDto {
        private Long id;
        private String name;
        private int likes;
        // getters and setters
    }
}
