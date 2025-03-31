package com.br.technical.db.crud.controller;

import com.br.technical.db.crud.dto.EnderecoDTO;
import com.br.technical.db.crud.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<EnderecoDTO>> listarEnderecosPorPessoa(@PathVariable Long pessoaId) {
        return ResponseEntity.ok(enderecoService.listarEnderecosPorPessoa(pessoaId));
    }

    @PostMapping("/pessoa/{pessoaId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EnderecoDTO> adicionarEndereco(@PathVariable Long pessoaId, @RequestBody EnderecoDTO enderecoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.adicionarEndereco(pessoaId, enderecoDTO));
    }

    @PutMapping("/{enderecoId}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Long enderecoId, @RequestBody EnderecoDTO enderecoDTO) {
        return ResponseEntity.ok(enderecoService.atualizarEndereco(enderecoId, enderecoDTO));
    }

    @DeleteMapping("/{enderecoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirEndereco(@PathVariable Long enderecoId) {
        enderecoService.excluirEndereco(enderecoId);
    }
}
