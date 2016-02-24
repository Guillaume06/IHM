package dvt.menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Permet de gerer l'action lors de l'appuie sur certaines touches
 * @author Justal Kevin
 */
public class Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Menu menu;
    private int choice;

    /**
     * L'objet qui sera cree lors de l'appuie sur une touche
     * @param jeu2 La fenetre ou se trouve le lien entre la touche et l'action
     * @param choice La valeur que l'on souhaite modifie
     */ 
    public Action(Menu menu, int choice) {
        this.menu = menu;
        this.choice = choice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menu.chooseChoice(choice);
    }
}
