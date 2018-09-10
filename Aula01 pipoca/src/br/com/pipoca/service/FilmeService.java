package br.com.pipoca.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.com.pipoca.dao.FilmeDAO;
import br.com.pipoca.model.Filme;

@Service
public class FilmeService {
	private FilmeDAO dao;
	
	public FilmeService() {
		dao = new FilmeDAO();
	}
	
	public Filme buscarFilme(int id) throws IOException{
		return dao.buscarFilme(id);
	}
	
	public Filme inserirFilme(Filme filme) throws IOException {
		int id = dao.inserirFilme(filme);
		filme.setId(id);
		return filme;
	}
	
	public ArrayList<Filme> listarFilmes(String chave) throws IOException{
		return dao.listarFilmes(chave);
	}

	public ArrayList<Filme> listarFilmes() throws IOException{
		return dao.listarFilmes();
	}

	public void updateFilme(Filme filme) throws IOException {
		dao.updateFilme(filme);
	}

	public void deleteFilme(Integer id) throws IOException {
		dao.deletaFilme(id);
		
	}

}
