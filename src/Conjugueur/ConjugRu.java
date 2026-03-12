/* Programme principal du conjugueur russe
 * Nécessite les *.jar icu4j-67_1.jar et jdom2-2.0.6.jar polices ParaType
 * @author MichelANTOINE@hotmail.com
*/
package Conjugueur;
import java.awt.GraphicsEnvironment;


/**
 * Structure MMV inspirée de https://baptiste-wicht.developpez.com/tutoriels/conception/mvc/
 */
public class ConjugRu {

    /**
     * Fctions appelées Conjugueur.M.ConjugRu::ConjugRu, Conjugueur.C.ConjugRu::ConjugRu
     * Algorithme
     * 1.crée un nouveau modèle en appelant Conjugueur.M.ConjugRu
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (DEBUG) { System.out.println("Conjugueur.ConjugRu::main") ; }
        boolean found = false ;
        for(String name:GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()){
            if (name.equals(NOMPOLICY)) {found = true ; break ;}
        }
        if (!found) { System.out.println("la police " + NOMPOLICY + " n'est pas installée !") ; return ;    }
        Conjugueur.M.ConjugRu modèle = new Conjugueur.M.ConjugRu(
                Conjugueur.M.ConjugRu.RUM.Indicatif.im(),                       // mode
                Conjugueur.M.ConjugRu.RUT.Futur.it(),                     // temps
                Conjugueur.M.ConjugRu.V.Active.iv(),                          // voix
                Conjugueur.M.ConjugRu.cbVerbeM.getSize()/2,                     // iVerbe
                Conjugueur.M.ConjugRu.cbAutreMotM.getSize()/2,
                Conjugueur.M.ConjugRu.N.Singulier.in());
        Conjugueur.C.ConjugRu contrôleur = new Conjugueur.C.ConjugRu(modèle);
        contrôleur.displayVues(); 
    }    
    public  static final String NOMPOLICY = "PT Mono" ;
    private static final boolean DEBUG = false ;    
}
