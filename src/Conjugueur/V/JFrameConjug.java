/**
 * @author MichelANTOINE@hotmail.com
 * Surclasse de JFrameConjDouble
 */
package Conjugueur.V;
import Conjugueur.M.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class JFrameConjug implements ConjugListener {

    public JFrameConjug(Conjugueur.C.Conjug contrôleur){
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjug::JFrameConjug") ; }          
        this.contrôleur = contrôleur;
        frame.setLayout(new java.awt.BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new java.awt.BorderLayout());
        jPanelAutreMot.setLayout(new FlowLayout()) ; jPanelAutreMot.add(jLabelAutreMot) ; 
        jPanelDroit.setLayout(new java.awt.BorderLayout()); 
        jPanelÉcriture.setLayout(new FlowLayout()) ; jPanelÉcriture.add(JLABELÉCRITURE) ;
        jPanelÉtat.setLayout(new FlowLayout()) ;  jPanelÉtat.add(JLabelÉtat) ;
        jPanelGauche.setLayout(new java.awt.BorderLayout());
        jPanelMode.setLayout(new FlowLayout());     jPanelMode.add(jLabelMode);
        jPanelNb.setLayout(new FlowLayout()) ;          jPanelNb.add(JLabelNb) ;   
        jPanelAspects.setLayout(new FlowLayout());    jPanelAspects.add(jLabelAspect);
        jPanelSchème.setLayout(new FlowLayout()) ; jPanelSchème.add(JLABELSCHEME) ;
        jPanelThèmes.setLayout(new FlowLayout());    jPanelThèmes.add(jLabelThèmes);
        jPanelVerbe.setLayout(new FlowLayout()) ; jPanelVerbe.add(JLABELVERBE) ;
        jPanelTemps.setLayout(new FlowLayout()) ; jPanelTemps.add(JLABELTEMPS) ;
        jPanelVoix.setLayout(new FlowLayout()) ;    jPanelVoix.add(jLabelVoix) ;
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jPanelGauche, jPanelDroit);
        splitPane.setResizeWeight(0.5);
        contentPane.setOpaque(true); //content panes must be opaque
    }

    public void close() {
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjug::close") ; }
        frame.dispose(); 
    }

    @Override  
    public void PChanged(PanelChangedEvent event) {
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjug::PChanged, event.getNewPanel() = "+ event.getNewPanel()) ; }
        switch (event.getNewPanel()) {
            case "D" -> { Conjugueur.M.Conjug.declTableM.MAJDecl(contrôleur) ; }
            case "G" -> { Conjugueur.M.Conjug.conjTableM.MAJConj(contrôleur) ; }
        }
    }                                                                           // PChanged
    public void display() {
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjug::display") ; }
        frame.pack();
        frame.setSize(largeur, hauteur);
        frame.setLocation(screenSize.width/2 - (frame.getSize().width/2),screenSize.height/2 - (frame.getSize().height/2));
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjug::actionPerformed, e.getActionCommand() = "+ e.getActionCommand()) ; }
        contrôleur.notifyVAChanged(e.getActionCommand(), ((JComboBox) e.getSource()).getSelectedIndex()) ;
    }
    public Conjugueur.C.Conjug getContrôleur() { return contrôleur ;  }
    
    public static java.util.List<org.jdom2.Element> déclinaisonChildren ;
    public static short hauteur, largeur, TAILLEPOLICE = 20, TAILLEPOLICEFRANÇAIS = 19, TAILLEPOLICEDECLINAISONS = 25 ;
    public static int tpl ;
    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public final static String ASPECT= "AS", AUTREMOT= "AU", ÉCRITURE = "ÉC", ÉTAT = "ÉT", EXPORT = "EX", MODE = "M", NOMBRE = "N", NOMPOLTNR = "Times New Roman", NOMPOLICEDECLINAISONS = "Serto Jerusalem", POLICE = "P", SCHEME = "S", TEMPS = "T", THÈME = "TH", VERBE = "V", VOIX = "VO" ;
    public static final Font FRANÇAIS = new Font (NOMPOLTNR, Font.PLAIN, TAILLEPOLICE), HÉBREU = new Font (NOMPOLTNR, Font.PLAIN, TAILLEPOLICE) ;
    public static Font SYRIAC = new Font (NOMPOLICEDECLINAISONS, Font.PLAIN, TAILLEPOLICEDECLINAISONS);
    public static JLabel jLabelAspect = new JLabel("Aspect : "), jLabelAutreMot = new JLabel("Autre mot : "), jLabelConjugaison = new JLabel("Conjugaison"), jLabelDéclinaison = new JLabel("Déclinaison"), JLABELÉCRITURE = new JLabel("Écriture : "), JLabelÉtat = new JLabel("État : "), JLabelForme = new JLabel(), JLabelNb = new JLabel("Nombre : "), jLabelMode = new JLabel("Mode : "), JLABELSCHEME = new JLabel("Schème :"), JLABELTEMPS = new JLabel("Temps : "), jLabelTemps = new JLabel("Temps : "), jLabelThèmes = new JLabel("Thèmes : "), JLABELVERBE = new JLabel("Verbe : "), jLabelVerbe = new JLabel("Verbe : "), jLabelVoix = new JLabel("Voix : ") ;
    public static JFrame frame = new JFrame () ;
    @SuppressWarnings("unchecked")
    protected static JPanel contentPane = new JPanel() ;
    public static final JPanel jPanelAspects = new JPanel(), jPanelAutreMot = new JPanel(), jPanelDéclinaison = new JPanel(), jPanelÉcriture = new JPanel(), jPanelÉtat = new JPanel(), jPanelNb = new JPanel(), jPanelDroit = new JPanel(), jPanelGauche = new JPanel(), jPanelMode = new JPanel(), jPanelSchème = new JPanel(), jPanelTemps = new JPanel(), jPanelThèmes = new JPanel(), jPanelVerbe = new JPanel(), jPanelVoix = new JPanel();
    public  JSplitPane splitPane;
    public JTableau jTabConj, jTabDecl  ;
    protected JComboBox jCBAutMot, jCBVerbe ;
    public static final JButton jButExport = new JButton("Export");
    protected  Conjugueur.C.Conjug contrôleur = null;

    private static final boolean DEBUG = false ;    
}
