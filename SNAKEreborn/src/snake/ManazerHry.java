package snake;


import Grafika.Platno;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * Trieda manazer pouzita z tvary V3
 * Automaticky posiela spravy danym objektom:<br />
 * posunDole() - pri stlaceni klavesy DOWN<br />
 * posunHore() - pri stlaceni klavesy UP<br />
 * posunVlavo() - pri stlacen klavesy LEFT<br />
 * posunVpravo() - pri stlaceni klavesy RIGHT<br />
 * aktivuj() - pri stlaceni klavesy ENTER alebo SPACE<br />
 * zrus() - pri stlaceni klavesy ESC<br />
 * tik() - kazdych 0,25 sekundy<br />
 * vyberSuradnice(x, y) - pri kliknuti mysou
 */
public class ManazerHry {
    private ArrayList<Object> spravovaneObjekty;
    private long oldTick;
    private static long TICK_LENGTH = 170000000;
    
    private class ManazerKlaves extends KeyAdapter {
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                ManazerHry.this.posliSpravu("posunD");
            } else if (event.getKeyCode() == KeyEvent.VK_UP) {
                ManazerHry.this.posliSpravu("posunH");
            } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                ManazerHry.this.posliSpravu("posunL");
            } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                ManazerHry.this.posliSpravu("posunR");
            } else if (event.getKeyCode() == KeyEvent.VK_SPACE || event.getKeyCode() == KeyEvent.VK_ENTER) {
                ManazerHry.this.posliSpravu("nastavPauzu");
            } else if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                ManazerHry.this.posliSpravu("restartujHru");
            } else if (event.getKeyCode() == KeyEvent.VK_E) {
                ManazerHry.this.posliSpravu("vykresliSiet");
            } else if (event.getKeyCode() == KeyEvent.VK_R) {
                ManazerHry.this.posliSpravu("pridajClanok");
            }
        }
    }
    
    private class ManazerCasovaca implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            long newTick = System.nanoTime();
            if (newTick - ManazerHry.this.oldTick >= ManazerHry.TICK_LENGTH || newTick < ManazerHry.TICK_LENGTH) {
                ManazerHry.this.oldTick = (newTick / ManazerHry.TICK_LENGTH) * ManazerHry.TICK_LENGTH;
                ManazerHry.this.posliSpravu("pohyb");
            }
        }
    }

    
    
    private class ManazerMysi extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            if (event.getButton() == MouseEvent.BUTTON1) {
                ManazerHry.this.posliSpravu("vyberSuradnice", event.getX(), event.getY());
            }
        }
    }
    
    private void posliSpravu(String selektor) {
        for (Object adresat : this.spravovaneObjekty) {
            try {
                Method sprava = adresat.getClass().getMethod(selektor);
                sprava.invoke(adresat);
            } catch (SecurityException e) {
                this.doNothing();
            } catch (NoSuchMethodException e) {
                this.doNothing();
            } catch (IllegalArgumentException e) {
                this.doNothing();
            } catch (IllegalAccessException e) {
                this.doNothing();
            } catch (InvocationTargetException e) {
                this.doNothing();
            }
        }
    }
    
    private void posliSpravu(String selektor, int prvyParameter, int druhyParameter) {
        for (Object adresat : this.spravovaneObjekty) {
            try {
                Method sprava = adresat.getClass().getMethod(selektor, Integer.TYPE, Integer.TYPE);
                sprava.invoke(adresat, prvyParameter, druhyParameter);
            } catch (SecurityException e) {
                this.doNothing();
            } catch (NoSuchMethodException e) {
                this.doNothing();
            } catch (IllegalArgumentException e) {
                this.doNothing();
            } catch (IllegalAccessException e) {
                this.doNothing();
            } catch (InvocationTargetException e) {
                this.doNothing();
            }
        }
    }
    
    private void doNothing() {
        
    }
    
    /**
     * Vytvori novy manazer, ktory nespravuje zatial ziadne objekty.
     */
    public ManazerHry() {
        this.spravovaneObjekty = new ArrayList<Object>();
        Platno.dajPlatno().addKeyListener(new ManazerKlaves());
        Platno.dajPlatno().addTimerListener(new ManazerCasovaca());
        Platno.dajPlatno().addMouseListener(new ManazerMysi());
    }
    
    /**
     * Manazer bude spravovat dany objekt.
     */
    public void spravujObjekt(Object objekt) {
        this.spravovaneObjekty.add(objekt);
    }
    
    public void nespravujObjekt(Object objekt) {
        this.spravovaneObjekty.remove(objekt);
    }
    
}
