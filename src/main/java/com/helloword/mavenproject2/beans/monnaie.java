/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helloword.mavenproject2.beans;

/**
 *
 * @author cons
 */
public class monnaie {
    private float montant ;
    private float taux ;
    private float converti;

    public float getMontant() {
        return montant;
    }
    public void setMontant(float montant) {
        this.montant = montant;
    }
    
    public float getTaux() {
        return taux;
    }
    public void setTaux(float taux) {
        this.taux = taux;
    }
    
    public float getConverti() {
        return converti;
    }
    public void setConverti(float converti) {
        this.converti = converti;
    }
    
    public void afficherMonnaie(float montant , String taux){
        if (taux.equals("CDF")){
            
        }
    }
}
