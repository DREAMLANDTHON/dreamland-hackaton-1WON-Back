package com.hackathonOne.hackathon.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SpecialType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialType_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
