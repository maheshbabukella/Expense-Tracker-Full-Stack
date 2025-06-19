package com.mahesh.expense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahesh.expense.model.Expense;
import com.mahesh.expense.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repo;

    public Expense addExpense(Expense e) {
        return repo.save(e);
    }

    public List<Expense> getAllExpenses() {
        return repo.findAll();
    }

    public void deleteExpense(Long id) { // FIX: Integer → Long
        repo.deleteById(id);
    }

    public List<Expense> getByMonthAndYear(int month, int year) {
        return repo.findByMonthAndYear(month, year);
    }

    @Transactional
    public void deleteByMonthAndYear(int month, int year) {
        repo.deleteByMonthAndYear(month, year);
    }

    // ✅ Optional - useful for multi-user support
    public List<Expense> getByUser(String username) {
        return repo.findByUser(username);
    }
}
