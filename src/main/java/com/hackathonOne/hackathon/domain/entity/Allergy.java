package com.hackathonOne.hackathon.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Allergy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="allergy_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

}
