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
    public void create(Theme theme) {
        JpaUtil.obtenirContextePersistance().persist(theme);
    }
    
    public Theme findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Theme.class, id);
    }
}
