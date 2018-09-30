package br.com.pipoca.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pipoca.dao.GeneroDAO;
import br.com.pipoca.model.Genero;

@Service
public class GeneroService {
	
	@Autowired
	private GeneroDAO dao;

	@Transactional
	public Genero buscarGenero(int id) throws IOException {
		return dao.buscarGenero(id);
	}

	@Transactional
	public List<Genero> listarGeneros() throws IOException {
		return dao.listarGeneros();
	}

	@Transactional
	public List<Genero> listaGenFilmes() throws IOException {

		List<Genero> generos = dao.listarGeneros();

		for (int i = 0; i < generos.size(); i++) {
			generos.get(i).setFilmes(dao.listarFilmes(generos.get(i)));
		}

		return generos;
	}

}