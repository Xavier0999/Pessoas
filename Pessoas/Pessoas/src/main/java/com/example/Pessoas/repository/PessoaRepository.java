package com.example.Pessoas.repository;


import com.example.Pessoas.model.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {
    List<Pessoa> findPessoaByNomeContains(String nome);

}
