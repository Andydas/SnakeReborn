/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objekty;

import java.util.Random;
import snake.HraciePole;
import snake.SegmentPola;

/**
 *
 * @author Andy
 */
public class Srdce extends Potrava {
    
    private Random random;
    private String nazov;
    
    public Srdce(int riadok, int stlpec, String nazovSuboru, String farba, HraciePole hraciePole) {
        super(riadok, stlpec, nazovSuboru, farba, hraciePole);
        this.random = new Random();
        this.nazov = "srdce";
    }
        
    public void spawn() {
        int sanca = this.random.nextInt(100) + 0;
        skry();
        skryStvorec();
        if (sanca <= 1) {
            vytvor();
        }
    }

    public String getNazov() {
        return nazov;
    }
}

