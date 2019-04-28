package Objekty;



import snake.HraciePole;
import java.util.Random;
import snake.SegmentPola;
/**
 * Jablko, ktore sluzi ako potrava pre hada.
 * Po tom ako had zje jablko, vykresli sa nove.
 * Suradnice jablka su generovane nahodne a tak, aby boli vzdy mimo hada a mimo okraj.
 */
public class Jablko extends Potrava{
    
    private String nazov;
    private int zmena;

    /**
     * Vytvori prve jablko na nahodnych suradniciach.
     */
    public Jablko(int riadok, int stlpec, String nazovSuboru, String farba, HraciePole hraciePole) {
        super(riadok, stlpec, nazovSuboru, farba, hraciePole);
        this.nazov = "jablko";
        this.zmena = 1;
    }

    public String getNazov() {
        return nazov;
    }
    
    public int getZmena() {
        return this.zmena;
    }
     
    
    
    
    
    
   
    
    
    /**
     * Vytvori nove jablko na nahodnych suradniciach.
     */
    
    
    /**
     * Kontroluje ci genrovane suradnice jablka su mimo hada a mimo steny hracieho pola
     */
    
    
    
}
