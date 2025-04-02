package com.example.Pessoas.service;


import com.example.Pessoas.exception.RegistroNaoEncontradoException;
import com.example.Pessoas.model.Pessoa;
import com.example.Pessoas.repository.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class PessoaService {


    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvar(@Valid Pessoa id){
        pessoaRepository.save(id);
                return id;
    }

public Pessoa busca(Integer id){
    Optional<Pessoa> buscar = pessoaRepository.findById(id);
    return buscar.orElseThrow(()-> new RegistroNaoEncontradoException("Pessoa: "+id+" nao encontrado"));
}

public List<Pessoa> buscarTodos(){
return StreamSupport.stream(pessoaRepository.findAll().spliterator(),false).toList();
}

public List<Pessoa> buscarPorNomeAproximado(String nome){
        return pessoaRepository.findPessoaByNomeContains(nome);
}

public Pessoa editar(Integer id, Pessoa pessoa){
        Pessoa buscaa = this.busca(id);

        buscaa.setNome(pessoa.getNome());
        buscaa.setEmail(pessoa.getEmail());
        buscaa.setTelefone(pessoa.getTelefone());


        pessoaRepository.save(buscaa);
        return buscaa;
}

public void remover(Integer id){

        Pessoa buscaa = this.busca(id);
        pessoaRepository.delete(buscaa);
}

}
