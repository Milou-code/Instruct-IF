/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.service;

import dao.IntervenantDao;
import dao.JpaUtil;
import dao.MatiereDao;
import dao.ThemeDao;
import java.util.ArrayList;
import java.util.List;
import metier.modele.Autre;
import metier.modele.Etudiant;
import metier.modele.Intervenant;
import metier.modele.Matiere;
import metier.modele.Professeur;
import metier.modele.Theme;

/**
 *
 * @author echaffraix
 */
public class ServiceInitialisation {
    public static void InitIntervenants() {
        // Création et ajout des intervenants dans une liste
        List<Intervenant> intervenants = new ArrayList<>();

        // 3 Professeurs
        intervenants.add(new Professeur("Lycée", "Dupont", "Alice", 6, 3, "alice.dupont@mail.fr", "mdp123", "0612345678"));
        intervenants.add(new Professeur("Collège", "Martin", "Jean", 5, 2, "jean.martin@mail.fr", "password", "0623456789"));
        intervenants.add(new Professeur("Université", "Bernard", "Claire", 7, 4, "claire.bernard@mail.fr", "securepass", "0634567890"));

        // 3 Étudiants
        intervenants.add(new Etudiant("Informatique", "INSA Lyon", "Leclerc", "Tom", 6, 5, "tom.leclerc@mail.fr", "pass123", "0645678901"));
        intervenants.add(new Etudiant("Mathématiques", "Sorbonne", "Durand", "Lucie", 5, 4, "lucie.durand@mail.fr", "mypassword", "0656789012"));
        intervenants.add(new Etudiant("Physique", "Polytech", "Moreau", "Max", 6, 6, "max.moreau@mail.fr", "123456", "0667890123"));

        // 3 Autres
        intervenants.add(new Autre("Retraité", "Chaffraix", "Emilien", 4, 3, "emilien.chaffraix@mail.fr", "pass567", "0678901234"));
        intervenants.add(new Autre("Consultant", "Roux", "Sophie", 3, 2, "sophie.roux@mail.fr", "secure1", "0689012345"));
        intervenants.add(new Autre("Formateur", "Faure", "Antoine", 5, 3, "antoine.faure@mail.fr", "secret!", "0690123456"));

        // Boucle pour persister chaque intervenant
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            IntervenantDao intervenantDao = new IntervenantDao();
            for (Intervenant i : intervenants) {
                intervenantDao.create(i);
                System.out.println("Persisté : " + i.getNom() + " " + i.getPrenom());
            }
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public static void InitMatiereTheme() {
    List<Matiere> matieres = new ArrayList<>();
    List<Theme> themes = new ArrayList<>();

    // Création des matières
    matieres.add(new Matiere("Français"));
    matieres.add(new Matiere("Mathématiques"));
    matieres.add(new Matiere("Physique"));
    matieres.add(new Matiere("Informatique"));
    matieres.add(new Matiere("Histoire"));
    matieres.add(new Matiere("Biologie"));

    try {
        JpaUtil.creerContextePersistance();
        JpaUtil.ouvrirTransaction();
        
        // Persistance des matières
        MatiereDao matiereDao = new MatiereDao();
        for (Matiere m : matieres) {
            matiereDao.create(m);
            System.out.println("Persisté Matière : " + m.getNom());
        }

        // Création des thèmes (3 par matière)
        themes.add(new Theme(matieres.get(0), "Le roman"));
        themes.add(new Theme(matieres.get(0), "La poésie"));
        themes.add(new Theme(matieres.get(0), "Le théâtre"));

        themes.add(new Theme(matieres.get(1), "Algèbre"));
        themes.add(new Theme(matieres.get(1), "Géométrie"));
        themes.add(new Theme(matieres.get(1), "Analyse"));

        themes.add(new Theme(matieres.get(2), "Mécanique"));
        themes.add(new Theme(matieres.get(2), "Électricité"));
        themes.add(new Theme(matieres.get(2), "Thermodynamique"));

        themes.add(new Theme(matieres.get(3), "Programmation"));
        themes.add(new Theme(matieres.get(3), "Bases de données"));
        themes.add(new Theme(matieres.get(3), "Réseaux"));

        themes.add(new Theme(matieres.get(4), "Moyen Âge"));
        themes.add(new Theme(matieres.get(4), "Renaissance"));
        themes.add(new Theme(matieres.get(4), "Révolution française"));

        themes.add(new Theme(matieres.get(5), "Botanique"));
        themes.add(new Theme(matieres.get(5), "Zoologie"));
        themes.add(new Theme(matieres.get(5), "Génétique"));

        // Persistance des thèmes
        ThemeDao themeDao = new ThemeDao();
        for (Theme t : themes) {
            themeDao.create(t);
            System.out.println("Persisté Theme : " + t.getNom() + " (" + t.getMatiere().getNom() + ")");
        }

        JpaUtil.validerTransaction();
    } catch (Exception ex) {
        ex.printStackTrace();
        JpaUtil.annulerTransaction();
    } finally {
        JpaUtil.fermerContextePersistance();
    }
}
    
}