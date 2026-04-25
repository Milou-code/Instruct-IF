/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package console;

import dao.JpaUtil;
import dao.MatiereDao;
import dao.ThemeDao;
import java.time.LocalDate;
import java.util.List;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Matiere;
import metier.modele.Soutien;
import metier.modele.Theme;
import metier.service.ServiceCompte;
import metier.service.ServiceInitialisation;
import metier.service.ServiceSoutien;
import static util.Saisie.lireChaine;

/**
 *
 * @author echaffraix
 */
public class Instructif {

    public static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();
        ServiceInitialisation.InitIntervenants();
        ServiceInitialisation.InitMatiereTheme();
        testEleve();
        JpaUtil.fermerFabriquePersistance();
    }
    
    private static void testEleve() {
        ServiceCompte serviceInscription = new ServiceCompte();

        printlnConsoleIHM("Création compte");
        Eleve e1 = new Eleve("Alice", "Dutour", 3, LocalDate.of(2012, 5, 21), "alice@mail.fr", "12345");
        Boolean resultat1 = serviceInscription.inscrireEleve(e1, "0010080G");
        printlnConsoleIHM(resultat1 + " -> Inscription eleve E1 " + e1);
        
        Eleve eleve = ServiceCompte.authentificationEleve("alice@mail.fr", "12345");
  
        if (eleve != null) {
            JpaUtil.creerContextePersistance();
            
            MatiereDao matiereDao = new MatiereDao();
            Matiere matiere = matiereDao.findByName("Informatique");
            ThemeDao themeDao = new ThemeDao();
            Theme theme = themeDao.findByName("Programmation");
            Soutien soutien = ServiceSoutien.creerDemande(eleve, matiere, theme, "Ceci est une description");
            
            // Connexion intervenant et affichage du soutien assignée
            String email = lireChaine("Entrez votre email :");
            String mdp = lireChaine("Entrez votre mot de passe :");
            Intervenant intervenant = ServiceCompte.authentificationIntervenant(email, mdp);
            if (intervenant != null) {
                System.out.println("---- CONNEXION REUSSIE ----");
                System.out.println("Voici votre soutien : ");
                Soutien soutienIntervenant = ServiceSoutien.getSoutienEnCoursIntervenant(intervenant);
                if (soutienIntervenant != null) {
                    System.out.println(soutienIntervenant.toString());
                }
            }
            
            
            if (soutien.getIntervenant() != null) {
                ServiceSoutien.finirSoutien(soutien, "Bravo tu as fait une super séance !");
            }
            List<Soutien> listeSoutiensEleve = ServiceSoutien.getSoutiensEleve(eleve);

            if (listeSoutiensEleve != null) {
                for (Soutien s : listeSoutiensEleve) {
                    System.out.println("---- Soutien ----");
                    System.out.println(s.toString());
                }
            } else {
                System.out.println("Aucun soutien trouvé ou erreur.");
            }
            
            List<Soutien> listeSoutiensIntervenant = ServiceSoutien.getSoutiensTerminesIntervenant(soutien.getIntervenant());

            if (listeSoutiensIntervenant != null) {
                for (Soutien s : listeSoutiensEleve) {
                    System.out.println("---- Soutien ----");
                    System.out.println(s.toString());
                }
            } else {
                System.out.println("Aucun soutien trouvé ou erreur.");
            }
        }
    }
    
    public static void printlnConsoleIHM(Object o) {
        String BG_CYAN = "\u001b[46m";
        String RESET = "\u001B[0m";

        System.out.print(BG_CYAN);
        System.out.println(String.format("%-80s", o));
        System.out.print(RESET);
    }
}
