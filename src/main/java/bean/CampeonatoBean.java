package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import DAO.CampeonatoDAO;
import entities.Campeonato;

@ManagedBean
@ViewScoped
public class CampeonatoBean {

	private Campeonato campeonato = new Campeonato();
	private List<Campeonato> lista;
	
	public String salvar() {
		CampeonatoDAO.salvar(campeonato);
		campeonato = new Campeonato();
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Feito!", "Campeonato Criado!"));
		lista = CampeonatoDAO.listar();
		return "cadastro_campeonato.xhtml";
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public List<Campeonato> getLista() {
		return lista;
	}

	public void setLista(List<Campeonato> lista) {
		this.lista = lista;
	}
	
	
	
}
