/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation.controleur;

import entite.Utilisateur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import presentation.vue.UtilisateurUI;
import service.UtilisateurService;
import service.GroupeService;

/**
 *
 * @author daniel
 */
public class UtilisateurControleur {

    private final UtilisateurService service;
    private final GroupeService groupeService;

    public UtilisateurControleur() {
        this.service = new UtilisateurService();
        this.groupeService = new GroupeService();
    }

    public void ajouter(UtilisateurUI utilisateurUI) {
        Utilisateur utilisateur = new Utilisateur();
        UtilisateurUI popup = new UtilisateurUI(utilisateur, this);
        popup.chargerGp(groupeService.lister());
        popup.getBoutonEnregistrer().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                popup.modifierUtilisateur();
                Utilisateur u = popup.getUtilisateur();
                service.ajouter(u);
                popup.dispose();
                utilisateurUI.chargerUtilisateurs(service.lister());
            }
        });
        popup.setVisible(true);
    }

    public void modifier(Utilisateur utilisateur, UtilisateurUI utilisateurUI) {
        if (utilisateur == null) {
            JOptionPane.showMessageDialog(null, "Aucun utilisateur sélectionné");
            return;
        }

        UtilisateurUI popup = new UtilisateurUI(utilisateur, this);
        popup.chargerGp(groupeService.lister());
        popup.getBoutonEnregistrer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.modifierUtilisateur();
                Utilisateur u = popup.getUtilisateur();
                service.modifier(u);
                popup.dispose();
                utilisateurUI.chargerUtilisateurs(service.lister());
            }
        });
        popup.setVisible(true);
    }

    public void HomeUserUI() {
        Utilisateur utilisateur = new Utilisateur();
        UtilisateurUI utilisateurUI = new UtilisateurUI(utilisateur, this);
        utilisateurUI.chargerGp(groupeService.lister());
        utilisateurUI.chargerUtilisateurs(service.lister());

        utilisateurUI.getBtnAjouter().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouter(utilisateurUI);
            }
        });

        utilisateurUI.getBtnModifier().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utilisateur u = utilisateurUI.getUserSelect();
                modifier(u, utilisateurUI);
            }
        });

        utilisateurUI.getBtnSupprimer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utilisateur u = utilisateurUI.getUserSelect();
                if (u == null) {
                    JOptionPane.showMessageDialog(null, "Aucun utilisateur selectionne");
                    return;
                }
                supprimer(u.getId());
                utilisateurUI.chargerUtilisateurs(service.lister());
            }
        });
        utilisateurUI.setVisible(true);
    }

    public void supprimer(Integer id) {
        service.supprimer(id);
        JOptionPane.showMessageDialog(null, "Utilisateur supprimé");
    }

    public void fermer() {
        this.service.fermer();
    }
}
