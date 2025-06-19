// This is an enhanced Spring Boot controller for your Expense Tracker project
// It supports add, get, delete, update, and multi-user + category support

package com.mahesh.expense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahesh.expense.model.Expense;
import com.mahesh.expense.repository.ExpenseRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    // POST: Add new expense
    @PostMapping("/expense")
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }

    // GET: Get all expenses
    @GetMapping("/expenses")
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // DELETE: Delete by ID
    @DeleteMapping("/expense/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
    }

    // PUT: Update any field by ID (inline editing)
    @PutMapping("/expense/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense updatedData) {
        return expenseRepository.findById(id).map(exp -> {
            if (updatedData.getAmount() != null) exp.setAmount(updatedData.getAmount());
            if (updatedData.getType() != null) exp.setType(updatedData.getType());
            if (updatedData.getDate() != null) exp.setDate(updatedData.getDate());
            if (updatedData.getCategory() != null) exp.setCategory(updatedData.getCategory());
            if (updatedData.getUser() != null) exp.setUser(updatedData.getUser());
            return expenseRepository.save(exp);
        }).orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
    }

}
