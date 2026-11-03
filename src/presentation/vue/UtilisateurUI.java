/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation.vue;

import entite.Groupe;
import java.util.List;
import entite.Utilisateur;
import presentation.controleur.UtilisateurControleur;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;

/**
 *
 * @author daniel
 */
public class UtilisateurUI extends JFrame {

    private final Utilisateur utilisateur;
    private final UtilisateurControleur utilisateurControleur;
    private final JTextField nom;
    private final JTextField prenom;
    private final JTextField identifiant;
    private final JPasswordField motDePasse;
    private final JButton boutonEnregistrer;
    private JComboBox cbgroupes;
    private final JTable table;
    private final DefaultTableModel Model;
    private List<Utilisateur> listeUtilisateurs;
    private JButton btnAjouter = new JButton("Ajouter");
    private JButton btnModifier = new JButton("Modifier");
    private JButton btnSupprimer = new JButton("Supprimer");

    public UtilisateurUI(Utilisateur utilisateur, UtilisateurControleur utilisateurCtrl) {

        this.utilisateur = utilisateur;
        this.utilisateurControleur = utilisateurCtrl;
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(1024, 1024);
        this.setTitle("Gestion des utilisateurs");
        this.setLocationRelativeTo(null);

        JPanel panelH = new JPanel(new BorderLayout(5, 5));
        JLabel Titre = new JLabel("Liste des utilisateurs", JLabel.CENTER);
        Titre.setForeground(Color.BLUE);
        Titre.setFont(new Font("sansserif", Font.BOLD, 24));
        panelH.add(Titre, BorderLayout.NORTH);
        this.setLayout(new BorderLayout(10, 10));
        
        String[] Tableau = {"ID", "Nom", "Prénoms", "Identifiant", "Mot de Passe ", "Groupe"};
        Model = new DefaultTableModel(Tableau, 0) {
            @Override
            public boolean isCellEditable(int ligne, int colonne) {
                return false;
            }
        };

        table = new JTable(Model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 200));
        panelH.add(scrollPane, BorderLayout.CENTER);
        this.add(panelH, BorderLayout.NORTH);

        JPanel panelBtn = new JPanel();
        panelBtn.add(btnAjouter);
        panelBtn.add(btnModifier);
        panelBtn.add(btnSupprimer);
        this.add(panelBtn, BorderLayout.SOUTH);

        JPanel panelFormulaire = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        layout.setConstraints(panelFormulaire, c);
        panelFormulaire.setLayout(layout);
        
        cbgroupes = new JComboBox<>();
        nom = new JTextField(35);
        prenom = new JTextField(35);
        identifiant = new JTextField(35);
        motDePasse = new JPasswordField(35);
        boutonEnregistrer = new JButton("Enregistrer");
        boutonEnregistrer.setBackground(Color.ORANGE);

        btnModifier.setForeground(Color.WHITE);
        btnSupprimer.setForeground(Color.WHITE);
        btnAjouter.setBackground(Color.GREEN);
        btnModifier.setBackground(Color.BLUE);
        btnSupprimer.setBackground(Color.RED);

        cbgroupes.setEditable(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);

        panelFormulaire.add(new JLabel("Nom : "), c);
        c.gridx = 1;
        panelFormulaire.add(nom, c);

        c.gridx = 0;
        c.gridy = 1;
        panelFormulaire.add(new JLabel(" Prénoms : "), c);
        c.gridx = 1;
        panelFormulaire.add(prenom, c);

        c.gridx = 0;
        c.gridy = 2;
        panelFormulaire.add(new JLabel(" Identifiant : "), c);
        c.gridx = 1;
        panelFormulaire.add(identifiant, c);

        c.gridx = 0;
        c.gridy = 3;
        panelFormulaire.add(new JLabel(" Mot de passe : "), c);
        c.gridx = 1;
        panelFormulaire.add(motDePasse, c);

        c.gridx = 0;
        c.gridy = 4;
        panelFormulaire.add(new JLabel(" Groupe : "), c);
        c.gridx = 1;

        panelFormulaire.add(cbgroupes, c);

        c.gridx = 1;
        c.gridy = 5;

        panelFormulaire.add(boutonEnregistrer, c);
        this.add(panelFormulaire, BorderLayout.CENTER);
        this.afficher();
    }

    public void chargerGp(List<Groupe> groupes) {
        cbgroupes.removeAllItems();
        for (Groupe g : groupes) {
            cbgroupes.addItem(g);
        }
    }

    public void chargerUtilisateurs(List<Utilisateur> utilisateurs) {
        this.listeUtilisateurs = utilisateurs;
        Model.setRowCount(0);
        if (utilisateurs == null) {
            return;
        }
        for (Utilisateur u : utilisateurs) {
            String nomGroupe = "";
            if (u.getGroupe() != null) {
                nomGroupe = u.getGroupe().getNom();
            }
            Object[] ligne = {u.getId(), u.getNom(), u.getPrenom(),
                u.getIdentifiant(), u.getMotDePasse(), nomGroupe
            };
            Model.addRow(ligne);
        }
    }

    public Utilisateur getUserSelect() {
        int ligne = table.getSelectedRow();
        if (ligne != -1 && listeUtilisateurs != null) {
            return listeUtilisateurs.get(ligne);
        }
        return null;
    }

    public void modifierUtilisateur() {
        this.utilisateur.setNom(nom.getText());
        this.utilisateur.setPrenom(prenom.getText());
        this.utilisateur.setIdentifiant(identifiant.getText());
        this.utilisateur.setMotDePasse(new String(motDePasse.getPassword()));
        this.utilisateur.setGroupe((Groupe) cbgroupes.getSelectedItem());
    }

    public JComboBox<Groupe> getComboGroupes() {
        return cbgroupes;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public JButton getBoutonEnregistrer() {
        return boutonEnregistrer;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getBtnAjouter() {
        return btnAjouter;
    }

    public JButton getBtnModifier() {
        return btnModifier;
    }

    public JButton getBtnSupprimer() {
        return btnSupprimer;
    }

    private void afficher() {
        if (this.utilisateur.getNom() != null) {
            this.nom.setText(this.utilisateur.getNom());
        } else {
            this.nom.setText("");
        }

        if (this.utilisateur.getPrenom() != null) {
            this.prenom.setText(this.utilisateur.getPrenom());
        } else {
            this.prenom.setText("");
        }

        if (this.utilisateur.getIdentifiant() != null) {
            this.identifiant.setText(this.utilisateur.getIdentifiant());
        } else {
            this.identifiant.setText("");
        }

        if (this.utilisateur.getMotDePasse() != null) {
            this.motDePasse.setText(this.utilisateur.getMotDePasse());
        } else {
            this.motDePasse.setText("");
        }

        if (this.utilisateur.getGroupe() != null) {
            this.cbgroupes.setSelectedItem(this.utilisateur.getGroupe());
        }
    }
}
