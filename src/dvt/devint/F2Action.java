package dvt.devint;

import java.awt.event.ActionEvent;

import static dvt.devint.ConstantesDevint.*;

import javax.swing.AbstractAction;

/**
 * Permet de gerer l'action pour la touche "F2"
 * @author Justal Kevin
 */
public class F2Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Fenetre fenetre;

    /**
     * Le constructeur appele lors de l'appuie sur la touche "F2"
     * @param fenetre La fenetre que l'on utilise
     * @author Justal Kevin
     */
    public F2Action(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        fenetre.getSIVOX().playWav(F2_SON);
    }

}
