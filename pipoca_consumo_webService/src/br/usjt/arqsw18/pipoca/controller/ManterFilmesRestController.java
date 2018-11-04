package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;
import br.usjt.arqsw18.pipoca.model.service.FilmeService;
import br.usjt.arqsw18.pipoca.model.service.GeneroService;

@RestController
public class ManterFilmesRestController {
	@Autowired
	private FilmeService fService;
	@Autowired
	private GeneroService gService;
	
	@Autowired
	private ServletContext  servletContext;
	
	@RequestMapping(method=RequestMethod.GET,value="rest/filmes")
	public List<Filme> listarFilmes(){
			try {
				return fService.listarFilmes();
			} catch (IOException e) {
				e.printStackTrace();
				
			}
			return null;
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="rest/filmes/populares/")
	public List<Filme> porPopularidade(){
			try {
				return fService.listarPopulares(0.0, 100.0);
			} catch (IOException e) {
				e.printStackTrace();
				
			}
			return null;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="rest/filmes/lancamentos/ultimoMes")
	public List<Filme> porLancamento(){
			try {
				return fService.porData("mes",1);
			} catch (IOException e) {
				e.printStackTrace();
				
			}
			return null;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="rest/filmes/lancamentos/ultimoAno")
	public List<Filme> porLancamentoAno(){
			try {
				return fService.porData("ano",1);
			} catch (IOException e) {
				e.printStackTrace();
				
			}
			return null;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="rest/filmes/lancamentos/ultimosAnos")
	public List<Filme> porLancamentoVelho(){
			try {
				return fService.porData("ano",2);
			} catch (IOException e) {
				e.printStackTrace();
				
			}
			return null;
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="rest/filmes")
	public ResponseEntity<Filme> criarFilme(@RequestBody Filme filme,@RequestParam("posterPath") MultipartFile file) {
		
		try {
			 filme = fService.inserirFilme(filme);
			 fService.gravarImagem(servletContext, filme, file);
			 return new ResponseEntity<Filme>(filme,HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Filme>(filme,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE ,value="rest/filmes")
	public ResponseEntity<Filme> exclurFilme(@RequestBody Filme filme) {
		Integer id = filme.getId();
		System.out.println(id);		
		try {
			 fService.excluirFilme(id);
			 return new ResponseEntity<Filme>(HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Filme>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT ,value="rest/filmes")
	public ResponseEntity<Filme> alterarFilme(@RequestBody Filme filme,@RequestParam("posterPath") MultipartFile file) {
		try {
			 filme = fService.updateFilme(filme);
			 fService.gravarImagem(servletContext, filme, file);
			 return new ResponseEntity<Filme>(filme,HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Filme>(filme,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(method=RequestMethod.GET,value="rest/filmes/porGeneros/")
	public ArrayList<Genero> porGeneros(){
			try {
				ArrayList <Genero> porGeneros = gService.listaGenFilmes();
				
				 return porGeneros;
			} catch (IOException e) {
				e.printStackTrace();
				
			}
			return null;
	}
		
	
}
