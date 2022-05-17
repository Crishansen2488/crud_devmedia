package br.com.devmedia.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.devmedia.crud.dto.CidadeDTO;
import br.com.devmedia.crud.dto.EnderecoDTO;
import br.com.devmedia.crud.dto.EstadoDTO;
import br.com.devmedia.crud.dto.PessoaDTO;
import br.com.devmedia.crud.dto.PreferenciaMusicalDTO;
import br.com.devmedia.crud.exception.PersistenciaException;
import br.com.devmedia.crud.util.ConexaoUtil;

public class PessoaDAO {
	
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public List<PreferenciaMusicalDTO> listarPreferencias() throws PersistenciaException {
		List<PreferenciaMusicalDTO> listaPreferencias = new ArrayList<PreferenciaMusicalDTO>();
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM PREFERENCIA");
			
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
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listaPreferencias;
	}
	
	public List<EstadoDTO> listarEstados() throws PersistenciaException {
		List<EstadoDTO> lista = new ArrayList<>();
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM ESTADO");

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
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public List<CidadeDTO> consultarCidadesPorEstado(Integer idEstado) throws PersistenciaException {
		List<CidadeDTO> listaCidades = new ArrayList<CidadeDTO>();
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM CIDADE");
			sql.append(" WHERE cod_estado = ?");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, idEstado);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				CidadeDTO cidadeDTO = new CidadeDTO();
				cidadeDTO.setIdCidade(resultSet.getInt("id_cidade"));
				cidadeDTO.setDescricao(resultSet.getString("descricao"));
				
				EstadoDTO estadoDTO = new EstadoDTO();
				estadoDTO.setIdEstado(resultSet.getInt("cod_estado"));
				
				cidadeDTO.setEstado(estadoDTO);
				
				listaCidades.add(cidadeDTO);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listaCidades;
	}
	
	public Integer cadastrarEndereco(EnderecoDTO enderecoDTO) throws PersistenciaException {
		Integer idGerado = null;
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ENDERECO(logradouro, cod_cidade)");
			sql.append(" VALUES(?, ?)");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
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
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void cadastrarPessoa(PessoaDTO pessoaDTO) throws PersistenciaException {
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();
			Integer codEndereco = cadastrarEndereco(pessoaDTO.getEndereco());
			Integer codPessoa = null;
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO PESSOA(nome, cpf, nasc, sexo, comentario, cod_endereco)");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?)");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, pessoaDTO.getNome());
			statement.setString(2, pessoaDTO.getCpf());
			Date nasc = new Date(dateFormat.parse(pessoaDTO.getNasc()).getTime()); 
			statement.setDate(3, nasc);
			statement.setString(4, String.valueOf(pessoaDTO.getSexo()));
			statement.setString(5, pessoaDTO.getComentario());
			statement.setInt(6, codEndereco);
			
			statement.executeUpdate();
			
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.first()) {
				codPessoa = resultSet.getInt(1);
			}
			cadastrarPreferencias(pessoaDTO.getPreferencias(), codPessoa);
			
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void cadastrarPreferencias(List<PreferenciaMusicalDTO> preferenciasMusicaisDTO, Integer codPessoa) throws PersistenciaException {
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO PREFERENCIA_PESSOA");
			sql.append(" VALUES (?, ?)");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
		
			for(PreferenciaMusicalDTO preferenciaMusical : preferenciasMusicaisDTO) {
				statement.setInt(1, codPessoa);
				statement.setInt(2, preferenciaMusical.getIdPreferencia());
				
				statement.execute();
			}
			
		}  catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
