/** 
 * Michel ANTOINE
 * Programme principal du conjugueur latin
 * Nécessite les *.jar icu4j-67_1.jar et jdom2-2.0.6.jar
 */
package Conjugueur;

import Conjugueur.M.Conjug;


/**
 * Structure MVC inspirée de https://baptiste-wicht.developpez.com/tutoriels/conception/mvc/
 */
public class ConjugLat   {

    /**
     * Fctions appelées Conjugueur.M.ConjugLat::ConjugLat, Conjugueur.C.ConjugLat::ConjugLat
     * Algorithme
     * 1.crée un nouveau modèle
     * 2. crée un nouveau contrôleur en lui passant le modèle
     * 3. demande au contrôleur d'afficher les vues.
     * @param args doit rester vide
     */
    public static void main(String[] args) {
        if (DEBUG) { System.out.println("Conjugueur.ConjugLat::main") ; }
        Conjugueur.M.ConjugLat modèle = new Conjugueur.M.ConjugLat(
                   Conjug.M.Indicatif.im(),                                     // mode
                    Conjugueur.M.ConjugLat.LAT.Présent.it(),                  // temps
                    Conjugueur.M.ConjugLat.V.Active.iv(),                       // voix
                    6   /*Conjugueur.M.ConjugLat.cbVerbeM.getSize()/2*/,                                        // iVerbe
                    60  /*Conjugueur.M.ConjugLat.cbAutreMotM.getSize()/2*/,
                Conjugueur.M.Conjug.N.Singulier.in()
        );
        Conjugueur.C.ConjugLat contrôleur = new Conjugueur.C.ConjugLat(modèle);
        contrôleur.displayVues(); 
    }                                                                           // main

    public static final boolean DEBUG = false ;
}                                                                                // ConjugLat
