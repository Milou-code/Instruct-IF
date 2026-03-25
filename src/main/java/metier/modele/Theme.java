/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author echaffraix
 */
@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nom;
    @ManyToOne
    private Matiere matiere;

    public Theme() {
    }

    public Theme(Matiere matiere, String nom) {
        this.nom = nom;
        this.matiere = matiere;
    }

    public String getNom() {
        return nom;
    }

    public Matiere getMatiere() {
        return matiere;
    }
    
    
}
