package com.jun.spitter.repository;

import com.jun.spitter.model.Spittle;

import java.util.List;

public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(Long id);
}
