/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author axeld
 */
@Entity
@SequenceGenerator(name="FALLAS_GEN",sequenceName = "FALLAS_SEQ", allocationSize = 1)
@Table(name = "FALLAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fallas.findAll", query = "SELECT f FROM Fallas f")
    , @NamedQuery(name = "Fallas.findByIdFalla", query = "SELECT f FROM Fallas f WHERE f.idFalla = :idFalla")
    , @NamedQuery(name = "Fallas.findByFechaFalla", query = "SELECT f FROM Fallas f WHERE f.fechaFalla = :fechaFalla")})
public class Fallas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FALLAS_GEN")
    @Basic(optional = false)
    @Column(name = "ID_FALLA")
    private BigDecimal idFalla;
    @Basic(optional = false)
    @Column(name = "FECHA_FALLA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFalla;
    @JoinColumn(name = "ID_CREDITO", referencedColumnName = "ID_CREDITO")
    @ManyToOne(optional = false)
    private Creditos idCredito;

    public Fallas() {
    }

    public Fallas(BigDecimal idFalla) {
        this.idFalla = idFalla;
    }

    public Fallas(BigDecimal idFalla, Date fechaFalla) {
        this.idFalla = idFalla;
        this.fechaFalla = fechaFalla;
    }

    public BigDecimal getIdFalla() {
        return idFalla;
    }

    public void setIdFalla(BigDecimal idFalla) {
        this.idFalla = idFalla;
    }

    public Date getFechaFalla() {
        return fechaFalla;
    }

    public void setFechaFalla(Date fechaFalla) {
        this.fechaFalla = fechaFalla;
    }

    public Creditos getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(Creditos idCredito) {
        this.idCredito = idCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFalla != null ? idFalla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fallas)) {
            return false;
        }
        Fallas other = (Fallas) object;
        if ((this.idFalla == null && other.idFalla != null) || (this.idFalla != null && !this.idFalla.equals(other.idFalla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Fallas[ idFalla=" + idFalla + " ]";
    }
    
}
