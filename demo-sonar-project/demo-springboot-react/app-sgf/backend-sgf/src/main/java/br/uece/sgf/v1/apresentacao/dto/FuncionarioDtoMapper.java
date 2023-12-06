package br.uece.sgf.v1.apresentacao.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.uece.sgf.v1.negocio.Funcionario;

public class FuncionarioDtoMapper {

    public static FuncionarioDto toDto(Funcionario funcionario) {
        FuncionarioDto dto = new FuncionarioDto();
        dto.setId(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setCargo(funcionario.getCargo());
        dto.setTelefone(funcionario.getTelefone());
        dto.setDataAdmissao(funcionario.getDataAdmissao());
        return dto;
    }
    
    public static Funcionario fromDto(FuncionarioDto dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.getId());
        funcionario.setNome(dto.getNome());
        funcionario.setCargo(dto.getCargo());
        funcionario.setTelefone(dto.getTelefone());
        funcionario.setDataAdmissao(dto.getDataAdmissao());
        return funcionario;
    }

    public static List<FuncionarioDto> toDtoList(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(FuncionarioDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Funcionario> fromDtoList(List<FuncionarioDto> dtos) {
        return dtos.stream()
                .map(FuncionarioDtoMapper::fromDto)
                .collect(Collectors.toList());
    }
}
