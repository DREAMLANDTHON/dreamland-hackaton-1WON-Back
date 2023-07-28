package com.hackathonOne.hackathon.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy="member", cascade = CascadeType.ALL)
        private List<Allergy> allergies = new ArrayList<>();
    @OneToMany(mappedBy="member", cascade = CascadeType.ALL)
        private List<CanEat> canEats = new ArrayList<>();
    @OneToMany(mappedBy="member", cascade = CascadeType.ALL)
        private List<SpecialType> specialTypes = new ArrayList<>();

    //== 연관관계 메서드 ==//
//    public void addCanEat(CanEat canEat){
//        CanEats.add(canEat);
//        CanEat.setMember(this);
//    }
        public void addAllergy(Allergy allergy){
        Allergies.add(allergy);
        Allergy.setMember(this);
    }
        public void addSpecialType(SpecialType specialType){
        SpecialTypes.add(specialType);
        SpecialType.setMember(this);
    }

    public static Member createMember(Member member, SpecialType... specialTypes, Allergy... allergies){

        for(SpecialType specialType : specialTypes){
            member.addSpecialType(specialType);
        }

        for(Allergy allergy : allergies){
            member.addAllergy(allergy);
        }

        return member;
    }

}
