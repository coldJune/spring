package com.coldjune.readinglistwithtest.service;

import com.coldjune.readinglistwithtest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository  extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);

}
