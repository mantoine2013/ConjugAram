/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import javax.swing.event.EventListenerList;

public class ConjugSans extends Conjug {

    /**
     * Fction appelante : Conjugueur.M.ConjugSans::ConjugSans
     * @param é
     * @return 
     */
    public ConjugSans(short mode, short temps, short voix, int iVerbe){
        super(mode, temps, iVerbe);
        if (DEBUG) { System.out.println("Conjugueur.Modèle.ConjugSans::ConjugSans, iAM = " + iAM) ; } 
        this.mode = mode ;  this.temps = temps ;
        conjTableM = new ConjTableSans() ; declTableM = new DeclTableSans() ;
        listeners = new EventListenerList() ;
    }
    
    public static final String XMLSOURCE = "ConjugSans.xml", ATTRIBUTVERBE = "forme", BALISÉNVERBE = "verbsans", BALISÉNAUTREMOT = "autremot" ;
    public final static ModèleMenu1 comboBoxAutreMotM = new ModèleMenu1(XMLSOURCE, BALISÉNAUTREMOT, ATTRIBUTVERBE) ;
    public final static ModèleMenu1 cbVerbeM = new ModèleMenu1(XMLSOURCE, BALISÉNVERBE, ATTRIBUTVERBE) ;
    protected EventListenerList listeners;  
    private final boolean DEBUG = true;      
}
