package br.edu.infnet.marcel.candidato.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Respostas_Criterios")
@NamedQueries({
        @NamedQuery(name = "RespostaCriterio.findAll", query = "SELECT r FROM RespostaCriterio r"),
        @NamedQuery(name = "RespostaCriterio.findById", query = "SELECT r FROM RespostaCriterio r WHERE r.id = :id"),
        @NamedQuery(name = "RespostaCriterio.findByResposta", query = "SELECT r FROM RespostaCriterio r WHERE r.resposta = :resposta"),
        @NamedQuery(name = "RespostaCriterio.findByIdCriterio", query = "SELECT r FROM RespostaCriterio r WHERE r.idCriterio = :idCriterio")})
public class RespostaCriterio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(nullable = false)
    private int resposta;

    @Basic(optional = false)
    @Column(name = "id_criterio", nullable = false)
    private int idCriterio;

    @JsonIgnore
    @JoinColumn(name = "id_resposta", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Resposta idResposta;

    public RespostaCriterio() {
    }

    public RespostaCriterio(Integer id) {
        this.id = id;
    }

    public RespostaCriterio(Integer id, int resposta, int idCriterio) {
        this.id = id;
        this.resposta = resposta;
        this.idCriterio = idCriterio;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RespostaCriterio)) {
            return false;
        }
        RespostaCriterio other = (RespostaCriterio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.infnet.marcel.candidato.domain.RespostaCriterio[ id=" + id + " ]";
    }
}
