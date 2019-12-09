package com.jun.spitter.repository.db.springData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class SpitterSpringDataRepositoryImpl implements SpitterSweeper{

    @PersistenceContext
    private EntityManager entityManager;

    public int eliteSweep() {
        String update =
                "UPDATE Spitter spitter " +
                        "SET spitter.status = 'Elite' " +
                        "WHERE spitter.status = 'Newbie' " +
                        "AND spitter.id IN (" +
                        "SELECT s FROM Spitter s WHERE (" +
                        "  SELECT COUNT(spittles) FROM s.spittles spittles) > 10000" +
                        ")";
        return entityManager.createQuery(update).executeUpdate();
    }
}
