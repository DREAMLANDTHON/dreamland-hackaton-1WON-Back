package com.hackathonOne.hackathon.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Allergy> allergies = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CanEat> canEats = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<SpecialType> specialTypes = new ArrayList<>();

    //== 연관관계 메서드 ==//
    public void addCanEat(CanEat canEat) {
        canEats.add(canEat);
        canEat.setMember(this);
    }

    public void cancelCanEat(CanEat canEat) {
        canEats.remove(canEat);
        canEat.setMember(null);
    }

    public void addAllergy(Allergy allergy) {
        allergies.add(allergy);
        allergy.setMember(this);
    }

    public void addSpecialType(SpecialType specialType) {
        specialTypes.add(specialType);
        specialType.setMember(this);
    }

    public static Member createMember(Member member, List<SpecialType> specialTypes, List<Allergy> allergies) {

        for (SpecialType specialType : specialTypes) {
            member.addSpecialType(specialType);
        }

        for (Allergy allergy : allergies) {
            member.addAllergy(allergy);
        }

        return member;
    }
}
