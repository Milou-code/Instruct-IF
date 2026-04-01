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
public class Soutien {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Eleve eleve;
    @ManyToOne
    private Intervenant intervenant;
    @ManyToOne
    private Matiere matiere;
    @ManyToOne
    private Theme theme;
    private String lien;
    private String description;
    private String statut;

    public Soutien() {
    }

    public Soutien(Eleve eleve, Matiere matiere, Theme theme, String description) {
        this.eleve = eleve;
        this.matiere = matiere;
        this.theme = theme;
        this.description = description;
        this.statut = "NOTFOUND";
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
