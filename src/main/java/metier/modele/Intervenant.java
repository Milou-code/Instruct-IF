/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author echaffraix
 */

@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
public class Intervenant {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private int niveauScolaireMin;
    private int niveauScolaireMax;
    @Column(unique = true)
    private String email;
    private String mdp;
    private String numeroTelephone; 

    public Intervenant() {
    }
    
    public Intervenant(String nom, String prenom, int niveauScolaireMin, int niveauScolaireMax, String email, String mdp, String numeroTelephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.niveauScolaireMin = niveauScolaireMin;
        this.niveauScolaireMax = niveauScolaireMax;
        this.email = email;
        this.mdp = mdp;
        this.numeroTelephone = numeroTelephone;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNiveauScolaireMin() {
        return niveauScolaireMin;
    }

    public int getNiveauScolaireMax() {
        return niveauScolaireMax;
    }

    public String getEmail() {
        return email;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }
    
    
}
