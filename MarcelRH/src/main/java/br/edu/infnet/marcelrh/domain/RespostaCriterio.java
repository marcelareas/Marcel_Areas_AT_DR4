package br.edu.infnet.marcelrh.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RespostaCriterio {

    private Integer id;
    private int resposta;
    private int idCriterio;
    @JsonIgnore
    private Resposta idResposta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    public Resposta getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(Resposta idResposta) {
        this.idResposta = idResposta;
    }
}
