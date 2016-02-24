package dvt.jeumultijoueur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Permet de gerer l'action pour la touche "Space"
 * @author Justal Kevin
 */
public class SpaceAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient JeuMulti jeu1;

    /**
     * L'objet qui sera cree lors de l'appuie sur la touche Space
     * @param jeu2 La fentre ou se trouve le lien entre l'action et la touche space
     */
    public SpaceAction(JeuMulti jeu1) {
        this.jeu1 = jeu1;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.jeu1.lauch();
    }
}
