/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Beans.FallasBean;
import Entidades.Clientes;
import Entidades.Creditos;
import Entidades.Fallas;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author axeld
 */
public class FallasController {
       EntityManagerFactory enf;
    EntityManager en;

    public FallasController(EntityManagerFactory e) {
        this.enf = e;
        en = enf.createEntityManager();
    }

    public void insertar(Fallas fal) {
        try {

            en.getTransaction().begin();

            en.persist(fal);

            en.getTransaction().commit();

            FallasBean.addMessage("Pago Realizado");
        } finally {
            if (en != null) {
                en.close();
            }
        }
    }

    public void eliminar(BigDecimal id) {
        try {
            en.getTransaction().begin();
            Fallas falla = new Fallas();
            try {
                falla = buscar(id);
                if (falla == null) {
                    FallasBean.addMessage("El abono con el " + id + " no existe.");
                } else {
                    en.remove(falla);
                    en.getTransaction().commit();
                    FallasBean.addMessage("Registro Eliminado");
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

    public void actualizar(Fallas fal) {

        try {
            en.getTransaction().begin();
            en.merge(fal);
            en.getTransaction().commit();
            FallasBean.addMessage("Registro Actualizado");
        } catch (Exception ex) {

            throw ex;
        } finally {
            if (en != null) {
                en.close();
            }
        }
    }

    public List<Fallas> listart() {
        String SQL = "select p from Fallas p";
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

    public List<Fallas> listarfalla(Creditos cre) {
        Query query = en.createQuery("select p from Fallas p where p.idCredito.idCredito=:arg1");
        query.setParameter("arg1", cre.getIdCredito());
        return query.getResultList();
    }

    public Fallas buscar(BigDecimal cod) {

        return en.find(Fallas.class, cod);

    }
}
