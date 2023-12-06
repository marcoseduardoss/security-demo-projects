package br.uece.sgf.v1.apresentacao.dto;

import java.time.LocalDateTime;

/**
 * Classe de domínio de negócio
 * @author marcos.eduardo
 */
public class FuncionarioDto {
	
    private Long id;
    private String nome;
    private String cargo;
    private String telefone;
    private LocalDateTime dataAdmissao;

    // Construtor
    public FuncionarioDto(Long id, String valor1, String valor2, LocalDateTime valor3) {
        this.id = id;
        this.nome = valor1;
        this.cargo = valor2;
        this.dataAdmissao = valor3;
    }
    // Construtor
    public FuncionarioDto(String valor1, String valor2) {
        this.nome = valor1;
        this.cargo = valor2;
    }
    
    public FuncionarioDto() {
	}
    
	// Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
        return nome;
    }

    public void setNome(String valor1) {
        this.nome = valor1;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String valor2) {
        this.cargo = valor2;
    }
	public LocalDateTime getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(LocalDateTime valor3) {
		this.dataAdmissao = valor3;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
    
}
