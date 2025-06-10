/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur;
import Conjugueur.M.*;
import Conjugueur.C.*;


public class ConjugHié {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (DEBUG) System.out.println("Conjugueur.ConjugHié::main") ;
        Conjugueur.M.ConjugHié modèle = new Conjugueur.M.ConjugHié();
        Conjugueur.C.ConjugHié contrôleur = new Conjugueur.C.ConjugHié(modèle);
        contrôleur.displayVues(); 
    }
    private static final boolean DEBUG = true;    
}
