package br.edu.infnet.marcel.empresa.domain;


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
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Vaga")
@NamedQueries({
        @NamedQuery(name = "Vaga.findAll", query = "SELECT v FROM Vaga v"),
        @NamedQuery(name = "Vaga.findById", query = "SELECT v FROM Vaga v WHERE v.id = :id"),
        @NamedQuery(name = "Vaga.findByCargo", query = "SELECT v FROM Vaga v WHERE v.cargo = :cargo"),
        @NamedQuery(name = "Vaga.findByCidade", query = "SELECT v FROM Vaga v WHERE v.cidade = :cidade"),
        @NamedQuery(name = "Vaga.findByFormaContratacao", query = "SELECT v FROM Vaga v WHERE v.formaContratacao = :formaContratacao")})
public class Vaga implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String cargo;

    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String cidade;

    @Basic(optional = false)
    @Column(name = "forma_contratacao", nullable = false, length = 45)
    private String formaContratacao;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVaga")
    private List<Criterio> criterioList;

    public Vaga(Integer id) {
        this.id = id;
    }

    public Vaga(){

    }

    public Vaga(Integer id, String cargo, String cidade, String formaContratacao) {
        this.id = id;
        this.cargo = cargo;
        this.cidade = cidade;
        this.formaContratacao = formaContratacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Vaga)) {
            return false;
        }
        Vaga other = (Vaga) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vaga{" + "id = " + id
                + ", cargo = " + cargo
                + ", cidade = " + cidade
                + ", forma de contratação = " + formaContratacao
                + ", id do usuário = " + idUsuario
                + ", critérios = " + criterioList + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getFormaContratacao() {
        return formaContratacao;
    }

    public void setFormaContratacao(String formaContratacao) {
        this.formaContratacao = formaContratacao;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Criterio> getCriterioList() {
        return criterioList;
    }

    public void setCriterioList(List<Criterio> criterioList) {
        this.criterioList = criterioList;
    }
}
