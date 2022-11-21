package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query(
            nativeQuery = true,
            value = "select * from Book where title like %?1% union all select * from Book where author like %?1%"
    )
    List<Book> selectBooksByTitleOrAuthor(String keyword);

    @Query(
            nativeQuery = true,
            value = "select * from Book where category like %?1%"
    )
    List<Book> selectBooksByCategory(String category);

}
