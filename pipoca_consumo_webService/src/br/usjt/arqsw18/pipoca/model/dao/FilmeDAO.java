package br.usjt.arqsw18.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;
@Repository
public class FilmeDAO {
	@PersistenceContext
	EntityManager manager;
	
	public int inserirFilme(Filme filme) throws IOException {
		manager.persist(filme);
		return filme.getId();
	}

	public Filme buscarFilme(int id) throws IOException{
		return manager.find(Filme.class, id);
	}
	
	public Filme updateFilme(Filme filme) throws IOException{
		return manager.merge(filme);
	}
	
	public void excluirFilme(Integer id) throws IOException{
		manager.remove(manager.find(Filme.class, id));
	}
	
	public List<Filme> listarFilmes(String chave) throws IOException {
		
		String jpql = "select f from Filme f where f.titulo like :chave";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("chave", "%"+chave+"%");

		List<Filme> result = query.getResultList();
		return result;
	}
	
	public List<Filme> listarFilmes() throws IOException {
		return manager.createQuery("select f from Filme f").getResultList();
	}

	public List<Filme> listarPopulares(Double inicio, Double fim) throws IOException {
		String jpql = "select f from Filme f where f.popularidade between :inicio and :fim";
		Query query = manager.createQuery(jpql)
		.setParameter("inicio",inicio)
		.setParameter("fim",fim );
		List<Filme> filmes = query.getResultList();
		return filmes;	
	}
	
	
	public List<Filme> porData(Date data) throws IOException {
		Date dataAtual = new Date();
		String jpql = "select f from Filme f where f.dataLancamento between :data and :dataAtual";
		Query query = manager.createQuery(jpql)
				.setParameter("data",new java.sql.Date(data.getTime()))
				.setParameter("dataAtual",new java.sql.Date(dataAtual.getTime()));
				List<Filme> lista = query.getResultList();
		return lista;
	}

}
