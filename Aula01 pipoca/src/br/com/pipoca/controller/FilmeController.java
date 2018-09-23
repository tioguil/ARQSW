package br.com.pipoca.controller;


import java.io.IOException;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class FilmeController {
	
	
	@RequestMapping("/spring")
	public ModelAndView novoFilme() throws IOException {
		ModelAndView andView = new ModelAndView("helloSpring");
		return andView;
	}
	
}