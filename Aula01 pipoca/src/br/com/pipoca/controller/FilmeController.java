package br.com.pipoca.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.pipoca.model.Filme;

@Controller
@RequestMapping("filme")
public class FilmeController {

	
	@RequestMapping("/novo")
	public String novoFilme() {
		return "novo";
	}
	
	@RequestMapping(value="/inserir", method = RequestMethod.POST)
	public ModelAndView inserirFilme(@ModelAttribute("filme") Filme filme) {
		System.out.println("Entrou");
		ModelAndView andView = new ModelAndView("inserido");
		andView.addObject("filme",filme);
;		return andView;
	}
}