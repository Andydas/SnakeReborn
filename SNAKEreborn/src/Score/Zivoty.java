/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Score;

import Grafika.Obrazok;

/**
 *
 * @author Andy
 */
public class Zivoty {
    
    
    private Obrazok zivoty;
    private int hodnotaZivotov;
    private SegmentovePismeno dotDot;
    private SegmentoveCislo jednotky;
    
    public Zivoty(int zivoty) {
        this.hodnotaZivotov = zivoty;
        this.zivoty = new Obrazok(0, 16 * 14, "heart.png");
        this.zivoty.zobraz();
        this.dotDot = new SegmentovePismeno(16 * 16, 2, ':');
        this.jednotky = new SegmentoveCislo(16 * 17, 2, this.hodnotaZivotov);
    }
    
    public void nastavZivoty(int zmena) {
        this.jednotky.zobraz(zmena);
    }
}
