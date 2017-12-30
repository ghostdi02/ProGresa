/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Controladores.AbonosController;
import Controladores.CreditoController;
import Entidades.Abonos;
import Entidades.Clientes;
import Entidades.Creditos;
import java.math.BigInteger;
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
public class AbonosBean {

    private Abonos abono = new Abonos();
    private List<Abonos> listabono = new ArrayList();
    private Clientes cliente = new Clientes();
    private List<Clientes> listcliente = new ArrayList();
    private Creditos credito = new Creditos();
    private List<Creditos> listacreditos = new ArrayList();

    static public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void listarcli() throws ParseException {
        AbonosController dao = new AbonosController(Persistence.createEntityManagerFactory("ProGresaPU"));
        listcliente = dao.listarcliente();
    }

    public void listacredi() throws ParseException {
        listacreditos = new ArrayList();
        AbonosController dao = new AbonosController(Persistence.createEntityManagerFactory("ProGresaPU"));
        listacreditos = dao.listarcreditos(cliente);
    }

    public void listarabo() throws ParseException {
        AbonosController dao = new AbonosController(Persistence.createEntityManagerFactory("ProGresaPU"));
        listabono = dao.listarabonos(credito);
    }

    public void ingresarabono() throws ParseException {

        AbonosController dao = new AbonosController(Persistence.createEntityManagerFactory("ProGresaPU"));
        listacreditos = dao.listarcreditos2(credito);
        int i = 0;
        credito.setIdCredito(listacreditos.get(i).getIdCredito());
        credito.setPlazo(listacreditos.get(i).getPlazo());
        credito.setMonto(listacreditos.get(i).getMonto());
        int a = credito.getMonto().intValue() / Integer.parseInt(credito.getPlazo());

        abono.setIdCredito(credito);

        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        abono.setFechaAbono(formateador.parse(formateador.format(new Date())));
        abono.setCantidad((BigInteger.valueOf(a)));

        dao.insertar(abono);
        abono = new Abonos();
        dao = new AbonosController(Persistence.createEntityManagerFactory("ProGresaPU"));
        listabono=dao.listarabonos(credito);

        

    }

    public void eliminar(Abonos ab) {
        AbonosController dao = new AbonosController(Persistence.createEntityManagerFactory("ProGresaPU"));
        dao.eliminar(ab.getIdAbono());

    }

    public Abonos getAbono() {
        return abono;
    }

    public void setAbono(Abonos abono) {
        this.abono = abono;
    }

    public List<Abonos> getListabono() {
        return listabono;
    }

    public void setListabono(List<Abonos> listabono) {
        this.listabono = listabono;
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
