/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;

import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class JFrameConjugRu  extends JFrameConjDouble implements ActionListener {
    /**
     * Fction appelante : Conjugueur.C.ConjugRu::ConjugRu
     * @param contrôleur 
     */
    @SuppressWarnings({"unchecked", "unchecked", "unchecked"})
    public JFrameConjugRu(Conjugueur.C.ConjugRu contrôleur) {
        super(contrôleur); 
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjugRu::JFrameConjugRu, contrôleur.toString() ="+contrôleur.toString()) ; }
        frame.setTitle("Conjugueur russe V0.8"); 
        largeur = LARGEUR ; hauteur = HAUTEUR ;
        jCBAutMot = new JComboBox(Conjugueur.M.ConjugRu.cbAutreMotM);
        jPanelNb.add(jComboBoxNb) ;     jPanelDroit.add(jPanelNb, java.awt.BorderLayout.EAST);    jComboBoxNb.setActionCommand(NOMBRE) ; jComboBoxNb.addActionListener(this); jCBAutMot.setRenderer(new ComboBoxVerbeRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT, Conjugueur.M.ConjugRu.ATTRIBUTMOT)); 
        jTabDecl = new JTableau(Conjugueur.M.ConjugRu.declTableM) ;
        jTabDecl.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(CYRILLIC, ComponentOrientation.LEFT_TO_RIGHT));
        jTabDecl.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabDecl.getColumnModel().getColumn(2).setPreferredWidth(0) ;        
        jPanelDroit.add(jTabDecl, java.awt.BorderLayout.SOUTH);
        jPanelMode.add(jComboBoxMode);      jPanelGauche.add(jPanelMode, java.awt.BorderLayout.WEST);      jComboBoxMode.setActionCommand(MODE);     jComboBoxMode.setSelectedIndex(contrôleur.model.getMode()) ; jComboBoxMode.addActionListener(this) ;
//        jPanelAspects.add(jComboBoxAspect);  jComboBoxAspect.setActionCommand(ASPECT);  
        jPanelTemps.add(jComboBoxTemps);    jPanelGauche.add(jPanelTemps, java.awt.BorderLayout.EAST);  jComboBoxTemps.setActionCommand(TEMPS); jComboBoxTemps.setSelectedIndex(contrôleur.model.getTemps())  ; jComboBoxTemps.addActionListener(this) ;
        jCBVerbe  = new JComboBox(Conjugueur.M.ConjugRu.cbVerbeM)  ;
        jPanelVerbe.add(jCBVerbe) ; jLabelConjugaison.setText(Conjugueur.M.ConjugRu.RUC.i2C(contrôleur.model.getNConj()).toString());    jPanelVerbe.add(jLabelConjugaison);    jLabelAspect.setText(Conjugueur.M.ConjugRu.RUA.i2A(contrôleur.model.getAspect()).toString());  jPanelVerbe.add(jLabelAspect) ;   jCBVerbe.setActionCommand(VERBE);   jCBVerbe.setSelectedIndex(contrôleur.model.getIndVerbe()) ; jCBVerbe.addActionListener(this) ;
        jCBVerbe.setRenderer(new ComboBoxVerbeRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT, Conjugueur.M.ConjugRu.ATTRIBUTVERBEI)); 
        jTabConj = new JTableau(Conjugueur.M.ConjugRu.conjTableM) ;
        jTabConj.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabConj.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabConj.resizeColumnWidth() ; 
        jPanelGauche.add(jTabConj, java.awt.BorderLayout.SOUTH);
        Conjugueur.M.ConjugRu.conjTableM.MAJConj(getContrôleur()) ;    
        Conjugueur.M.ConjugRu.declTableM.MAJDecl(getContrôleur());
}                                                                           // JFrameConjugRu
    public final static JComboBox<Conjugueur.M.Conjug.M> jComboBoxMode = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.Conjug.M.values()));
    public final static JComboBox<Conjugueur.M.ConjugRu.RUT> jComboBoxTemps = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugRu.RUT.values())) ;
//    private final JComboBox<Conjugueur.M.ConjugRu.RUA> jComboBoxAspect = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugRu.RUA.values())) ;
    public static final int HAUTEUR = 400, LARGEUR = 930, TAILLEPOLICE = 17, TAILLEPOLICEFRANÇAIS = 17  ;
    private final  JComboBox<Conjugueur.M.ConjugRu.N> jComboBoxNb = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugRu.N.values())) ;
    public final static Font CYRILLIC = new Font (Conjugueur.ConjugRu.NOMPOLICY, Font.PLAIN, TAILLEPOLICE), FRANÇAIS = new Font (NOMPOLTNR, Font.PLAIN, TAILLEPOLICEFRANÇAIS) ;
    private final boolean DEBUG = false ;        
}
