package br.uece.sgf.v1.negocio;

import java.util.List;

import org.springframework.stereotype.Service;

import br.uece.sgf.v1.persistencia.RepositorioFuncionario;

@Service
public class ServicoFuncionario {

	private RepositorioFuncionario repositorioFuncionario;

	public ServicoFuncionario(RepositorioFuncionario repositorioFuncionario) {
		this.repositorioFuncionario = repositorioFuncionario;
	}

	public List<Funcionario> listarFuncionarios() {
		List<Funcionario> funcionarios = repositorioFuncionario.listarFuncionarios();
		if (funcionarios.isEmpty()) {
			throw new NegocioException("Nenhuma funcionario encontrada.");
		}
		return funcionarios;
	}

	public Funcionario criarFuncionario(Funcionario novaFuncionario) {
		return repositorioFuncionario.salvarFuncionario(novaFuncionario);
	}

	public Funcionario atualizarFuncionario(Funcionario funcionario) {
		repositorioFuncionario.atualizarFuncionario(funcionario);
		return buscarFuncionarioPorId(funcionario.getId());
	}
		
	public void removerFuncionario(Funcionario funcionario) {
		Funcionario funcionarioExistente = this.buscarFuncionarioPorId(funcionario.getId());
		repositorioFuncionario.excluirFuncionario(funcionarioExistente);
	}
	
	public Funcionario buscarFuncionarioPorId(Long id) {
		Funcionario funcionarioExistente = repositorioFuncionario.buscarFuncionarioPorId(id);
		
		if (funcionarioExistente == null) {
			throw new NegocioException("Funcionario não encontrada.");
		}
		
		return funcionarioExistente;
	}

}
