/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.service;

import dao.EleveDao;
import dao.EtablissementDao;
import dao.IntervenantDao;
import dao.JpaUtil;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import metier.modele.Intervenant;
import util.EtablissementOutils;
import util.Message;

/**
 *
 * @author echaffraix
 */
public class ServiceCompte {

    public ServiceCompte() {
    }
    
    public Boolean inscrireEleve(Eleve eleve, String codeUAI)
    {
        Boolean creationSucces = false;
        EleveDao eleveDao = new EleveDao();
        EtablissementDao etablissementDao = new EtablissementDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            Etablissement etablissement = etablissementDao.findById(codeUAI);
            if (etablissement == null) {
                etablissement = EtablissementOutils.getEtablissement(codeUAI);
                etablissementDao.create(etablissement);// inscire etablissement
            }
 
            eleve.setEtablissement(etablissement);
            eleveDao.create(eleve);
            JpaUtil.validerTransaction();
            Message.envoyerMail(
                    "instructif@insa-lyon.fr", 
                    eleve.getEmail(), 
                    "Inscription Réussie", 
                    "Votre inscription à notre application a fonctionné");
            creationSucces = true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            Message.envoyerMail(
                    "instructif@insa-lyon.fr", 
                    eleve.getEmail(), 
                    "Inscription Échouée", 
                    "Votre inscription à notre application n'a pas fonctionné");
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return creationSucces;
    }
    
    public Eleve ConnexionEleve(String mail, String mdp){
        EleveDao eleveDao = new EleveDao();
        Eleve e1 = eleveDao.findByLogin(mail, mdp);
        if (e1 == null){
            System.out.println("Erreur de connexion");
        }
        return e1;
    }
    
     public Intervenant ConnexionIntervenant(String mail, String mdp){
        IntervenantDao intervenantDao = new IntervenantDao();
        Intervenant i1 = intervenantDao.findByLogin(mail, mdp);
        if (i1 == null){
            System.out.println("Erreur de connexion");
        }
        return i1;
    }
}
