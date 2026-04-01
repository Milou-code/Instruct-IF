/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.service;

import static console.Instructif.printlnConsoleIHM;
import dao.IntervenantDao;
import dao.JpaUtil;
import dao.SoutienDao;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Matiere;
import metier.modele.Soutien;
import metier.modele.Theme;
import util.Message;

/**
 *
 * @author echaffraix
 */
public class ServiceSoutien {
    public static void creerDemande(Eleve eleve, Matiere matiere, Theme theme, String description){
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            int niveauEleve = eleve.getNiveauScolaire();
            SoutienDao soutienDao = new SoutienDao();
            Soutien soutien = new Soutien(eleve, matiere,theme,description);
            soutienDao.create(soutien);
            
            IntervenantDao intervenantDao = new IntervenantDao();
            Intervenant intervenant = intervenantDao.findIntervenantLibre(niveauEleve);
            
            if(intervenant == null) {
                printlnConsoleIHM("Aucun Intervenant Disponible");
                soutien.setStatut("CANCELLED");
            }
            else {
                soutien.setIntervenant(intervenant);
                intervenant.ajouterSoutien();
                intervenant.setLibre(false);
                soutien.setStatut("FOUND");
                
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
                String time = LocalTime.now().format(dtf); // récupère l'heure actuelle et formate

                String message = String.format(
                        "Bonjour %s. Merci de prendre en charge la demande de soutien en %s demandée à %s par %s en classe de %sème.",
                        intervenant.getPrenom(),
                        matiere.getNom(),
                        time,
                        eleve.getPrenom(),
                        niveauEleve
                );                
                Message.envoyerNotification(intervenant.getNumeroTelephone(), message);
                
                String lien = String.format(
                        "https://servif.insa-lyon.fr/InteractIF/visio.html?eleve=%s&intervenant=%s%s",
                        eleve.getEmail(),
                        intervenant.getPrenom().toLowerCase().charAt(0),
                        intervenant.getNom().toLowerCase()
                );    
                soutien.setLien(lien);
            }
            JpaUtil.validerTransaction();
        } catch (Exception ex){
            ex.printStackTrace();  
            JpaUtil.annulerTransaction();  
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
}
