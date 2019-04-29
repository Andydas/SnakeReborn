package snake;

import Grafika.Obrazok;
import Objekty.Burger;
import Objekty.Had;
import Objekty.Jablko;
import Objekty.Potrava;
import Objekty.Srdce;
import Score.HighScore;
import Score.Score;
import Score.Zivoty;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Ovláda všetky časti hry a triedy ktoré ju tvoria
 */
public class Hra {

    private Had had;
    private Score zobrazovacScore;
    private HighScore zobrazovacHighScore;
    private int score;
    private int highScore;
    private String menoHighScore;
    private String menoHraca;
    private ManazerHry manazerHry;
    private boolean povolenyInput;
    private boolean koniecHry;
    private boolean pauza;
    private HraciePole hraciePole;
    private Jablko jablko;
    private Burger burger;
    private Srdce srdce;
    private int dlzkaBurger;
    private int dlzkaSrdca;
    private Zivoty zobrazovacZivoty;
    private int zivoty;
    

    /**
     * Vytvori novu hru snake. Pociatocne skore nastavene na 0, zakazana zmena
     * smeru.
     */
    public Hra() {
        this.score = 0;
        try {
            this.highScore = this.citajHighscore();
            this.menoHighScore = this.citajMeno();
        } catch(IOException e) {
            System.out.println("Subor s highscore sa nenasiel");
        }
        this.zivoty = 1;
        this.zobrazovacScore = new Score(this.score);
        this.zobrazovacHighScore = new HighScore(this.highScore);
        this.zobrazovacZivoty = new Zivoty(this.zivoty);
        this.hraciePole = new HraciePole();
        this.had = new Had(3, "green", this.hraciePole);
        this.manazerHry = new ManazerHry();
        this.manazerHry.spravujObjekt(this);
        this.povolenyInput = false;
        this.koniecHry = false;
        this.vytvorPotravu("jablko");
        this.vytvorPotravu("burger");
        this.vytvorPotravu("srdce");
        this.pauza = true;
        

    }

    public void vytvorPotravu(String paPotrava) {
        switch (paPotrava) {
            case "jablko": {
                this.jablko = new Jablko(0, 0, "apple.png", "red", this.hraciePole);
                this.had.setJablko(this.jablko);
                this.jablko.zobraz();
                break;
            }
            case "burger": {
                this.burger = new Burger(0,0, "burger.png", "yellow", this.hraciePole);
                this.burger.nastavSuradniceObrazka(0, 0);
                this.burger.skry();
                this.burger.skryStvorec();
                this.had.setBurger(this.burger);
                this.dlzkaBurger = 0;
                break;
            }
            case "srdce": {
                this.srdce = new Srdce(0,0, "heart.png", "pink", this.hraciePole);
                this.srdce.nastavSuradniceObrazka(0, 0);
                this.srdce.skry();
                this.srdce.skryStvorec();
                this.had.setSrdce(this.srdce);
                this.had.setZivoty(this.zivoty);
                this.dlzkaSrdca = 0;
                break;
            }
        }
    }

    /**
     * Posiela spravu hadovi aby zmenil smer pohybu smerom DOLE a zakaze dalsiu
     * zmenu smeru. Metoda je osetrena aby sa had neotocil sam do seba.
     */
    public void posunD() {
        if (this.had.getSmer() == 'h') {
            return;
        } else if (this.povolenyInput) {
            this.had.urciSmer('d');
            this.povolenyInput = false;
        }
    }

    /**
     * Posiela spravu hadovi aby zmenil smer pohybu smerom HORE a zakaze dalsiu
     * zmenu smeru. Metoda je osetrena aby sa had neotocil sam do seba.
     */
    public void posunH() {
        if (this.had.getSmer() == 'd') {
            return;
        } else if (this.povolenyInput) {
            this.had.urciSmer('h');
            this.povolenyInput = false;
        }
    }

    /**
     * Posiela spravu hadovi aby zmenil smer pohybu smerom DOLAVA a zakaze
     * dalsiu zmenu smeru. Metoda je osetrena aby sa had neotocil sam do seba.
     */
    public void posunL() {
        if (this.had.getSmer() == 'r') {
            return;
        } else if (this.povolenyInput) {
            this.had.urciSmer('l');
            this.povolenyInput = false;
        }
    }

    /**
     * Posiela spravu hadovi aby zmenil smer pohybu smerom DOPRAVA a zakaze
     * dalsiu zmenu smeru. Metoda je osetrena aby sa had neotocil sam do seba.
     */
    public void posunR() {
        if (this.had.getSmer() == 'l') {
            return;
        } else if (this.povolenyInput) {
            this.had.urciSmer('r');
            this.povolenyInput = false;
        }
    }

    /**
     * Posiela spravu hadovi aby obratil hodnotu pauzy. Metoda je osetrena aby
     * had nemohol menit smer ked je hra zastavena.
     */
    public void nastavPauzu() {
        this.pauza = !this.pauza;
        this.had.setPauza(pauza);
    }

    /**
     * Vrati ciselnu hodnotu score v int.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Vrati boolean hodnotu pauzy, TRUE = hra je pozastavena, FALSE = hra nie
     * je pozastavena.
     */
    public boolean getPauza() {
        return this.had.getPauza();
    }

    /**
     * Posiela spravu hadovi aby vykonal pohyb. Prva podmienka osetruje aby sa
     * nic nevykonalo pokial je hra pozastavena alebo ukoncena Druha podmienka
     * osetruje aby sa hra ukoncila ak had do niecoho narazil Tretia podmienka
     * vykonava pohyb hada a zaroven pripocita 10 score ak had zjedol jablko
     */
    public void pohyb() {
        if (this.koniecHry || this.had.getPauza()) {
            return;
        }
        if (this.had.kontrolaPrehry()) {
            this.ukonciHru();
            return;
        }
        if (!this.had.kontrolaPrehry()) {
            if (this.had.zjedolPotravu(EnumSegment.JABLKO)) {
                this.score += 10;
                this.zobrazovacScore.noveScore(this.score);
                this.burger.spawn();
                this.dlzkaBurger = 0;
                this.srdce.spawn();
                this.dlzkaSrdca = 0;
            } else if (this.had.zjedolPotravu(EnumSegment.BURGER)) {
                this.score += 30;
                this.zobrazovacScore.noveScore(this.score);
            } else if (this.had.zjedolPotravu(EnumSegment.SRDCE)) {
                this.zivoty += 1;
                this.had.setZivoty(this.zivoty);
                this.zobrazovacZivoty.nastavZivoty(this.zivoty);
            }
            this.had.pohyb();
            this.povolenyInput = true;
        }
        if (this.zivoty != this.had.getZivoty()) {
            this.zivoty = this.had.getZivoty();
            this.zobrazovacZivoty.nastavZivoty(this.zivoty);
        }
        if (this.burger.jeViditelny()) {
            ++this.dlzkaBurger;
            System.out.println("Burger " + this.dlzkaBurger);
            if (this.dlzkaBurger == 35){
                this.skryObjekt("burger");
            }
        }
        if (this.srdce.jeViditelny()) {
            ++this.dlzkaSrdca;
            System.out.println("Srdce " + this.dlzkaSrdca);
            if (this.dlzkaSrdca == 35){
                this.skryObjekt("srdce");
            }
        }
    }
    
    public void skryObjekt(String objekt) {
        switch (objekt) {
            case "burger": {
                this.burger.skry();
                this.burger.skryStvorec();
                break;
            }
            case "srdce" : {
                this.srdce.skry();
                this.srdce.skryStvorec(); 
                break;
            }
        }
    }

    /**
     * Ukonci hru hada, vypise dosiahnute score a restartuje hru.
     */
    public void ukonciHru() {
        
        if (this.jeHighscore()) {
            this.zobrazovacZivoty.nastavZivoty(0);
            this.menoHraca = JOptionPane.showInputDialog("Zadaj svoje meno.");
            System.out.println(this.menoHraca);
            try {
                this.zapisHighsore(this.score, this.menoHraca);
                this.highScore = this.citajHighscore();
            } catch (IOException e) {
                System.out.println("Neexistuje subor");
            }
            JOptionPane.showMessageDialog(null, "Koniec hry, podarilo "
                    + "sa ti prekonat hraca " + this.menoHighScore + 
                    ", tvoje score je: " + this.score, "Koniec Hry"
                    , JOptionPane.INFORMATION_MESSAGE);
            this.koniecHry = true;
            this.restartujHru();
            return;
        } else {
            this.zobrazovacZivoty.nastavZivoty(0);
            JOptionPane.showMessageDialog(null, "Koniec hry, nepodarilo "
                    + "sa ti prekonat hraca " + this.menoHighScore + 
                    ", tvoje score je: " + this.score, "Koniec Hry"
                    , JOptionPane.INFORMATION_MESSAGE);
            this.koniecHry = true;
            this.restartujHru();
            return;
        }
        
       
    }

    /**
     * Restartuje hru. Vymaze sa stary had, vykresi sa novy Vymaze sa stare
     * jablko, vykresli sa nove Score sa vynuluje a vynuluje sa aj na diplayi
     */
    public void restartujHru() {
        this.had.vymazHada();
        this.had.vytvorHada();
        this.had.vymazPrekazky();
        this.burger.skry();
        this.burger.skryStvorec();
        this.jablko.skry();
        this.jablko.skryStvorec();
        this.jablko.vytvor();
        this.srdce.skry();
        this.srdce.skryStvorec();
        this.score = 0;
        this.zivoty = 1;
        this.had.setZivoty(this.zivoty);
        this.zobrazovacZivoty.nastavZivoty(this.zivoty);
        this.zobrazovacScore.noveScore(this.score);
        this.zobrazovacHighScore.noveScore(this.highScore);
        this.koniecHry = false;
        this.povolenyInput = false;
        this.pauza = true;
        try {
            this.menoHighScore = this.citajMeno();
        } catch (IOException e) {
            System.out.println("Subor sa nenasiel");
        }
        
    }

    public void zapisHighsore(int score, String meno) throws IOException {
        File subor = new File("highscore.txt");
        PrintWriter zapisovac = new PrintWriter(subor);
        zapisovac.println(score);
        zapisovac.println(meno);
        zapisovac.close();
    }

    public int citajHighscore() throws IOException {
        File subor = new File("highscore.txt");
        Scanner citac = new Scanner(subor);
        int tempScore;
        tempScore = citac.nextInt();
        citac.close();
        return tempScore;
    }
    
    public String citajMeno() throws IOException {
        File subor = new File("highscore.txt");
        Scanner citac = new Scanner(subor);
        String tempMeno;
        citac.nextLine();
        tempMeno = citac.nextLine();
        return tempMeno;
    }

    public boolean jeHighscore() {
        try {
            int highscore = this.citajHighscore();
            if (highscore < this.score) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            System.out.println("subor neexistuje");
        }
        return true;
    }

    public void vykresliSiet() {
        this.hraciePole.vykresliStvorcovuSiet();
    }

}
