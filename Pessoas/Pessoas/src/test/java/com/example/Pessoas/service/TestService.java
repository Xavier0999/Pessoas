package com.example.Pessoas.service;


import com.example.Pessoas.model.Pessoa;
import com.example.Pessoas.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
public class TestService {


    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

@Test
    void testaAdicionarPessoa(){
    Pessoa add = build(2, "Gustavo", "Gustavo@gmail.com", 51993111712L);

    doAnswer(invocacao -> {
        return add;
    }).when(pessoaRepository).save(eq(add));

    Pessoa  retorno = pessoaService.salvar(add);
    verify(pessoaRepository, times(1)).save(eq(add));
    assertThat("Nao retornou o Id certo", retorno.getId(), equalTo(add.getId()));
}








    private Pessoa build(Integer id, String nome, String email, Long telefone) {
        return new Pessoa(id, nome,email, telefone);
    }
}
