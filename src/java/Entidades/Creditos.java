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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author axeld
 */
@Entity
@SequenceGenerator(name="CREDITOS_GEN",sequenceName = "CREDITOS_SEQ1", allocationSize = 1)
@Table(name = "CREDITOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Creditos.findAll", query = "SELECT c FROM Creditos c")
    , @NamedQuery(name = "Creditos.findByIdCredito", query = "SELECT c FROM Creditos c WHERE c.idCredito = :idCredito")
    , @NamedQuery(name = "Creditos.findByMonto", query = "SELECT c FROM Creditos c WHERE c.monto = :monto")
    , @NamedQuery(name = "Creditos.findByFechaCreacion", query = "SELECT c FROM Creditos c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Creditos.findByPlazo", query = "SELECT c FROM Creditos c WHERE c.plazo = :plazo")})
public class Creditos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CREDITOS_GEN")
    @Basic(optional = false)
    @Column(name = "ID_CREDITO")
    private BigDecimal idCredito;
    @Basic(optional = false)
    @Column(name = "MONTO")
    private BigInteger monto;
    @Basic(optional = false)
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "PLAZO")
    private String plazo;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    @ManyToOne(optional = false)
    private Clientes idCliente;
    @JoinColumn(name = "TIPO_CREDITO", referencedColumnName = "ID_TIPO_CREDITO")
    @ManyToOne(optional = false)
    private TipoCredito tipoCredito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCredito")
    private List<Abonos> abonosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCredito")
    private List<Fallas> fallasList;

    public Creditos() {
    }

    public Creditos(BigDecimal idCredito) {
        this.idCredito = idCredito;
    }

    public Creditos(BigDecimal idCredito, BigInteger monto, Date fechaCreacion) {
        this.idCredito = idCredito;
        this.monto = monto;
        this.fechaCreacion = fechaCreacion;
    }

    public BigDecimal getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(BigDecimal idCredito) {
        this.idCredito = idCredito;
    }

    public BigInteger getMonto() {
        return monto;
    }

    public void setMonto(BigInteger monto) {
        this.monto = monto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public TipoCredito getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(TipoCredito tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    @XmlTransient
    public List<Abonos> getAbonosList() {
        return abonosList;
    }

    public void setAbonosList(List<Abonos> abonosList) {
        this.abonosList = abonosList;
    }

    @XmlTransient
    public List<Fallas> getFallasList() {
        return fallasList;
    }

    public void setFallasList(List<Fallas> fallasList) {
        this.fallasList = fallasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCredito != null ? idCredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Creditos)) {
            return false;
        }
        Creditos other = (Creditos) object;
        if ((this.idCredito == null && other.idCredito != null) || (this.idCredito != null && !this.idCredito.equals(other.idCredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Creditos[ idCredito=" + idCredito + " ]";
    }
    
}
