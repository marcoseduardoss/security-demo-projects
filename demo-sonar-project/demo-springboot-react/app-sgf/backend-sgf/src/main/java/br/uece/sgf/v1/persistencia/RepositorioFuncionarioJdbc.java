package br.uece.sgf.v1.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import br.uece.sgf.v1.negocio.Funcionario;

@Component
public class RepositorioFuncionarioJdbc implements RepositorioFuncionario {

	private final Connection conexao;

	public RepositorioFuncionarioJdbc(DataSource dataSource) {
		try {

			this.conexao = dataSource.getConnection();

		} catch (SQLException e) {
			throw new PersistenciaException(e);
		}
	}
	
	
	private Funcionario mapearResultadoParaFuncionario(ResultSet resultado) throws SQLException {
		Long id = resultado.getLong("id");
		String nome = resultado.getString("nome");
		String cargo = resultado.getString("cargo");
		String telefone = resultado.getString("telefone");
		String dataAdmissao = resultado.getString("data_admissao");
		return new Funcionario(id, nome, cargo, telefone, dataAdmissao);
	}

	@Override
	public Funcionario salvarFuncionario(Funcionario funcionario) {
		String sql = "INSERT INTO funcionario (nome, cargo, telefone, data_admissao) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCargo());
			stmt.setString(3, funcionario.getTelefone());
			stmt.setString(4, funcionario.getDataAdmissao()== null ? null : funcionario.getDataAdmissao().toString());

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenciaException("A inserção falhou, nenhum registro foi adicionado.");
			}

			// Recupere o ID gerado para o registro inserido
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					funcionario.setId(generatedKeys.getLong(1));
					System.out.printf("[LOG] ID do registro 'inserido': %d!\n", funcionario.getId());
				} else {
					throw new PersistenciaException("Nenhum ID gerado.");
				}
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao salvar funcionario.", e);
		}
		return funcionario;
	}

	@Override
	public Funcionario buscarFuncionarioPorId(Long id) {
		String sql = "SELECT * FROM funcionario WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setLong(1, id);
			try (ResultSet resultado = stmt.executeQuery()) {
				if (resultado.next()) {
					return mapearResultadoParaFuncionario(resultado);
				}
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao buscar funcionario por ID.", e);
		}
		return null;
	}

	@Override
	public List<Funcionario> listarFuncionarios() {
		
		List<Funcionario> funcionarios = new ArrayList<>();
		String sql = "SELECT * FROM funcionario ORDER BY id DESC";
		
		try (
				PreparedStatement stmt = conexao.prepareStatement(sql); 
				ResultSet resultado = stmt.executeQuery()
		) {
			
			while (resultado.next()) {
				funcionarios.add(mapearResultadoParaFuncionario(resultado));
			}
			
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao listar funcionarios.", e);
		}
		return funcionarios;
	}

	@Override
	public void atualizarFuncionario(Funcionario funcionario) {
		String sql = "UPDATE funcionario SET nome = ?, cargo = ?, telefone =?, data_admissao = ? WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCargo());
			stmt.setString(3, funcionario.getTelefone());
			stmt.setString(4, funcionario.getDataAdmissao()== null ? null : funcionario.getDataAdmissao().toString());
			stmt.setLong(5, funcionario.getId());
			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenciaException("A atualização falhou, nenhum registro foi atualizado.");
			}

			System.out.printf("[LOG] ID do registro 'atualizado': %d!\n", funcionario.getId());
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao atualizar funcionario.", e);
		}
	}

	@Override
	public void excluirFuncionario(Funcionario funcionario) {
		String sql = "DELETE FROM funcionario WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setLong(1, funcionario.getId());
			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenciaException("A exclusão falhou, nenhum registro foi removido.");
			}

			System.out.printf("[LOG] ID do registro 'removido': %d!\n", funcionario.getId());
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao excluir funcionario.", e);
		}
	}
	
}
