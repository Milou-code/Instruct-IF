/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 *
 * @author echaffraix
 */
@Entity
@Table (name = "Etablissement")
public class Etablissement {
    @Id
    private String codeUAI;
    private String nom;

    public Etablissement() {
    }

    public Etablissement(String codeUAI) {
        this.codeUAI = codeUAI;
        this.nom = null;
    }

    public String getCodeUAI() {
        return codeUAI;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCodeUAI(String codeUAI) {
        this.codeUAI = codeUAI;
    }

    @Override
    public String toString() {
        return "Etablissement{" + "codeUAI=" + codeUAI + ", nom=" + nom + '}';
    }
}
