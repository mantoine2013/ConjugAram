/* Programme principal du conjugueur syriaque
 * Nécessite les *.jar icu4j-67_1.jar et jdom2-2.0.6.jar
 */
package Conjugueur;

import Conjugueur.M.ConjÉtatSyriM;
import Conjugueur.M.Conjug;
import java.awt.*;
import javax.swing.*;

public final class ConjugSyri extends JPanel  {
                         
       
    public static void main(String[] args) {
        if (DEBUG) System.out.println("Conjugueur.ConjugSyri::main") ;
        boolean found = false ;
        for(String name:GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()){
            if (name.equals("Serto Jerusalem")) {found = true ; break ;}
        }
        if (!found) { System.out.println("la police Serto Jerusalem n'est pas installée !") ; return ;    }
        found = false ;
        for(String name:GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()){
            if (name.equals("Estrangelo Edessa")) {found = true ; break ;}
        }
        if (!found) { System.out.println("la police Estrangelo Edessa n'est pas installée !") ; return ;    }
        if (args.length == 1) defargs = args ;                                  // des arguments sont là                                                                     // les arguments n'étaient pas là

        Conjugueur.M.ConjugSyri modèle = new Conjugueur.M.ConjugSyri(
                Conjug.M.Indicatif.im(),                                        // mode
                    /*temps*/Conjugueur.M.ConjugSyri.SYT.Inaccompli.it(),
                    /*voix*/Conjugueur.M.ConjugSyri.V.Active.iv(),
                    10/*cbVerbeM.getSize()/2*/,                                       // iVerbe
                Conjugueur.M.ConjugSyri.SYF.Serto.ifo(),
                Conjugueur.M.ConjugSyri.SYS.Peal.is()
        );
        Conjugueur.C.ConjugSyri contrôleur = new Conjugueur.C.ConjugSyri(modèle);
        contrôleur.displayVues(); 
    }                                                                           // main


    public static String defargs[] =  {"Résultat"} ;

    public enum SYÉ {                                                          // écriture / police
        MS((short)0, "Masc. sing.", new String []{/*Emphatique*/"ܳܐ", /*construit*/"", /*absolu*/""}), MP((short)1, "Masc. plur.", new String []{/*Emphatique*/"ܶܐ", /*construit*/"ܝ", /*absolu*/"ܝܢ"}), FS((short)2, "Fém. sing.", new String []{/*Emphatique*/"ܬܰܐ", /*construit*/"ܬ݂", /*absolu*/"ܐ"}), FP((short)3, "Fém. plur.", new String []{/*Emphatique*/"ܬ݂ܳܐ", /*construit*/"ܬ݂", /*absolu*/"ܢ"}) ;
        private final short ié;
        private final String lc ;
        private final String [] suffixes ;                                     // préfixe et suffixe par schème

        public short ié() { return ié; }    
        SYÉ (short ié, String lc, String [] suffixes) { this.ié = ié ; this.lc = lc ; this.suffixes = suffixes ; }
        public String lc() { return lc; }
        public String spip() { return suffixes [ConjÉtatSyriM.getÉtat().iét()] ; }          // renvoie le suffixe d'un groupe donné   
    }                                                                           // SYÉ
    
    public static final boolean DEBUG = true;
}                                                                                  // ConjugSyri
