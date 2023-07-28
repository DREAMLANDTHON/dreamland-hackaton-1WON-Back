package com.hackathonOne.hackathon.domain.response;

import com.hackathonOne.hackathon.domain.entity.Allergy;
import com.hackathonOne.hackathon.domain.entity.CanEat;
import com.hackathonOne.hackathon.domain.entity.SpecialType;
import com.hackathonOne.hackathon.domain.request.UpdateMemberRequest;
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

        // getters and setters
    }
}
