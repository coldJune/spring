package com.jun.spitter.repository;

import com.jun.spitter.model.Spitter;

public interface SpitterRepository {
    Spitter save(Spitter spitter);

    Spitter findByUsername(String userName);
}
