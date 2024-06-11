package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Campeonato;
import entities.Jogo;
import util.JPAUtil;

public class JogoDAO {
	
	public static void salvar(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		if (jogo != null && jogo.getCampeonato() != null) {
			Campeonato campeonato = jogo.getCampeonato();
			if (campeonato.getId() == null) {
				em.persist(campeonato);
			}
		}
		em.persist(jogo);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void editar(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(jogo);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void deletar(int id) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		Jogo jogoDeletado = em.find(Jogo.class, id);
		
		if (jogoDeletado != null) {
			em.remove(jogoDeletado);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	public static List<Jogo> listar(){
		EntityManager em = JPAUtil.criarEntityManager();
		
		Query qry = em.createNamedQuery("Jogo.listar");
		List<Jogo> lista = qry.getResultList();
		em.clear();
		return lista;
	}

}
