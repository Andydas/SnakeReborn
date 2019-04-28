/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objekty;

import java.util.Random;
import snake.HraciePole;
import snake.EnumSegment;
import snake.SegmentPola;

/**
 *
 * @author Andy
 */
public class Potrava extends SegmentPola{
    
    
    private Random random;
    
    
    
    
    public Potrava(int riadok, int stlpec, String nazovSuboru, String farba, HraciePole hraciePole) {
        super(riadok, stlpec, nazovSuboru, farba, hraciePole);
        this.random = new Random();
        this.vytvor();
    }
    
    public void vytvor() {
        super.setRiadok(this.random.nextInt(super.getHraciePole().getVyska()) + 0); 
        super.setStlpec(this.random.nextInt(super.getHraciePole().getSirka()) + 0);
        while (!this.povolenySpawn()) {
            super.setRiadok(this.random.nextInt(super.getHraciePole().getVyska()) + 0); 
            super.setStlpec(this.random.nextInt(super.getHraciePole().getSirka()) + 0);
        }
        this.prefarbiStvorec();
        this.nastavSuradniceObrazka(super.getRiadok(), super.getStlpec());
        this.zobraz();
        
    }
    
    public boolean povolenySpawn() {
        if (super.getHraciePole().obsadenyStvorec(super.getRiadok(), super.getStlpec()) == EnumSegment.POLE) {
            return true;
        }
        return false;
    }
    
}
