/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Club {
    protected List<Partner> partners;
    
    public Club(){
        this.partners = new ArrayList<>();
    }
    
    public boolean addPartner(Partner partner){
        if(partners.size()>=35){
            System.out.println("El club ha alcanzado el máximo de 35 socios");
            return false;
        }
        for(Partner p: partners){
            if(p.getCedula().equals(partner.getCedula())){
                System.out.println("Ya existe un socio con ese número de cédula");
                return false;
            }
        }
        if (partner.isVIP() && countVips()>=3){
            System.out.println("El club ha alcanzado el máximo de socios de tipo VIP");  
            return false;
        }
        return partners.add(partner);
    }
    
    public int countVips(){
        int count = 0;
        
        for (Partner p: partners){
            if(p.isVIP()){
                count = count + 1;
            }
        }
        return count;
    }
    
    public String searchPartner(String cedula){
        for (Partner p: partners){
            if(p.getCedula().equals(cedula)){
                return p.getCedula();
            }
        }
        return null;
        
    }
    public boolean login(String cedula){
        for (Partner p: partners){
            if(p.getCedula().equals(cedula)){
                return true;
            }
        }
        return false;
    }
    
    
}

