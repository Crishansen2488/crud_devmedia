package br.com.devmedia.crud.dto;

import java.io.Serializable;
import java.util.List;

public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idPessoa;

	private String nome;

	private String cpf;

	private String nasc;

	private char sexo;

	private List<PreferenciaMusicalDTO> preferencias;

	private String comentario;

	private EnderecoDTO endereco;

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public List<PreferenciaMusicalDTO> getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(List<PreferenciaMusicalDTO> preferencias) {
		this.preferencias = preferencias;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public String getNasc() {
		return nasc;
	}

	public void setNasc(String nasc) {
		this.nasc = nasc;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
