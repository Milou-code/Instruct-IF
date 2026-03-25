/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import metier.modele.Intervenant;

/**
 *
 * @author echaffraix
 */
public class IntervenantDao {
    public void create(Intervenant intervenant) {
        JpaUtil.obtenirContextePersistance().persist(intervenant);
    }
    
    public Intervenant findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Intervenant.class, id);
    }
    
    public Intervenant findByLogin(String mail, String mdp) {
        try {
            return JpaUtil.obtenirContextePersistance()
                    .createQuery("SELECT e FROM Intervenant e WHERE e.email = :mail AND e.mdp = :mdp", Intervenant.class)
                    .setParameter("mail", mail)
                    .setParameter("mdp", mdp)
                    .getSingleResult();
        } catch (Exception ex) {
            return null; // aucun élève trouvé avec ce mail
        }   
    }
}
