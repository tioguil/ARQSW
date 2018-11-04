package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Usuario;
import br.usjt.arqsw18.pipoca.model.service.UsuarioService;

@RestController
public class ManterUsuariosRestController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method=RequestMethod.POST,value="rest/usuario")
	public ResponseEntity <Usuario> logar(@RequestBody Usuario usuario){
		System.out.println(usuario);
		
		try {
			usuario = usuarioService.logar(usuario);
			if (usuario == null ) {
				return new ResponseEntity<Usuario>(usuario,HttpStatus.NOT_FOUND);
			}
			else {
			 return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
			}
		}catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Usuario>(usuario,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
