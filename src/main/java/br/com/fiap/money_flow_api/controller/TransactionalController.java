package br.com.fiap.money_flow_api.controller;

import br.com.fiap.money_flow_api.model.Transactional;
import br.com.fiap.money_flow_api.repository.TransactionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionalController {

    @Autowired
    private TransactionalRepository repository;

    @GetMapping
    public List<Transactional> index(){
        return repository.findAll();
    }
}
