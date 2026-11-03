/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entite.Groupe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 *
 * @author daniel
 */

public class GroupeDao {

    private final EntityManagerFactory emf;

    public GroupeDao() {
        emf = Persistence.createEntityManagerFactory("jpaPU");
    }

    public void ajouter(Groupe groupe) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(groupe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        em.close();
    }

    public Groupe modifier(Groupe groupe) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Groupe g = em.merge(groupe);
            transaction.commit();
            return g;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            return null;
        } finally {
            em.close();
        }

    }

    public List<Groupe> lister() {
        EntityManager em = emf.createEntityManager();
        List<Groupe> resultats = em.createQuery("SELECT g FROM Groupe g ", Groupe.class)
                .getResultList();
        em.close();
        return resultats;
    }

    public void supprimer(Integer id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Groupe groupe = em.find(Groupe.class, id);
            if (groupe != null) {
                em.remove(groupe);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
    }

    public void fermer() {
        if (emf.isOpen()) {
            emf.close();
        }
    }

}
