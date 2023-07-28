package com.hackathonOne.hackathon.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Allergy {

    @Id @GeneratedValue
    @Column(name="allergy_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

}
