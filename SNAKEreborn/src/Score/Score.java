package Score;

import Grafika.Obdlznik;


/**
 * Sekcia, ktora vykresli slovo "Score:" pomocou segmentovych pismen a za nim ciselnu hodnotu score podla zadaneho vstupu.
 */
public class Score {
    private SegmentovePismeno pismenoS;
    private SegmentovePismeno pismenoC;
    private SegmentovePismeno pismenoO;
    private SegmentovePismeno pismenoR;
    private SegmentovePismeno pismenoE;
    private SegmentovePismeno dotDot;
    private SegmentoveCislo jednotky;
    private SegmentoveCislo desiatky;
    private SegmentoveCislo stovky;
    private SegmentoveCislo tisicky;
    private int hodnotaScore;
    
    /**
     * Vytvori sa sekcia nad hracim polom, kde sa zobrazi napis "Score:" a aktualne score.
     * Sekcia ma vysku 30 a sirku 390. Biele pismena a cisla na ciernom pozadi.     
     */
    public Score(int hodnotaScore) {
        this.hodnotaScore = hodnotaScore;
        this.pismenoS = new SegmentovePismeno(16 * 0, 2, 's');
        this.pismenoC = new SegmentovePismeno(16 * 1, 2, 'c');
        this.pismenoO = new SegmentovePismeno(16 * 2, 2, 'o');
        this.pismenoR = new SegmentovePismeno(16 * 3, 2, 'r');
        this.pismenoE = new SegmentovePismeno(16 * 4, 2, 'e');
        this.dotDot = new SegmentovePismeno(16 * 5, 2, ':');
        this.tisicky = new SegmentoveCislo(16 * 6, 2, this.hodnotaScore / 1000);
        this.stovky = new SegmentoveCislo(16 * 7, 2, (this.hodnotaScore % 1000) / 100);
        this.desiatky = new SegmentoveCislo(16 * 8, 2, (this.hodnotaScore % 100) / 10);
        this.jednotky = new SegmentoveCislo(16 * 9, 2, (this.hodnotaScore % 10) / 1);
    }
    
    /**
     * Vrati aktualnu hodnotu score.
     */
    public int getScore() {
        return this.hodnotaScore;
    }
    
    /**
     * Prepise aktualne zobrazenu ciselnu hodnotu score podla zadaneho vstupu.
     */
    public void noveScore(int score) {
        this.hodnotaScore = score;
        this.tisicky.zobraz(this.hodnotaScore / 1000);
        this.stovky.zobraz((this.hodnotaScore % 1000) / 100);
        this.desiatky.zobraz((this.hodnotaScore % 100) / 10);
        this.jednotky.zobraz((this.hodnotaScore % 10) / 1);
    }
   
    
   
}
