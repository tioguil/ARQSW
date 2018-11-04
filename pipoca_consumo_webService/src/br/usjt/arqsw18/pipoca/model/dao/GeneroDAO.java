package br.usjt.arqsw18.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;
@Repository
public class GeneroDAO {
	
	@PersistenceContext
	EntityManager manager;
	
	
	public Genero buscarGenero(int id) throws IOException {
		return manager.find(Genero.class, id);
	}

	public List<Genero> listarGeneros() throws IOException {
		return manager.createQuery("select g from Genero g").getResultList();
	}
	
	public List<Filme> listarFilmes(Genero genero) throws IOException{
		String jpql = "select f from Filme f where id_genero=:id";
		Query query = manager.createQuery(jpql);
		query.setParameter("id", genero.getId());
		List<Filme> filmes = query.getResultList();
		return filmes;
	}
}
