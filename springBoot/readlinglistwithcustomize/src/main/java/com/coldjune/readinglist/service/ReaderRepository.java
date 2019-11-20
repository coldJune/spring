package com.coldjune.readinglist.service;

import com.coldjune.readinglist.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, String> {
}
