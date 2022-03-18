package com.example.expensereport.controllers;

import com.example.expensereport.models.Item;
import com.example.expensereport.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/items")
public class ItemController {

    @Autowired
    private ItemRepository iRepo;

    @GetMapping
    public ResponseEntity<Iterable<Item>> getItems() {
        var items = iRepo.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> getItem(@PathVariable int id) {
        var item = iRepo.findById(id);
        if (item.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(item.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Item item) {
        if (item == null || item.getId() != 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        iRepo.save(item);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Item> update(@PathVariable int id, @RequestBody Item item) {
        if (item == null || item.getId() != 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        var i = iRepo.findById(id);
        if (i.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var newItem = iRepo.save(item);
        return new ResponseEntity<>(newItem, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        var item = iRepo.findById(id);
        if (item.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iRepo.delete(item.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
