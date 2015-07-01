package br.g4.senairunner.senairunner.dominio;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 1541714 on 30/06/2015.
 */
public class Corredor implements Serializable {

    private Long idCorredor;
    private String nomeCorredor;
    private Date dataNascimento;
    private String cidade;
    private String estado;

    public Long getIdCorredor() {
        return idCorredor;
    }

    public void setIdCorredor(Long idCorredor) {
        this.idCorredor = idCorredor;
    }

    public String getNomeCorredor() {
        return nomeCorredor;
    }

    public void setNomeCorredor(String nomeCorredor) {
        this.nomeCorredor = nomeCorredor;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public String getStatusCorredot() {
        return statusCorredot;
    }

    public void setStatusCorredot(String statusCorredot) {
        this.statusCorredot = statusCorredot;
    }

    private String statusCorredot;

}
