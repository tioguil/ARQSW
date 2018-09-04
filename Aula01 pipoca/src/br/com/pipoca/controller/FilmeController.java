package br.com.pipoca.controller;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import br.com.pipoca.model.Filme;
import br.com.pipoca.model.Genero;
import br.com.pipoca.service.FilmeService;
import br.com.pipoca.service.GeneroService;

@Controller
@RequestMapping("filme")
public class FilmeController {
	
	@Autowired
	private FilmeService filmeService;
	
	private GeneroService generoService;
	
	public FilmeController() {
//		filmeService = new FilmeService();
	}
	
	@RequestMapping("/novo")
	public ModelAndView novoFilme() throws IOException {
		ModelAndView andView = new ModelAndView("novo");
		ArrayList<Genero> list = generoService.listarGeneros();
		andView.addObject(list);
		return andView;
	}
	
	@RequestMapping(value="/inserir", method = RequestMethod.POST)
	public ModelAndView inserirFilme(@ModelAttribute("filme") Filme filmeRequest) {
		System.out.println("Entrou");
		
		try {
			Genero genero = new Genero();
			genero.setId(12);
			genero.setNome("adasd");
			filmeRequest.setGenero(genero);
			filmeService.inserirFilme(filmeRequest);
			ModelAndView andView = new ModelAndView("inserido");
			andView.addObject("filme",filmeRequest);
			return andView;
		}catch(IOException e) {
			e.printStackTrace();
			ModelAndView andView = new ModelAndView("inserido");
			andView.addObject("erro",e);
			return andView;
		}
		 
	}
}