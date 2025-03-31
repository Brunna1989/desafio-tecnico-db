package com.br.technical.db.crud.service;

import com.br.technical.db.crud.dto.EnderecoDTO;
import com.br.technical.db.crud.exception.BusinessException;
import com.br.technical.db.crud.model.Endereco;
import com.br.technical.db.crud.model.Pessoa;
import com.br.technical.db.crud.repository.EnderecoRepository;
import com.br.technical.db.crud.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public List<EnderecoDTO> listarEnderecosPorPessoa(Long pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        List<Endereco> enderecos = enderecoRepository.findByPessoa(pessoa);

        return enderecos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public EnderecoDTO adicionarEndereco(Long pessoaId, EnderecoDTO enderecoDTO) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        if (enderecoDTO.isPrincipal()) {
            enderecoRepository.findByPessoaAndPrincipalTrue(pessoa)
                    .ifPresent(e -> {
                        throw new BusinessException("Já existe um endereço principal cadastrado para esta pessoa.");
                    });
        }

        Endereco endereco = new Endereco(
                enderecoDTO.getId(),
                enderecoDTO.getRua(),
                enderecoDTO.getNumero(),
                enderecoDTO.getBairro(),
                enderecoDTO.getCidade(),
                enderecoDTO.getEstado(),
                enderecoDTO.getCep(),
                enderecoDTO.isPrincipal(),
                pessoa
        );

        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return converterParaDTO(enderecoSalvo);
    }

    @Transactional
    public EnderecoDTO atualizarEndereco(Long enderecoId, EnderecoDTO enderecoDTO) {
        Endereco enderecoExistente = enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        if (enderecoDTO.isPrincipal()) {
            enderecoRepository.findByPessoaAndPrincipalTrue(enderecoExistente.getPessoa())
                    .filter(e -> !e.getId().equals(enderecoExistente.getId())) // Garante que não estamos comparando o mesmo endereço
                    .ifPresent(e -> {
                        throw new BusinessException("Já existe um endereço principal cadastrado para esta pessoa.");
                    });
        }

        enderecoExistente.setRua(enderecoDTO.getRua());
        enderecoExistente.setNumero(enderecoDTO.getNumero());
        enderecoExistente.setBairro(enderecoDTO.getBairro());
        enderecoExistente.setCidade(enderecoDTO.getCidade());
        enderecoExistente.setEstado(enderecoDTO.getEstado());
        enderecoExistente.setCep(enderecoDTO.getCep());
        enderecoExistente.setPrincipal(enderecoDTO.isPrincipal());

        Endereco enderecoAtualizado = enderecoRepository.save(enderecoExistente);
        return converterParaDTO(enderecoAtualizado);
    }

    @Transactional
    public void excluirEndereco(Long enderecoId) {
        Endereco endereco = enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        enderecoRepository.delete(endereco);
    }

    private EnderecoDTO converterParaDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.isPrincipal()
        );
    }
}
