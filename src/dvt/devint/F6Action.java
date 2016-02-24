package dvt.devint;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Permet de gerer l'action pour la touche "F5"
 * @author Justal Kevin
 */
public class F6Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Fenetre fenetre;

    /**
     * Le constructeur appele lors de l'appuie sur la touche "F5"
     * @param fenetre La fenetre que l'on utilise
     * @author Justal Kevin
     */
    public F6Action(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        fenetre.changeMouse();
    }

}
