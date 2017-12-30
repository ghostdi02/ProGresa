/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Beans.CreditoBean;
import Entidades.Clientes;
import Entidades.Creditos;
import Entidades.TipoCredito;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author axeld
 */
public class CreditoController {

    private EntityManagerFactory enf;
    private EntityManager en;

    public CreditoController(EntityManagerFactory e) {
        this.enf = e;
        en = enf.createEntityManager();
    }

    public void insertar(Creditos cre) {
        try {
            en.getTransaction().begin();
            en.persist(cre);
            en.getTransaction().commit();
            CreditoBean.addMessage("Registro Guardado");
        } finally {
            if (en != null) {
                en.close();
            }
        }
    }

    public void eliminar(BigDecimal id) {
        try {
            en.getTransaction().begin();
            Creditos credito = new Creditos();

            try {
                credito = buscar(id);
                if (credito == null) {
                    System.out.println("El credito con el " + id + " no existe.");
                } else {
                    en.remove(credito);
                    en.getTransaction().commit();
                    CreditoBean.addMessage("Registro Eliminado");
                }
            } catch (Exception e) {

            }

        } finally {
            if (en != null) {
                en.close();
            }
        }
    }

    public void actualizar(Creditos cre) {

        try {
            en.getTransaction().begin();
            en.merge(cre);
            en.getTransaction().commit();
            CreditoBean.addMessage("Registro Actualizado");
        } catch (Exception ex) {

            throw ex;
        } finally {
            if (en != null) {
                en.close();
            }
        }
    }

    public List<Creditos> listart() {
        String SQL = "select p from Creditos p";
        return en.createQuery(SQL).getResultList();

    }

    public List<TipoCredito> listartipocredito() {
        String SQL = "select p from TipoCredito p";
        return en.createQuery(SQL).getResultList();

    }

    public List<Clientes> listarcliente() {
       String SQL = "select p from Clientes p";
        return en.createQuery(SQL).getResultList();
    }
    
    
     
         public List<Creditos> listarcreditos(Date fecha1, Date fecha2) {
        Query query = en.createQuery("select c from Creditos c where c.fechaCreacion between :arg1 and :arg2");
         query.setParameter("arg1", fecha1);
         query.setParameter("arg2", fecha2);
        return query.getResultList();
    }
    
   

    public Creditos buscar(BigDecimal cod) {
        return en.find(Creditos.class, cod);
    }
}
