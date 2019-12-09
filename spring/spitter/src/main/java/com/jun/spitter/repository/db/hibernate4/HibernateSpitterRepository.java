package com.jun.spitter.repository.db.hibernate4;

import com.jun.spitter.model.Spitter;
import com.jun.spitter.repository.SpitterRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.io.Serializable;

@Repository
@Profile("hibernate4")
public class HibernateSpitterRepository implements SpitterRepository {
    private SessionFactory sessionFactory;

    @Inject
    public HibernateSpitterRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }
    public Spitter findByUsername(String userName) {
        return (Spitter) currentSession().createCriteria(Spitter.class)
                .add(Restrictions.eq("username",userName))
                .list().get(0);
    }

    public Spitter save(Spitter spitter) {
        Serializable id = currentSession().save(spitter);
        return new Spitter((Long)id,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFullName(),
                spitter.getEmail(),
                spitter.isUpdateByEmail());
    }
}
