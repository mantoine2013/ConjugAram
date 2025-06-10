/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;
import Conjugueur.M.*;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class JFrameConjugSans  extends JFrameConjug implements ActionListener {

    /**
     * Fction appelante : Conjugueur.C.ConjugSans::ConjugSans
     * @param contrôleur 
     */
    @SuppressWarnings({"unchecked", "unchecked"})
    public JFrameConjugSans(Conjugueur.C.ConjugSans contrôleur) {
        super(contrôleur); 
        if (DEBUG) System.out.println("Conjugueur.V.JFrameConjugSans::JFrameConjugSans") ;
        frame.setTitle("Conjugueur sanscrit V0.8");
        frame.setSize(LARGEUR, HAUTEUR);
        jPanelAutreMot.add(jComboBoxAutMot) ; jPanelDroit.add(jPanelAutreMot, java.awt.BorderLayout.NORTH) ; jComboBoxAutMot.setActionCommand(AUTREMOT) ; jComboBoxAutMot.setSelectedIndex(contrôleur.model.getIAutreMot()) ; jComboBoxAutMot.addActionListener(this) ;
        jPanelNb.add(jComboBoxNb) ;     jPanelDroit.add(jPanelNb, java.awt.BorderLayout.EAST);    jComboBoxNb.setActionCommand(ÉTAT) ; jComboBoxNb.addActionListener(this); jComboBoxAutMot.setRenderer(new ComboBoxVerbeRenderer(LATIN, ComponentOrientation.LEFT_TO_RIGHT, ConjugSans.ATTRIBUTVERBE)); 
        jTabDecl = new JTableau(Conjugueur.M.Conjug.declTableM) ; // Conjugueur.M.ConjugSans.declTableM
        jTabDecl.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(LATIN, ComponentOrientation.LEFT_TO_RIGHT));
        jTabDecl.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabDecl.getColumnModel().getColumn(2).setPreferredWidth(0) ;        
        jPanelDroit.add(jTabDecl, java.awt.BorderLayout.SOUTH);
        jPanelMode.add(jComboBoxMode);      jPanelGauche.add(jPanelMode, java.awt.BorderLayout.WEST);      jComboBoxMode.setActionCommand(MODE);     jComboBoxMode.setSelectedItem(contrôleur.model.getMode());jComboBoxMode.addActionListener(this) ;
        jPanelTemps.add(jComboBoxTemps);    jPanelGauche.add(jPanelTemps, java.awt.BorderLayout.CENTER);  jComboBoxTemps.setActionCommand(TEMPS);   jComboBoxTemps.setSelectedItem(contrôleur.model.getTemps());jComboBoxTemps.addActionListener(this) ;
        jPanelVerbe.add(jComboBoxVerbe) ;   jPanelGauche.add(jPanelVerbe, java.awt.BorderLayout.NORTH);   jComboBoxVerbe.setActionCommand(VERBE);   jComboBoxVerbe.setSelectedIndex(contrôleur.model.getIndVerbe());jComboBoxVerbe.addActionListener(this) ;
        jComboBoxVerbe.setRenderer(new ComboBoxVerbeRenderer(LATIN, ComponentOrientation.LEFT_TO_RIGHT, ConjugSans.ATTRIBUTVERBE)); 
        jPanelVoix.add(jComboBoxVoix) ;     jPanelGauche.add(jPanelVoix, java.awt.BorderLayout.EAST);    jComboBoxVoix.setActionCommand(VOIX);jComboBoxVoix.addActionListener(this);
        jTabConj = new JTableau(Conjugueur.M.Conjug.conjTableM) ;
        jTabConj.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(LATIN, ComponentOrientation.LEFT_TO_RIGHT));
        jTabConj.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabConj.getColumnModel().getColumn(2).setPreferredWidth(0) ;        
        jPanelGauche.add(jTabConj, java.awt.BorderLayout.SOUTH);
        frame.add(splitPane, BorderLayout.CENTER);
        Conjugueur.M.ConjugSans.conjTableM.MAJConj(contrôleur) ;
        Conjugueur.M.ConjugSans.declTableM.MAJDecl(contrôleur);
    }
    @Override
    public void close() {
        frame.dispose(); 
    }
 
    @Override
    public void display() {
        if (DEBUG) System.out.println("Conjugueur.Vue.ConjugSansContrôleur::display") ;
        contentPane.setOpaque(true); //content panes must be opaque
        frame.setLocation(screenSize.width/2 - (JFrameConjug.frame.getSize().width/2),screenSize.height/2 - (JFrameConjug.frame.getSize().height/2));
        frame.setVisible(true);
    }
 
    public void conjugSansChanged(ConjugSansChangedEvent event) {
        if (DEBUG) System.out.println("Conjugueur.Vue.ConjugSansContrôleur::conjugLatChanged") ;
//        field.setValue(event.getNewConjugSans());
    }
    private static final int HAUTEUR = 470, LARGEUR = 1000, TAILLEPOLICE = 24;
    private final static String AUTREMOT= "A", MODE = "M", ÉTAT = "N", NOMPOLICE = "Times New Roman", TEMPS = "T", VERBE = "V", VOIX = "VO" ;
    public static final Font FRANÇAIS = new Font (NOMPOLICE, Font.PLAIN, TAILLEPOLICE), LATIN = new Font (NOMPOLICE, Font.PLAIN, TAILLEPOLICE);
    @SuppressWarnings("unchecked")
    private final JComboBox jComboBoxVerbe  = new JComboBox(Conjugueur.M.ConjugSans.cbVerbeM)  ;
    @SuppressWarnings("unchecked")
    public final JComboBox jComboBoxAutMot = new JComboBox(Conjugueur.M.ConjugSans.comboBoxAutreMotM);
    private final  JComboBox<Conjugueur.M.ConjugSans.N> jComboBoxNb = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugSans.N.values())) ;
    public static final JComboBox<Conjugueur.M.ConjugSans.M> jComboBoxMode = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugSans.M.values()));
    public static  final JComboBox<Conjugueur.M.ConjugSans.T> jComboBoxTemps = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugSans.T.values())) ;
    public static final  JComboBox<Conjugueur.M.ConjugSans.V> jComboBoxVoix = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugSans.V.values())) ;
    private  final boolean DEBUG = false;    
    
}
