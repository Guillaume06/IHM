package dvt.jeututo;
import javax.swing.*;
import java.awt.event.KeyEvent;

import static dvt.devint.ConstantesDevint.*;
import static dvt.jeuchronometre.ConstantesJeu.CONSIGNE;

import dvt.score.Score;
/**
 * Created by Guillaume on 10/02/2016.
 */
public class JeuTuto extends dvt.devint.Jeu {

    // Global variables
    private static final long serialVersionUID = 1L;

    // Jpanel
    private JPanel all;
    private JLabel info;

    // JLabel
    private JLabel picture1;
    private JLabel picture2;

    // String
    private String winner;

    // Integer
    private int YPicture1;
    private int YPicture2;
    private int scoreV;

    // Score
    private Score score;

    // Boolean
    private boolean sound;
    private boolean win;

    @Override
    /**
     * Global init of the game
     */
    public void init() {
        all = new JPanel();

        // Info
        info = new JLabel(CONSIGNE, JLabel.CENTER);
        info.setFont(getFont());
        info.setVisible(true);


        // Pictures
        picture1 = new JLabel( new ImageIcon( "../ressources/images/lea.JPG"));
        picture1.setName("Player1");
        picture1.setLocation(this.getWidth()/2- picture1.getWidth(),0);

        // Picture 2
        picture2 = new JLabel(new ImageIcon("../ressources/images/theoPetit.jpg"));
        picture2.setName("Player2");
        picture2.setLocation(this.getWidth()/2+ picture2.getWidth(),0);

        // Control
        addControlDown(KeyEvent.VK_SPACE, new Space(this));
        addControlDown(KeyEvent.VK_ENTER, new Enter(this));

        // Pictures visibility
        picture1.setVisible(true);
        picture2.setVisible(true);

        // Picture background
        picture1.setBackground(getForeground());
        picture2.setBackground(getForeground());

        // Add pictures
        all.add(picture1);
        all.add(picture2);

        // Add info
        all.add(info);

        // Add all
        this.add(all);
        
        // Var init
        score=new Score();
        win=false;
        sound=true;
    }

    @Override
    /**
     * Will handle the update and check the victory
     */
    public void update() {
        if(isWinner(picture1) || isWinner(picture2)) {
            win = true;
        }else{
            YPicture1 = picture1.getY() + 1;
        }

    }



    @Override
    /**
     * Will handle the global display
     */
    public void render() {
        info.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.info.setFont(getFont());
        this.info.setForeground(getForeground());
        if (win) {
            if (winner.equals("Player1")) {
                if (sound){
                    this.getSIVOX().playText("You win", SYNTHESE_MAXIMALE);
                    sound=false;
                }
                info.setText("Your fail");
            }
            else {
                if (sound){
                    this.getSIVOX().playText("You lose", SYNTHESE_MAXIMALE);
                    sound=false;
                }
                info.setText("Wou win with " + scoreV+" points");
            }
            info.setVisible(true);
            picture1.setVisible(false);
            picture2.setVisible(false);
        }else{
            info.setVisible(false);
            picture2.setLocation(picture2.getX(), YPicture2);
            picture1.setLocation(picture1.getX(), YPicture1);
        }

        all.setBackground(getBackground());
    }

    @Override
    /**
     * Reset all
     */
    public void reset() {
        picture1.setLocation(this.getWidth()/2- picture1.getWidth(),0);
        picture2.setLocation(this.getWidth()/2+ picture2.getWidth(),0);
        picture1.setVisible(true);
        picture2.setVisible(true);
        YPicture2 =0;
        YPicture1 =0;
        picture1.setBackground(getForeground());
        picture2.setBackground(getForeground());
        win=false;
        sound=true;
    }


    /**
     * Will check if the player is the winner
     * @param player    Player to check
     * @return          True if he's the winner
     */
    private boolean isWinner(JLabel player){
        if (this.getHeight() - (player.getY() + player.getHeight()) <= 0) {
            winner=player.getName();
            scoreV= picture2.getY() - picture1.getY();
            score.writeXML("Toto", scoreV);
            return true;
        }else
            return false;
    }



    /**
     *  Will set the position of the player if there is no winner
     */
    public void setPosition(){
        if(!win)YPicture2 = picture2.getY()+20;
    }
}
