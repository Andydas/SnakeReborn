package Objekty;

import snake.HraciePole;




/**
 * Uklada suradnice a farbu jednotlivych stvorcov v hracom poli ako segment pola.
 */
public class StvorcovySegment {
    private int riadok;
    private int stlpec;
    private String farba;
    private HraciePole hraciePole;
    
    /**
     * Vytvori novy segment pola podla zadaneho riadku, stlpca a farby.
     */
    public StvorcovySegment(int riadok, int stlpec, String farba, HraciePole hraciePole) {
        this.farba = farba;
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.hraciePole = hraciePole;
    }
    
    /**
     * Zobrazi dany segment tak, ze stvorec hracieho pola na rovnakych suradniciach prefarbi podla zadanej farby.
     */
    public void zobraz() {
        this.hraciePole.prefarbiStvorec(this.riadok, this.stlpec, this.farba);
    }
    
    /**
     * Skryje dany segment tak, farebny stvorec hracieho pola zmeni naspat na ciernu farbu.
     */
    public void skry() {
        this.hraciePole.prefarbiStvorec(this.riadok, this.stlpec, "black");
    }
    
    /**
     * Zmeni riadok a stlpec aktualneho segmentu.
     */
    public void nastav(int riadok, int stlpec) {
        this.riadok = riadok;
        this.stlpec = stlpec;
    }
    
    /**
     * Vrati aktualny riadok segmentu.
     */
    public int getRiadok() {
        return this.riadok;
    }
    
    /**
     * Vrati aktualny stlpec segmentu.
     */
    public int getStlpec() {
        return this.stlpec;
    }
}
