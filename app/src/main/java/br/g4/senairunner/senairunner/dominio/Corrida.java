package br.g4.senairunner.senairunner.dominio;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 1541714 on 30/06/2015.
 */
public class Corrida implements Serializable {

    private Long idCorrida;
    private String nomeCorrida;
    private String descricaoCorrida;
    private Date dataCorrida;
    private String cidade;
    private String estado;
    private String statusCorrida;
    private Double valorInscricao;

    public Double getValorInscricao() {
        return valorInscricao;
    }

    public void setValorInscricao(Double valorInscricao) {
        this.valorInscricao = valorInscricao;
    }

    public Long getIdCorrida() {
        return idCorrida;
    }

    public void setIdCorrida(Long idCorrida) {
        this.idCorrida = idCorrida;
    }

    public String getNomeCorrida() {
        return nomeCorrida;
    }

    public void setNomeCorrida(String nomeCorrida) {
        this.nomeCorrida = nomeCorrida;
    }

    public String getDescricaoCorrida() {
        return descricaoCorrida;
    }

    public void setDescricaoCorrida(String descricaoCorrida) {
        this.descricaoCorrida = descricaoCorrida;
    }

    public Date getDataCorrida() {
        return dataCorrida;
    }

    public void setDataCorrida(Date dataCorrida) {
        this.dataCorrida = dataCorrida;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getStatusCorrida() {
        return statusCorrida;
    }

    public void setStatusCorrida(String statusCorrida) {
        this.statusCorrida = statusCorrida;
    }
}
