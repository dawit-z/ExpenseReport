package com.example.expensereport.controllers;

import com.example.expensereport.models.Expense;
import com.example.expensereport.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expRepo;

    @GetMapping
    public ResponseEntity<Iterable<Expense>> getExpenses() {
        var expenses = expRepo.findAll();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable int id) {
        var expense = expRepo.findById(id);
        if (expense.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(expense.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Expense expense) {
        if (expense == null || expense.getId() != 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        expRepo.save(expense);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Expense> update(@PathVariable int id, @RequestBody Expense expense) {
        if (expense == null || expense.getId() != 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        var exp = expRepo.findById(id);
        if (exp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var newExp = expRepo.save(expense);
        return new ResponseEntity<>(newExp, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        var expense = expRepo.findById(id);
        if (expense.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        expRepo.delete(expense.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
