/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package console;

import dao.EleveDao;
import dao.JpaUtil;
import java.time.LocalDate;
import metier.modele.Eleve;
import metier.service.ServiceInitialisation;
import metier.service.ServiceCompte;

/**
 *
 * @author echaffraix
 */
public class Instructif {

    public static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();
        testerInscrireEleve();
        JpaUtil.fermerFabriquePersistance();
    }
    
    private static void testerInscrireEleve() {
        ServiceCompte serviceInscription = new ServiceCompte();

        printlnConsoleIHM("Inscription Eleve E1");
        Eleve e1 = new Eleve("Hugo", "Victor", 3, LocalDate.of(2012, 5, 21), "hugo@mail.fr", "12345");
        Boolean resultat1 = serviceInscription.inscrireEleve(e1, "0160563A");
        printlnConsoleIHM(resultat1 + " -> Inscription eleve E1 " + e1);
        
        printlnConsoleIHM("Inscription Eleve E2");
        Eleve e2 = new Eleve("Hugo", "Victor", 3, LocalDate.of(2012, 5, 21), "hugo@mail.fr", "12345");
        Boolean resultat2 = serviceInscription.inscrireEleve(e2, "0160563A");
        printlnConsoleIHM(resultat2 + " -> Inscription eleve E2 " + e2);
        
        printlnConsoleIHM("Inscription Eleve E3");
        Eleve e3 = new Eleve("Hugue", "Paul", 4, LocalDate.of(2013, 9, 20),"paul@mail.fr", "monmotdepasse");
        Boolean resultat3 = serviceInscription.inscrireEleve(e3, "0160563A");
        printlnConsoleIHM(resultat3 + " -> Inscription eleve E3 " + e3);
        

        
        ServiceInitialisation.InitIntervenants();
        ServiceInitialisation.InitMatiereTheme();
        serviceInscription.Test();
    }
    
    public static void printlnConsoleIHM(Object o) {
        String BG_CYAN = "\u001b[46m";
        String RESET = "\u001B[0m";

        System.out.print(BG_CYAN);
        System.out.println(String.format("%-80s", o));
        System.out.print(RESET);
    }
}
