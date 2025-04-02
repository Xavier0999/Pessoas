package com.example.Pessoas.model;


import com.example.Pessoas.annotation.PessoaValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "PESSOA")
public class Pessoa {



@Id
@Column(name = "id")
@GeneratedValue
private Integer id;

    @Column(name = "nome")
    @NotBlank(message = "Nome nao pode ser nulo")
    @Size(min = 10, message = "O nome nao pode ter menos de 10 caracteres ou mais de 10 caracteres")
    private String nome;

    @Column(name = "email")
    @NotNull
    @Email(message = "O email nao pode ser nulo")
    private String email;

    @Column(name = "telefone")
    @PessoaValidation(message = "O telefone nao pode ser nulo ou no formato errado")
    private Long telefone;


    public Pessoa() {
    }

    public Pessoa(Integer id, String nome, String email, Long telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}


