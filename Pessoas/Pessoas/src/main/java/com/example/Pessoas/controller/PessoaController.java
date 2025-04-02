package com.example.Pessoas.controller;


import com.example.Pessoas.model.Pessoa;
import com.example.Pessoas.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "pessoa")
public class PessoaController {


    @Autowired
   private PessoaService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Pessoa>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
    }

@GetMapping(value = { "{id}" },produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Pessoa> get(@PathVariable("id")Integer id){
        Pessoa retorno = service.busca(id);
        return ResponseEntity.status(HttpStatus.OK).body(retorno);
}

    @GetMapping(value={"consulta/{nome}"},produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Pessoa>> get(@PathVariable("nome") String nome) {
        List<Pessoa> buscarNome = service.buscarPorNomeAproximado(nome);
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorNomeAproximado(nome));
    }

    @PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Pessoa> adicionar(@RequestBody @Valid Pessoa pessoa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(pessoa));
    }

    @PutMapping(value = { "{id}" },  consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Pessoa> editar(@PathVariable("id") Integer id, @RequestBody Pessoa pessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(service.editar(id, pessoa));
    }

    @DeleteMapping(value = { "{id}" },
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Pessoa> remover(@PathVariable("id") Integer id) {
        service.remover(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }




}
