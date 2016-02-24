package dvt.menu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static dvt.devint.ConstantesDevint.*;
import static dvt.jeuchronometre.ConstantesJeu.OPTIMAL_TIME;
import static dvt.menu.ConstantesMenu.*;
import dvt.devint.Fenetre;

/**
 * Permet de gerer le menu et la fenetre qui contient le menu
 * @author Justal Kevin
 */
public class Menu extends Fenetre {
    private static final long serialVersionUID = 1L;

    private GridBagConstraints c = new GridBagConstraints();
    private JPanel menuPrincipal = new JPanel();
    private int countMenu, menuSelected;

    private List<JButton> listeBoutton;
    private JLabel titleJeu;
    private int gameChoice;

    /**
     * Le constructeur du menu<br />
     * Permet de construire un Menu avec tout les composants
     */
    public Menu() {
        listeBoutton = new ArrayList<JButton>();
        this.getSIVOX().playWav(ACCUEIL_SON);

        GridBagLayout layoutMenu = new GridBagLayout();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(MARGE_TOP_BOT, MARGE_LEFT_RIGHT, MARGE_TOP_BOT,
                MARGE_LEFT_RIGHT);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;
        c.gridwidth = 3;
        menuPrincipal.setLayout(layoutMenu);

        addLabel(TITLE_GAME);
        
        // les options possibles
        addMenu("Multi joueur", new Action(this, 1));
        addMenu("Chrono", new Action(this, 2));
        addMenu("Quizz", new Action(this, 3));
        addMenu("Score Jeu 2", new Action(this, 4));
        addMenu("Jeu tuto", new Action(this, (5)));
        addMenu("Quitter", new Action(this, (6)));

        
        // la gestion des touches directionnelles haut et bas 
        addControl("DOWN", new DownAction(this));
        addControl("UP", new UpAction(this));

        this.add(menuPrincipal);
        this.setVisible(true);
    }

    /**
     * La loop du menu
     */
    public void loop() {
    	long lastLoopTime,timeLoop;

    	while (this.isDisplayable()) {
    		long now = System.nanoTime();
    		lastLoopTime = now;
    		render();
    		if (gameChoice==6)
    			this.dispose();
    		else if (gameChoice != 0) {
				this.setVisible(false);
				this.getSIVOX().stop();
    			switch (gameChoice) {
                    case 5  :
                        new dvt.jeututo.JeuTuto().loop();
                        break;
    			case 4:
    				new dvt.score.Score().loop();
    				break;
     			case 1:
    				new dvt.jeumultijoueur.JeuMulti().loop();
    				break;
    			case 2:
    				new dvt.jeuchronometre.JeuChrono().loop();
     				break;
    			case 3:
    				new dvt.jeuquizz.JeuQuizz().loop();
    				break;
    			default:
    				break;
    			}
				this.getSIVOX().stop();
				this.setVisible(true);
    		}

    		gameChoice = 0;
            
            try {
                timeLoop = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                if(timeLoop>0) {
                    Thread.sleep(timeLoop);
                }
            } catch (InterruptedException e) {
                throw new IllegalArgumentException("");
            }
        }
    }

    /**
     * Permet de gerer le rendu du menu
     */
    private void render() {
        menuPrincipal.setBackground(getBackground());
        this.titleJeu.setFont(getFont());
        this.titleJeu.setForeground(getForeground());

        for (int i = 0; i < listeBoutton.size(); i++) {
            if (i == menuSelected % listeBoutton.size()) {
                selectedButton(listeBoutton.get(i));
            } else {
                unselectedButton(listeBoutton.get(i));
            }
        }
    }

    /**
     * Permet d'ajouter du texte dans le menu
     * @param title Le titre du jeu
     */
    public void addLabel(String title) {
        this.titleJeu = new JLabel(title.toUpperCase(), JLabel.CENTER);
        c.weightx = c.weighty = 1.0;
        c.gridy = countMenu++;
        menuPrincipal.add(this.titleJeu, c);
    }

    /**
     * Permet d'ajouter un bouton
     * @param title Le texte sur le bouton
     * @param action L'action que l'on lie au bouton
     */
    public void addMenu(String title, ActionListener action) {
        JButton button = new JButton(title.toUpperCase());
        button.addActionListener(action);
        unselectedButton(button);
        c.weightx = c.weighty = 1.0;
        c.gridy = countMenu++;
        menuPrincipal.add(button, c);
        listeBoutton.add(button);
    }

    /**
     * Permet de gere l'action lorsque 'lon appuie sur bas
     */
    public void down() {
        unselectedButton(listeBoutton.get(menuSelected++ % listeBoutton.size()));
        selectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        this.getSIVOX().stop();
        this.getSIVOX().playText(listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MAXIMALE);
        this.getSIVOX().playText("CETTE OPTION EST "+listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MINIMALE);
    }

    /**
     * Permet de gerer l'action lorsque l'on appuie sur haut
     */
    public void up() {
        unselectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        menuSelected = menuSelected == 0 ? (listeBoutton.size() - 1)
                : menuSelected - 1;
        selectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        this.getSIVOX().stop();
        this.getSIVOX().playText(listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MAXIMALE);
        this.getSIVOX().playText("CETTE OPTION EST "+listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MINIMALE);
    }

    /**
     * Permet de gerer l'action lorsque l'on appuie sur haut
     */
    public void actionChoice(int choice) {
        menuSelected = choice;
        unselectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        menuSelected = menuSelected == 0 ? (listeBoutton.size() - 1)
                : menuSelected - 1;
        selectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        this.getSIVOX().playText(listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MOYENNE);
        this.getSIVOX().playText("CETTE OPTION EST "+listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MINIMALE);

        for (int i = 0; i < listeBoutton.size(); i++) {
            listeBoutton.get(i).setFocusable(false);
        }
    }    
    
    /**
     * Permet de gerer les action lie au choix dans le menu
     * @param choice Le choix que l'on a effectue dans le menu
     */
    public void chooseChoice(int choice) {
        actionChoice(choice);
        this.gameChoice = choice;
    }

    /**
     * ###################################################################################################"
     */
    
    public GridBagConstraints getC() {
        return c;
    }
    
    public JPanel getMenuPrincipal() {
        return menuPrincipal;
    }
    
    public int getCountMenu() {
        return countMenu;
    }
    
    public int getMenuSelected() {
        return menuSelected;
    }
    
    public List<JButton> getListeBoutton() {
        return listeBoutton;
    }
    
    public JLabel getTitleJeu() {
        return titleJeu;
    }
}
