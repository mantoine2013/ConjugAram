/*
 * Copyright (C) 2026 mau
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Conjugueur.M;

import static Conjugueur.M.Conjug.conjTableM;
import static Conjugueur.M.Conjug.declTableM;
import static Conjugueur.M.ConjugAnFr.ATTRIBUT;
import static Conjugueur.M.ConjugAnFr.BALISÉNAUTREMOT;
import static Conjugueur.M.ConjugAnFr.BALISÉNVERBE;
import static Conjugueur.M.ConjugAnFr.LANGUE;
import javax.swing.event.EventListenerList;

/**
 *
 * @author mau
 */
public class ConjugTurc extends Conjug {
    public ConjugTurc(short mode, short temps, short voix, int iVerbe, int iAM, short nb){
        super(mode, temps, iVerbe);
        if (DEBUG) { System.out.println("Conjugueur.M.ConjugTurc::ConjugTurc, mode = " +  Conjugueur.M.Conjug.M.i2M(mode).toString()  + ", temps = " + Conjugueur.M.ConjugTurc.TTurc.i2T(temps).toString() + ", iAM = " + iAM +", iVerbe = "+ ConjugTurc.cbVerbeM.get(iVerbe).getAttributeValue(ConjugTurc.ATTRIBUT) ) ; } 
        this.iAM = iAM ; this.écriture = écriture ; this.nb = nb ;
        conjTableM = new ConjTableTurc();
        declTableM = new DeclTableTurc() ;
        listeners = new EventListenerList() ;
    }
    /*
     * Temps pour l'ancien français
     */
    public enum TTurc { Présent((short)0), Infinitif((short)2) ;
        private final short it;
        TTurc (short it) { this.it = it ;  }
        public short it() { return it; }    
        public static TTurc l2T(String temps) { TTurc result = null;
            for (TTurc t : TTurc.values()) if (t.name().equals(temps)) { result = t;  break;  }
            return result;
        }                                                                       //l2T
        public static TTurc i2T(short temps) { TTurc result = null;
            for (TTurc t : TTurc.values()) { if (temps == t.it()) { result = t ;  break; }}
            return result;
        }                                                                       //i2TTurc
    } 
    public static final String ATTRIBUT = "infinitif", BALISÉNAUTREMOT = "autremot", BALISÉNVERBE = "verbturc", LANGUE = "fr", XMLSOURCE = "ConjugTurc.xml";
    public final static ModèleMenu1 cbVerbeM = new ModèleMenu1(XMLSOURCE, BALISÉNVERBE, ATTRIBUT, LANGUE) ; 
    public final static ModèleMenu1 cbAutreMotM = new ModèleMenu1(XMLSOURCE, BALISÉNAUTREMOT, ATTRIBUT, LANGUE) ;
    private static final boolean DEBUG = true;      
    
}
