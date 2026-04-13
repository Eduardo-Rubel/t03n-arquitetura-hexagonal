package com.fag.lucasmartins.arquitetura_software.core.domain.bo;

import com.fag.lucasmartins.arquitetura_software.core.domain.exceptions.DomainException;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class PessoaBO {

    private UUID id;
    private String nomeCompleto;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String telefone;

    public void validarCadastro() {
        validarNomeCompleto();
        validarCpf();
        validarDataNascimento();
        validarEmail();
        validarTelefone();
    }

    private void validarNomeCompleto() {
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new DomainException("Erro: nome completo é obrigatório.");
        }
    }

    private void validarCpf() {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new DomainException("Erro: CPF é obrigatório.");
        }

        if (cpf.trim().length() != 11) {
            throw new DomainException("Erro: CPF deve possuir 11 caracteres.");
        }
    }

    private void validarDataNascimento() {
        if (dataNascimento == null) {
            throw new DomainException("Erro: data de nascimento é obrigatória.");
        }

        if (Period.between(dataNascimento, LocalDate.now()).getYears() < 18) {
            throw new DomainException("Erro: a pessoa deve ser maior de 18 anos.");
        }
    }

    private void validarEmail() {
        if (email == null || email.trim().isEmpty()) {
            throw new DomainException("Erro: e-mail é obrigatório.");
        }

        if (!email.contains("@")) {
            throw new DomainException("Erro: e-mail inválido.");
        }
    }

    private void validarTelefone() {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new DomainException("Erro: telefone é obrigatório.");
        }

        if (telefone.trim().length() != 11) {
            throw new DomainException("Erro: telefone deve possuir 11 caracteres.");
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
