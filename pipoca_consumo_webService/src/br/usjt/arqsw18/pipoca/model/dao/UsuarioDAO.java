package br.usjt.arqsw18.pipoca.model.dao;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw18.pipoca.model.entity.Usuario;

@Repository
public class UsuarioDAO {
	@PersistenceContext
	EntityManager manager;
	
	public Usuario logar(Usuario usuario) throws IOException {
		String jpql = "select u from Usuario u where u.email = :email and u.senha=: senha";
		System.out.println(usuario.getEmail());
		try{
			System.out.println("entrou no try");
			Query query = manager.createQuery(jpql)
					
					.setParameter("email",usuario.getEmail())
					.setParameter("senha",usuario.getEmail());
			return (Usuario) query.getSingleResult();
			
			}catch (NoResultException nre){
				System.out.println("caiu no catch");
				System.out.println(nre);
			return null;
			}
		
		
	}
}
