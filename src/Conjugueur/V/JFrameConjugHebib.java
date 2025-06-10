/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;
import com.ibm.icu.text.Transliterator;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.BadLocationException;

public class JFrameConjugHebib extends JFrameConjug implements ActionListener {

    /**
     * Fction appelante : Conjugueur.C.ConjugHebib::ConjugHebib
     * @param contrôleur 
     * @throws javax.swing.text.BadLocationException 
     */
    @SuppressWarnings({"unchecked", "unchecked"})
    public JFrameConjugHebib(Conjugueur.C.Conjug contrôleur)  throws BadLocationException {
        super(contrôleur); 
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjugHebib::JFrameConjugHebib, contrôleur = "+ contrôleur) ; }
        frame.setTitle("Conjugueur hébreu biblique V0.8");
        largeur = LARGEUR ; hauteur = HAUTEUR ;
        jPanelAutreMot.add(jCBAutMot) ; jPanelDroit.add(jPanelAutreMot, java.awt.BorderLayout.NORTH) ; jCBAutMot.setActionCommand(AUTREMOT) ; jCBAutMot.setSelectedIndex(contrôleur.model.getIAutreMot()) ; jCBAutMot.addActionListener(this) ;
        jTabDecl = new JTableau(Conjugueur.M.Conjug.declTableM) ;
        jTabDecl.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(HÉBREU, ComponentOrientation.LEFT_TO_RIGHT));
        jTabDecl.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabDecl.getColumnModel().getColumn(2).setPreferredWidth(0) ;        
        jPanelDroit.add(jTabDecl, java.awt.BorderLayout.SOUTH);
        jPanelMode.add(jCBMode) ; jPanelMode.setPreferredSize(new Dimension(jPanelMode.getWidth()/2, jPanelMode.getHeight()*2)); jPanelGauche.add(jPanelMode, java.awt.BorderLayout.CENTER) ; jCBMode.setSelectedIndex(contrôleur.model.getMode()) ;              jCBMode.setActionCommand(MODE); jCBMode.addActionListener(this);
        jPanelSchème.add(jCBSchème) ; /* jPanelSchème.setPreferredSize(new Dimension(LARGEURPANNEAU, HAUTEURPANNEAU));*/ jPanelSchème.add(jLabelVoix) ; jPanelSchème.add(JLabelForme) ; jPanelGauche.add(jPanelSchème, java.awt.BorderLayout.EAST) ; jCBSchème.setSelectedIndex(contrôleur.model.getSchème()); jCBSchème.setActionCommand(SCHEME) ; jCBSchème.addActionListener(this);                                             jCBSchème.setActionCommand(SCHEME) ;jCBSchème.addActionListener(this);       
        jCBVerbe = new JComboBox<>(Conjugueur.M.ConjugHebib.cbVerbeM) ; jCBVerbe.setSelectedIndex(contrôleur.model.getIndVerbe()) ;   jCBVerbe.addActionListener(this) ;        jCBVerbe.setActionCommand(VERBE) ; jCBVerbe.setFont(HÉBREU) ; jCBVerbe.setRenderer(new ComboBoxVerbeRenderer(HÉBREU, ComponentOrientation.RIGHT_TO_LEFT, ATTRIBUTVERBE)) ;
        jPanelVerbe.add(jCBVerbe) ; jPanelGauche.add(jPanelVerbe, java.awt.BorderLayout.NORTH) ;   jPanelVerbe.add(jButExport) ; jButExport.setActionCommand(EXPORT); jButExport.addActionListener(this) ;        
        jTabConj = new JTableau(Conjugueur.M.Conjug.conjTableM) ;
        jTabConj.getColumnModel().getColumn(0).setCellRenderer(new PersonneCellRenderer(FRANÇAIS)) ;
        jTabConj.getColumnModel().getColumn(1).setCellRenderer(new DéclinaisonCellRenderer(HÉBREU, ComponentOrientation.RIGHT_TO_LEFT));
        jTabConj.getColumnModel().getColumn(2).setCellRenderer(new PersonneCellRenderer(FRANÇAIS)) ;
        jTabConj.getColumnModel().getColumn(1).setPreferredWidth(220);
        jTabConj.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(HÉBREU, ComponentOrientation.LEFT_TO_RIGHT));
        jPanelGauche.add(jTabConj, java.awt.BorderLayout.SOUTH);
        frame.add(splitPane, BorderLayout.CENTER);
        Conjugueur.M.ConjugHebib.conjTableM.MAJConj(getContrôleur());    
        Conjugueur.M.ConjugHebib.declTableM.MAJDecl(getContrôleur());
    }

    /** Listens to the combo box.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e)  {
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjugHebib::actionPerformed") ; }
        getContrôleur().notifyVAChanged(e.getActionCommand(), ((JComboBox) e.getSource()).getSelectedIndex()) ;
        switch(e.getActionCommand()) {
            case EXPORT -> {
//                try {
//                    ConjugM.conjTableModel.Exporter(ConjugHebib.defargs);
//                } catch (IOException | BadLocationException ex) {
//                    Logger.getLogger(JFrameConjugHebib.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }

           
            case SCHEME -> { ItemizeComboSchèmes(getContrôleur()) ;}
        }        
        Conjugueur.M.ConjugHebib.conjTableM.MAJConj(getContrôleur());    
    }
 
 
 
    

    /**
     * Fonction : Inhibe les items de JFrameConjugHebib.jCBSchèmes qui n'ont aucun usage
     */
    private void ItemizeComboSchèmes (Conjugueur.C.Conjug contrôleur) { 
        if (DEBUG) System.out.println("ConjugHebib:ItemizeComboSchèmes") ;
        jCBSchème.removeActionListener(this);
        jCBSchème.removeAllItems();
        contrôleur.model.setSchème((short)-1) ;
        java.util.List<org.jdom2.Element>    usageChildren = Conjugueur.M.ConjugHebib.cbVerbeM.get(contrôleur.model.getIndVerbe()).getChildren("usage");
        for (org.jdom2.Element usage:usageChildren) {                           // Pour ttes les usages
            for (Conjugueur.M.ConjugHebib.HBS schème : Conjugueur.M.ConjugHebib.HBS.values()) {                                       // Pour ts les schèmes
                if (usage.getAttributeValue("schème").equals(schème.toString())){
                    jCBSchème.addItem(schème); 
                    if (contrôleur.model.getSchème() == (short)-1) {contrôleur.model.setSchème(schème.is()) ; 
                    jCBSchème.setSelectedItem(contrôleur.model.getSchème());}
                    break ;
                }
            }                                                                       // Pour ts les schèmes
        }                                                                       // Pour ttes les usages
//        JFrameConjugHebib.jCBSchème.addActionListener(this) ;
    }                                                                           // ItemizeComboSchèmes
        
    public static final String HBPP2MS = "אַתָּה", HBPP2FS = "אַתְּ", HBPP3MS = "הוּא", HBPP3FS = "הִיא" ;    
    public final static int IL_AM1S = 0 ;                                    // arabe moderne 1ère personne sing.
    public final static String ETI_AM1PS = "1è. p. s. : " ;                    // 1ère personne sing.
    public final static String ETI_AM3MS = "3è. m. s. : " ;                    // 3ème masc. sing.
    public final static int IL_AM2MS = 1 ;                                    // arabe moderne 2ème personne masc sing
    public final static int IL_AM2FS = 2 ;                                    //   arabe moderne 2ème personne fém. sing.
    public final static int IL_AM3MS = 3 ;                                    // arabe moderne 3ème personne masc sing
    public final static int IL_AM3FS = 4 ;                                    //   arabe moderne 3ème personne fém. sing.
    public final static int IL_AM2D = 5 ;                                    //   arabe moderne 2ème personne duel
    public final static int IL_AM3D = 6 ;                                    // 1ère personne masc. sing.
    public final static String[] ETIPERS = new String[12] ;
      
    private final JComboBox<Conjugueur.M.ConjugHebib.HBM> jCBMode = new JComboBox<>(Conjugueur.M.ConjugHebib.cModelModes);
    private final JComboBox<Conjugueur.M.ConjugHebib.HBS> jCBSchème = new JComboBox<>(Conjugueur.M.ConjugHebib.cModelSchème) ;
    @SuppressWarnings("unchecked")
    private final JComboBox<String> jCBAutMot = new JComboBox<>(Conjugueur.M.ConjugHebib.comboBoxAutreMotM);
    private final  JComboBox<Conjugueur.M.ConjugGrecA.GAN> jCBNb = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugGrecA.GAN.values())) ;
    public static final short HAUTEUR = 550, LARGEUR = 1000, HAUTEURPANNEAU = 20, LARGEURPANNEAU = 10, PRÉFIXE = 0, SUFFIXE = 1 ;
    public final static String  BEGADKEPAT = "בגדכפת", GUTTURALES = "אהחע", MATRESLECTIONIS = "יוה", ATTRIBUTVERBE = "infinitif", BALISÉNTRÉE = "verhb", ETI_FR2MS = "2è. m. s.", ETI_FR2FS = "2è. f. s.", ETI_FR3MS = "3è. m. s.", ETI_FR3FS = "3è. f. s.", HEBIB_TO_LATIN = "Hebrew-Latin", NOMPOLICE = "Times New Roman", XMLSOURCE = "ConjugSemi.xml" ;
    public static  final Transliterator hebToLatinTrans = Transliterator.getInstance(HEBIB_TO_LATIN);

    private static final boolean DEBUG = false;    
    
    
    
}
