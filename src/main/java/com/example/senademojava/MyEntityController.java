package com.example.senademojava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entities")
public class MyEntityController {

    @Autowired
    private MyEntityRepository repository;

    @PostMapping
    public MyEntity create(@RequestBody MyEntity entity) {
        return repository.save(entity);
    }

    @GetMapping("/{id}")
    public MyEntity read(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    }

    @GetMapping
    public List<MyEntity> list() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public MyEntity update(@PathVariable Long id, @RequestBody MyEntity updatedEntity) {
        MyEntity entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        entity.setName(updatedEntity.getName());
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
