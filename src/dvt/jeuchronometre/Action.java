package dvt.jeuchronometre;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Permet de gerer l'action lors de l'appuie sur une touche
 * @author Justal Kevin
 */
public class Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient JeuChrono jeu2;
    private char c;

    /**
     * L'objet qui sera cree lors de l'appuie sur une touche
     * @param jeu2 La fenetre ou se trouve le lien entre la touche et l'action
     */
    public Action(JeuChrono jeu2, char c) {
        this.jeu2 = jeu2;
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.jeu2.action(this.c);
    }

}
