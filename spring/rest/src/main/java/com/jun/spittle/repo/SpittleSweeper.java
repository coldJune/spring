package com.jun.spittle.repo;


import com.jun.spittle.model.Spittle;

import java.util.List;

public interface SpittleSweeper {
    public List<Spittle> findSpittles(long max, int count);
}
