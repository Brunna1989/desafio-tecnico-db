package com.br.technical.db.crud.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private Integer idade;  // Idade calculada e mapeada corretamente
    private List<EnderecoDTO> enderecos;

}
