package dvt.jeuchronometre;

import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
    char[] track1 = {'d','f', 'j', 'k', 'j', 'k', 'd', 'f'};

    /**
     * L'initalisation du jeu
     */
    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getForeground());
        world.setLayout(null);
        info = new JLabel(CONSIGNE, JLabel.CENTER);
        info.setFont(getFont());
        info.setVisible(true);
        world.add(info);

        this.add(world);

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
        info.setText(CONSIGNE);
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
                        + track1[place] + "</center></html>";
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
        info.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.info.setFont(getFont());
        this.info.setForeground(getForeground());
        this.info.setText(HTMLtext);
        world.setBackground(getBackground());
    }

    /**
     * Permet de gerer les resultats des actions
     */
    public void action(char c) {
        if (end) return;
            if (c == track1[place]) {
                count++;
                place++;
                score += count;
                if (place >= track1.length) end = true;

            } else {
                count = 0;
            }

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
