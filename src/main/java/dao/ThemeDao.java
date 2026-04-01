/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import metier.modele.Theme;

/**
 *
 * @author echaffraix
 */
public class ThemeDao {
    public Theme findByName(String nom) {
        try {
            return JpaUtil.obtenirContextePersistance()
                    .createQuery("SELECT t FROM Theme t WHERE t.nom = :nom", Theme.class)
                    .setParameter("nom", nom)
                    .getSingleResult();
        } catch (Exception ex) {
            return null; // aucun thème trouvé
        }
    }
}
