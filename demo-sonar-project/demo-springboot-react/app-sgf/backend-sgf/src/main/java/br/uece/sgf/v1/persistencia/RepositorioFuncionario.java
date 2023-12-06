package br.uece.sgf.v1.persistencia;

import java.util.List;

import br.uece.sgf.v1.negocio.Funcionario;

public interface RepositorioFuncionario{//Porta de Saída
    Funcionario salvarFuncionario(Funcionario funcionario);
    Funcionario buscarFuncionarioPorId(Long id);
    List<Funcionario> listarFuncionarios();
    void atualizarFuncionario(Funcionario funcionario);
    void excluirFuncionario(Funcionario funcionario);
}
