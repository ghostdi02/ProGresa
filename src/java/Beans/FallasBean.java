/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import Controladores.FallasController;
import Entidades.Clientes;
import Entidades.Creditos;
import Entidades.Fallas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class FallasBean {
    private Fallas falla=new Fallas();
    private List<Fallas> listfalla=new ArrayList();
    private Clientes cliente = new Clientes();
    private List<Clientes> listcliente = new ArrayList();
    private Creditos credito = new Creditos();
    private List<Creditos> listacreditos = new ArrayList();

    static public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void listarcli() throws ParseException {
        FallasController dao = new FallasController(Persistence.createEntityManagerFactory("ProGresaPU"));
        listcliente = dao.listarcliente();
    }

    public void listacredi() throws ParseException {
        listacreditos = new ArrayList();
        FallasController dao = new FallasController(Persistence.createEntityManagerFactory("ProGresaPU"));
        listacreditos = dao.listarcreditos(cliente);
    }
    
    
      public void listafalla() throws ParseException {
        FallasController dao = new FallasController(Persistence.createEntityManagerFactory("ProGresaPU"));
        listfalla = dao.listarfalla(credito);
    }
      
        public void ingresarfalla() throws ParseException {

        FallasController dao = new FallasController(Persistence.createEntityManagerFactory("ProGresaPU"));
        listacreditos = dao.listarcreditos2(credito);
        int i = 0;
        credito.setIdCredito(listacreditos.get(i).getIdCredito());
        credito.setPlazo(listacreditos.get(i).getPlazo());
        credito.setMonto(listacreditos.get(i).getMonto());
  

        falla.setIdCredito(credito);

        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        falla.setFechaFalla(formateador.parse(formateador.format(new Date())));
       

        dao.insertar(falla);
        falla = new Fallas();
        dao = new FallasController(Persistence.createEntityManagerFactory("ProGresaPU"));
        listfalla=dao.listarfalla(credito);

        

    }
          public void eliminar(Fallas fal) {
        FallasController dao = new FallasController(Persistence.createEntityManagerFactory("ProGresaPU"));
        dao.eliminar(fal.getIdFalla());

    }

    public Fallas getFalla() {
        return falla;
    }

    public void setFalla(Fallas falla) {
        this.falla = falla;
    }

    public List<Fallas> getListfalla() {
        return listfalla;
    }

    public void setListfalla(List<Fallas> listfalla) {
        this.listfalla = listfalla;
    }
        
        

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public List<Clientes> getListcliente() {
        return listcliente;
    }

    public void setListcliente(List<Clientes> listcliente) {
        this.listcliente = listcliente;
    }

    public Creditos getCredito() {
        return credito;
    }

    public void setCredito(Creditos credito) {
        this.credito = credito;
    }

    public List<Creditos> getListacreditos() {
        return listacreditos;
    }

    public void setListacreditos(List<Creditos> listacreditos) {
        this.listacreditos = listacreditos;
    }
    
    
    
    
}
