package br.uece.sgf.v1.negocio;

import java.time.LocalDateTime;

/**
 * Classe de domínio de negócio
 * @author marcos.eduardo
 */
public class Funcionario {
	
    private Long id;
    private String nome;//titulo
    private String cargo;//descricao
    private String telefone;
    private LocalDateTime dataAdmissao;//data

    // Construtor
    public Funcionario(Long id, String nome, String cargo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
    }
    public Funcionario(Long id, String nome, String cargo, String telefone, String valor3) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.telefone = telefone;
        this.dataAdmissao = valor3 != null ? LocalDateTime.parse(valor3) : null;
    }
    // Construtor
    public Funcionario(String valor1, String valor2) {
        this.nome = valor1;
        this.cargo = valor2;
    }
    public Funcionario(Long id) {
        this.id = id;
    }
    public Funcionario() {
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
