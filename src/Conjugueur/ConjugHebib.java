/* Programme principal du conjugueur hébreu biblique
 * Nécessite les *.jar icu4j-67_1.jar et jdom2-2.0.6.jar
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur;
import javax.swing.text.BadLocationException;


public final class ConjugHebib  {                    

    public static void main(String[] args) throws BadLocationException {
        if (args.length == 1) defargs = args ;                                  // des arguments sont là                                                                     // les arguments n'étaient pas là
            Conjugueur.M.ConjugHebib modèle = new Conjugueur.M.ConjugHebib(
                    Conjugueur.M.ConjugHebib.HBM.Qatal.im(),                    // mode + temps
                    Conjugueur.M.ConjugHebib.HBS.Piel.is(),                     // shème
                4/*cbVerbeM.getSize()/2*/
            );
            Conjugueur.C.ConjugHebib contrôleur = new Conjugueur.C.ConjugHebib(modèle);
            contrôleur.displayVues();
    }                                                                           // main

    public static String defargs[] =  {"Résultat"} ;    
    public final static String[] ETIPERS = new String[12] ;

    private static final boolean DEBUG = true;
}                                                                                // ConjugHebib