/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Beans.AbonosBean;
import Entidades.Abonos;
import Entidades.Clientes;
import Entidades.Creditos;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author axeld
 */
public class AbonosController {

    EntityManagerFactory enf;
    EntityManager en;

    public AbonosController(EntityManagerFactory e) {
        this.enf = e;
        en = enf.createEntityManager();
    }

    public void insertar(Abonos ab) {
        try {

            en.getTransaction().begin();

            en.persist(ab);

            en.getTransaction().commit();

            AbonosBean.addMessage("Pago Realizado");
        } finally {
            if (en != null) {
                en.close();
            }
        }
    }

    public void eliminar(BigDecimal id) {
        try {
            en.getTransaction().begin();
            Abonos abono = new Abonos();
            try {
                abono = buscar(id);
                if (abono == null) {
                    AbonosBean.addMessage("El abono con el " + id + " no existe.");
                } else {
                    en.remove(abono);
                    en.getTransaction().commit();
                    AbonosBean.addMessage("Registro Eliminado");
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

    public void actualizar(Abonos cli) {

        try {
            en.getTransaction().begin();
            en.merge(cli);
            en.getTransaction().commit();
            AbonosBean.addMessage("Registro Actualizado");
        } catch (Exception ex) {

            throw ex;
        } finally {
            if (en != null) {
                en.close();
            }
        }
    }

    public List<Abonos> listart() {
        String SQL = "select p from Abonos p";
        return en.createQuery(SQL).getResultList();

    }

    public List<Clientes> listarcliente() {
        String SQL = "select p from Clientes p";
        return en.createQuery(SQL).getResultList();

    }

    public List<Creditos> listarcreditos(Clientes cli) {
        Query query = en.createQuery("select p from Creditos p where p.idCliente.idCliente=:arg1");
        query.setParameter("arg1", cli.getIdCliente());
        return query.getResultList();
    }
    
        public List<Creditos> listarcreditos2(Creditos cre) {
        Query query = en.createQuery("select p from Creditos p where p.idCredito=:arg1");
        query.setParameter("arg1", cre.getIdCredito());
        return query.getResultList();
    }

    public List<Abonos> listarabonos(Creditos cre) {
        Query query = en.createQuery("select p from Abonos p where p.idCredito.idCredito=:arg1");
        query.setParameter("arg1", cre.getIdCredito());
        return query.getResultList();
    }

    public Abonos buscar(BigDecimal cod) {

        return en.find(Abonos.class, cod);

    }
}
