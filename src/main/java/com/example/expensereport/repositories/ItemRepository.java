package com.example.expensereport.repositories;

import com.example.expensereport.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
