package br.usjt.arqsw18.pipoca.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw18.pipoca.model.dao.GeneroDAO;
import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;


@Service
public class GeneroService {
	
	@Autowired
	private GeneroDAO dao;
	
	public Genero buscarGenero(int id) throws IOException {
		return dao.buscarGenero(id);
	}
	
	public List<Genero> listarGeneros() throws IOException{
		return dao.listarGeneros();
	}
	
	public ArrayList<Genero> listaGenFilmes() throws IOException{
		
		ArrayList <Genero> generos = new ArrayList<Genero> (dao.listarGeneros());
		System.out.println(generos);
		
		for(int i = 0; i<generos.size();i++ ) {
			List<Filme> filmes = new ArrayList<Filme>();
			
			
			filmes = dao.listarFilmes(generos.get(i));
			generos.get(i).setFilmes(new ArrayList(filmes));

		}
		
		return generos;
	}
}
