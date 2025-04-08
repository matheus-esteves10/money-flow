package br.com.fiap.money_flow_api.controller;

import br.com.fiap.money_flow_api.model.Category;
import br.com.fiap.money_flow_api.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryRepository repository;

    @GetMapping()
    public List<Category> getCategories() {
        return repository.findAll();
    }

    @PostMapping()
    public ResponseEntity<Category> create(@RequestBody @Valid Category category) {
        logger.info("Cadastrando... " + category.getName());
        repository.save(category);
        return ResponseEntity.status(201).body(category);
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        logger.info("Buscando " + id);

        return getCategory(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById (@PathVariable Long id) {
        logger.info("Deletando " + id);
        repository.delete(getCategory(id));
    }

    @PutMapping("/{id}")
    public Category editById (@PathVariable Long id, @RequestBody @Valid Category category) {
        logger.info("Editando " + id);

        getCategory(id);
        category.setId(id);
        repository.save(category);

        return category;
    }

    private Category getCategory(Long id){
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }

}
