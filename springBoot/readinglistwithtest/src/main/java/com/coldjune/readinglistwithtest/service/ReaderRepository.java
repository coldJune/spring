package com.coldjune.readinglistwithtest.service;

import com.coldjune.readinglistwithtest.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, String> {
}
