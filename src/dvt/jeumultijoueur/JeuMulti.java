package dvt.jeumultijoueur;

import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import static dvt.devint.ConstantesDevint.*;
import static dvt.jeumultijoueur.ConstantesJeu.*;

/**
 * Permet de gerer le jeu et la fenetre qui contient le jeu
 * @author Justal Kevin
 */
public class JeuMulti extends dvt.devint.Jeu {
    private static final long serialVersionUID = 1L;
    private JPanel personnage1;
    private JPanel personnage2;
    private JPanel monster;
    private JPanel world;
    private transient Positions positionsPersonnage1, positionsPersonnage2, positionsMonster;
    private boolean play;
    private boolean win;
    String winner;
    private JLabel info;

    // Ce tableau permet de gerer les differentes touches de deplacement
    // L'ordre des variables sont les suivantes
    // Joueur 1 haut,Joueur 1 bas,Joueur 1 gauche,Joueur 1 droite,Joueur 2
    // haut,Joueur 2 bas,Joueur 2 gauche,Joueur 2 droite
    // Pour les SI3 : J'ai utilise cela parce que cela me permet de faire un
    // "key binding" unique pour les 2 joueurs
    private boolean[] controlPlayers = { false, false, false, false, false,false, false, false };

    /**
     * Methode qui ajoute tous les composants necessaires dans le layer
     * principale de la fenetre<br />
     * Pour les SI3 : Explication in coming ! <br />
     * Il y a un panel "world" qui englobe toutes la fenetre qui apparait.<br />
     * On va ajouter a ce panel tous les Jcomposant que l'on souhaite.<br />
     * Dans le cas ci-dessous, j'ajoute le Panel du personnage1, du personnage2,
     * du petit carre et ainsi que le label qui affiche le texte.<br />
     * Tous les composants que l'on souhaite ajouter doivent se trouver ici meme
     * si ils seront declare invisible. Si dans la boucle de jeu, vous cree un
     * composant, le jeu pourrait ralentir.<br />
     * <br />
     * Puis j'ajoute les differents controller afin d'activer ou de desactiver
     * les deplacements.<br />
     * addControlDown permet de passer "true" une variable de deplacement
     * pendant que l'on appuie sur la touche<br />
     * addControlUp permet de passer "false" une variable de deplacement lorsque
     * l'on relache une touche<br />
     */
    @Override
    public void init() {
        world = new JPanel();
        world.setLayout(null);

        // Player 1
        addControlDown(KeyEvent.VK_DOWN, new Action(this, 0, true));
        addControlUp(KeyEvent.VK_DOWN, new Action(this, 0, false));

        addControlDown(KeyEvent.VK_UP, new Action(this, 1, true));
        addControlUp(KeyEvent.VK_UP, new Action(this, 1, false));

        addControlDown(KeyEvent.VK_LEFT, new Action(this, 2, true));
        addControlUp(KeyEvent.VK_LEFT, new Action(this, 2, false));

        addControlDown(KeyEvent.VK_RIGHT, new Action(this, 3, true));
        addControlUp(KeyEvent.VK_RIGHT, new Action(this, 3, false));

        personnage1 = new JPanel();
        world.add(personnage1);

        // Player2
        addControlDown(KeyEvent.VK_D, new Action(this, 4, true));
        addControlUp(KeyEvent.VK_D, new Action(this, 4, false));

        addControlDown(KeyEvent.VK_E, new Action(this, 5, true));
        addControlUp(KeyEvent.VK_E, new Action(this, 5, false));

        addControlDown(KeyEvent.VK_S, new Action(this, 6, true));
        addControlUp(KeyEvent.VK_S, new Action(this, 6, false));

        addControlDown(KeyEvent.VK_F, new Action(this, 7, true));
        addControlUp(KeyEvent.VK_F, new Action(this, 7, false));

        addControl("SPACE", new SpaceAction(this));

        personnage2 = new JPanel();
        world.add(personnage2);

        // Monster
        monster = new JPanel();
        world.add(monster);

        info = new JLabel(CONSIGNE, JLabel.CENTER);
        this.getSIVOX().playText(CONSIGNE_WITHOUT_HTML,SYNTHESE_MAXIMALE);
        info.setFont(getFont());
        world.add(info);

        this.add(world);
    }    

    /**
     * Methode qui gere l'ensemble des updates de deplacement des joueurs Pour
     * les SI3 : Si une des touche est active alors on incremente ou decremente
     * de 5 la position x ou y du personnage. J'utilise les operateurs ternaire
     * pour checker si la valeur depasse ou non les limites de l'ecran
     */
    @Override
    public void update() {
        if (play) {
            updatePlayer(0,1,2,3,positionsPersonnage1);
            updatePlayer(4,5,6,7,positionsPersonnage2);
        }
    }

    /**
     * Permet de gerer l'update d'un joueur
     * @param bot La position dans l'array du mouvement vers le bas
     * @param top La position dans l'array du mouvement vers le haut
     * @param left La position dans l'array du mouvement vers la gauche
     * @param right La position dans l'array du mouvement vers la droite
     * @param positions Les Position que l'on souhaite modifie
     */
    private void updatePlayer(int bot,int top,int left,int right,Positions positions) {
        if (controlPlayers[bot]) {positions.moveBot();}
        if (controlPlayers[top]) {positions.moveTop();}
        if (controlPlayers[left]) {positions.moveLeft();}
        if (controlPlayers[right]) {positions.moveRight();}
    }

    /**
     * Le coeur du jeu - Le decisionnaire ! Pour les SI3 : Cette methode
     * effectue les changements graphiques et verifie que le joueur a gagne ou
     * pas
     */
    @Override
    public void render() {
        if (win) {
            info.setText(winner);
            info.setVisible(true);
        } else if (play) {
            info.setVisible(false);
            monster.setVisible(true);
            monster.setBounds(positionsMonster.getPositionX(), positionsMonster.getPositionY(), TAILLE_X_MONSTER, TAILLE_Y_MONSTER);
            personnage1.setBounds(positionsPersonnage1.getPositionX(), positionsPersonnage1.getPositionY(), 100, 100);
            personnage2.setBounds(positionsPersonnage2.getPositionX(), positionsPersonnage2.getPositionY(), 100, 100);
        } else {
            monster.setVisible(false);
            info.setBounds(0, 0, this.getWidth(), this.getHeight());
        }
        personnage1.setBackground(getForeground());
        personnage2.setBackground(getForeground());
        info.setFont(getFont());
        info.setForeground(getForeground());
        monster.setBackground(getForeground());
        world.setBackground(getBackground());
        winPlayer(positionsPersonnage1.getPositionX(), positionsPersonnage1.getPositionY());
        if (win == true){
            winner = "<html><center>JOUEUR 1 A GAGNE<br /><br /><br /><br />Appuyer sur espace pour recommencer</center></html>";
        }

        winPlayer(positionsPersonnage2.getPositionX(), positionsPersonnage2.getPositionY());
        if (win == true){
            winner = "<html><center>JOUEUR 2 A GAGNE<br /><br /><br /><br />Appuyer sur espace pour recommencer</center></html>";
        }
    }

    /**
     * Permet de gerer la victoire d'un des joueur
     * @param xPlayer La position X du joueur
     * @param yPlayer La position Y du joueur
     */
    public void winPlayer(int xPlayer, int yPlayer) {
        if (positionsMonster.getPositionX() + TAILLE_X_MONSTER / 2 > xPlayer
                && positionsMonster.getPositionX() + TAILLE_X_MONSTER / 2 < xPlayer + 100
                && positionsMonster.getPositionY() + TAILLE_Y_MONSTER / 2 > yPlayer
                && positionsMonster.getPositionY() + TAILLE_Y_MONSTER / 2 < yPlayer + 100) {
            win = true;
        }
    }

    /**
     * Permet de recommencer une partie en remettant a zero les diffrentes variable du jeu
     */
    @Override
    public void reset() {
        positionsPersonnage1 = new Positions(0,0,0,this.getWidth(),0,this.getHeight());
        positionsPersonnage2 = new Positions(this.getWidth() - 100,this.getHeight() - 100,0,this.getWidth(),0,this.getHeight());
        positionsMonster = new Positions((this.getWidth() - 50) / 2,(this.getHeight() - 50) / 2,0,this.getWidth(),0,this.getHeight());
        monster.setVisible(false);
        personnage1.setBounds(positionsPersonnage1.getPositionX(), positionsPersonnage1.getPositionY(), 100, 100);
        personnage2.setBounds(positionsPersonnage2.getPositionX(), positionsPersonnage2.getPositionY(), 100, 100);
        info.setText(CONSIGNE);
        info.setVisible(true);
        play = false;
        win = false;
    }

    /**
     * Permet de gerer l'action lors de l'appuie sur une touche
     * @param position La position dans l'array que l'on souhaite modifie
     * @param value La valeur que l'on souhaite passe dans l'array
     */
    public void action(int position, boolean value) {
        controlPlayers[position] = value;
    }

    /**
     * Permet de gerer les actions lie a l'appuie sur la touche space
     */
    public void lauch() {
        if (!play) {
            this.play = true;
        } else {
            this.win = false;
            reset();
        }
    }
    
    /**
     * ###################################################################################################"
     */
    
    public boolean[] getControlPLayer() {
        return controlPlayers;
    }
    
    public boolean getPlay() {
        return play;
    }
    
    public boolean getWin() {
        return win;
    }
}
