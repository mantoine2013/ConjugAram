/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;

import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class JFrameConjugItal  extends JFrameConjug implements ActionListener {

    /**
     * Fction appelante : Conjugueur.Contrôleur.ConjugItal::ConjugItal
     * @param contrôleur 
     */
    @SuppressWarnings("unchecked")
    public JFrameConjugItal(Conjugueur.C.Conjug contrôleur) {
       super(contrôleur); 
        if (DEBUG) System.out.println("Conjugueur.Vue.JFrameConjugItal::JFrameConjugItal") ;
        frame.setTitle("Conjugueur Italien V0.8");
        frame.setSize(LARGEUR, HAUTEUR);
//        TableColumn col0 = jTabConj.getColumnModel().getColumn(0);        col0.setPreferredWidth(30);
//        TableColumn col1 = jTabConj.getColumnModel().getColumn(1);        col1.setPreferredWidth(500);
//        TableColumn col2 = jTabConj.getColumnModel().getColumn(2);        col2.setPreferredWidth(0);
        jPanelMode.add(jComboBoxMode);      contentPane.add(jPanelMode, java.awt.BorderLayout.WEST) ;       jComboBoxMode.setActionCommand(MODE) ;      jComboBoxMode.setSelectedItem(contrôleur.model.getMode()) ; jComboBoxMode.addActionListener(this);
        jPanelTemps.add(jCBTemps);    contentPane.add(jPanelTemps, java.awt.BorderLayout.CENTER) ;    jCBTemps.setActionCommand(TEMPS) ;    jCBTemps.setSelectedIndex(contrôleur.model.getTemps()); jCBTemps.addActionListener(this);
        jPanelVerbe.add(jComboBoxVerbe);    contentPane.add(JFrameConjug.jPanelVerbe, java.awt.BorderLayout.NORTH) ;     jComboBoxVerbe.setActionCommand(VERBE) ;    jComboBoxVerbe.setSelectedItem(contrôleur.model.getIndVerbe()) ; jComboBoxVerbe.addActionListener(this);
        jTabConj = new JTableau (Conjugueur.M.Conjug.conjTableM) ;
        jTabConj.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(ITALIEN, ComponentOrientation.LEFT_TO_RIGHT)); 
        jTabConj.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT)); jComboBoxVerbe.setRenderer(new ComboBoxVerbeRenderer(ITALIEN, ComponentOrientation.LEFT_TO_RIGHT, "infinitif")); 
        contentPane.add(jTabConj, java.awt.BorderLayout.SOUTH);                
        Conjugueur.M.Conjug.conjTableM.MAJConj(contrôleur);
        frame.setContentPane(contentPane);
    }    

    @SuppressWarnings("unchecked")
    public static final JComboBox jComboBoxVerbe = new JComboBox(Conjugueur.M.ConjugItal.comboBoxVerbeM);
    private static final DefaultComboBoxModel<Conjugueur.M.ConjugItal.M> cModelModes = new DefaultComboBoxModel<>(Conjugueur.M.ConjugItal.M.values()) ; 
    public static final JComboBox<Conjugueur.M.ConjugItal.M> jComboBoxMode = new JComboBox<>(cModelModes);


    @Override
    public void close() {
        frame.dispose(); 
    }
 
    @Override
    public void display() {
        if (DEBUG) System.out.println("Conjugueur.Vue.ConjugHebibContrôleur::display") ;
        frame.setLocation(screenSize.width/2 - (JFrameConjug.frame.getSize().width/2),screenSize.height/2 - (JFrameConjug.frame.getSize().height/2));
        frame.setVisible(true);
    }
    public static final int LARGEUR = 750, HAUTEUR = 400 ;
    public final static JComboBox<Conjugueur.M.ConjugItal.T> jCBTemps = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugItal.T.values())) ;
    private static final String VERBE = "V", MODE = "M", NOMPOLICE = "Times New Roman" ;
    public static final Font ITALIEN = new Font (NOMPOLICE, Font.PLAIN, TAILLEPOLICE), FRANÇAIS = new Font (NOMPOLICE, Font.PLAIN, TAILLEPOLICE) ;

    private static final boolean DEBUG = true;    
}
