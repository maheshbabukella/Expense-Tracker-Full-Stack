package com.mahesh.expense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mahesh.expense.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    //  Get expenses by month and year
    @Query(value = "SELECT * FROM expense WHERE MONTH(STR_TO_DATE(date, '%Y-%m-%d')) = :month AND YEAR(STR_TO_DATE(date, '%Y-%m-%d')) = :year", nativeQuery = true)
    List<Expense> findByMonthAndYear(@Param("month") int month, @Param("year") int year);

    //  Delete expenses by month and year
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM expense WHERE MONTH(STR_TO_DATE(date, '%Y-%m-%d')) = :month AND YEAR(STR_TO_DATE(date, '%Y-%m-%d')) = :year", nativeQuery = true)
    void deleteByMonthAndYear(@Param("month") int month, @Param("year") int year);

    //  Optional: Filter by user (for multi-user support)
    List<Expense> findByUser(String user);
}
