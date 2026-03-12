/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;
import java.awt.ComponentOrientation;
import java.awt.event.*;
import javax.swing.*;
/*
 * Conjugueur.V.JFrameConjugAnFr hérite Conjugueur.C.ConjugAnFrV qui hérite de Conjugueur.V.JFrameConjDouble qui hérite de Conjugueur.V.JFrameConjug qui implémente Conjugueur.M.ConjugListener
 */
public class JFrameConjugAnFr extends JFrameConjDouble implements ActionListener {
 
    /**
     * Fction appelante : Conjugueur.C.ConjugAnFr::ConjugAnFr
     * @param contrôleur 
     */
    @SuppressWarnings({"unchecked", "unchecked", "unchecked"})
    public JFrameConjugAnFr(Conjugueur.C.ConjugAnFr contrôleur) {
        super(contrôleur); 
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjugAnFr::JFrameConjugAnFr, contrôleur.toString() ="+contrôleur.toString()) ; }
        frame.setTitle("Conjugueur ancien français V0.8"); 
        largeur = LARGEUR ; hauteur = HAUTEUR ;
        jCBAutMot = new JComboBox(Conjugueur.M.ConjugAnFr.cbAutreMotM);
        jPanelAutreMot.add(jCBAutMot) ;  jPanelAutreMot.add(jLabelDéclinaison) ; jCBAutMot.setActionCommand(AUTREMOT) ; jCBAutMot.setSelectedIndex(contrôleur.model.getIAutreMot()) ; jCBAutMot.addActionListener(this) ;
        jPanelNb.add(jComboBoxNb) ;     jPanelDroit.add(jPanelNb, java.awt.BorderLayout.EAST);    jComboBoxNb.setActionCommand(NOMBRE) ; jComboBoxNb.addActionListener(this); jCBAutMot.setRenderer(new ComboBoxVerbeRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT, Conjugueur.M.ConjugAnFr.ATTRIBUT)); 
        jTabDecl = new JTableau(Conjugueur.M.ConjugAnFr.declTableM) ;
        jTabDecl.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabDecl.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabDecl.getColumnModel().getColumn(2).setPreferredWidth(0) ;        
        jPanelDroit.add(jTabDecl, java.awt.BorderLayout.SOUTH);
        jPanelMode.add(jComboBoxMode);      jPanelGauche.add(jPanelMode, java.awt.BorderLayout.WEST);      jComboBoxMode.setActionCommand(MODE);     jComboBoxMode.setSelectedIndex(contrôleur.model.getMode());jComboBoxMode.addActionListener(this) ;
        jPanelTemps.add(jComboBoxTemps);    jPanelGauche.add(jPanelTemps, java.awt.BorderLayout.CENTER);  jComboBoxTemps.setActionCommand(TEMPS);   jComboBoxTemps.setSelectedIndex(contrôleur.model.getTemps()) ; jComboBoxTemps.addActionListener(this) ;
        jCBVerbe  = new JComboBox(Conjugueur.M.ConjugAnFr.cbVerbeM)  ;
        jPanelVerbe.add(jCBVerbe) ;    jPanelVerbe.add(jLabelConjugaison);   jCBVerbe.setActionCommand(VERBE) ;   jCBVerbe.setSelectedIndex(contrôleur.model.getIndVerbe()) ; jCBVerbe.addActionListener(this) ;
        jCBVerbe.setRenderer(new ComboBoxVerbeRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT, Conjugueur.M.ConjugAnFr.ATTRIBUT)); 
        jTabConj = new JTableau(Conjugueur.M.ConjugAnFr.conjTableM) ;
        jTabConj.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabConj.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabConj.getColumnModel().getColumn(2).setPreferredWidth(0) ;        
        jPanelGauche.add(jTabConj, java.awt.BorderLayout.SOUTH);
        Conjugueur.M.ConjugAnFr.conjTableM.MAJConj(getContrôleur()) ;
        Conjugueur.M.ConjugAnFr.declTableM.MAJDecl(getContrôleur());
    }
    
    public final static JComboBox<Conjugueur.M.Conjug.M> jComboBoxMode = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.Conjug.M.values()));
    public final static JComboBox<Conjugueur.M.ConjugAnFr.TAnFr> jComboBoxTemps = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugAnFr.TAnFr.values())) ;
    public final static  JComboBox<Conjugueur.M.ConjugAnFr.V> jComboBoxVoix = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugAnFr.V.values())) ;
    private final  JComboBox<Conjugueur.M.ConjugAnFr.N> jComboBoxNb = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugAnFr.N.values())) ;
    public static final int HAUTEUR = 380, LARGEUR = 870 ;
    @SuppressWarnings("unchecked")
    public final static JComboBox<String> jComboBoxVerbe = new JComboBox<>(Conjugueur.M.ConjugAnFr.cbVerbeM);  

    private final boolean DEBUG = false;        
}
