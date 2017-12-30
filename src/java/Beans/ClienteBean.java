/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Controladores.ClienteController;
import Entidades.Clientes;
import java.util.ArrayList;
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
public class ClienteBean {

    private Clientes per = new Clientes();
    private List<Clientes> listt = new ArrayList();

    static public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void ingresar() {

        ClienteController dao = new ClienteController(Persistence.createEntityManagerFactory("ProGresaPU"));
        dao.insertar(per);
        per = new Clientes();

    }

    public void listart() {
        ClienteController dao = new ClienteController(Persistence.createEntityManagerFactory("ProGresaPU"));
        listt = dao.listart();
    }

    public void buscar(Clientes cli) {
        ClienteController dao = new ClienteController(Persistence.createEntityManagerFactory("ProGresaPU"));
        per = dao.buscar(cli.getIdCliente());
    }

    public void modificar() {
        ClienteController dao = new ClienteController(Persistence.createEntityManagerFactory("ProGresaPU"));
        dao.actualizar(per);
        per = new Clientes();
    }

    public void eliminar(Clientes cli) {

        ClienteController dao = new ClienteController(Persistence.createEntityManagerFactory("ProGresaPU"));
        dao.eliminar(cli.getIdCliente());

    }

    public Clientes getPer() {
        return per;
    }

    public void setPer(Clientes per) {
        this.per = per;
    }

    public List<Clientes> getListt() {
        return listt;
    }

    public void setListt(List<Clientes> listt) {
        this.listt = listt;
    }
}
