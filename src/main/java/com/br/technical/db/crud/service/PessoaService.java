package com.br.technical.db.crud.service;

import com.br.technical.db.crud.exception.BusinessException;
import com.br.technical.db.crud.model.Endereco;
import com.br.technical.db.crud.model.Pessoa;
import com.br.technical.db.crud.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa createPessoa(Pessoa pessoa) {
        if (pessoaRepository.existsByCpf(pessoa.getCpf())) {
            throw new BusinessException("CPF já cadastrado.");
        }

        verificarEnderecoPrincipal(pessoa);

        return pessoaRepository.save(pessoa);
    }

    public Pessoa findByCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf)
                .orElseThrow(() -> new BusinessException("Pessoa não encontrada para o CPF: " + cpf));
    }

    public Pessoa updatePessoa(Long id, Pessoa pessoa) {
        // Verificando se o CPF já está cadastrado, mas não pertence à mesma pessoa
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(id);
        if (pessoaExistente.isPresent() && !pessoaExistente.get().getCpf().equals(pessoa.getCpf())) {
            if (pessoaRepository.existsByCpf(pessoa.getCpf())) {
                throw new BusinessException("CPF já cadastrado.");
            }
        }

        verificarEnderecoPrincipal(pessoa);

        pessoa.setId(id);
        return pessoaRepository.save(pessoa);
    }

    private void verificarEnderecoPrincipal(Pessoa pessoa) {
        long countPrincipal = pessoa.getEnderecos().stream().filter(Endereco::isPrincipal).count();
        if (countPrincipal > 1) {
            throw new BusinessException("Apenas um endereço pode ser marcado como principal.");
        }
    }

    public Page<Pessoa> findAllPessoas(PageRequest pageRequest) {
        return pessoaRepository.findAll(pageRequest);
    }
}
