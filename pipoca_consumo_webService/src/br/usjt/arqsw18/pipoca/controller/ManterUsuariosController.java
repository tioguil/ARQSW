package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw18.pipoca.model.entity.Usuario;
import br.usjt.arqsw18.pipoca.model.service.UsuarioService;

@Controller
public class ManterUsuariosController {
	private UsuarioService uService;
	
	@Autowired
	public ManterUsuariosController(UsuarioService us) {
		this.uService = us;
	}
	
	
	@RequestMapping("/")
	public String iniciar() {
		System.out.println("sadsadas");
		return "index";
	}
	
	
	@RequestMapping("/logar")
	public String logar(Usuario usuario, BindingResult erros,HttpSession session)  throws IOException{
		
		usuario = uService.logar(usuario);
		if(usuario == null) {
			return "index";
		}else {
			session.setAttribute("usuario", usuario);
			return "Home";	
		}
		
		
	}

}
