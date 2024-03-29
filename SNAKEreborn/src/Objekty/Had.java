package Objekty;

import snake.HraciePole;
import java.util.ArrayList;
import snake.EnumSegment;

/**
 * Had, jeho pohyb po poli a kontrola kolizie + vytvorenie jablka. 
 */
public class Had {

    private int riadok;
    private int stlpec;
    private int dlzka;
    private int zivoty;
    private boolean pauza;
    private char smerPohybu;
    private ArrayList<CastHada> had;
    private ArrayList<Poop> prekazky;
    private HraciePole hraciePole;
    private Jablko jablko;
    private Burger burger;
    private String farba;
    private Srdce srdce;
    private int indexPrekazky;

    /**
     * Vytvori prveho hada ako ArrayList s pozadovanou dlzkou a farbou a jablko
     * na zadanom hracom poli.
     */
    public Had(int dlzka, String farba, HraciePole hraciePole) {
        this.dlzka = dlzka;
        this.hraciePole = hraciePole;
        this.farba = farba;
        this.had = new ArrayList<CastHada>();
        this.prekazky = new ArrayList<Poop>();
        this.vytvorHada();
        this.zivoty = 1;
    }

    /**
     * Vytvori noveho hada.
     */
    public void vytvorHada() {
        this.riadok = (this.hraciePole.getVyska() / 2);
        this.stlpec = (this.hraciePole.getSirka() / 2);
        this.had.add(0, new CastHada(this.riadok, this.stlpec, "hlavaL.png", "green", this.hraciePole));
        this.had.get(0).prefarbiStvorec();
        this.had.get(0).zobraz();
        for (int i = 1; i < this.dlzka; ++i) {
            this.pridajCastHada(this.riadok, this.stlpec + i, this.farba);
            this.had.get(i).prefarbiStvorec();
            this.had.get(i).zobraz();
        }
        this.pauza = true;
        this.smerPohybu = 'l';
    }

    /**
     * Vymaze aktualneho hada.
     */
    public void vymazHada() {
        for (int i = 0; i < this.had.size(); ++i) {
            this.had.get(i).skry();
            this.had.get(i).skryStvorec();
        }
        this.had.clear();
    }

    /**
     * Prida cast hada na suradnice podla riadku, stlpca a zvolenej farby.
     */
    public void pridajCastHada(int riadok, int stlpec, String farba) {
        this.had.add(new CastHada(riadok, stlpec, "snake.png", "green", this.hraciePole));
    }

    /**
     * Skryje vykresleny stvorec na zadanych suradniciach.
     */
    public void skry(int riadok, int stlpec) {
        this.hraciePole.prefarbiStvorec(riadok, stlpec, "black");
    }

    /**
     * Zobrazi vykresleny stvorec na zadanych suradniciach.
     */
    public void zobraz(int riadok, int stlpec) {
        this.hraciePole.prefarbiStvorec(riadok, stlpec, this.farba);
    }

    /**
     * Urci smer pohybu hada (vlavo 'l', vpravo 'r', hore 'h', dole 'd').
     */
    public void urciSmer(char smer) {
        this.smerPohybu = smer;
    }

    /**
     * Vykona pohyb hada do urciteho smeru podla urceneho smeru pohybu.
     */
    public void pohyb() {
        if (!this.pauza) {
            switch (this.smerPohybu) {
                case 'd':
                    this.posunD();
                    break;

                case 'l':
                    this.posunL();
                    break;

                case 'r':
                    this.posunR();
                    break;

                case 'h':
                    this.posunH();
                    break;
            }
        } else {
            return;
        }
    }

    /**
     * Kontroluje, ci had zjedol jablko, TRUE = zjedol, FALSE = nezjedol.
     * Kontroluje sa stvorec pred hlavou hada.
     */
    public boolean zjedolPotravu(EnumSegment potrava) {
        switch (this.smerPohybu) {
            case 'd':
                if (this.hraciePole.obsadenyStvorec(this.riadok + 1, this.stlpec) == potrava) {
                    return true;
                }
                break;
            case 'l':
                if (this.hraciePole.obsadenyStvorec(this.riadok, this.stlpec - 1) == potrava) {
                    return true;
                }
                break;
            case 'r':
                if (this.hraciePole.obsadenyStvorec(this.riadok, this.stlpec + 1) == potrava) {
                    return true;
                }
                break;
            case 'h':
                if (this.hraciePole.obsadenyStvorec(this.riadok - 1, this.stlpec) == potrava) {
                    return true;
                }
                break;
        }
        return false;
    }

    public void posunHlavy() {
        switch (this.smerPohybu) {
            case 'd':
                this.had.add(0, new CastHada(this.riadok, this.stlpec, "hlavaD.png", "green", this.hraciePole));
                break;
            case 'l':
                this.had.add(0, new CastHada(this.riadok, this.stlpec, "hlavaL.png", "green", this.hraciePole));
                break;
            case 'r':
                this.had.add(0, new CastHada(this.riadok, this.stlpec, "hlavaR.png", "green", this.hraciePole));
                break;
            case 'h':
                this.had.add(0, new CastHada(this.riadok, this.stlpec, "hlavaH.png", "green", this.hraciePole));
                break;
            default:
                return;
        }
    }

    public void posunHada(boolean zjedol, EnumSegment potrava) {
        int tempRiadok = this.had.get(this.had.size() - 1).getRiadok();
        int tempStlpec = this.had.get(this.had.size() - 1).getStlpec();
        if (zjedol) {
            this.posunHlavy();
            this.had.get(0).zobraz();
            this.had.get(0).prefarbiStvorec();
            this.had.get(1).zmenObrazok("snake.png");
            switch (potrava) {
                case JABLKO: {
                    this.jablko.skry();
                    this.jablko.vytvor();
                    break;
                }
                case BURGER: {
                    this.burger.skry();
                    this.burger.nastavSuradniceObrazka(0, 0);
                    this.prekazky.add(new Poop(tempRiadok, tempStlpec, "poop.png", "brown", this.hraciePole));
                    break;
                }
                case SRDCE: {
                    System.out.println("skry");
                    this.srdce.skry();
                    this.srdce.nastavSuradniceObrazka(0, 0);
                    this.had.get(this.had.size() - 1).skry();
                    this.had.get(this.had.size() - 1).skryStvorec();
                    this.had.remove(this.had.size() - 1);
                    break;
                }
            }

        } else {
            this.posunHlavy();
            this.had.get(0).zobraz();
            this.had.get(0).prefarbiStvorec();
            this.had.get(1).zmenObrazok("snake.png");
            this.had.get(this.had.size() - 1).skry();
            this.had.get(this.had.size() - 1).skryStvorec();
            this.had.remove(this.had.size() - 1);
        }
        for (Poop prekazka : prekazky) {
            prekazka.zobraz();
            prekazka.prefarbiStvorec();
        }
    }

    /**
     * Posunie hada o jeden stvorec smerom DOLE. Prida stvorec na prve miesto o
     * riadok nizsie a odstrani posledny stvorec
     */
    public void posunD() {
        if (this.pauza) {
            return;
        } else if (this.zjedolPotravu(EnumSegment.JABLKO)) {
            ++this.riadok;
            this.posunHada(true, EnumSegment.JABLKO);
        } else if (this.zjedolPotravu(EnumSegment.BURGER)) {
            ++this.riadok;
            this.posunHada(true, EnumSegment.BURGER);
        } else if (this.zjedolPotravu(EnumSegment.SRDCE)) {
            ++this.riadok;
            this.posunHada(true, EnumSegment.SRDCE);
        } else {
            ++this.riadok;
            this.posunHada(false, null);
        }
    }

    /**
     * Posunie hada o jeden stvorec smerom DOLAVA. Prida stvorec na prve miesto
     * o stlpec nizsie a odstrani posledny stvorec
     */
    public void posunL() {
        if (this.pauza) {
            return;
        } else if (this.zjedolPotravu(EnumSegment.JABLKO)) {
            --this.stlpec;
            this.posunHada(true, EnumSegment.JABLKO);
        } else if (this.zjedolPotravu(EnumSegment.BURGER)) {
            --this.stlpec;
            this.posunHada(true, EnumSegment.BURGER);
        } else if (this.zjedolPotravu(EnumSegment.SRDCE)) {
            --this.stlpec;
            this.posunHada(true, EnumSegment.SRDCE);
        } else {
            --this.stlpec;
            this.posunHada(false, null);
        }
    }

    /**
     * Posunie hada o jeden stvorec smerom DOPRAVA. Prida stvorec na prve miesto
     * o stlpec vyssie a odstrani posledny stvorec
     */
    public void posunR() {
        if (this.pauza) {
            return;
        } else if (this.zjedolPotravu(EnumSegment.JABLKO)) {
            ++this.stlpec;
            this.posunHada(true, EnumSegment.JABLKO);
        } else if (this.zjedolPotravu(EnumSegment.BURGER)) {
            ++this.stlpec;
            this.posunHada(true, EnumSegment.BURGER);
        } else if (this.zjedolPotravu(EnumSegment.SRDCE)) {
            ++this.stlpec;
            this.posunHada(true, EnumSegment.SRDCE);
        } else {
            ++this.stlpec;
            this.posunHada(false, null);
        }
    }

    /**
     * Posunie hada o jeden stvorec smerom HORE. Prida stvorec na prve miesto o
     * riadok vyssie a odstrani posledny stvorec
     */
    public void posunH() {
        if (this.pauza) {
            return;
        } else if (this.zjedolPotravu(EnumSegment.JABLKO)) {
            --this.riadok;
            this.posunHada(true, EnumSegment.JABLKO);
        } else if (this.zjedolPotravu(EnumSegment.BURGER)) {
            --this.riadok;
            this.posunHada(true, EnumSegment.BURGER);
        } else if (this.zjedolPotravu(EnumSegment.SRDCE)) {
            --this.riadok;
            this.posunHada(true, EnumSegment.SRDCE);
        } else {
            --this.riadok;
            this.posunHada(false, null);
        }
    }

    /**
     * Kontroluje ci had nenarazil do steny alebo do seba. Hodnota true = had
     * nabural, to znamena prehru Hodnota false = pred hadom je volne policko,
     * vsetko je ok Kontroluje sa stvorec pred hlavou hada
     */
    public boolean kontrolaPrehry() {
        int tempRiadok;
        int tempStlpec;
        //System.out.println("zivoty: " + this.zivoty);
        switch (this.smerPohybu) {
            case 'd':
                EnumSegment polickoD = this.hraciePole.obsadenyStvorec(this.riadok + 1, this.stlpec);
                if (this.zivoty >= 2) {
                    if (polickoD == EnumSegment.POOP) {
                        for (int i = 0; i < this.prekazky.size(); ++i) {
                            tempRiadok = this.prekazky.get(i).getRiadok();
                            tempStlpec = this.prekazky.get(i).getStlpec();
                            if (tempRiadok == this.riadok + 1 && tempStlpec == this.stlpec) {
                                this.prekazky.get(i).skry();
                                this.prekazky.get(i).skryStvorec();
                                this.prekazky.remove(i);
                            }
                        }
                        this.zivoty = this.zivoty - 1;
                        return false;
                    }
                }
                if (polickoD == EnumSegment.PREKAZKA || polickoD == EnumSegment.POOP) {
                    return true;
                }
                break;

            case 'l':
                EnumSegment polickoL = this.hraciePole.obsadenyStvorec(this.riadok, this.stlpec - 1);
                if (this.zivoty >= 2) {
                    if (polickoL == EnumSegment.POOP) {
                        for (int i = 0; i < this.prekazky.size(); ++i) {
                            tempRiadok = this.prekazky.get(i).getRiadok();
                            tempStlpec = this.prekazky.get(i).getStlpec();
                            if (tempRiadok == this.riadok && tempStlpec == this.stlpec - 1) {
                                this.prekazky.get(i).skry();
                                this.prekazky.get(i).skryStvorec();
                                this.prekazky.remove(i);
                            }
                        }
                        this.zivoty = this.zivoty - 1;
                        return false;
                    }
                }
                if (polickoL == EnumSegment.PREKAZKA || polickoL == EnumSegment.POOP) {

                    return true;
                }
                break;

            case 'r':
                EnumSegment polickoR = this.hraciePole.obsadenyStvorec(this.riadok, this.stlpec + 1);
                if (this.zivoty >= 2) {
                    if (polickoR == EnumSegment.POOP) {
                        for (int i = 0; i < this.prekazky.size(); ++i) {
                            tempRiadok = this.prekazky.get(i).getRiadok();
                            tempStlpec = this.prekazky.get(i).getStlpec();
                            if (tempRiadok == this.riadok && tempStlpec == this.stlpec + 1) {
                                this.prekazky.get(i).skry();
                                this.prekazky.get(i).skryStvorec();
                                this.prekazky.remove(i);
                            }
                        }
                        this.zivoty = this.zivoty - 1;
                        return false;
                    }
                }
                if (polickoR == EnumSegment.PREKAZKA || polickoR == EnumSegment.POOP) {
                    return true;
                }
                break;

            case 'h':
                EnumSegment polickoH = this.hraciePole.obsadenyStvorec(this.riadok - 1, this.stlpec);
                if (this.zivoty >= 2) {
                    if (polickoH == EnumSegment.POOP) {
                        for (int i = 0; i < this.prekazky.size(); ++i) {
                            tempRiadok = this.prekazky.get(i).getRiadok();
                            tempStlpec = this.prekazky.get(i).getStlpec();
                            if (tempRiadok == this.riadok - 1 && tempStlpec == this.stlpec) {
                                this.prekazky.get(i).skry();
                                this.prekazky.get(i).skryStvorec();
                                this.prekazky.remove(i);
                            }
                        }
                        this.zivoty = this.zivoty - 1;
                        return false;
                    }
                }
                if (polickoH == EnumSegment.PREKAZKA || polickoH == EnumSegment.POOP) {
                    return true;
                }
                break;

        }
        return false;
    }

    /**
     * Skruje aktualne jablko na hracom poli.
     */
    public void skryJablko() {
        this.jablko.skry();
    }

    /**
     * Zobrazi nove jablko s nahodnymi suradnicami na hracom poli.
     */
    public void vytvorJablko() {
        this.jablko.vytvor();
    }

    public void vytvorBurger() {
        this.burger.vytvor();
    }

    public void vymazPrekazky() {
        for (Poop prekazka : prekazky) {
            prekazka.skry();
            prekazka.skryStvorec();
        }
        this.prekazky.clear();
    }

    /**
     * Vrati String hodnotu farby.
     */
    public String getFarba() {
        return this.farba;
    }

    /**
     * Vrati char hodnotu smeru (vlavo 'l', vpravo 'r', hore 'h', dole 'd').
     *
     */
    public char getSmer() {
        return this.smerPohybu;
    }

    /**
     * Vrati int hodnotu ako suradnicu stlpca na ktorom je hlava hada.
     */
    public int getStlpec() {
        return this.stlpec;
    }

    /**
     * Vrati int hodnotu ako suradnicu riadku na ktorom je hlava hada.
     */
    public int getRiadok() {
        return this.riadok;
    }

    /**
     * Vrati boolean hodnotu pauzy, TRUE = hra je zastavena, FALSE = hra nie je
     * zastavena.
     */
    public boolean getPauza() {
        return this.pauza;
    }

    public void setJablko(Jablko jablko) {
        this.jablko = jablko;
    }

    public void setBurger(Burger burger) {
        this.burger = burger;
    }

    public void setSrdce(Srdce srdce) {
        this.srdce = srdce;
    }

    public void setZivoty(int zivoty) {
        this.zivoty = zivoty;
    }

    public int getZivoty() {
        return this.zivoty;
    }

    public void setPauza(boolean pauza) {
        this.pauza = pauza;
    }

}
