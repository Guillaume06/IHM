package dvt.jeumultijoueur;

import java.awt.event.ActionEvent;


import javax.swing.AbstractAction;
/**
 * Permet de gerer l'action lors de l'appuie sur certaines touches
 * @author Justal Kevin
 */
public class Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient JeuMulti jeu1;
    private int position;
    private boolean value;

    /**
     * L'objet qui sera cree lors de l'appuie sur une touche
     * @param jeu2 La fenetre ou se trouve le lien entre la touche et l'action
     * @param position La position dans l'array que l'on fera le changement
     * @param value La valeur que l'on souhaite modifie
     */    
    public Action(JeuMulti jeu1, int position, boolean value) {
        this.jeu1 = jeu1;
        this.position = position;
        this.value = value;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.jeu1.action(position, value);
    }

}
