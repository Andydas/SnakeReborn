package snake;

import Grafika.Obrazok;
import Grafika.Stvorec;

/**
 * Hracie pole na ktorom sa pohybuje had a vykresluje sa jablko
 */
public class HraciePole {

    private Stvorec[][] stvorce;
    private Obrazok pozadie;
    private boolean viditelnaSiet;

    /**
     * Vytvori nove hracie pole s rozmerom 20x20 stvorcov s rozmerom 15x15
     * pricom kazdy stvorec ma okraj 1. Hracie pole ma modry okraj ktory je
     * chapany ako stena pola.
     */
    public HraciePole() {

        this.pozadie = new Obrazok(31, 0, "pozadie.png");
        this.pozadie.zobraz();
        this.stvorce = new Stvorec[21][21];
        for (int i = 0; i < this.stvorce.length; ++i) {
            for (int k = 0; k < this.stvorce[i].length; ++k) {
                this.stvorce[i][k] = new Stvorec(k * 32, 32 + i * 32, "black");
            }
        }
        //nastav okraj
        for (int i = 0; i < 21; ++i) {
            this.stvorce[0][i].zmenFarbu("blue");
            this.stvorce[i][20].zmenFarbu("blue");
            this.stvorce[20][i].zmenFarbu("blue");
            this.stvorce[i][0].zmenFarbu("blue");
        }
        this.viditelnaSiet = false;
    }

    public void vykresliStvorcovuSiet() {
        if (!this.viditelnaSiet) {
            for (int i = 0; i < this.stvorce.length; ++i) {
                for (int k = 0; k < this.stvorce[i].length; ++k) {
                    this.stvorce[i][k].zobraz();
                }
            }
            this.viditelnaSiet = !this.viditelnaSiet;
        } else {
            for (int i = 0; i < this.stvorce.length; ++i) {
                for (int k = 0; k < this.stvorce[i].length; ++k) {
                    this.stvorce[i][k].skry();
                }
            }
            this.viditelnaSiet = !this.viditelnaSiet;
        }
    }

    /**
     * Kontroluje cim je obsadeny stvorec pola. Jablko (cerveny stvorec) = 'j'
     * Had (zeleny stvorec) = 'h' Okraj (stena) pola (modry stvorec) = 'o' Volne
     * policko pola (cierny stvorec) = 'n'
     */
    public EnumSegment obsadenyStvorec(int riadok, int stlpec) {
        if (this.stvorce[riadok][stlpec].getFarba().equals("red")) {
            return EnumSegment.JABLKO;
        } else if (this.stvorce[riadok][stlpec].getFarba().equals("yellow")) {
            return EnumSegment.BURGER;
        } else if (this.stvorce[riadok][stlpec].getFarba().equals("pink")) {
            return EnumSegment.SRDCE;
        } else if (this.stvorce[riadok][stlpec].getFarba().equals("green")) {
            return EnumSegment.PREKAZKA;
        } else if (this.stvorce[riadok][stlpec].getFarba().equals("brown")) {
            return EnumSegment.POOP;
        } else if (this.stvorce[riadok][stlpec].getFarba().equals("blue")) {
            return EnumSegment.PREKAZKA;
        } else {
            return EnumSegment.POLE;
        }
    }

    /**
     * Prefarbuje jednotlive stvorce pola podla zadanych suradnic na danu farbu.
     */
    public void prefarbiStvorec(int riadok, int stlpec, String farba) {
        if (riadok < this.getVyska()) {
            if (stlpec < this.getSirka()) {
                this.stvorce[riadok][stlpec].zmenFarbu(farba);
            } else {
                return;
            }
        } else {
            return;
        }
    }

    /**
     * Vracia sirku pola (pocet stvorcov na sirku).
     */
    public int getSirka() {
        return this.stvorce[0].length;
    }

    /**
     * Vracia vysku pola (pocet stvorcov na vysku).
     */
    public int getVyska() {
        return this.stvorce.length;
    }
}
