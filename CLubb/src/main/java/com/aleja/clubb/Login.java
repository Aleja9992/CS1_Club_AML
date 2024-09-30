/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aleja.clubb;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 *
 * @author Usuario
 */
@Entity
public class Login implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; 
    private String user;
    private String passaword;
    
    
    public Login(){
        
    }

    public Login(int id, String user, String passaword) {
        this.id = id;
        this.user = user;
        this.passaword = passaword;
        
    }   

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the passaword
     */
    public String getPassaword() {
        return passaword;
    }

    /**
     * @param passaword the passaword to set
     */
    public void setPassaword(String passaword) {
        this.passaword = passaword;
    }

}

   
  
  
    
