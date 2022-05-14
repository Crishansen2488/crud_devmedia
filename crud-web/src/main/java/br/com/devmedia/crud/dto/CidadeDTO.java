package br.com.devmedia.crud.dto;

import java.io.Serializable;

public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idCidade;

	private String descricao;

	private EstadoDTO estado;

	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public EstadoDTO getEstado() {
		return estado;
	}

	public void setEstado(EstadoDTO estado) {
		this.estado = estado;
	}

}
