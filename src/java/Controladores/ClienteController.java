/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Beans.ClienteBean;
import Entidades.Clientes;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author axeld
 */
public class ClienteController {

    EntityManagerFactory enf;
    EntityManager en;

    public ClienteController(EntityManagerFactory e) {
        this.enf = e;
        en = enf.createEntityManager();
    }

    public void insertar(Clientes cli) {
        try {
           
            en.getTransaction().begin();
        
            en.persist(cli);
          
            en.getTransaction().commit();
          
            ClienteBean.addMessage("Registro Guardado");
        } finally {
            if (en != null) {
                en.close();
            }
        }
    }

    public void eliminar(BigDecimal id) {
        try {
            en.getTransaction().begin();
            Clientes cliente = new Clientes();
            try {
                cliente = buscar(id);
                if (cliente == null) {
                    System.out.println("El cliente con el " + id + " no existe.");
                } else {
                    en.remove(cliente);
                    en.getTransaction().commit();
                    ClienteBean.addMessage("Registro Eliminado");
                }
            } catch (Exception e) {
                throw e;
            }

        } finally {
            if (en != null) {
                en.close();
            }
        }
    }

    public void actualizar(Clientes cli) {

        try {
            en.getTransaction().begin();
            en.merge(cli);
            en.getTransaction().commit();
            ClienteBean.addMessage("Registro Actualizado");
        } catch (Exception ex) {

            throw ex;
        } finally {
            if (en != null) {
                en.close();
            }
        }
    }

    public List<Clientes> listart() {
        String SQL = "select p from Clientes p";
        return en.createQuery(SQL).getResultList();

    }

    public Clientes buscar(BigDecimal cod) {

        return en.find(Clientes.class, cod);

    }

}
