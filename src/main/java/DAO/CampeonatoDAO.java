package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Campeonato;
import util.JPAUtil;

public class CampeonatoDAO {
	
	public static void salvar(Campeonato campeonato) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(campeonato);
		em.getTransaction().commit();
		em.close();
	}
	
	public static List<Campeonato> listar(){
		EntityManager em = JPAUtil.criarEntityManager();
		
		Query q = em.createNamedQuery("Campeonato.listar");
		List<Campeonato> lista = q.getResultList();
		em.clear();
		return lista;
	}
	
	

}
