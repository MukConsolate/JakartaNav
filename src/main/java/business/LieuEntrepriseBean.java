/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package business;

import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import com.helloword.mavenproject2.beans.LieuBean.Lieu;

/**
 *
 * @author cons
 */
@Stateless
@LocalBean
public class LieuEntrepriseBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void ajouterLieuEntreprise(String nom_lieu, String description, String latitude, String longitude) {
        Lieu lieu = new Lieu(nom_lieu, description, latitude, longitude);
        em.persist(lieu);
    }

    public List<Lieu> listerTousLesLieux() {
        return em.createQuery("SELECT L FROM Lieu L", Lieu.class).getResultList();
    }

    @Transactional
    public void supprimerLieu(int id) {
        Lieu lieu = em.find(Lieu.class, id);
        if (lieu != null) {
            em.remove(lieu);
        }
    }

        @Transactional
    public void modifierLieuEntreprise(int id, String nom_lieu, String description, String latitude, String longitude) {
        Lieu lieu = em.find(Lieu.class, id);
        if (lieu != null) {
//            lieu.setNom(nom_lieu);
//            lieu.setDescription(description);
//            lieu.setLatitude(latitude);
//            lieu.setLongitude(longitude);
            em.merge(lieu); // Fusionne les modifications avec la base de données
        }
    }
    
    public Lieu trouverLieuParId(int id) {
        return em.find(Lieu.class, id);
    }
}
