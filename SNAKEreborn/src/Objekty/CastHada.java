/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objekty;

import snake.HraciePole;
import snake.SegmentPola;

/**
 *
 * @author Andy
 */
public class CastHada extends SegmentPola {

    private char smer;
    
    public CastHada(int riadok, int stlpec, String nazovSuboru, String farba, HraciePole hraciePole) {
        super(riadok, stlpec, nazovSuboru, farba, hraciePole);
    }

    public char getSmer() {
        return smer;
    }

    public void setSmer(char smer) {
        this.smer = smer;
    }
    
    
    
}
