/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aleja.clubb;

import com.aleja.clubb.exceptions.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class LoginJpaController1 implements Serializable {

    private EntityManagerFactory emf = null;

    public LoginJpaController1() {
        this.emf = Persistence.createEntityManagerFactory("loginPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear un nuevo registro de Login
    public void create(Login login) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(login);  
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar un registro de Login
    public void edit(Login login) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            login = em.merge(login); 
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception("Error al actualizar el login: " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Eliminar un registro de Login por su ID
    public void destroy(int id) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Login login;
            try {
                login = em.getReference(Login.class, id);
                login.getId();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("El login con el ID " + id + " no existe.");
            }
            em.remove(login);  
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Buscar un registro de Login por su ID
    public Login findLogin(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Login.class, id);
        } finally {
            em.close();
        }
    }

    // Obtener una lista de todos los registros de Login
    public List<Login> findLoginEntities() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT l FROM Login l");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
