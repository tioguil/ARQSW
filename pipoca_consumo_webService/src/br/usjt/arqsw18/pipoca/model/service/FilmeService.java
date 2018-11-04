package br.usjt.arqsw18.pipoca.model.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import br.usjt.arqsw18.pipoca.model.dao.FilmeDAO;
import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;

@Service
public class FilmeService {
	
	@Autowired
	private FilmeDAO dao;
	@Autowired
	private GeneroService gs;
		
	public static final String API_KEY = "395c82c198c40ae62dc618f4f52ae57c";
	public static final String IMG="https://image.tmdb.org/t/p/w500";
	public static final String URL = "https://api.themoviedb.org/3/movie/popular?api_key="+ API_KEY +"&language=en-US";
	
	@Transactional
	public Filme buscarFilme(int id) throws IOException{
		return dao.buscarFilme(id);
	}
	
	@Transactional
	public Filme inserirFilme(Filme filme) throws IOException {
		int id = dao.inserirFilme(filme);
		filme.setId(id);
		return filme;
	}
	
	@Transactional
	public List<Filme> listarFilmes(String chave) throws IOException{
		return dao.listarFilmes(chave);
	}
	@Transactional
	public List<Filme> listarFilmes() throws IOException{
		return dao.listarFilmes();
	}
	
	@Transactional
	public Filme updateFilme(Filme filme) throws IOException {
		return dao.updateFilme(filme);
	}
	
	@Transactional
	public void excluirFilme(Integer id) throws IOException{
		dao.excluirFilme(id);
	}
	@Transactional
	public List<Filme> listarPopulares(Double inicio,Double fim) throws IOException{		
		return dao.listarPopulares(inicio, fim);
	}
	@Transactional
	public void carregarFilmes() throws IOException {
		RestTemplate rt = new RestTemplate();
		ResultadoTMDb resultado = rt.getForObject(URL,ResultadoTMDb.class);
		System.out.print(resultado);
		ArrayList<FilmeTMDb> lista = resultado.getResults();
		for (FilmeTMDb filmetmdb:lista) {
			Filme filme = new Filme();
			filme.setTitulo(filmetmdb.getTitle());
			filme.setDescricao(filmetmdb.getOverview());
			filme.setPopularidade(filmetmdb.getPopularity());
			filme.setPosterPath(IMG+filmetmdb.getPoster_path());
			Genero genero = gs.buscarGenero(filmetmdb.getGenre_ids()[0]);
			filme.setGenero(genero);
			dao.inserirFilme(filme);
			
		}
	}
	
	
	@Transactional
	public void gravarImagem(ServletContext servletContext, Filme filme, MultipartFile file)throws IOException{
		
		if(!file.isEmpty()) {
			BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
			String path= servletContext.getRealPath(servletContext.getContextPath());
			path = path.substring(0, path.lastIndexOf('/'));
			String nomeArquivo = "img"+filme.getId() + ".jpg";
			System.out.println(nomeArquivo);
			filme.setPosterPath(nomeArquivo);
			updateFilme(filme);
			File destination = new File (path+ File.separatorChar + "img" + File.separatorChar + nomeArquivo);
			if (destination.exists()) {
				destination.delete();
			}
			ImageIO.write(src,"jpg", destination);
		}
	}
	@Transactional
	public List<Filme> porData(String chave,Integer periodo) throws IOException{
		Date data = new Date();
		Calendar calendar = Calendar.getInstance();
		if(chave.equals("ano")) {
			calendar.add(Calendar.YEAR,(periodo)*-1);
			calendar.set(Calendar.MONTH,0);
			calendar.set(Calendar.DAY_OF_MONTH,1);
		}
		else {
			calendar.add(Calendar.MONTH,-1*(periodo));
			calendar.set(Calendar.DAY_OF_MONTH,1);
		}
		data = calendar.getTime();
		return dao.porData(data); 
	}
}
