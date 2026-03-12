/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import Conjugueur.V.JFrameConjugAraM;
import static Conjugueur.V.JFrameConjugAraM.ARABE;
import com.ibm.icu.text.Transliterator;
import Conjugueur.V.DéclinaisonStyledDoc;
import static Conjugueur.V.JFrameConjugAraM.PRÉFIXE;
import static Conjugueur.V.JFrameConjugAraM.SUFFIXE;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

public class ConjTableAraM extends ConjTable  {

    /**
     * Fonction appelante : Conjugueur.Vue.JFrameConjugAraM::JFrameConjugAraM
     * Algorithme : 
     * 1. Remettre le tableau de conjugaison à zéro
     * 2. S'aiguiller selon le temps
     * 2.1 Voix active
     * 2.1.1 S'aiguiller selon 
     * 2.1.1.1 Temps Passé
     * 2.1.1.2 Temps Inaccompli
     * 2.1.1.2.1 Si le verbe est régulier alors
     * 2.1.1.2.1.1  Créer une ligne pour chaque déclinaison et y placer les libellés des personnes à conjuguer : masc sing, masc plur, fém sing, fém plur en appelant la fction "AjouterPersonnes ()"
     * 2.1.1.2.1.2 S'il n'y a pas de présent particuler alors
     * 2.1.1.2.1.2.1 Pour ts les caractères de l'infinitif faire
     * 2.1.1.2.1.2.1.1 Si c'est une consonne alors
     * 2.1.1.2.1.2.1.1.1 Incrémenter compteur de consonnes
     * 2.1.1.2.1.2.1.1.2 Insérer un "וֹ" en 2è position s'il n'est pas déjà présent
     * 2.1.1.2.1.2.1.1.3 Copier le caractère en appelant "CopierCaractèRacine"
     * 2.3 Groupe Piel
    * 2.3.1 S'aiguiller selon le temps       
     * 2.3.1 Temps Passé
     * 2.3.2 Temps Inaccompli
     * 2.3.2.1  Créer une ligne pour chaque déclinaison et y placer les libellés des personnes à conjuguer : masc sing, masc plur, fém sing, fém plur en appelant la fction "AjouterPersonnes ()"
     * 2.7 Groupe Piel
    * 2.7.1 S'aiguiller selon le temps       
     * 2.7.1 Temps Passé
     * 2.7.2 Temps Inaccompli
     * 2.7.2.1  Créer une ligne pour chaque déclinaison et y placer les libellés des personnes à conjuguer : masc sing, masc plur, fém sing, fém plur en appelant la fction "AjouterPersonnes ()"
     */
    @Override
    public void MAJConj (Conjugueur.C.Conjug contrôleur) {
       if (DEBUG) { System.out.println ("Conjugueur.M.ConjTableAraM::MAJConj, Verbe = " + ConjugAraM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugAraM.ATTRIBUT) + ", Mode = " + Conjugueur.M.ConjugLat.M.i2M(contrôleur.model.getMode()).toString() + ", Temps = " + Conjugueur.M.ConjugAraM.ART.i2T(contrôleur.model.getTemps()).toString() + ", contrôleur.model.getVoix() = " + contrôleur.model.getVoix()) ; }
        this.contrôleur = contrôleur ; 
        try {
            vider ();
            switch (Conjugueur.M.ConjugLat.M.i2M(contrôleur.model.getMode())) {                                          // mode
                case Indicatif -> {                                                  // Indicatif, Subjonctif
                    déclinaisonChildren =  ConjugAraM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getChildren("déclinaison");
                    switch (Conjugueur.M.ConjugAraM.ART.i2T(contrôleur.model.getTemps())) {                             // temps
                        case Accompli -> {
                            for ( ARP personne :  ARP.values()) {               // pour ttes les personnes
                                if ( déclinaisonChildren.isEmpty()) {             // verbe régulier
                                    inbLignes = AjouterLigne(personne.ip(), personne.lp(),  ARABE, StyleConstants.ALIGN_RIGHT) ;
                                    DéclinerRégulièrement (personne) ;    
                                }                                                   // verbe régulier
                                AjouterTranslittération(inbLignes, araMToLatinTrans) ;
                            }                                                               // ttes les personnes                                                                             
                        }                                                       // Accompli
                        case Inaccompli -> {
                            for ( ARP personne :  ARP.values()) {               // pour ttes les personnes
                                switch (Conjugueur.M.ConjugAraM.V.i2V(contrôleur.model.getVoix())){                                     // Voix
                                    case Active -> {
                                        if ( déclinaisonChildren.isEmpty()) {             // verbe régulier
                                            inbLignes = AjouterLigne(personne.ip(), personne.lp(),  ARABE, StyleConstants.ALIGN_RIGHT) ;
                                            DéclinerRégulièrement (personne) ;    
                                        }                                                   // verbe régulier
                                    }                                           
                                    case Passive -> {
                                        if ( déclinaisonChildren.isEmpty()) {             // verbe régulier
                                            inbLignes = AjouterLigne(personne.ip(), personne.lp(),  ARABE, StyleConstants.ALIGN_RIGHT) ;
                                            DéclinerRégulièrement (personne) ;    
                                        }                                                   // verbe régulier
                                    }
                                }                                                           // // Voix
                                AjouterTranslittération(inbLignes, araMToLatinTrans) ;
                            }                                                               // ttes les personnes                                                                             
                        }                                                               // case Inaccompli
                        case Futur -> {
                            AjouterPersonnes () ;                                // remplit la col. gauche avec les personnes
                                Préfixer( IL_AR1S, "سأَ") ; Préfixer( IL_AR2MS, "ستَ") ; Préfixer( IL_AR2FS, "ستََ") ; Préfixer( IL_AM3MS, "سيَ") ; Préfixer( IL_AM3FS, "ستَ") ; Préfixer( IL_AM2D, "ستَ") ; Préfixer( IL_AM3D, "ست") ;
                                CopieRacine(ConjugAraM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("Infinitif")) ;
                                Suffixer( IL_AR2FS, "ينَ")  ; Suffixer( IL_AM2D, "انِ")  ; Suffixer( IL_AM3D, "انِ")  ;
                        }
                    }                                                                   // switch (Temps)
                }                                                               // Indicatif, Subjonctif
                case Impératif -> {                                                     // Impératif
                    for ( ARP personne :  new ARP[]{ARP.AR2MS, ARP.AR2FS, ARP.AM2D, ARP.AR2MP, ARP.AR2FP}) {               // pour ttes les personnes
                        inbLignes = AjouterLigne(personne.ip(), personne.lp(), ARABE, StyleConstants.ALIGN_RIGHT) ;
                        if (!personne.ppiyi(Conjugueur.M.Conjug.M.i2M(contrôleur.model.getMode()), Conjugueur.M.ConjugAraM.ART.i2T(contrôleur.model.getTemps())).isEmpty())  Préfixer(personne.ppiyi(Conjugueur.M.Conjug.M.i2M(contrôleur.model.getMode()), Conjugueur.M.ConjugAraM.ART.i2T(contrôleur.model.getTemps()))) ;
                        CopieRacine(ConjugAraM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugAraM.ATTRIBUT)) ; 
                        if (!personne.spip(Conjugueur.M.Conjug.M.i2M(contrôleur.model.getMode()), Conjugueur.M.ConjugAraM.ART.i2T(contrôleur.model.getTemps())).isEmpty())   Suffixer (ConjTable.inbLignes, personne.spip(Conjugueur.M.Conjug.M.i2M(contrôleur.model.getMode()), Conjugueur.M.ConjugAraM.ART.i2T(contrôleur.model.getTemps()))) ;
                        AjouterTranslittération(inbLignes, araMToLatinTrans) ;
                    }                                                               // ttes les personnes                                                                             
                }                                                               // Impératif
            
            }                                                                   // mode
        } catch (BadLocationException ble) {}
//        updateRowHeights(ConjugAraModèle.jTabConj) ;
    }                                                                           // updateTextPaneConj
    
    /**
     * Fonction : Ajoute les libellés des personnes à conjuguer : masc sing, masc plur, fém sing, fém plur
     * Algorithme
     * 1.1 Mettre le 1er libellé en place
     * 1.2 Insèrer une 1ère ligne ds le tableau de conjugaison "tabLigneConj" en appelant AjouterPers et son libellé ds la colonne "personne"
     * 1.3 mettre ds la colonne "déclinaison" de cette 1ère ligne le libellé du 1er pronom personnel pour l'hébreu moderne
     * 1.4 ajouter ds la colonne "déclinaison" de cette 1ère ligne une barre oblique "/"
     * 1.5  ajouter ds la colonne "déclinaison" de cette 1ère ligne  le libellé du 2è pronom personnel pour l'hébreu moderne
     * 1.6 ajouter ds la colonne "déclinaison" de cette 1ère ligne une 2è barre oblique "/"
     * 1.7 ajouter ds la colonne "déclinaison" de cette 1ère ligne  le libellé du 3è pronom personnel pour l'hébreu moderne
     * 2 Même chose pour la 2è ligne
     * 3 Même chose pour la 3è ligne
     * 4 Même chose pour la 4è ligne
    */
    private void AjouterPersonnes ()  throws BadLocationException {
        for (ARP personne : ARP.values()) {                             // pour ttes les personnes
            inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugAraM.ARABE, StyleConstants.ALIGN_RIGHT) ;         AjouterPers(IL_AR1S) ; CopierPersonne (IL_AR1S, AMPP1MS) ;
        }                                           // ttes les personnes                      }                                               // verbe régulier
    }                                                                            // AjouterPersonnes    
       
    /**
     * Fonction : remplit la colonne de déclinaisons régulière du mode indicatif
     * Fction appelante : ConjTableAraM::MAJConj
     * Algorithme
     * 1.Copier le libellé du Pronom Personnel : "أنا ", "أنتَ "
 2. Si le préfixe existe, l'ajouter
     * @param personne : AR1S,AR2MS, AR2FS, ...  
     */
    public void DéclinerRégulièrement(ARP personne) throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTableAraM::DéclinerRégulièrement, personne = " + personne.lp+", Verbe = " + ConjugAraM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugAraM.ATTRIBUT) + ", Mode = " + Conjugueur.M.ConjugLat.M.i2M(contrôleur.model.getMode()).toString() + ", Temps = " + Conjugueur.M.ConjugAraM.ART.i2T(contrôleur.model.getTemps()).toString()) ; }
        Copier(personne.lap(), DéclinaisonStyledDoc.stylepp);  Espace () ;
        if (!personne.ppiyi(Conjugueur.M.Conjug.M.i2M(contrôleur.model.getMode()), Conjugueur.M.ConjugAraM.ART.i2T(contrôleur.model.getTemps())).isEmpty())  Préfixer(personne.ppiyi(Conjugueur.M.Conjug.M.i2M(contrôleur.model.getMode()), Conjugueur.M.ConjugAraM.ART.i2T(contrôleur.model.getTemps()))) ;
        CopieRacine(ConjugAraM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugAraM.ATTRIBUT)) ; 
        if (!personne.spip(Conjugueur.M.Conjug.M.i2M(contrôleur.model.getMode()), Conjugueur.M.ConjugAraM.ART.i2T(contrôleur.model.getTemps())).isEmpty())   Suffixer (ConjTable.inbLignes, personne.spip(Conjugueur.M.Conjug.M.i2M(contrôleur.model.getMode()), Conjugueur.M.ConjugAraM.ART.i2T(contrôleur.model.getTemps()))) ;

     }
    public enum ARP {                                                          // constantes définissant l'ordre des personnes
        AR1S    ((short)0, "1è. p. s. : ", "أنا ", new String [][][]{/*Indicatif*/{/*Inaccompli*/{"أَ", ""},/*Accompli*/{"", "تُ"}}, /*Subjonctif*/{{"َ", ""},{"", ""}}, /*Impératif*/{}}),
        AR2MS   ((short)1, "2è. m. s. : ", "أنتَ ", new String [][][]{/*Indicatif*/{/*Inaccompli*/{"تَ", ""},/*Accompli*/{"", "تَ"}}, /*Subjonctif*/{{"َ", ""},{"", ""}}, /*Impératif*/{{"اَِ", ""}, {"اَِ", ""}}}),
        AR2FS   ((short)2, "2è. f. s. : ", "أنتِ", new String [][][]{/*Indicatif*/{/*Inaccompli*/{"تََ", "ينَ"},/*Accompli*/{"", "تِ"}}, /*Subjonctif*/{{"َ", ""},{"", ""}}, /*Impératif*/{{"اَِ", "ي"},{"اِ", "ي"}}}),
        AM3MS   ((short)3, "3è. m. s. : ", "هو ", new String [][][]{/*Indicatif*/{/*Inaccompli*/{"يَ", ""},/*Accompli*/{"", ""}}, /*Subjonctif*/{{"َ", ""},{"", ""}}, /*Impératif*/{}}),
        AM3FS   ((short)4, "3è. f. s. : ", "هي ", new String [][][]{/*Indicatif*/{/*Inaccompli*/{"تَ", ""},/*Accompli*/{"", "تْ"}}, /*Subjonctif*/{{"َ", ""},{"", ""}}, /*Impératif*/{}}),
        AM2D    ((short)5, "2è. duel : ", "أَنْتُما  ", new String [][][]{/*Indicatif*/{/*Inaccompli*/{"تَ", "انِ"},/*Accompli*/{"", "تُما"}}, /*Subjonctif*/{{"َ", ""},{"", ""}}, /*Impératif*/{{"َ", "لا"},{"", "لا"}}}),
        AM3DM    ((short)6, "3è. d. m. : ", "هُمَا ", new String [][][]{/*Indicatif*/{/*Inaccompli*/{"تَ", "انِ"},/*Accompli*/{"", "لا"}}, /*Subjonctif*/{{"َ", ""},{"", ""}}, /*Impératif*/{{"َ", ""},{"", ""}}}),
        AM3DF    ((short)7, "3è. d. f : ", "هُمَا ", new String [][][]{/*Indicatif*/{/*Inaccompli*/{"تَ", "انِ"},/*Accompli*/{"", "تا"}}, /*Subjonctif*/{{"َ", ""},{"", ""}}, /*Impératif*/{{"َ", ""},{"", ""}}}),
        AM1P    ((short)8, "1è. p. p. : ", "أَنْتُمْ  ", new String [][][]{/*Indicatif*/{/*Inaccompli*/{"نَ", ""},/*Accompli*/{"", "نا"}}, /*Subjonctif*/{{"َ", ""},{"", ""}}, /*Impératif*/{}}),
        AR2MP   ((short)9, "2è. m. p. : ", "أَنْتُمْ  ", new String [][][]{/*Indicatif*/{/*Inaccompli*/{"نَ", "ون"},/*Accompli*/{"", "تُم"}}, /*Subjonctif*/{{"َ", ""},{"", ""}}, /*Impératif*/{{"اَِ", "وا"},{"اِ", "وا"}}}),
        AR2FP   ((short)10, "2è. f. p. : ", "أَنْتُنَّ  ", new String [][][]{/*Indicatif*/{/*Inaccompli*/{"ي", "ن"},/*Accompli*/{"", "تُنَّ"}}, /*Subjonctif*/{{"َ", ""},{"", ""}}, /*Impératif*/{{"اَِ", "نَ"},{"اِ", "نَ"}}}),
        AM3MP   ((short)11, "3è. m. p.", "هُمْ ", new String [][][]{/*Indicatif*/{/*Inaccompli*/{"ي", "ون"},/*Accompli*/{"", "وا"}}, /*Subjonctif*/{{"َ", ""},{"", ""}}, /*Impératif*/{}}),
        AM3FP   ((short)12, "3è. f. p.", "هُنَّ  ", new String [][][]{/*Indicatif*/{/*Inaccompli*/{"ي", "ن"},/*Accompli*/{"", "نَ"}}, /*Subjonctif*/{{"َ", ""},{"", ""}}, /*Impératif*/{}}) ;
        private final short ip ;                                                  // indice de personne 0, 1, 2,...
        public final String lp ;                                               // libellé de personne "1è. s.", "2è. m. s."
        private final String lap ;                                              // PP arabeu
        private final String [/*mode*/][/*temps*/][] PairePS ;                                     // préfixe et suffixe par schème
        ARP(short ip, String libpers, String lap, String [][][] PairePS) { this.ip = ip ; this.lp = libpers ; this.lap    = lap ; this.PairePS = PairePS ; }
        public short ip() { return ip; }
        public String lp() { return lp; }
        public String lap() { return lap; }
        public String spip(Conjugueur.M.Conjug.M im, Conjugueur.M.ConjugAraM.ART it) { return PairePS [im.im()][it.it()][SUFFIXE] ; }          // renvoie le suffixe d'un schème donné
        public String ppiyi(Conjugueur.M.Conjug.M im, Conjugueur.M.ConjugAraM.ART it) { return PairePS [im.im()][it.it()][PRÉFIXE] ; }
    }                                                                           //ARP
    private final static String AMPP1MS = "أنا ", ARAM_TO_LATIN = "Arabic-Latin" ;                                      // arabe moderne pronom personnel 1ère personne masc. sing.
    private final static int IL_AR1S = 0 ;                                    // arabe moderne 1ère personne sing.
    private final static int IL_AR2MS = 1 ;                                    // arabe moderne 2ème personne masc sing
    private final static int IL_AR2FS = 2 ;                                    //   arabe moderne 2ème personne fém. sing.
    private final static int IL_AM3MS = 3 ;                                    // arabe moderne 3ème personne masc sing
    private final static int IL_AM3FS = 4 ;                                    //   arabe moderne 3ème personne fém. sing.
    private final static int IL_AM2D = 5 ;                                    //   arabe moderne 2ème personne duel
    private final static int IL_AM3D = 6 ;                                    // 1ère personne masc. sing.

    public static final Transliterator araMToLatinTrans = Transliterator.getInstance(ARAM_TO_LATIN);
    private Conjugueur.C.Conjug contrôleur ;
    private final boolean DEBUG = true ;
}
