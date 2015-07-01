package br.g4.senairunner.senairunner.dominio;

import java.io.Serializable;

/**
 * Created by 1541714 on 30/06/2015.
 */
public class Inscricao implements Serializable{

    private Long idInscricao;
    private Corrida corrida;
    private Corredor corredor;
    private Boolean statuPagamento;

    public Long getIdInscricao() {
        return idInscricao;
    }

    public void setIdInscricao(Long idInscricao) {
        this.idInscricao = idInscricao;
    }

    public Corrida getCorrida() {
        return corrida;
    }

    public void setCorrida(Corrida corrida) {
        this.corrida = corrida;
    }

    public Corredor getCorredor() {
        return corredor;
    }

    public void setCorredor(Corredor corredor) {
        this.corredor = corredor;
    }

    public Boolean getStatuPagamento() {
        return statuPagamento;
    }

    public void setStatuPagamento(Boolean statuPagamento) {
        this.statuPagamento = statuPagamento;
    }
}
