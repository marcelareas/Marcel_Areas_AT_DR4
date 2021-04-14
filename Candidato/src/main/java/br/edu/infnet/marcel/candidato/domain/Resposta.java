package br.edu.infnet.marcel.candidato.domain;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Resposta")
@NamedQueries({
        @NamedQuery(name = "Resposta.findAll", query = "SELECT r FROM Resposta r"),
        @NamedQuery(name = "Resposta.findById", query = "SELECT r FROM Resposta r WHERE r.id = :id"),
        @NamedQuery(name = "Resposta.findByData", query = "SELECT r FROM Resposta r WHERE r.data = :data"),
        @NamedQuery(name = "Resposta.findByIndice", query = "SELECT r FROM Resposta r WHERE r.indice = :indice"),
        @NamedQuery(name = "Resposta.findByIdUsuario", query = "SELECT r FROM Resposta r WHERE r.idUsuario = :idUsuario"),
        @NamedQuery(name = "Resposta.findByIdVaga", query = "SELECT r FROM Resposta r WHERE r.idVaga = :idVaga")})
public class Resposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Basic(optional = false)
    @Column(nullable = false, precision = 2, scale = 1)
    private BigDecimal indice;

    @Basic(optional = false)
    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;

    @Basic(optional = false)
    @Column(name = "id_vaga", nullable = false)
    private int idVaga;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idResposta")
    private List<RespostaCriterio> respostaCriterioList;

    public Resposta(){

    }

    public Resposta(Integer id) {
        this.id = id;
    }

    public Resposta(Integer id, Date data, BigDecimal indice, int idUsuario, int idVaga) {
        this.id = id;
        this.data = data;
        this.indice = indice;
        this.idUsuario = idUsuario;
        this.idVaga = idVaga;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getIndice() {
        return indice;
    }

    public void setIndice(BigDecimal indice) {
        this.indice = indice;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    public List<RespostaCriterio> getRespostaCriterioList() {
        return respostaCriterioList;
    }

    public void setRespostaCriterioList(List<RespostaCriterio> respostaCriterioList) {
        this.respostaCriterioList = respostaCriterioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Resposta)) {
            return false;
        }
        Resposta other = (Resposta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.infnet.marcel.candidato.domain.Resposta[ id=" + id + " ]";
    }
}
