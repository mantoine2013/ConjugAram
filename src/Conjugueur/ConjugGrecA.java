/* Programme principal du conjugueur latin
 * Nécessite les *.jar icu4j-67_1.jar et jdom2-2.0.6.jar
 */
package Conjugueur;
import javax.swing.*;
import javax.swing.text.BadLocationException;



public class ConjugGrecA extends JPanel  {

    public static void main(String[] args) throws BadLocationException {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
            Conjugueur.M.ConjugGrecA modèle = new Conjugueur.M.ConjugGrecA(
                    Conjugueur.M.ConjugGrecA.M.Indicatif.im(),                  // mode
                    Conjugueur.M.ConjugGrecA.GAT.Futur.it(),                // thème
                    Conjugueur.M.ConjugGrecA.V.Active.iv(),                     // voix
                    0/*Conjugueur.M.ConjugGrecA.cbVerbeM.getSize()/2*/,
                    Conjugueur.M.ConjugGrecA.cbAutreMotM.getSize()/2,
                    Conjugueur.M.ConjugGrecA.N.Singulier.in());
            Conjugueur.C.ConjugGrecA contrôleur = new Conjugueur.C.ConjugGrecA(modèle);                      
            contrôleur.displayVues(); 
    }


    // indicateur de liste déroulantes
    public final static String AUTREMOT= "A", MODE = "M", NOMBRE = "N", VERBE = "V", VOIX = "VO" ;
    public  static  GAD itype  ;                                                      // N° de groupe
    public  static String forme[] = new String [5] ;

   // Déclinaisons https://fr.wikipedia.org/wiki/D%C3%A9clinaisons_en_latin
    public enum GAC { 
        Nominatif (0, new String [][][]{/*féminin*/{/*PreDeclᾱαςf*/{/*sing*/"ᾱ",/*duel*/"ᾱ",/*plur*/"αί"},/*PreDeclηf*/{/*sing*/"η",/*duel*/"ᾱ",/*plur*/"αι"},/*PreDeclᾱηςf*/{/*sing*/"α",/*duel*/"ᾱ",/*plur*/"ēs"},/*PreDeclαςουf*/{/*sing*/"ας",/*duel*/"",/*plur*/"ēs"},/*PreDeclm*/{/*sing*/"ας",/*duel*/"ᾱ",/*plur*/"αι"},/*DeuDeclm*/{/*sing*/"ος",/*duel*/"ᾱ",/*plur*/"οι"},/*DeuDecln*/{/*sing*/"ον",/*duel*/"ᾱ",/*plur*/"οι"},/*TroisDecl*/{/*sing*/"ξ",/*duel*/"ᾱ",/*plur*/"οι"}}}),
        Vocatif(1, new String [][][]{/*féminin*/{/*PreDeclᾱαςf*/{/*sing*/"ᾱ",/*duel*/"ᾱ",/*plur*/"αί"},/*PreDeclηf*/{/*sing*/"η",/*duel*/"ᾱ",/*plur*/"αι"},/*PreDeclᾱηςf*/{/*sing*/"α",/*duel*/"ᾱ",/*plur*/"ēs"},/*PreDeclαςουf*/{/*sing*/"α",/*duel*/"",/*plur*/"ēs"},/*PreDeclm*/{/*sing*/"α",/*duel*/"ᾱ",/*plur*/"αι"},/*DeuDeclm*/{/*sing*/"ε",/*duel*/"ᾱ",/*plur*/"οι"},/*DeuDecln*/{/*sing*/"ον",/*duel*/"ᾱ",/*plur*/"οι"},/*TroisDecl*/{/*sing*/"ξ",/*duel*/"ᾱ",/*plur*/"οι"}}}),
        Accusatif(2, new String [][][]{/*féminin*/{/*PreDeclᾱαςf*/{/*sing*/"άν",/*duel*/"ᾱ",/*plur*/"άς"},/*PreDeclηf*/{/*sing*/"ην",/*duel*/"ᾱ",/*plur*/"ας"},/*PreDeclᾱηςf*/{/*sing*/"αν",/*duel*/"ᾱ",/*plur*/"ēs"},/*PreDeclαςουf*/{/*sing*/"ίαν",/*duel*/"",/*plur*/"ēs"},/*PreDeclm*/{/*sing*/"αν",/*duel*/"ᾱ",/*plur*/"ᾱς"},/*DeuDeclm*/{/*sing*/"ον",/*duel*/"ᾱ",/*plur*/"ους"},/*DeuDecln*/{/*sing*/"ον",/*duel*/"ᾱ",/*plur*/"οι"},/*TroisDecl*/{/*sing*/"κᾰ",/*duel*/"ᾱ",/*plur*/"οι"}}}),
        Génitif(3, new String [][][]{/*féminin*/{/*PreDeclᾱαςf*/{/*sing*/"ᾶς",/*duel*/"αιν",/*plur*/"ῶν"},/*PreDeclηf*/{/*sing*/"ης",/*duel*/"αιν",/*plur*/"ῶν"},/*PreDeclᾱηςf*/{/*sing*/"ης",/*duel*/"ᾱ",/*plur*/"ium"},/*PreDeclαςουf*/{/*sing*/"ίου",/*duel*/"",/*plur*/"ēs"},/*PreDeclm*/{/*sing*/"ου",/*duel*/"αιν",/*plur*/"ῶν"},/*DeuDeclm*/{/*sing*/"ου",/*duel*/"ᾱ",/*plur*/"ων"},/*DeuDecln*/{/*sing*/"ου",/*duel*/"ᾱ",/*plur*/"οι"},/*TroisDecl*/{/*sing*/"κος",/*duel*/"ᾱ",/*plur*/"οι"}}}),
        Datif(4, new String [][][]{/*féminin*/{/*PreDeclᾱαςf*/{/*sing*/"ᾷ ",/*duel*/"αιν",/*plur*/"αῖς"},/*PreDeclηf*/{/*sing*/"ῃ",/*duel*/"αιν",/*plur*/"αις"},/*PreDeclᾱηςf*/{/*sing*/"ῃ",/*duel*/"ᾱ",/*plur*/"ibus"},/*PreDeclαςουf*/{/*sing*/"ίᾳ",/*duel*/"",/*plur*/"ēs"},/*PreDeclm*/{/*sing*/"ίᾳ",/*duel*/"αιν",/*plur*/"αις"},/*DeuDeclm*/{/*sing*/"ῳ",/*duel*/"ᾱ",/*plur*/"οις"},/*DeuDecln*/{/*sing*/"ῳ",/*duel*/"ᾱ",/*plur*/"οι"},/*TroisDecl*/{/*sing*/"κῐ",/*duel*/"ᾱ",/*plur*/"οι"}}}); 
        private final int ic;
        private final String [][][] suffixes ;                                     // préfixe et suffixe par schème
        GAC (int ic, String [][][] suffixes) { this.ic = ic ;   this.suffixes = suffixes ;}
        public int ic() { return ic; }    
        public String spip(int ig, int it, int in) {return suffixes [ig][it][in] ; }          // renvoie le suffixe d'un groupe donné   
    }                                                                           // GAC

    public enum GAD {PreDeclᾱαςf((short)0), PreDeclηf((short)1), PreDeclᾱηςf((short)2), PreDeclαςουf((short)3), PreDeclm((short)4), DeuDeclm((short)5), DeuDecln((short)6), TroisDecl((short)7);
        private final short id;
        GAD (short id) { this.id = id ;  }
        public short id() { return id; }    
    }                                                                           // GAD
/*    public enum GAG{Féminin((short)0), Duel((short)1), Masculin((short)2); 
        private final short ig;
        GAG (short ig) { this.ig = ig ;  }
        public short ig() { return ig; }            
    }              */                                                             // GAG

    public static final boolean DEBUG = false;
}                                                                                // ConjugGrecA
