/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helloword.mavenproject2.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;

/**
 *
 * @author cons
 */
@Named (value = "NavigationBean")
@RequestScoped
public class NavigationBean {
        public void voirLieu(){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("pages/lieu.xhtml");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        
        public void voirHome(){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        
                public void visiterLieu(){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("pages/guide.xhtml");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        
        public void voirApropos(){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("pages/a_propos.xhtml");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
}
