package br.com.pipoca.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pipoca.dao.UsuarioDAO;
import br.com.pipoca.model.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO dao;
	
	@Transactional
	public Usuario autenticar(Usuario usuario) throws IOException {
		usuario =  dao.autenticar(usuario);
		return usuario;
	}

}
