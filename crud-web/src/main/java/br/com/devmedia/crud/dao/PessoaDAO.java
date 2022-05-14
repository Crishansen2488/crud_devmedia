package br.com.devmedia.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.devmedia.crud.dto.CidadeDTO;
import br.com.devmedia.crud.dto.EstadoDTO;
import br.com.devmedia.crud.dto.PreferenciaMusicalDTO;
import br.com.devmedia.crud.exception.PersistenciaException;
import br.com.devmedia.crud.util.ConexaoUtil;

public class PessoaDAO {

	public List<EstadoDTO> listarEstados() throws PersistenciaException {
		List<EstadoDTO> lista = new ArrayList<>();
		try {
			Connection conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tb_uf");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				EstadoDTO estadoDTO = new EstadoDTO();
				estadoDTO.setIdEstado(resultSet.getInt(1));
				estadoDTO.setSigla(resultSet.getString(2));
				estadoDTO.setDescricao(resultSet.getString(3));
				
				lista.add(estadoDTO);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		}
		return lista;
	}
	
	public List<PreferenciaMusicalDTO> listarPreferencias() throws PersistenciaException {
		List<PreferenciaMusicalDTO> listaPreferencias = new ArrayList<PreferenciaMusicalDTO>();
		try {
			Connection conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tb_preferencia");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				PreferenciaMusicalDTO preferenciaMusical = new PreferenciaMusicalDTO();
				preferenciaMusical.setIdPreferencia(resultSet.getInt(1));
				preferenciaMusical.setDescricao(resultSet.getString(2));
				
				listaPreferencias.add(preferenciaMusical);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		}
		return listaPreferencias;
	}
	
	public List<CidadeDTO> consultarCidadesPorEstado(Integer idEstado) throws PersistenciaException {
		List<CidadeDTO> listaCidades = new ArrayList<CidadeDTO>();
		try {
			Connection conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tb_cidade");
			sql.append(" WHERE COD_ESTADO = ?");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, idEstado);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				CidadeDTO cidadeDTO = new CidadeDTO();
				cidadeDTO.setIdCidade(resultSet.getInt("ID_CIDADE"));
				cidadeDTO.setDescricao(resultSet.getString("DESCRICAO"));
				
				EstadoDTO estadoDTO = new EstadoDTO();
				estadoDTO.setIdEstado(resultSet.getInt("COD_ESTADO"));
				
				cidadeDTO.setEstado(estadoDTO);
				
				listaCidades.add(cidadeDTO);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		}
		return listaCidades;
	}
	/*
	public void cadastrarPessoa() throws PersistenciaException {
		try {
			Connection conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_pessoa");
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		}
	}
	
	public Integer cadastrarEndereco(EnderecoDTO enderecoDTO) throws PersistenciaException {
		Integer idGerado = null;
		try {
			Connection conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_endereco(LOGRADOURO, COD_CIDADE)");
			sql.append(" VALUES(?, ?)");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setString(1, enderecoDTO.getLogradouro());
			statement.setInt(2, enderecoDTO.getCidade().getIdCidade());
			statement.executeUpdate();
			
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.first()) {
				idGerado = resultSet.getInt(1);
			}
			return idGerado;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		}
	}
	*/
}
