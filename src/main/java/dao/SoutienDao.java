/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import metier.modele.Soutien;

/**
 *
 * @author echaffraix
 */
public class SoutienDao {
    public void create(Soutien soutien) {
        JpaUtil.obtenirContextePersistance().persist(soutien);
    }
    
    public Soutien findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Soutien.class, id);
    }
}
