package com.br.technical.db.crud.controller;

import com.br.technical.db.crud.dto.EnderecoDTO;
import com.br.technical.db.crud.dto.PessoaDTO;
import com.br.technical.db.crud.model.Pessoa;
import com.br.technical.db.crud.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public Page<PessoaDTO> getAllPessoas(@RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Pessoa> pessoas = pessoaService.findAllPessoas(pageRequest);

        return pessoas.map(pessoa -> new PessoaDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getDataNascimento(),
                pessoa.getCpf(),
                pessoa.getIdade(),
                pessoa.getEnderecos().stream().map(endereco -> new EnderecoDTO(
                        endereco.getId(),
                        endereco.getRua(),
                        endereco.getNumero(),
                        endereco.getBairro(),
                        endereco.getCidade(),
                        endereco.getEstado(),
                        endereco.getCep(),
                        endereco.isPrincipal()
                )).collect(Collectors.toList())
        ));
    }

    @PostMapping
    public PessoaDTO createPessoa(@RequestBody Pessoa pessoa) {
        Pessoa savedPessoa = pessoaService.createPessoa(pessoa);
        return new PessoaDTO(
                savedPessoa.getId(),
                savedPessoa.getNome(),
                savedPessoa.getDataNascimento(),
                savedPessoa.getCpf(),
                savedPessoa.getIdade(),
                savedPessoa.getEnderecos().stream().map(endereco -> new EnderecoDTO(
                        endereco.getId(),
                        endereco.getRua(),
                        endereco.getNumero(),
                        endereco.getBairro(),
                        endereco.getCidade(),
                        endereco.getEstado(),
                        endereco.getCep(),
                        endereco.isPrincipal()
                )).collect(Collectors.toList())
        );
    }

    @PutMapping("/{id}")
    public PessoaDTO updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa updatedPessoa = pessoaService.updatePessoa(id, pessoa);
        return new PessoaDTO(
                updatedPessoa.getId(),
                updatedPessoa.getNome(),
                updatedPessoa.getDataNascimento(),
                updatedPessoa.getCpf(),
                updatedPessoa.getIdade(),
                updatedPessoa.getEnderecos().stream().map(endereco -> new EnderecoDTO(
                        endereco.getId(),
                        endereco.getRua(),
                        endereco.getNumero(),
                        endereco.getBairro(),
                        endereco.getCidade(),
                        endereco.getEstado(),
                        endereco.getCep(),
                        endereco.isPrincipal()
                )).collect(Collectors.toList())
        );
    }
}
