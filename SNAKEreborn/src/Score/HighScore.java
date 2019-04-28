/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Score;

/**
 *
 * @author Andy
 */
public class HighScore {
    private SegmentovePismeno pismenoH;
    private SegmentovePismeno pismenoI;
    private SegmentovePismeno pismenoG;
    private SegmentovePismeno pismenoH1;
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
    
    public HighScore(int hodnotaScore) {
        this.hodnotaScore = hodnotaScore;
        this.pismenoI = new SegmentovePismeno(16 * 29 - 1, 2, 'i');
        this.pismenoH = new SegmentovePismeno(16 * 28 + 10, 2, 'h');
        this.pismenoG = new SegmentovePismeno(16 * 30, 2, 'g');
        this.pismenoH1 = new SegmentovePismeno(16 * 31, 2, 'h');
        this.pismenoS = new SegmentovePismeno(16 * 32, 2, 's');
        this.pismenoC = new SegmentovePismeno(16 * 33, 2, 'c');
        this.pismenoO = new SegmentovePismeno(16 * 34, 2, 'o');
        this.pismenoR = new SegmentovePismeno(16 * 35, 2, 'r');
        this.pismenoE = new SegmentovePismeno(16 * 36, 2, 'e');
        this.dotDot = new SegmentovePismeno(16 * 37, 2, ':');
        this.tisicky = new SegmentoveCislo(16 * 38, 2, this.hodnotaScore / 1000);
        this.stovky = new SegmentoveCislo(16 * 39, 2, (this.hodnotaScore % 1000) / 100);
        this.desiatky = new SegmentoveCislo(16 * 40, 2, (this.hodnotaScore % 100) / 10);
        this.jednotky = new SegmentoveCislo(16 * 41, 2, (this.hodnotaScore % 10) / 1);
    }
    
    public int getHighScore() {
        return this.hodnotaScore;
    }
    
    public void noveScore(int score) {
        this.hodnotaScore = score;
        this.tisicky.zobraz(this.hodnotaScore / 1000);
        this.stovky.zobraz((this.hodnotaScore % 1000) / 100);
        this.desiatky.zobraz((this.hodnotaScore % 100) / 10);
        this.jednotky.zobraz((this.hodnotaScore % 10) / 1);
    }
}

