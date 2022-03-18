package com.example.expensereport.controllers;

import com.example.expensereport.models.Expenseline;
import com.example.expensereport.repositories.ExpenselineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/expenselines")
public class ExpenselineController {

    @Autowired
    private ExpenselineRepository lineRepo;

    @GetMapping
    public ResponseEntity<Iterable<Expenseline>> getExpenselines() {
        var eLines = lineRepo.findAll();
        return new ResponseEntity<>(eLines, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Expenseline> getExpenseline(@PathVariable int id) {
        var eLine = lineRepo.findById(id);
        if (eLine.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(eLine.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Expenseline expenseline) {
        if (expenseline == null || expenseline.getId() != 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        lineRepo.save(expenseline);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Expenseline> update(@PathVariable int id, @RequestBody Expenseline expenseline) {
        if (expenseline == null || expenseline.getId() != 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        var eLine = lineRepo.findById(id);
        if (eLine.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var newLine = lineRepo.save(expenseline);
        return new ResponseEntity<>(newLine, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        var line = lineRepo.findById(id);
        if (line.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lineRepo.delete(line.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
