/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.metiersUser;

import java.util.Date;

public final class SessionUser {
    private static SessionUser instance;
    
    private static int id;
    private static String email;
    private static String roles;
    private static String password;
    private static Boolean is_verified;
    private static Date date_naissance;
    private static String nom;
    private static String prenom;
    private static String genre;
   

    private SessionUser(int id, String email, String roles, String password, Boolean is_verified,Date date_naissance,String nom,String prenom,String genre) {
        SessionUser.id = id;
        SessionUser.email = email;
        SessionUser.roles = roles;
        SessionUser.password = password;
        SessionUser.is_verified = is_verified;
        SessionUser.date_naissance = date_naissance;
        SessionUser.nom = nom;
        SessionUser.prenom = prenom;
        SessionUser.genre = genre;
    }
/********************************************************************************/
    public static SessionUser getInstace(int id, String email, String roles, String password, Boolean is_verified,Date date_naissance,String nom,String prenom,String genre) {
        if(instance == null) {
            instance = new SessionUser(id,email,roles,password,is_verified,date_naissance,nom,prenom,genre);
        }
        return instance;
    } 
/*******************************************************************************/
    public static SessionUser getInstance() {
        return instance;
    }


    public static int getId() {
        return id;
    }

    public static String getEmail() {
        return email;
    }

    public static String getRoles() {
        return roles;
    }

    public static String getPassword() {
        return password;
    }

    public static Boolean getIs_verified() {
        return is_verified;
    }

    public static Date getDate_naissance() {
        return date_naissance;
    }

    public static String getNom() {
        return nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static String getGenre() {
        return genre;
    }

/**************************************************************/
    public static void setInstance(SessionUser instance) {
        SessionUser.instance = instance;
    }

    public static void setId(int id) {
        SessionUser.id = id;
    }

    public static void setEmail(String email) {
        SessionUser.email = email;
    }

    public static void setRoles(String roles) {
        SessionUser.roles = roles;
    }

    public static void setPassword(String password) {
        SessionUser.password = password;
    }

    public static void setIs_verified(Boolean is_verified) {
        SessionUser.is_verified = is_verified;
    }

    public static void setDate_naissance(Date date_naissance) {
        SessionUser.date_naissance = date_naissance;
    }

    public static void setNom(String nom) {
        SessionUser.nom = nom;
    }

    public static void setPrenom(String prenom) {
        SessionUser.prenom = prenom;
    }

    public static void setGenre(String genre) {
        SessionUser.genre = genre;
    }

/**************************************************************/
    @Override
    public String toString() {
        return "SessionUser{"+
                "email='" + email + '\'' +
                "id='" + id + '\'' +
               
                "nom = '" + nom + '\'' +
           
                ", privileges=" + roles + '}';
    }

    
/**************************************************************/
    public static void cleanUserSession() {
      SessionUser.setInstance(null);
    }
  /**************************************************************/
  
}
