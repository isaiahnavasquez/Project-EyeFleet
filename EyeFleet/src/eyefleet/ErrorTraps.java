/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eyefleet;

/**
 *
 * @author IsaiahJan
 */
public class ErrorTraps {

    public ErrorTraps() {
    }
    
    public boolean isNumerical(String word){
        
        try {
            double d = Double.parseDouble(word);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
}
