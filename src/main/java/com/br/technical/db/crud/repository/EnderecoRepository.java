package com.br.technical.db.crud.repository;

import com.br.technical.db.crud.model.Endereco;
import com.br.technical.db.crud.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

    List<Endereco> findByPessoa(Pessoa pessoa);

    Optional<Endereco> findByPessoaAndPrincipalTrue(Pessoa pessoa);
}
