package dvt.jeuchronometre;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;

import static dvt.devint.ConstantesDevint.*;
import static dvt.jeuchronometre.ConstantesJeu.*;
import dvt.jeuchronometre.Action;
import dvt.score.Score;

/**
 * Permet de gerer le jeu et la fenetre qui contient le jeu
 * @author Justal Kevin
 */
public class JeuChrono extends dvt.devint.Jeu {
    private static final long serialVersionUID = 1L;
    // composants graphiques
    private JPanel world;
    private JLabel info;
    private JPanel display;
    private JLabel d;
    private JLabel f;
    private JLabel j;
    private JLabel k;


    // éléments du modèle
    private transient Chronometer ch;
    private int count;
    private boolean pressed;
    private int record;
    private boolean once;
    private String HTMLtext;
    private int score = 0;
    boolean end = false;
    boolean first = true;
    int place = 0;
    int bonus = 1;
    ArrayList<ArrayList <Character>> tracks;
    ArrayList<Character>  track;
    public ArrayList<ArrayList<Character>> parse(){
        ArrayList<ArrayList <Character>> ret = new ArrayList<>();
        ArrayList<Character> track = new ArrayList<>();
        try {
            char c;
            BufferedReader br = new BufferedReader(new FileReader(System.getProperties().get("user.dir") + "\\" + "tracks.txt"));
            do {
                c = (char) br.read();
                if (c == ';'){
                    ret.add(track);
                    track = new ArrayList<>();
                    break;
                }
                track.add(c);
            } while(c != '.');
            ret.add(track);
            return ret;
        }catch (Exception e){}
        return null;
    }

    /**
     * L'initalisation du jeu
     */
    @Override
    public void init() {
        tracks = parse();
        Random r = new Random();
        int random = r.nextInt(tracks.size() -1);
        track = tracks.get(random);

        // World
        world = new JPanel();
        world.setBackground(getForeground());
        world.setLayout(new BorderLayout());

        // Display
        display = new JPanel();
        display.setBackground(Color.white);
        display.setFont(getFont());
        display.setLayout(new GridLayout(1, 4));

        // Elements of display
        d = new JLabel();
        d.setFont(getFont());
        f = new JLabel();
        f.setFont(getFont());
        j = new JLabel();
        j.setFont(getFont());
        k = new JLabel();
        k.setFont(getFont());


        // Info init
        info = new JLabel();
        info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setFont(getFont());
        info.setForeground(Color.white);
        info.setText( "<html><center>" + 0 + "<br /><br />"
                + track.get(place) + "</center></html>");


        // Adding components to the display
        display.add(d);
        d.setVerticalAlignment(SwingConstants.CENTER);
        d.setHorizontalAlignment(SwingConstants.CENTER);
        d.setText("D");
        d.setForeground(Color.black);
        d.setBackground(Color.white);
        d.setOpaque(true);

        display.add(f);
        f.setVerticalAlignment(SwingConstants.CENTER);
        f.setHorizontalAlignment(SwingConstants.CENTER);
        f.setText("F");
        f.setForeground(Color.black);
        f.setBackground(Color.white);
        f.setOpaque(true);

        display.add(j);
        j.setVerticalAlignment(SwingConstants.CENTER);
        j.setHorizontalAlignment(SwingConstants.CENTER);
        j.setText("J");
        j.setForeground(Color.black);
        j.setBackground(Color.white);
        j.setOpaque(true);

        display.add(k);
        k.setVerticalAlignment(SwingConstants.CENTER);
        k.setHorizontalAlignment(SwingConstants.CENTER);
        k.setText("K");
        k.setForeground(Color.black);
        k.setBackground(Color.white);
        k.setOpaque(true);

        getLabel(track.get(place)).setForeground(Color.blue);
        getLabel(track.get(place)).setBackground(Color.black);

        world.add(info, BorderLayout.NORTH);
        world.add(display, BorderLayout.CENTER);
        this.add(world);


        // Controler addition
        // Controler addition
        addControlDown(KeyEvent.VK_D, new Action(this, 'd'));
        addControlDown(KeyEvent.VK_F, new Action(this, 'f'));
        addControlDown(KeyEvent.VK_J, new Action(this, 'j'));
        addControlDown(KeyEvent.VK_K, new Action(this, 'k'));
        addControlDown(KeyEvent.VK_ENTER, new Restart(this));

    }

    /**
     * Permet de reset le jeu afin de recommencer une partie
     */
    @Override
    public void reset() {
        count = 0;
        score = 0;
        place = 0;
        first = true;
        ch = new Chronometer();
        this.pressed = true;
        this.once = false;
        this.init();
        end = false;
        this.getSIVOX().stop();
        this.getSIVOX().playText(CONSIGNE_WITHOUT_HTML,SYNTHESE_MAXIMALE);
        init();
    }

    /**
     * Permet de faire l'update du jeu
     */
    @Override
    public void update() {
        if (first){
            ch.start();
            first = false;
        }
        if (score >= 0) {
            ch.stop();
            int seconds = ch.getSeconds();
            if (!end) {
                HTMLtext = "<html><center>" + ch.getChrono() + "<br /><br />"
                        + "Combo : "+count+"</center></html>";
                record = score;
            } else {
                end = true;
                HTMLtext ="<html><center>BRAVO<br />Vous avez un score de  "
                        + score
                        + " <br /><br /><br />Pour recommencer, appuyez sur 'Entree'</center></html>";
                if(!once) {
                    this.getSIVOX().stop();
                    Score.writeXML("NAME IF YOU WANT", score);
                    this.getSIVOX().playText("Vous avez un score de "+score+"  ! Pour rejouer, appuyez sur Entree",SYNTHESE_MAXIMALE);
                    this.once = !once;
                }
            }
        }
    }

    /**
     * Permet de faire le rendue du jeu suivant les modifications faites dans l'update
     */
    @Override
    public void render() {

        this.info.setText(HTMLtext);
        //world.setBackground(getBackground());
    }

    /**
     * Permet de gerer les resultats des actions
     */
    public void action(char c) {
        if (end) return;
            if (c == track.get(place)) {
                getLabel(track.get(place)).setForeground(Color.black);
                getLabel(track.get(place)).setBackground(Color.white);
                count++;
                if (count%10 == 0){
                    bonus*=count/10;
                }
                place++;
                score += count + bonus;
                if (place >= track.size()){
                    scoreTimeBonus();
                    end = true;
                }else {
                    getLabel(track.get(place)).setForeground(Color.blue);
                    getLabel(track.get(place)).setBackground(Color.black);
                }

            } else {

                bonus = 1;
                count = 0;
            }

    }

    public JLabel getLabel( char c){
        switch (c){
            case 'd' : return d;
            case 'f' : return f;
            case 'j' : return j;
            case 'k' : return k;
            default : return null;
        }
    }

    public void scoreTimeBonus(){
        int total = ch.getHours()*3600+ch.getMinutes()*60+ch.getSeconds();
        score-=total;
    }

    /**
     * Permet de gerer l'action pour redemarrer le jeu
     */
    public void restart() {
        reset();
    }

    /**
     * ###################################################################################################"
     */

    public int getCount() {
        return count;
    }


    public boolean getPressed() {
        return pressed;
    }
}
