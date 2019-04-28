/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import Grafika.Obrazok;

/**
 *
 * @author Andy
 */
public class SegmentPola {
    private Obrazok obrazok;
    private String nazovSuboru;
    private int riadok;
    private int stlpec;
    private HraciePole hraciePole;
    private String farba;

    public SegmentPola(int riadok, int stlpec, String nazovSuboru, String farba, HraciePole hraciePole) {
        this.nazovSuboru = nazovSuboru;
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.farba = farba;
        this.hraciePole = hraciePole;
        this.obrazok = new Obrazok(riadok  * 32 + 31, stlpec *32 , nazovSuboru);
    }
    
    public void zobraz() {
        this.obrazok.zobraz();
    }
    
    public void skry() {
        this.obrazok.skry();
    }
    
    public void prefarbiStvorec() {
        this.hraciePole.prefarbiStvorec(this.riadok, this.stlpec, this.farba);
    }
    
    public void skryStvorec() {
        this.hraciePole.prefarbiStvorec(this.riadok, this.stlpec, "black");
    }
    
    public void nastav(int riadok, int stlpec) {
        this.riadok = riadok;
        this.stlpec = stlpec;
    }

    public int getRiadok() {
        return riadok;
    }

    public int getStlpec() {
        return stlpec;
    }

    public String getFarba() {
        return farba;
    }

    public HraciePole getHraciePole() {
        return hraciePole;
    }

    public void setRiadok(int riadok) {
        this.riadok = riadok;
    }

    public void setStlpec(int stlpec) {
        this.stlpec = stlpec;
    }
    
    public void nastavSuradniceObrazka(int riadok, int stlpec) {
        this.obrazok.zmenPolohu( stlpec *32 ,riadok  * 32 + 31 );
    }
    
    public void zmenObrazok(String nazov) {
        this.obrazok.zmenObrazok(nazov);
        this.obrazok.zobraz();
    }
    
    public boolean jeViditelny() {
        return this.obrazok.jeViditelny();
    }
     
    
    
}
