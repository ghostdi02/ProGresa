/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@SequenceGenerator(name="ABONOS_GEN",sequenceName = "ABONOS_SEQ", allocationSize = 1)
@Table(name = "ABONOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Abonos.findAll", query = "SELECT a FROM Abonos a")
    , @NamedQuery(name = "Abonos.findByIdAbono", query = "SELECT a FROM Abonos a WHERE a.idAbono = :idAbono")
    , @NamedQuery(name = "Abonos.findByFechaAbono", query = "SELECT a FROM Abonos a WHERE a.fechaAbono = :fechaAbono")
    , @NamedQuery(name = "Abonos.findByCantidad", query = "SELECT a FROM Abonos a WHERE a.cantidad = :cantidad")})
public class Abonos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ABONOS_GEN")
    @Basic(optional = false)
    @Column(name = "ID_ABONO")
    private BigDecimal idAbono;
    @Basic(optional = false)
    @Column(name = "FECHA_ABONO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAbono;
    @Basic(optional = false)
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @JoinColumn(name = "ID_CREDITO", referencedColumnName = "ID_CREDITO")
    @ManyToOne(optional = false)
    private Creditos idCredito;

    public Abonos() {
    }

    public Abonos(BigDecimal idAbono) {
        this.idAbono = idAbono;
    }

    public Abonos(BigDecimal idAbono, Date fechaAbono, BigInteger cantidad) {
        this.idAbono = idAbono;
        this.fechaAbono = fechaAbono;
        this.cantidad = cantidad;
    }

    public BigDecimal getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(BigDecimal idAbono) {
        this.idAbono = idAbono;
    }

    public Date getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(Date fechaAbono) {
        this.fechaAbono = fechaAbono;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
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
        hash += (idAbono != null ? idAbono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Abonos)) {
            return false;
        }
        Abonos other = (Abonos) object;
        if ((this.idAbono == null && other.idAbono != null) || (this.idAbono != null && !this.idAbono.equals(other.idAbono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Abonos[ idAbono=" + idAbono + " ]";
    }
    
}
