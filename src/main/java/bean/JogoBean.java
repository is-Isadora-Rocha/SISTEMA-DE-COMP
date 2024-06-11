package bean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import DAO.JogoDAO;
import entities.Campeonato;
import entities.Jogo;

@ManagedBean
public class JogoBean {
	private Jogo jogo = new Jogo();
	private List<Jogo> lista;
	private Jogo jogoSelecionado;
	
	@PostConstruct
    public void init() {
        jogo = new Jogo();
        jogo.setCampeonato(new Campeonato());
    }
	
	public String salvar() {
        if (jogo != null) {
            jogo.setDataCadastro(new Date());
            
            if (jogo.getTime1().equals(jogo.getTime2())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não é permitido salvar um jogo cujos valores de time1 e time2 sejam iguais."));
                return null;
            }

            JogoDAO.salvar(jogo);
            
            jogo = new Jogo();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo!", "Jogo criado!"));

            return "filtro.xhtml";
        }
        return null;
    }
	
	public String editar(Jogo jogoEditado) {
		if (jogoEditado != null) {
			JogoDAO.editar(jogoEditado);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Jogo atualizado!"));
			lista = JogoDAO.listar();
			return "listagem.xthml";
		}
		return null;
	}
	
	public String deletar(int id) {

		JogoDAO.deletar(id);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Jogo excluído!"));
		lista = JogoDAO.listar();
		return "listagem.xthml";

	}
	
	public Jogo getJogoSelecionado() {
		return jogoSelecionado;
	}

	public void setJogoSelecionado(Jogo jogoSelecionado) {
		this.jogoSelecionado = jogoSelecionado;
	}

	public void selecionarJogo(Jogo jogo) {
        this.jogoSelecionado = jogo;
        exibirResumo();
    }

    public void exibirResumo() {
        if (jogoSelecionado != null) {
            int pontuacao = jogoSelecionado.getGolsTime1() > jogoSelecionado.getGolsTime2() ? 3 :
                            (jogoSelecionado.getGolsTime1() < jogoSelecionado.getGolsTime2() ? 0 : 1);

            int vitorias = jogoSelecionado.getGolsTime1() > jogoSelecionado.getGolsTime2() ? 1 : 0;
            int derrotas = jogoSelecionado.getGolsTime1() < jogoSelecionado.getGolsTime2() ? 1 : 0;
            int empates = jogoSelecionado.getGolsTime1() == jogoSelecionado.getGolsTime2() ? 1 : 0;

            int golsMarcados = jogoSelecionado.getGolsTime1();
            int golsSofridos = jogoSelecionado.getGolsTime2();
            int saldoGols = golsMarcados - golsSofridos;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Resumo do Jogo",
                    "Pontuação: " + pontuacao + "<br/>" +
                    "Vitórias: " + vitorias + "<br/>" +
                    "Derrotas: " + derrotas + "<br/>" +
                    "Empates: " + empates + "<br/>" +
                    "Gols Marcados: " + golsMarcados + "<br/>" +
                    "Gols Sofridos: " + golsSofridos + "<br/>" +
                    "Saldo de Gols: " + saldoGols));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Nenhum jogo selecionado."));
        }
    }

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Jogo> getLista() {
		if (lista == null) {
			lista = JogoDAO.listar();
		}
		return lista;
	}

	public void setLista(List<Jogo> lista) {
		this.lista = lista;
	}
	
	

}
