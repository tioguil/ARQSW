package br.com.pipoca.dao;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.pipoca.model.Usuario;

@Repository
public class UsuarioDAO {
	@PersistenceContext
	EntityManager manager;
	
	public Usuario autenticar(Usuario usuario) throws IOException {
		String jpql = "select u from Usuario u where u.email = :email and u.senha=: senha";
		System.out.println(usuario.getUserName());
		try{
			System.out.println("entrou no try");
			Query query = manager.createQuery(jpql)
					
					.setParameter("email",usuario.getUserName())
					.setParameter("senha",usuario.getPassword());
			return (Usuario) query.getSingleResult();
			
			}catch (NoResultException nre){
				System.out.println("caiu no catch");
				System.out.println(nre);
			return null;
			}
		
		
	}
}
