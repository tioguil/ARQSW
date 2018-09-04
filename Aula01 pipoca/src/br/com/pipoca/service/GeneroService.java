package br.com.pipoca.service;

import java.io.IOException;
import java.util.ArrayList;

import br.com.pipoca.dao.GeneroDAO;
import br.com.pipoca.model.Genero;

public class GeneroService {
	private GeneroDAO dao;
	
	public GeneroService() {
		this.dao = new GeneroDAO();
	}
	
	public Genero buscarGenero(int id) throws IOException {
		return dao.buscarGenero(id);
	}
	
	public ArrayList<Genero> listarGeneros() throws IOException{
		return dao.listarGeneros();
	}

}
