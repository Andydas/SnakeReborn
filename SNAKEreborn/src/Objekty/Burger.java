/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objekty;

import java.util.Random;
import snake.HraciePole;

    
/**
 *
 * @author Andy
 */
public class Burger extends Potrava {
    
    private String nazov;
    private Poop prekazka;
    private Random random;
    
    public Burger(int riadok, int stlpec, String nazovSuboru, String farba, HraciePole hraciePole) {
        super(riadok, stlpec, nazovSuboru, farba, hraciePole);
        this.nazov = "burger";
        this.random = new Random();
    }
    
    public void spawn() {
        int sanca = this.random.nextInt(100) + 0;
        if (sanca <= 10) {
            vytvor();
        }
    }

    public String getNazov() {
        return nazov;
    }


    
    
    
}
