/** 
 * Michel ANTOINE
 * Programme principal du conjugueur Sanscrit
 * Nécessite les *.jar icu4j-67_1.jar et jdom2-2.0.6.jar
 */
package Conjugueur;
import Conjugueur.M.*;
import java.awt.GraphicsEnvironment;


/**
 * Structure MMV inspirée de https://baptiste-wicht.developpez.com/tutoriels/conception/mvc/
 * Algorithme
 * 1. crée un nouveau modèle
 * 2. crée un nouveau contrôleur en lui passant le modèle
 * 3. demande au contrôleur d'afficher les vues. 
 */
public class ConjugSans   {

    public static void main(String[] args) {
        if (DEBUG) System.out.println("Conjugueur.ConjugSans::main") ;
        boolean found = false ;
        for(String name:GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()){
            if (name.equals(NOMPOLICOPT)) {found = true ; break ;}
        }
        if (!found) { System.out.println("la police "+NOMPOLICOPT+" n'est pas installée !") ; return ;    }
        Conjugueur.M.ConjugSans modèle = new Conjugueur.M.ConjugSans(
                   Conjug.M.Indicatif.im(),
                    Conjugueur.M.ConjugSans.T.Présent.it(),
                    Conjugueur.M.ConjugSans.V.Active.iv(),
                    Conjugueur.M.ConjugSans.cbVerbeM.getSize()/2
        );
        Conjugueur.C.ConjugSans contrôleur = new Conjugueur.C.ConjugSans(modèle);
        contrôleur.displayVues(); 
    }

    public  static final String NOMPOLICOPT = "Dekko" ;
    private static final boolean DEBUG = true;
}                                                                                // ConjugSans
