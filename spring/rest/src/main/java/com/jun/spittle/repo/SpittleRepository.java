package com.jun.spittle.repo;

import com.jun.spittle.model.Spittle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpittleRepository extends JpaRepository<Spittle,Long>,SpittleSweeper {

}
