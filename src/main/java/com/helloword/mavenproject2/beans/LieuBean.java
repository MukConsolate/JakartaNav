/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helloword.mavenproject2.beans;

import business.LieuEntrepriseBean;
import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author cons
 */
@Named("LieuBean")
@RequestScoped
public class LieuBean implements Serializable{
    private String nom;
    private String description;
    private String latitude;
    private String longitude;
    private String message;
    private List<Lieu> lieux = new ArrayList<>();
    
    @Inject
    private LieuEntrepriseBean lieuEntrepriseBean;

    public String getNom() {
        return nom;
    }

    public String getDesc() {
        return description;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getMessage() {
        return message;
    }

    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public List<Lieu> getLieux() {
        return lieuEntrepriseBean.listerTousLesLieux();
    }

    public void ajouterLieu() {
        if (nom != null && !nom.isEmpty() && description != null && !description.isEmpty()) {
            lieuEntrepriseBean.ajouterLieuEntreprise(nom, description, latitude, longitude);
        }
    }

    public static class Lieu {
        private String nom;
        private String description;
        private String latitude;
        private String longitude;

        public Lieu(String nom, String description, String latitude, String longitude) {
            this.nom = nom;
            this.description = description;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getNom() {
            return nom;
        }

        public String getDesc() {
            return description;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }
    }

    public void afficherMessage(){
        if (nom != null && description != null && latitude != null && longitude != null 
            && !nom.trim().isEmpty() && !description.trim().isEmpty() && !latitude.trim().isEmpty() && !longitude.trim().isEmpty()) {
             message = "" +
            "Nom du lieu : " + nom +
            " Description : " + description +
            " Latitude : " + latitude +
            " Longitude : " + longitude ;
        }
        else{
            message = "Il semble que vous n'avez pas rempli tous les champs du formulaire! "
                + "<br> Veillez tout remplir pour qu'on vous afficher les détails";
        }
    }
    
    
}
