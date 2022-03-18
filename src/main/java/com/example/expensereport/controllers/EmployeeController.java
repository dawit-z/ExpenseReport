package com.example.expensereport.controllers;

import com.example.expensereport.models.Employee;
import com.example.expensereport.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository empRepo;

    @GetMapping
    public ResponseEntity<Iterable<Employee>> findEmployees() {
        var employees = empRepo.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> findEmployee(@PathVariable int id) {
        var employee = empRepo.findById(id);
        if (employee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Employee employee) {
        if (employee == null || employee.getId() != 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        empRepo.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> update(@PathVariable int id, @RequestBody Employee employee) {
        if (employee == null || employee.getId() != 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        var emp = empRepo.findById(id);
        if (emp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var newEmp = empRepo.save(employee);
        return new ResponseEntity<>(newEmp, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        var employee = empRepo.findById(id);
        if (employee.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        empRepo.delete(employee.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
