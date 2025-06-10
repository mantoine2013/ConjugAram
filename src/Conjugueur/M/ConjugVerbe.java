package Conjugueur.M;
import javax.swing.event.EventListenerList;
 
public class ConjugVerbe extends Conjug { 
 
    public ConjugVerbe(short mode, short temps, short voix, int iVerbe){
        super(mode, temps, iVerbe);
       if (DEBUG) System.out.println ("ConjugVerbe::ConjugVerbe") ; 
//        conjTableM = new ConjTableModelLat();
        listeners = new EventListenerList();
    }
 
    public static final String XMLSOURCE = "ConjugAraM.xml" ;
    public static ModèleMenu1 comboBoxVerbemodel = new ModèleMenu1(XMLSOURCE, "verbe", "Infinitif")  ; 
    private static final boolean DEBUG = true;
}