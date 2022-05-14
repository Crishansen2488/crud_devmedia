package br.com.devmedia.crud.dto;

import java.io.Serializable;

public class EstadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idEstado;

	private String sigla;

	private String descricao;

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdUF(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
