package dvt.jeututo;
import javax.swing.*;
import java.awt.event.ActionEvent;


/**
 * Created by Guillaume on 10/02/2016.
 */
public class Space extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient JeuTuto jeu;
    public Space(JeuTuto jeu) {
        this.jeu = jeu;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.jeu.setPosition();
    }
}
