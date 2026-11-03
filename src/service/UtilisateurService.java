/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UtilisateurDao;
import entite.Utilisateur;
import java.util.List;

/**
 *
 * @author daniel
 */
public class UtilisateurService {

    private final UtilisateurDao dao;

    public UtilisateurService() {
        this.dao = new UtilisateurDao();
    }

    public void ajouter(Utilisateur utilisateur) {
        this.dao.ajouter(utilisateur);
    }

    public void modifier(Utilisateur utilisateur) {
        this.dao.modifier(utilisateur);
    }

    public List<Utilisateur> lister() {
        return dao.lister();
    }

    public void supprimer(Integer id) {
        this.dao.supprimer(id);
    }

    public void fermer() {
        this.dao.fermer();
    }
}
