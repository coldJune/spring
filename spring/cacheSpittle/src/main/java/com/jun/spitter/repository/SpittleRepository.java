package com.jun.spitter.repository;

import com.jun.spitter.model.Spittle;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    @Cacheable("spittleCache")
    Spittle findOne(Long id);
    @CachePut(value = "spittleCache", key = "#result.id")
    Spittle save(Spittle spittle);
}
