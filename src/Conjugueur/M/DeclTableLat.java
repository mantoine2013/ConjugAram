/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import Conjugueur.V.*;
import java.util.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

public class DeclTableLat extends ConjTable {
    
    /**
     * Fction appelante : Conjugueur.V.JFrameConjugLat::JFrameConjugLat
     * Algorithme
     *  Si la déclinaison est régulière
     * 
     */
    @Override
    public void MAJDecl(Conjugueur.C.Conjug contrôleur) {
        if (DEBUG) { System.out.println ("Conjugueur.M.DeclTableLat::MAJDecl, Mot = " + Conjugueur.M.ConjugLat.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugLat.ATTRIBUT) + ", Nombre = "+ Conjugueur.M.ConjugLat.N.i2N(contrôleur.model.getNb()).toString()) ;   }
        vider();                                                 
        try {
            déclinaisonChildren = Conjugueur.M.ConjugLat.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getChildren("déclinaison");
            if (déclinaisonChildren.isEmpty()) {                    // verbe régulier
                itype = DéterminerDéclinaison(contrôleur) ; 
                for (var cas : LAC.values()) {
                    inbLignes = AjouterLigne(cas.ic(), cas.lc(), JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
/* provisoire               if ((((cas == LAC.Nomina)||(cas == LAC.Voca)) && (Données.getNb() == LAN.Singulier))
                        ||((itype == LAD.TroiDeclParisyllabiquesNF.id())&&(cas == LAC.Accusa))
                        ||(itype == LAD.TroiDeclImparisyllabiquesMEF.id())) {        // nominatif vocatif singulier
                    for (char c : (forme[LAF.NominatifSingulier.ifo()]).substring(0, forme[LAF.NominatifSingulier.ifo()].length()).toCharArray()){
                        Copier (inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                    }                                                    // pour ts les caractères
                    if (itype == LAD.TroiDeclImparisyllabiquesMEF.id()) Suffixer (inbLignes, cas.spip(itype, Données.getNb().in())) ;
                }                                                               // nominatif singulier
                else { */
                    switch (itype) {
                        case PreDecl ->{
                            for (char c : (forme[LAF.NominatifSingulier.ifo()]).substring(0, forme[LAF.NominatifSingulier.ifo()].length()- 1).toCharArray()){
                                Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                            }                                                    // pour ts les caractères
                        }
                        case DeuDecler ->{
                            for (char c : (forme[LAF.NominatifSingulier.ifo()]).substring(0, forme[LAF.NominatifSingulier.ifo()].length()- 1).toCharArray()){
                                Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                            }                                                    // pour ts les caractères
                        }
                        case TroiDeclImparisyllabiquesMEF ->{
                            for (char c : (forme[LAF.NominatifSingulier.ifo()]).toCharArray()){
                                Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                            }                                                    // pour ts les caractères                            
                        }
                        default ->{
                            for (char c : (forme[LAF.NominatifSingulier.ifo()]).substring(0, forme[LAF.NominatifSingulier.ifo()].length()- (itype == LAD.DeuDecler ? 0 : 2)).toCharArray()){
                                Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                            }                                                    // pour ts les caractères
                            if (forme[LAF.GénitifSingulier.ifo()].length() == 3) {
                                if (forme[LAF.NominatifSingulier.ifo()].substring(forme[LAF.NominatifSingulier.ifo()].length()-2, forme[LAF.NominatifSingulier.ifo()].length()).equals(forme[LAF.GénitifSingulier.ifo()].substring(0, 2))) {
                                    for (char c : (forme[LAF.NominatifSingulier.ifo()]).substring(forme[LAF.NominatifSingulier.ifo()].length()-2, forme[LAF.NominatifSingulier.ifo()].length()).toCharArray()){
                                        Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                                    }                                                    // pour ts les caractères
                         
                                }
                            }
                        }                   
                    }
                    Suffixer (cas.spip(LAG.l2G(forme[LAF.Genre.ifo()]).ig(), itype.id(), contrôleur.model.getNb())) ;
// provisoire               }                                           // ttes les cas
            }
            }                                                       // verbe régulier
        } catch (BadLocationException ble) {        }
//        update(jTabDecl);
    }                                                                           // updateTextPaneDecl
    /**
     * Fonction : Détermine le type de déclinaison
     * Algorithme
     *  1. Prendre le nominatif singulier (equus, ...)
     *  2. Prendre le génitif (æ, ..)
     *  3. Prendre le genre (f, m ou n)
     */    
    private LAD DéterminerDéclinaison(Conjugueur.C.Conjug contrôleur)  {
        if (DEBUG) { System.out.println ("Conjugueur.M.DeclTableLat::DéterminerDéclinaison, Mot = " + Conjugueur.M.ConjugLat.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugLat.ATTRIBUT) + ", Nombre = " + Conjugueur.M.ConjugLat.N.i2N(contrôleur.model.getNb()).toString()) ; }   
        StringTokenizer tokenizer = new StringTokenizer(Conjugueur.M.ConjugLat.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugLat.ATTRIBUT), " ");
        forme[LAF.NominatifSingulier.ifo()] = tokenizer.nextToken() ;
        forme[LAF.GénitifSingulier.ifo()] = tokenizer.nextToken() ;
        forme[LAF.Genre.ifo()] = tokenizer.nextToken() ;
        if('+' == forme[LAF.GénitifSingulier.ifo()].charAt(0)) {
            switch (forme[LAF.GénitifSingulier.ifo()]) {
                case "+æ" -> { JFrameConjug.jLabelDéclinaison.setText("1ère déclinaison") ; return LAD.PreDecl ; }
                case "+is" -> {if (!"n".equals(forme[LAF.Genre.ifo()])) return LAD.TroiDeclImparisyllabiquesMEF ; else return LAD.TroiDeclImparisyllabiquesNF  ;}
                case "+ī" -> { JFrameConjug.jLabelDéclinaison.setText("2ème déclinaison") ; return LAD.DeuDeclPure ; }
                case "+eri" -> { JFrameConjug.jLabelDéclinaison.setText("2ème déclinaison") ; return LAD.DeuDecler ; }
                case "īre" -> { JFrameConjug.jLabelDéclinaison.setText("3ème déclinaison") ; return LAD.TroiDeclParisyllabiquesNF ; }
                case "+us" -> { JFrameConjug.jLabelDéclinaison.setText("4ème déclinaison") ; return LAD.QuaDecl ; }
                case "+ei" -> { JFrameConjug.jLabelDéclinaison.setText("5ème déclinaison") ; return LAD.QuaDecl ; }
            }                                                   // switch
        } else if ("is".equals(forme[LAF.GénitifSingulier.ifo()].substring(forme[LAF.GénitifSingulier.ifo()].length() - 2))) {
            if(SyllableCount(forme[LAF.NominatifSingulier.ifo()]) == SyllableCount(forme[LAF.GénitifSingulier.ifo()])) {
                JFrameConjug.jLabelDéclinaison.setText("3ème déclinaison") ; return LAD.TroiDeclParisyllabiquesMEF ;
            }
            
        } else if ("er".equals(forme[LAF.NominatifSingulier.ifo()].substring(forme[LAF.NominatifSingulier.ifo()].length() - 2))) {
                JFrameConjug.jLabelDéclinaison.setText("2ème déclinaison") ; return LAD.DeuDecler ;            
        }
        return (LAD.PreDecl) ;
    }                                                                           // DéterminerDéclinaison

    // Déclinaisons https://fr.wikipedia.org/wiki/D%C3%A9clinaisons_en_latin
    public enum LAC { 
        Nomina ((short)0, "Nominatif", new String [][][] {/*masc*/{/*1è déc*/{/*sing*/"a",/*plur*/"æ"},/*2è déc*/{/*sing*/"us",/*plur*/"ī"},/*2è déc er*/{/*sing*/"",/*plur*/"ī"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"is",/*plur*/"ēs"},/*3è déc Parisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, /*4è déc */{/*sing*/"us",/*plur*/"us"}}, /*fem*/{/*1è déc*/{/*sing*/"a",/*plur*/"æ"},/*2è déc*/{/*sing*/"um",/*plur*/"æ"},{},{},{},{},{},/*4è déc*/{/*sing*/"us",/*plur*/"us"}}, /*neu*/{/*1è déc*/{/*sing*/"um",/*plur*/"æ"},/*2è déc*/{/*sing*/"um",/*plur*/"a"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, {}, {}, {}, /*4è déc */{/*sing*/"u",/*plur*/"ua"}}}),
        Voca((short)1, "Vocatif", new String [][][] {/*masc*/{/*1è déc*/{/*sing*/"a",/*plur*/"æ"},/*2è déc*/{/*sing*/"ĕ",/*plur*/"ī"},/*2è déc er*/{/*sing*/"",/*plur*/"ī"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"is",/*plur*/"ēs"},/*3è déc Parisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, /*4è déc */{/*sing*/"us",/*plur*/"us"}}, /*fem*/{/*1è déc*/{/*sing*/"a",/*plur*/"æ"},/*2è déc*/{/*sing*/"um",/*plur*/"æ"},{},{},{},{},{},/*4è déc*/{/*sing*/"us",/*plur*/"us"}}, /*neu*/{/*1è déc*/{/*sing*/"um",/*plur*/"æ"},/*2è déc*/{/*sing*/"um",/*plur*/"a"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, {}, {}, {}, /*4è déc */{/*sing*/"u",/*plur*/"ua"}}}),
        Géni((short)3, "Génitif", new String [][][] {/*masc*/{/*1è déc*/{/*sing*/"æ",/*plur*/"ārum"},/*2è déc*/{/*sing*/"ī",/*plur*/"ōrum"},/*2è déc er*/{/*sing*/"i",/*plur*/"orum"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"is",/*plur*/"um"},/*3è déc Parisyllabiques neutres*/{/*sing*/"is",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"is",/*plur*/"um"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, /*4è déc */{/*sing*/"us",/*plur*/"uum"}}, /*fem*/{/*1è déc*/{/*sing*/"ae",/*plur*/"arum"},/*2è déc*/{/*sing*/"um",/*plur*/"æ"},{},{},{},{},{},/*4è déc*/{/*sing*/"us",/*plur*/"uum"}}, /*neu*/{/*1è déc*/{/*sing*/"ī",/*plur*/"æ"},/*2è déc*/{/*sing*/"i",/*plur*/"orum"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, {}, {}, {}, /*4è déc */{/*sing*/"us",/*plur*/"uum"}}}),
        Da((short)4, "Datif", new String [][][] {/*masc*/{/*1è déc*/{/*sing*/"æ ",/*plur*/"īs"},/*2è déc*/{/*sing*/"ō",/*plur*/"īs"},/*2è déc er*/{/*sing*/"o",/*plur*/"is"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"ī",/*plur*/"ibus"},/*3è déc Parisyllabiques neutres*/{/*sing*/"ī",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"ī",/*plur*/"ibus"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, /*4è déc */{/*sing*/"ui",/*plur*/"ibus"}}, /*fem*/{/*1è déc*/{/*sing*/"ae",/*plur*/"is"},/*2è déc*/{/*sing*/"um",/*plur*/"æ"},{},{},{},{},{},/*4è déc*/{/*sing*/"ui",/*plur*/"ibus"}}, /*neu*/{/*1è déc*/{/*sing*/"a",/*plur*/"æ"},/*2è déc*/{/*sing*/"o",/*plur*/"is"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, {}, {}, {}, /*4è déc */{/*sing*/"ui",/*plur*/"ibus"}}}), 
        Abla((short)5, "Ablatif", new String [][][] {/*masc*/{/*1è déc*/{/*sing*/"ā",/*plur*/"īs"},/*2è déc*/{/*sing*/"ō",/*plur*/"īs"},/*2è déc er*/{/*sing*/"o",/*plur*/"is"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"e",/*plur*/"ī"},/*3è déc Parisyllabiques neutres*/{/*sing*/"ī",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"e",/*plur*/"ibus"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, /*4è déc */{/*sing*/"u",/*plur*/"ibus"}}, /*fem*/{/*1è déc*/{/*sing*/"a",/*plur*/"is"},/*2è déc*/{/*sing*/"um",/*plur*/"æ"},{},{},{},{},{},/*4è déc*/{/*sing*/"u",/*plur*/"ibus"}}, /*neu*/{/*1è déc*/{/*sing*/"a",/*plur*/"æ"},/*2è déc*/{/*sing*/"o",/*plur*/"is"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, {}, {}, {}, /*4è déc */{/*sing*/"u",/*plur*/"ibus"}}}), 
        Accusa((short)6, "Accusatif", new String [][][] {/*masc*/{/*1è déc*/{/*sing*/"am",/*plur*/"ās"},/*2è déc*/{/*sing*/"um",/*plur*/"ōs"},/*2è déc er*/{/*sing*/"um",/*plur*/"os"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"em",/*plur*/"ēs"},/*3è déc Parisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"em",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, /*4è déc */{/*sing*/"um",/*plur*/"us"}}, /*fem*/{/*1è déc*/{/*sing*/"am",/*plur*/"as"},/*2è déc*/{/*sing*/"um",/*plur*/"æ"},{},{},{},{},{},/*4è déc*/{/*sing*/"um",/*plur*/"us"}}, /*neu*/{/*1è déc*/{/*sing*/"um",/*plur*/"æ"},/*2è déc*/{/*sing*/"um",/*plur*/"a"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, {}, {}, {}, /*4è déc */{/*sing*/"u",/*plur*/"ua"}}});
        private final short ic;                                                 // indice 0, 1, 2, ...
        private final String lc ;                                               // libellé de personne "1è. s.", "2è. m. s."
        private final String [][][] suffixes ;                                     // préfixe et suffixe par schème
        LAC (short ic, String lc, String [][][] suffixes) { this.ic = ic ;  this.lc = lc ; this.suffixes = suffixes ;}
        public short ic() { return ic; }    
        public String lc() { return lc; }
        /**
         * @param ig : indice de genre 0, 1, 2
         * @param id
         * @param in
         * @return 
         */
        public String spip(short ig, int id, int in) { 
            return suffixes [ig][id][in] ; }          // renvoie le suffixe d'un groupe donné   
    }

    private static LAD itype ;                                            // N° de groupe
    public enum LAD {PreDecl((short)0), DeuDeclPure((short)1), DeuDecler((short)2), TroiDeclParisyllabiquesMEF((short)3), TroiDeclParisyllabiquesNF((short)4), TroiDeclImparisyllabiquesMEF((short)5), TroiDeclImparisyllabiquesNF((short)6), QuaDecl((short)7), CinqDecl((short)8);
        private final short id;
        LAD (short id) { this.id = id ;  }
        public short id() { return id; }    
    }
    public enum LAF {
        NominatifSingulier((short)0), GénitifSingulier((short)1), Genre((short)2);
        private final short ifo;
        private LAF(short ifo) {  this.ifo = ifo;  }
        public short ifo() {   return this.ifo;   }        
    }                                                                           // LAF
    public enum LAG {
        Masculin((short)0, "m"), Féminin((short)1, "f"), Neutre((short)2, "n");
        private final short ig;
        private final String lg ;
        private LAG(short ig, String lg) {  this.ig = ig; this.lg     = lg ; }
        public short ig() {   return this.ig;   }        
        public String lg() { return lg; }
        public static LAG l2G(String genre) {
            LAG result = null;
            for (LAG g : LAG.values()) if (g.lg().equals(genre)) { result = g;  break;  }
            return result;
        }                                                                       //findByName
    }                                                                           // LAG

    private int SyllableCount(String s) {
        if (DEBUG) { System.out.println ("Conjugueur.M.DeclTableLat::SyllableCount, s = " + s) ; }   
        int count = 0;
        boolean isPrevVowel = false;
        s = s.toLowerCase(); 
        for (int j = 0; j < s.length(); j++) {
            if (s.contains("a") || s.contains("e") || s.contains("i") || s.contains("o") || s.contains("u")) {
            // checking if character is a vowel and if the last letter of the word is 'e' or not
                if (isVowel(s.charAt(j)) && !((s.charAt(j) == 'e') && (j == s.length()-1))) {
                    if (isPrevVowel == false) {
                        count++;
                        isPrevVowel = true;
                    }
                } else {
                        isPrevVowel = false;
                }
            } else {
                count++;
                break;
            }
        }                                                                       // pour ts les caractères
        return count;
    }                                                                               // SyllableCount

    private boolean isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        } else {
            return false;
        }
    }                                                                               // isVowel
    String forme[] = new String [5] ;
    private final boolean DEBUG = false ;
}                                                                               // DeclTableLat
