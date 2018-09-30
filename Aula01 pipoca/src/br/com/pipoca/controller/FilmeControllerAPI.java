package br.com.pipoca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pipoca.model.Filme;
import br.com.pipoca.model.Genero;
import br.com.pipoca.service.FilmeService;
import br.com.pipoca.service.GeneroService;

@RestController
//Mapiando a classe para acesso a API
//example /api/rota desejada
@RequestMapping("/api")
public class FilmeControllerAPI {
	
	
	private ResponseEntity res;
	
	@Autowired
	private FilmeService filmeService;
	@Autowired
	private GeneroService generoService;
		
	
	
	//Insere FIlme
	@PostMapping("/inserir")
	public ResponseEntity<Filme> inserirFilme(@ModelAttribute("filme") Filme filmeRequest) {
		System.out.println("Entrou");
		
		try {
			Genero genero = new Genero();
			genero.setId(12);
			genero.setNome("adasd");
			filmeRequest.setGenero(genero);
			filmeRequest = filmeService.inserirFilme(filmeRequest);
			return new ResponseEntity<Filme>(filmeRequest ,HttpStatus.OK);
		}catch(IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Filme>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}
	
	//Deleta Filme
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteFilme(Integer id) throws IOException {

		try {
			filmeService.deleteFilme(id);
			return new ResponseEntity<String>("Filme Deletado com sucesso!",HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("Erro ao deletar Filme: " + e.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//Atulizar Filme na base
	@PutMapping("/update")
	public ResponseEntity<String> updateFilme(Filme filme) throws IOException {
		
		try {
			filmeService.updateFilme(filme);
			return new ResponseEntity<String>("Filme atualizado com sucesso",HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("Erro ao deletar Filme: " + e.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	
	//Get filme por ID
	@GetMapping("/visualizar/{id}")
	public ResponseEntity<Filme> visualizarFilme(@PathVariable Integer id) throws IOException {
		
		try {
			Filme filme = filmeService.buscarFilme(id);
			return new ResponseEntity<Filme>(filme,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Filme>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	//Buscar filme por parametro ex? titulo etc;
	@GetMapping({"/listar_filmes", "/listar_filmes/{chave}"})
	public ResponseEntity<List<Filme>> listarFilmes(@PathVariable Optional<String> chave) {
		try {

			List<Filme> lista;
			if (chave.isPresent()) {
				lista = filmeService.listarFilmes(chave.get());
			} else {
				lista = filmeService.listarFilmes();
			}
			return new ResponseEntity<List<Filme>>(lista,HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<List<Filme>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Retorna uma lista de Genero, dentro de gereno temos uma lista de Filme pertencente a esse genero.
	@GetMapping("/catalogo/genero")
	public ResponseEntity<List<Genero>> porGeneros() throws IOException {
		
		try {
			List <Genero> porGeneros = generoService.listaGenFilmes();
			
			return new ResponseEntity<List<Genero>>(porGeneros,HttpStatus.OK);
		}catch (Exception e) {
			
			return new ResponseEntity<List<Genero>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//Filme por Lançamento, passanto o tipo na url ano mes Penultimo;
	@GetMapping("/catalogo/lancamento/{tipo}")
	public ResponseEntity<List<Filme>> porDtLancamento(@PathVariable String tipo) throws IOException {
		
		try {
			List<Filme> filmes= new ArrayList<>();
			if(tipo.equals("ano")) {
				filmes = filmeService.porData("ano",1);
			}else if(tipo.equals("mes")) {
				filmes = filmeService.porData("mes",1);
			}else if(tipo.equals("Penultimo")) {
				filmes = filmeService.porData("ano",2);
			}
				
			return new ResponseEntity<List<Filme>>(filmes,HttpStatus.OK);
		}catch (Exception e) {
			
			return new ResponseEntity<List<Filme>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//Recebe Parametro para pesquisa por popularidade
	@GetMapping("/catalogo/popularidade/{inicio}/{fim}")
	public ResponseEntity<List<Filme>> porPopularidade(@PathVariable("inicio") Integer inicio,
			@PathVariable("fim") Integer fim) throws IOException {

		
		try {
			
			if((inicio > 0 && inicio < 100) &&  (fim > 0 && inicio < fim) ) {
				List<Filme> filmes = filmeService.listarPopulares(inicio,fim);
				return new ResponseEntity<List<Filme>>(filmes,HttpStatus.OK);
			}	
			
			return new ResponseEntity<List<Filme>>(HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			
			return new ResponseEntity<List<Filme>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
