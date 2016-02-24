package dvt.jeuchronometre;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Permet de gerer l'action pour la touche "Entree"
 * @author Justal Kevin
 */
public class Restart extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient JeuChrono jeu2;

    /**
     * L'objet qui sera cree lors de l'appuie sur une touche
     * @param jeu2 La fentre ou se trouve le lien entre l'action et la touche
     */
    public Restart(JeuChrono jeu2) {
        this.jeu2 = jeu2;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.jeu2.restart();
    }

}
