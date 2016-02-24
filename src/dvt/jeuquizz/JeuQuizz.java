package dvt.jeuquizz;

import static dvt.devint.ConstantesDevint.SYNTHESE_MOYENNE;
import static dvt.menu.ConstantesMenu.MARGE_LEFT_RIGHT;
import static dvt.menu.ConstantesMenu.MARGE_TOP_BOT;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dvt.jeuquizz.Action;

public class JeuQuizz extends dvt.devint.Jeu {
    private static final long serialVersionUID = 1L;
    private JPanel world;
    private GridBagConstraints c;
    private JLabel question;
    private JButton button1,button2,button3;
    private static final String[] QUESTIONS = {"Combien font 4 fois 4 ?", "Combien font 30-5 ?", "Combien font 5+5 ?" };
    private static final int[] ANSWERS = {16,25,10};
    private int random;
    private int choix;
    private boolean valid;
    
    @Override
    public void init() {
    	
    	// composants graphiques
        world = new JPanel();
        c = new GridBagConstraints();
                
        GridBagLayout layoutMenu = new GridBagLayout();
        world.setLayout(layoutMenu);
        c.insets = new Insets(MARGE_TOP_BOT, MARGE_LEFT_RIGHT, MARGE_TOP_BOT,MARGE_LEFT_RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 100;     
        
        button1 = new JButton("16");
        button1.setFont(getFont());
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        world.add(button1,c);

        button2 = new JButton("10");
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        world.add(button2,c);        
  
        button3 = new JButton("25");
        button3.setFont(getFont());
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        world.add(button3,c);         
        
        question = new JLabel("Combien font 4*4 ?", JLabel.CENTER);
        question.setBackground(getForeground());
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.ipady = 500;
        c.gridx = 0;
        c.gridy = 0;
        world.add(question,c);   
        
        this.add(world);
        
        // association des actions aux touches
        addControl("LEFT", new Action(this,false));
        addControl("RIGHT", new Action(this,true));
        addControl("ENTER", new EnterAction(this));

    }
    
    @Override
    public void update() {
        /** Dont need any update **/
    }

    @Override
    public void render() {
        try {
            if(valid && choix == random) {
                this.getSIVOX().stop();
                this.getSIVOX().playText("BONNE REPONSE !",SYNTHESE_MOYENNE);
                question.setText("BONNE REPONSE !");
                Thread.sleep(1000);
                reset();
            } else if(valid) {
                this.getSIVOX().stop();
                this.getSIVOX().playText("MAUVAISE REPONSE !",SYNTHESE_MOYENNE);
                question.setText("MAUVAISE REPONSE !");
                Thread.sleep(1000);
                reset();
            } else {
                question.setText(QUESTIONS[random]);
            }
            question.setFont(getFont());
            question.setForeground(getForeground());
            question.setBackground(getBackground());
            world.setBackground(getBackground());
            world.setForeground(getForeground());
            button1.setForeground(getButtonSelectedForeground());
            button1.setBackground(getButtonSelectedBackground());
            button2.setForeground(getButtonSelectedForeground());
            button2.setBackground(getButtonSelectedBackground());
            button3.setForeground(getButtonSelectedForeground());
            button3.setBackground(getButtonSelectedBackground());
        } catch (InterruptedException e) {
            throw new IllegalArgumentException(e);
        }
        
    }
    
    public void left() {
        if(choix == 1) {
            unselectedButton(button2);
            choix--;
            selectedButton(button1);
        } else if(choix == 2) {
            unselectedButton(button3);
            choix--;
            selectedButton(button2);
        } 
        this.getSIVOX().stop();
        this.getSIVOX().playText(String.valueOf(ANSWERS[choix]),SYNTHESE_MOYENNE);    
    }
    
    public void right() {
        if(choix == 0) {
            unselectedButton(button1);
            choix++;
            selectedButton(button2);
        } else if(choix == 1) {
            unselectedButton(button2);
            choix++;
            selectedButton(button3);
        }
        this.getSIVOX().stop();
        this.getSIVOX().playText(String.valueOf(ANSWERS[choix]),SYNTHESE_MOYENNE); 
    }
    
    @Override
    public void reset() {
       random = (int)(Math.random() * QUESTIONS.length);
       question.setText(QUESTIONS[random]);
       getSIVOX().playText(QUESTIONS[random]);
       button1.setText(String.valueOf(ANSWERS[0]));
       button2.setText(String.valueOf(ANSWERS[1]));
       button3.setText(String.valueOf(ANSWERS[2]));
       choix = 0;
       selectedButton(button1);
       unselectedButton(button2);
       unselectedButton(button3);
       valid = false;
    }

    public void valid() {
        valid = true;
    }

    /**
     * ###################################################################################################"
     */
    
    public boolean getValid() {
        return valid;
    }
    
    public int getRandom() {
        return random;
    }
    
    public int getChoice() {
        return choix;
    }
}
