package com.example.expensereport.repositories;

import com.example.expensereport.models.Expenseline;
import org.springframework.data.repository.CrudRepository;

public interface ExpenselineRepository extends CrudRepository<Expenseline, Integer> {
}
