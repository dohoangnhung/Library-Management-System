package com.example.demo.repository;

import com.example.demo.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, UUID> {

    @Query(
            nativeQuery = true,
            value = "select * from Borrow where book_barcode = ?1 and date_returned is null"
    )
    Optional<Borrow> selectBorrowedBookItemByBarcode(String barcode);

}
