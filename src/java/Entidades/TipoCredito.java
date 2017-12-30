/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author axeld
 */
@Entity
@SequenceGenerator(name="TIPO_CREDITO_GEN",sequenceName = "TIPO_CREDITO_SEQ", allocationSize = 1)
@Table(name = "TIPO_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCredito.findAll", query = "SELECT t FROM TipoCredito t")
    , @NamedQuery(name = "TipoCredito.findByIdTipoCredito", query = "SELECT t FROM TipoCredito t WHERE t.idTipoCredito = :idTipoCredito")
    , @NamedQuery(name = "TipoCredito.findByNombre", query = "SELECT t FROM TipoCredito t WHERE t.nombre = :nombre")})
public class TipoCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TIPO_CREDITO_GEN")
    @Basic(optional = false)
    @Column(name = "ID_TIPO_CREDITO")
    private BigDecimal idTipoCredito;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoCredito")
    private List<Creditos> creditosList;

    public TipoCredito() {
    }

    public TipoCredito(BigDecimal idTipoCredito) {
        this.idTipoCredito = idTipoCredito;
    }

    public TipoCredito(BigDecimal idTipoCredito, String nombre) {
        this.idTipoCredito = idTipoCredito;
        this.nombre = nombre;
    }

    public BigDecimal getIdTipoCredito() {
        return idTipoCredito;
    }

    public void setIdTipoCredito(BigDecimal idTipoCredito) {
        this.idTipoCredito = idTipoCredito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Creditos> getCreditosList() {
        return creditosList;
    }

    public void setCreditosList(List<Creditos> creditosList) {
        this.creditosList = creditosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCredito != null ? idTipoCredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCredito)) {
            return false;
        }
        TipoCredito other = (TipoCredito) object;
        if ((this.idTipoCredito == null && other.idTipoCredito != null) || (this.idTipoCredito != null && !this.idTipoCredito.equals(other.idTipoCredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TipoCredito[ idTipoCredito=" + idTipoCredito + " ]";
    }
    
}
