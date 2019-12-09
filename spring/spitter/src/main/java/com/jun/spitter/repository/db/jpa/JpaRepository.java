package com.jun.spitter.repository.db.jpa;

import com.jun.spitter.model.Spitter;
import com.jun.spitter.repository.SpitterRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Profile("jpa")
public class JpaRepository implements SpitterRepository {

    @PersistenceContext
    private EntityManager entityManager;
    public Spitter save(Spitter spitter) {
        entityManager.persist(spitter);
        return spitter;

    }

    public Spitter findByUsername(String userName) {
        return (Spitter)entityManager.createQuery("select s from Spitter s where s.username=?").setParameter(1,userName).getSingleResult();
    }
}
