/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import Conjugueur.V.DéclinaisonStyledDoc;
import Conjugueur.V.LigneConj;
import java.awt.Font;
import java.io.*;
import java.util.ArrayList ;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import com.ibm.icu.text.Transliterator ;
import Conjugueur.V.JTableau;
import java.awt.Component;
import java.nio.charset.StandardCharsets;

public class ConjTable extends AbstractTableModel {
    
    public final  ArrayList<LigneConj> tab = new ArrayList<>();
    private final  String[] colNames = { "PP", "D", "T" };

    public ConjTable () {
        super();
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable::ConjTable") ; }
        tab.clear() ;
        inbLignes = 0 ;
    }                                                                           // ConjTable

    @Override
    public int getColumnCount() {
      return colNames.length;
    }                                                                          // getColumnCount

    @Override
    public int getRowCount() {
      return tab.size() ;
    }                                                                                       // getRowCount

    @Override
    public void setValueAt(Object value, int row, int col) {
      switch (col) {
       case 0 -> tab.get(row).libpersonne = ((String) value);
       case 1 -> tab.get(row).déclinaison = ((DéclinaisonStyledDoc) value);
       case 2 -> tab.get(row).translittération = ((String) value);
      }
    }                                                                                   // setValueAt

    @Override
    public Class getColumnClass(int col) {
      switch (col) {
       case 0 -> {          return String.class;            }
       case 1 -> {          return DéclinaisonStyledDoc.class;            }
       case 2 -> {          return String.class;            }
      }
      return Boolean.class;
    }                                                                                                   // getColumnClass

    @Override
    public Object getValueAt(int row, int col) {
        return switch (col) {
            case 0 -> tab.get(row).libpersonne;
            case 1 -> tab.get(row).déclinaison;
            case 2 -> tab.get(row).translittération;
            default -> null;
        };
    }                                                                                       // getValueAt

    /**
     * Fonction : Ajoute une personne spécifiée par l'indice "ipersonne" : 1, 2, ....Algorithme : insère une ligne ds le tableau de conjugaison "ConjTablemodele.tab" et en 1ère colonne "personne" le libellé passé en paramètre
     * @param alignement
     * @return 
    */
    public int AjouterLigne(String etipers, Font FontUtilisateur, int alignement) {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable::AjouterLigne, etipers = " + etipers+", FontUtilisateur.getFontName() = "+FontUtilisateur.getFontName()) ; }
        tab.add(new LigneConj(etipers, FontUtilisateur, alignement)) ;
        inbLignes++ ;
       return tab.size() - 1 ;
    }                                                                           // AjouterLigne

    /**
     * Fonction : Ajoute une personne spécifiée par l'indice "ipersonne" : 1, 2, ....
     * Algorithme : insère une ligne ds le tableau de conjugaison "ConjTablemodele.tab" et en 1ère colonne "personne" le libellé passé en paramètre
     * Fction appelante : ConjTableAraM::MAJ
     * @param ip    indice ds la table "tab
     * @param lpers libellé de personne "1è. s.", "2è. m. s."
     * @param PoliceDéclinaison police 2è col
     * @param alignement
     * @return 
    */
    public final int AjouterLigne (int ip, String lpers, Font PoliceDéclinaison, int alignement){
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable::AjouterLigne, ip = " + ip +", lpers = " + lpers+", PoliceDéclinaison.getFontName() = "+PoliceDéclinaison.getFontName()) ; }
       tab.add(new LigneConj(lpers, PoliceDéclinaison, alignement)) ;
        inbLignes++ ;
       return tab.size() - 1 ;
    }                                                                            // AjouterLigne    
    public final void AjouterPers (int ip, String[] etipers, Font FontUtilisateur, int alignement){
//        if (DEBUG) System.out.println("Conjugueur.M.ConjTable::AjouterPers, etipers[ip] = " + etipers[ip]+", FontUtilisateur.getFontName() = "+FontUtilisateur.getFontName()) ;
       tab.add(new LigneConj(etipers[ip], FontUtilisateur, alignement)) ;
    }                                                                            // AjouterPers

    /**
     * Fonction : Ajoute une ipersonne spécifiée par l'indice "personne" : 1 ou 2 ou autre
     * Algorithme : insère une ligne ds le tableau de conjugaison "ConjTablemodele" et en 1ère colonne "personne" le libellé passé en paramètre
     * @param ipersonne
    */
    public  final void AjouterPers (int ipersonne){
//       tab.add(new LigneConj(ConjugSyriDoc.ETIPERS[ipersonne])) ;
    }                                                                            // AjouterPers
    public  final void AjouterPers (){
//       tab.add(new LigneConj(ConjugSyriDoc.ETIPERS[ConjugSyriDoc.inbLignes])) ;
    }                                                                            // AjouterPers
   
    /**
     * Fction appelante : ConjTableAraM
     * @param ip
     * @param toLatinTrans
     * @throws BadLocationException 
     */
   public void AjouterTranslittération (int ip, Transliterator toLatinTrans)  throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable::AjouterTranslittération, ip = " + ip) ; }
//        if (DEBUG) for (char c : tab.get(ip).déclinaison.getText(0, tab.get(ip).déclinaison.getLength()).toCharArray()) new PrintWriter(System.out,true).printf("c = %c , unicode = %x ", c, (int)c) ;
       tab.get(ip).translittération = toLatinTrans.transliterate(tab.get(ip).déclinaison.getText(0, tab.get(ip).déclinaison.getLength())); tab.get(ip).translittération = toLatinTrans.transliterate(tab.get(ip).déclinaison.getText(0, tab.get(ip).déclinaison.getLength())); 
   }

   public void AjouterTranslittération (Transliterator toLatinTrans)  throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable::AjouterTranslittération, ip = " + inbLignes+", toLatinTrans = "+toLatinTrans.toString()) ; }
//        if (DEBUG) for (char c : tab.get(inbLignes).déclinaison.getText(0, tab.get(inbLignes).déclinaison.getLength()).toCharArray()) new PrintWriter(System.out,true).printf("c = %c , unicode = %x ", c, (int)c) ;
       tab.get(inbLignes).translittération = toLatinTrans.transliterate(tab.get(inbLignes).déclinaison.getText(0, tab.get(inbLignes).déclinaison.getLength())); tab.get(inbLignes).translittération = toLatinTrans.transliterate(tab.get(inbLignes).déclinaison.getText(0, tab.get(inbLignes).déclinaison.getLength())); 
   }

   public void BarrOblique (int ip) throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable::BarrOblique, ip = " + ip) ; }
        tab.get(ip).déclinaison.insertString(tab.get(ip).déclinaison.getLength(), " / ", DéclinaisonStyledDoc.stylera) ;
    }                                                                            // BarrOblique
   
      

    /**
     * Remplit une ligne (identifiée par l'indice "ip" ) de la table "tab"
     * @param ip indice ds la table "tab
     * @param caractères à copier
     * @param style
     * @throws javax.swing.text.BadLocationException
     * Fction appelante : JFrameConjugAraM::DéclinerIndicatifRégulièrement
    */
    public void Copier (int ip, String caractères, Style style) throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable::Copier, ip = " + ip + ", caractères = " + caractères + ", tab.size() = " + tab.size()) ; }
//       if (DEBUG) for (char c : caractères.toCharArray()) new PrintWriter(System.out,true).printf("c = %c , unicode = %x ", c, (int)c) ;
        if (!(caractères.isEmpty()||caractères.isBlank()||(caractères.charAt(0) == '\u0000'))) tab.get(ip).déclinaison.insertString(tab.get(ip).déclinaison.getLength(), caractères, style) ;
    }                                                                           // Copier

    public void Copier (String caractères, Style style) throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable::Copier, inbLignes = " + inbLignes + ", caractères = " + caractères + ", tab.size() = " + tab.size()) ; }
        if (!(caractères.isEmpty()||caractères.isBlank()||(caractères.charAt(0) == '\u0000'))) tab.get(inbLignes).déclinaison.insertString(tab.get(inbLignes).déclinaison.getLength(), caractères, DéclinaisonStyledDoc.styledéclinaison) ;
    }                                                                           // Copier

    /**
    * Affiche le pronom personnel identifiée par l'indice "personne"
     * @param libpers à copier "1è. s.", "2è. s.", "3è. s.",... 
     * @throws javax.swing.text.BadLocationException
   */
    public void CopierPersonne (String libpers) throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable::CopierPersonne, inbLignes = " + inbLignes+", libpers = "+libpers) ; }
        Copier(libpers, DéclinaisonStyledDoc.stylepp); // ConjugLatM.conjTableM.Espace (inbLignes) ;
    }                                                                           // CopierPersonne
    
    /**
    * Affiche le pronom personnel identifiée par l'indice "personne"
     * @param libpers à copier "1è. s.", 
     * @param inbLignes indice de ligne : 0, 1, 2...
     * @throws javax.swing.text.BadLocationException
   */
    public void CopierPersonne (int inbLignes, String libpers) throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable::CopierPersonne, inbLignes = " + inbLignes) ; }
        Copier (inbLignes, libpers, DéclinaisonStyledDoc.stylepp) ; Espace (inbLignes) ;
    }                                                                           // CopierPersonne

    /**
     * Fction appelante : ConjTableAnFr::DéclinerRégulièrement
     * @param racine
     * @throws javax.swing.text.BadLocationException
     */
    public void CopieRacine(String racine) throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable::CopieRacine"+", racine = " + racine) ; }
        for (char c : racine.toCharArray())  Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
    }                                                                           // CopieRacine      
  
    public void Espace () throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable::Espace"+", inbLignes = " + inbLignes) ; }
        tab.get(inbLignes).déclinaison.insertString(tab.get(inbLignes).déclinaison.getLength(), " ", DéclinaisonStyledDoc.stylera) ;
   }
    public void Espace (int ip) throws BadLocationException {
        tab.get(ip).déclinaison.insertString(tab.get(ip).déclinaison.getLength(), " ", DéclinaisonStyledDoc.stylera) ;
   }

    /**
    * Exporte le contenu du tableau de conjugaison sur un fichier à plat
     * @param defargs nom du fichier de sortie
     * @throws java.io.IOException
     * @throws javax.swing.text.BadLocationException
   */
    public void Exporter (String defargs[]) throws IOException, BadLocationException {
        if (DEBUG) { System.out.println ("Conjugueur.M.ConjTable::Exporter, defargs = "+ defargs[0]) ; }
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(defargs[0]), StandardCharsets.UTF_8))) {
            for (int ir = 0; ir < tab.size() ; ir++)  {
                StringBuilder buffer = new StringBuilder();
                buffer.append(tab.get(ir).libpersonne).append(" ");
                buffer.append(tab.get(ir).déclinaison.getText(0, tab.get(ir).déclinaison.getLength())).append(" ");
                buffer.append(tab.get(ir).translittération).append(",");
                writer.write(buffer.toString() + "\r\n");
            }                                                                       // pour ttes les lignes
            writer.close();
        } catch (IOException ex) {}
    }                                                                           // Exporter   

    public void MAJConj(Conjugueur.C.Conjug contrôleur) {} ;
    public void MAJDecl(Conjugueur.C.Conjug contrôleur) {} ;

    /**
     * @param polidecl police 2è col
     */

    public void Polichange (String polidecl) {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable::Polichange, polidecl = "+ polidecl) ; }  
        for(int ir = 0 ; ir < tab.size(); ir++)
            tab.get(ir).déclinaison.Polichange(polidecl) ;
            
    }
           
    public void Préfixer (String préfixe) throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable:Préfixer, inbLignes = " + inbLignes + ", préfixe = " + préfixe) ; }
        tab.get(inbLignes).déclinaison.insertString(tab.get(inbLignes).déclinaison.getLength(), préfixe, DéclinaisonStyledDoc.stylepr) ;   
    }                                                                            // Préfixer
    /**
     * @param ip indice de personne 0, 1, 2, 3, 4, 5
     */      
    public void Préfixer (int ip, String préfixe) throws BadLocationException {
        if (DEBUG) System.out.println("Conjugueur.M.ConjTable:Préfixer, ip = " + ip + ", préfixe = " + préfixe) ;
        tab.get(ip).déclinaison.insertString(tab.get(ip).déclinaison.getLength(), préfixe, DéclinaisonStyledDoc.stylepr) ;   
    }                                                                            // Préfixer
   
    /**
     * @param ip indice de personne 0, 1, 2, 3, 4, 5
     * @param suffixe
     * @throws javax.swing.text.BadLocationException
     * Algorithme : 
     * 1. Supprimer les caracères avant la dernière consonne
     * 2. Supprimer cette consonne
     * 3. Insérer le suffixe
     */      
    public void RemplaCarFinal (int ip, String suffixe) throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable:RemplaCarFinal, ip = " + ip +", suffixe = " + suffixe+", tab.get(ip).déclinaison.getLength() = "+tab.get(ip).déclinaison.getLength()+", tab.get(ip).déclinaison.getText(0, tab.get(ip).déclinaison.getLength() = "+tab.get(ip).déclinaison.getText(0, tab.get(ip).déclinaison.getLength())) ; }
        while (!Conjugueur.M.ConjTableSyri.Consonne(tab.get(ip).déclinaison.getText(tab.get(ip).déclinaison.getLength()-1, 1).charAt(0)) ) tab.get(ip).déclinaison.remove(tab.get(ip).déclinaison.getLength()-1, 1) ;
        tab.get(ip).déclinaison.remove(tab.get(ip).déclinaison.getLength()-1, 1) ;
        tab.get(ip).déclinaison.insertString(tab.get(ip).déclinaison.getLength()-1, suffixe.substring(1), DéclinaisonStyledDoc.stylesu) ;
    }                                                                           // RemplaConsonneFinale
    
   public void RemplaceVoyelle (int ip) throws BadLocationException {
    tab.get(ip).déclinaison.remove(tab.get(ip).déclinaison.getLength()-1, 1) ;
//    tab.get(ip).déclinaison.insertString(tab.get(ip).déclinaison.getLength()-1, "ֶ", ConjugSyriDoc.styles) ;
   }                                                                            // RemplaceVoyelle

    /**
     * @param ip indice de personne 0, 1, 2, 3, 4, 5
     * @param suffixe
     * @throws javax.swing.text.BadLocationException
     */      
    public void Suffixer (int ip, String suffixe) throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable:Suffixer, ip = " + ip +", suffixe = " + suffixe) ;        }
        if(suffixe.isEmpty() ) return ;
        if(suffixe.charAt(0) == '-') RemplaCarFinal(ip, suffixe) ;
        else tab.get(ip).déclinaison.insertString(tab.get(ip).déclinaison.getLength(), suffixe, DéclinaisonStyledDoc.stylesu) ;   
   }                                                                            // Suffixer
    /**
     * Fction appelante :  DéclinerRégulièrement
     * @param suffixe
     * @throws javax.swing.text.BadLocationException
     */      

    public void Suffixer (String suffixe) throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTable:Suffixer, inbLignes = " + inbLignes +", suffixe = " + suffixe) ; }
        if(suffixe.isEmpty()) return ;
        if(suffixe.charAt(0) == '-') RemplaCarFinal(inbLignes, suffixe) ;
        else tab.get(inbLignes).déclinaison.insertString(tab.get(inbLignes).déclinaison.getLength(), suffixe, DéclinaisonStyledDoc.stylesu) ;   
   }                                                                            // Suffixer

    public void updateRowHeights(JTableau jTabConj) {
       if (DEBUG) { System.out.println ("Conjugueur.M.ConjTable::updateRowHeights") ; }
        for (int row = 0; row < jTabConj.getRowCount(); row++)  {
            int rowHeight = jTabConj.getRowHeight();

            for (int column = 0; column < jTabConj.getColumnCount(); column++)        {
                Component comp = jTabConj.prepareRenderer(jTabConj.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }
//       if (DEBUG) System.out.println ("ConjugSyri::updateRowHeights, rowHeight = "+rowHeight) ;

        jTabConj.setRowHeight(row, rowHeight);
        }
    }                                                                           // updateRowHeights
    public  void vider() {
        tab.clear() ;
        inbLignes = 0 ;
    }                                                                           // vider
    public static int inbLignes = 0 ; 
    public static short nbcons = 0 ;                                                    // compteur de consonnes
    public  java.util.List<org.jdom2.Element> déclinaisonChildren ;
    private final boolean DEBUG = false ;
}