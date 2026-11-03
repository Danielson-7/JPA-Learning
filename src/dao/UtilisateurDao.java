/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entite.Utilisateur;
import entite.Groupe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author daniel
 */
public class UtilisateurDao {

    private final EntityManagerFactory emf;

    public UtilisateurDao() {
        emf = Persistence.createEntityManagerFactory("jpaPU");

    }

    private Groupe obtenirGroupeUnique(EntityManager em, Groupe groupe) {
        if (groupe == null || groupe.getNom() == null || groupe.getNom()
                .trim().isEmpty()) {
            return null;
        }
        String nom = groupe.getNom().trim();
        List<Groupe> resultats = em.createQuery("SELECT g FROM Groupe g WHERE g.nom = :nom", Groupe.class)
                .setParameter("nom", nom)
                .getResultList();
        if (!resultats.isEmpty()) {
            return resultats.get(0);
        }
        groupe.setNom(nom);
        return groupe;

    }

    public void ajouter(Utilisateur utilisateur) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            utilisateur.setGroupe(obtenirGroupeUnique(em, utilisateur.getGroupe()));
            em.persist(utilisateur);
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

    public void modifier(Utilisateur utilisateur) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            utilisateur.setGroupe(obtenirGroupeUnique(em, utilisateur.getGroupe()));
            em.merge(utilisateur);
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

    public List<Utilisateur> lister() {
        EntityManager em = emf.createEntityManager();
        List<Utilisateur> listeUtilisteurs = em.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class)
                .getResultList();
        em.close();
        return listeUtilisteurs;
    }

    public void supprimer(Integer id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Utilisateur utilisateur = em.find(Utilisateur.class, id);
            if (utilisateur != null) {
                em.remove(utilisateur);
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
