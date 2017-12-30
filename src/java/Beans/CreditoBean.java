/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Controladores.CreditoController;
import Entidades.Clientes;
import Entidades.Creditos;
import Entidades.TipoCredito;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Persistence;

/**
 *
 * @author axeld
 */
@ManagedBean
@ViewScoped
public class CreditoBean implements Serializable{
    private Date fechainicio;
    private Date fechafinal;
    private Creditos cre = new Creditos();
    private Clientes clien = new Clientes();
    private TipoCredito tipocre = new TipoCredito();
    private List<Creditos> listt = new ArrayList();
    private List<TipoCredito> lsttipocre = new ArrayList();
    private List<Clientes> listcliente = new ArrayList();

    static public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    
    
    public void pre() throws ParseException {
         CreditoController dao = new CreditoController(Persistence.createEntityManagerFactory("ProGresaPU"));
       lsttipocre=dao.listartipocredito();
       listcliente=dao.listarcliente();
    }

    public void ingresar() {

        CreditoController dao = new CreditoController(Persistence.createEntityManagerFactory("ProGresaPU"));
        cre.setIdCliente(clien);
        cre.setTipoCredito(tipocre);
        dao.insertar(cre);
        cre = new Creditos();

    }

    public void listart() throws ParseException {
    
        CreditoController dao = new CreditoController(Persistence.createEntityManagerFactory("ProGresaPU"));
        listt = dao.listart(); 
      
       
    }
    
    public void listarporfecha() {
       
         CreditoController dao = new CreditoController(Persistence.createEntityManagerFactory("ProGresaPU"));
        listt=dao.listarcreditos(fechainicio, fechafinal);
         for (int i = 0; i < listt.size(); i++) {
            System.out.println(listt.get(i).getFechaCreacion());
        }
      
    }

    public void buscar(Creditos credi) {
        CreditoController dao = new CreditoController(Persistence.createEntityManagerFactory("ProGresaPU"));
        cre = dao.buscar(credi.getIdCredito());
    }

    public void modificar() {
        CreditoController dao = new CreditoController(Persistence.createEntityManagerFactory("ProGresaPU"));
        dao.actualizar(cre);
        cre = new Creditos();
    }

    public void eliminar(Creditos credi) {
        CreditoController dao = new CreditoController(Persistence.createEntityManagerFactory("ProGresaPU"));
        dao.eliminar(credi.getIdCredito());

    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }
        
    
 
    public List<Clientes> getListcliente() {
        return listcliente;
    }

    public TipoCredito getTipocre() {
        return tipocre;
    }

    public void setTipocre(TipoCredito tipocre) {
        this.tipocre = tipocre;
    }
    
    

    public void setListcliente(List<Clientes> listcliente) {
        this.listcliente = listcliente;
    }
    
    
    

    public List<TipoCredito> getLsttipocre() {
        return lsttipocre;
    }

    public Clientes getClien() {
        return clien;
    }

    public void setClien(Clientes clien) {
        this.clien = clien;
    }

    
    
    
    
    public void setLsttipocre(List<TipoCredito> lsttipocre) {
        this.lsttipocre = lsttipocre;
    }

    

    public Creditos getCre() {
        return cre;

    }

    public void setCre(Creditos cre) {
        this.cre = cre;
    }

    public List<Creditos> getListt() {
        return listt;
    }

    public void setListt(List<Creditos> listt) {
        this.listt = listt;
    }

}
