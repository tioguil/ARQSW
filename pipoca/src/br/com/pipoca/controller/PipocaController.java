package br.com.pipoca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

@Controller
public class PipocaController {
	
	@RequestMapping("hello")
	public ModelAndView hello() {
		ModelAndView andView = new ModelAndView("hello");
		
		return andView;
	}
	
	@RequestMapping("/")
	public String hellow() {
		return "hello";
	}
}
