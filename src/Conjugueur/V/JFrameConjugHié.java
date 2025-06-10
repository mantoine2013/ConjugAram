/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;

import Conjugueur.M.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.text.DefaultFormatter;
import static Conjugueur.V.JFrameConjug.frame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class JFrameConjugHié extends JFrameConjug implements ActionListener {
    private JPanel contentPane = null;
    private JFormattedTextField field = null;
    private JButton button = null;
    private NumberFormat format = null;
 
    public JFrameConjugHié(Conjugueur.C.ConjugHié contrôleur) {
        this(contrôleur, 0);
    }
 
    public JFrameConjugHié(Conjugueur.C.ConjugHié contrôleur, int conjugHié){
        super(contrôleur); 
        if (DEBUG) System.out.println("Conjugueur.Vue.ConjugHié::JFrameConjugHié") ;
 
        buildPanel(conjugHié);
    }
 
    private void buildPanel(int conjugHié) {
        if (DEBUG) System.out.println("Conjugueur.Vue.ConjugHié::buildPanel") ;
        frame = new JFrame();
        contentPane = new JPanel();
 
        format = NumberFormat.getNumberInstance();
        format.setParseIntegerOnly(true);
        format.setGroupingUsed(false);        
        format.setMaximumFractionDigits(0);
        format.setMaximumIntegerDigits(3);
 
        field = new JFormattedTextField(format);
        field.setValue(conjugHié);
        ((DefaultFormatter)field.getFormatter()).setAllowsInvalid(false);
        contentPane.add(field);
 
        button = new JButton("Mettre à jour");
        button.addActionListener(this);
        contentPane.add(button);
        jPanelVerbe.setLayout(new FlowLayout()) ; jPanelVerbe.add(JLABELVERBE) ; jPanelVerbe.add(jComboBoxVerbe) ;
 
        frame.setContentPane(contentPane);
        frame.setTitle("Conjugueur Hiéroglyphe V0.8");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(LARGEUR, HAUTEUR);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(screenSize.width/2 - (frame.getSize().width/2),screenSize.height/2 - (frame.getSize().height/2));
    }
 
    @Override
    public void close() {
        frame.dispose(); 
    }
 
    @Override
    public void display() {
        frame.setVisible(true);
    }
 
 
    private final JPanel jPanelVerbe = new JPanel() ;
    private static final JLabel JLABELTEMPS = new JLabel("Temps : "), JLABELVERBE = new JLabel("Verbe : "), JLABELVOIX = new JLabel("Voix : ");
    public static final int HAUTEUR = 600, LARGEUR = 1400 ;
    private final static String ATTRIBUT = "Infinitif", AUTREMOT= "A", BALISÉNVERBE = "verhi", BALISÉNAUTREMOT = "autremot", ÉTAT = "É", EXPORT = "E", POLICE = "P", SCHEME = "S", TEMPS = "T", VERBE = "V", NOMPOLICE = "JSesh font", NOMPOLICEDECLINAISONS = "Serto Jerusalem", SYRIAC_TO_LATIN = "Syriac-Latin", XMLSOURCE = "ConjugHié.xml" ;
    private static final int TAILLEPOLICE = 25, TAILLEPOLICEDECLINAISONS = 25 ;
    public static final Font HIÉROGLYPHE = new Font (NOMPOLICE, Font.PLAIN, TAILLEPOLICE), FRANÇAIS = new Font (NOMPOLICE, Font.PLAIN, TAILLEPOLICE) ;
    private final static ModèleMenu1 comboBoxVerbemodel = new ModèleMenu1(XMLSOURCE, BALISÉNVERBE, ATTRIBUT) ; 
    @SuppressWarnings("unchecked")
    public  final static JComboBox<String> jComboBoxVerbe = new JComboBox<>(comboBoxVerbemodel);
    private static final boolean DEBUG = true;    
    
}
