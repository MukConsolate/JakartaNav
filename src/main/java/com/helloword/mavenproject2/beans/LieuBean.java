/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helloword.mavenproject2.beans;

import business.LieuEntrepriseBean;
import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author cons
 */
@Named("lieuBean")
@RequestScoped
public class LieuBean implements Serializable{
    private String nom;
    private String description;
    private String latitude;
    private String longitude;
    private String message;
    private List<Lieu> lieux = new ArrayList<>();
    private String weatherMessage;
    private int selectedLieu;
    private Lieu lieu;
    
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

    public int getSelectedLieu() {
        return selectedLieu;
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

    public void supprimerLieu(Lieu lieu) {
        if (lieu != null) {
            lieuEntrepriseBean.supprimerLieu(lieu.getId());
        }
    }
    
    public void resetFields() {
        nom = null;
        description = null;
        latitude = "0";
        longitude = "0";
        lieu = null; // Réinitialiser le lieu
    }
    
    public void selectLieu(Lieu lieu) {
        this.lieu = lieu;
        this.nom = lieu.getNom();
        this.description = lieu.getDescription();
        this.latitude = lieu.getLatitude();
        this.longitude = lieu.getLongitude();
    }

    public void fetchWeatherMessage(Lieu l) {
      
        if (l != null) {
            // Appel au service web pour obtenir les données météorologiques
        
            String serviceURL = "http://localhost:8080/jweather/webapi/JarkartaWeather?latitude="
                    + l.getLatitude() + "&longitude=" + l.getLongitude();
            Client client = ClientBuilder.newClient();
            String response = client.target(serviceURL)
                    .request(MediaType.TEXT_PLAIN)
                    .get(String.class);
            // Enregistrement du message météo dans la variable weatherMessage
          this.weatherMessage =response;
        }
       
    }
    
    public void updateWeatherMessage(AjaxBehaviorEvent event) {
        
        Lieu lieu=lieuEntrepriseBean.trouverLieuParId(selectedLieu);
        this.fetchWeatherMessage(lieu);
    }
 public String getWeatherMessage() {
        return weatherMessage;
    }
    public void setWeatherMessage(String weatherMessage) {
        this.weatherMessage = weatherMessage;
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

        private String getDescription() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private int getId() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
