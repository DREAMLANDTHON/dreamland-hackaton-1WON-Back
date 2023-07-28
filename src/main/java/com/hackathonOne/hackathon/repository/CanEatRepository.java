package com.hackathonOne.hackathon.repository;

import com.hackathonOne.hackathon.domain.entity.CanEat;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CanEatRepository {

    private final EntityManager em;

    public List<CanEat> findAllByName(String name){
        return em.createQuery("SELECT m FROM CanEat m JOIN FETCH m.member WHERE m.name = :name AND m.member IS NOT NULL", CanEat.class)
                .setParameter("name", name)
                .getResultList();
    }

}
