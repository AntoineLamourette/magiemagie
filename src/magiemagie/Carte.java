/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiemagie;

/**
 *
 * @author Formation
 */
public class Carte {

    @Override
    public String toString() {
        return type.toString(); 
    }
    
    public enum TypeCarte{
        
        BAVE_DE_CRAPAUD,
        AILE_DE_CHAUVE_SOURIS,
        LAPIS_LAZULI,
        MANDRAGORE,
        CORNE_DE_LICORNE
                
    }
    
    private TypeCarte type;
//Vérification que 2 obj sont égaux à partir de 2 codes d'emplacement différents
    @Override
    public boolean equals(Object obj) {
        
        Carte carteParam = (Carte) obj;
        
        if (getType() == carteParam.getType())
            return true;
        else
            return false;
        
    }

    
    
    public TypeCarte getType() {
        return type;
    }

    public void setType(TypeCarte type) {
        this.type = type;
    }
    
        
    }
