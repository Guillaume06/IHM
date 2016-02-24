package dvt.menu;

import java.awt.Color;

/**
 * Permet de rassembler l'ensemble des constanstes du menu dans une classe
 * @author Justal Kevin
 */
public final class ConstantesMenu {
    // Titre du jeu
    public static final String TITLE_GAME = "Modele de Jeu";
    public static final String FONT_TYPE_DEFAULT = "Arial";

    public static final java.awt.Color BACKGROUND_COLOR = new Color(155, 215, 202);

    // Style du titre du menu
    public static final String FONT_TYPE_TITLE = FONT_TYPE_DEFAULT;
    public static final int FONT_TYPE_SIZE_TITLE = 50;
    public static final java.awt.Color FOREGROUND_TITLE = Color.BLACK;

    // Style du menu pour les boutons selectionne
    public static final String FONT_TYPE_SELECTED_DEFAULT = FONT_TYPE_DEFAULT;
    public static final float TAILLE_SELECTED_DEFAULT = 70;
    public static final java.awt.Color BORDURE_SELECTED_DEFAULT = Color.BLACK;
    public static final int BORDURE_SIZE_SELECTED_DEFAULT = 12;
    public static final java.awt.Color BACKGROUND_SELECTED_DEFAULT = Color.WHITE;
    public static final java.awt.Color FOREGROUND_SELECTED_DEFAULT = Color.BLACK;

    // Style du menu pour les boutons non selectionne
    public static final String FONT_TYPE_UNSELECTED_DEFAULT = FONT_TYPE_DEFAULT;
    public static final int TAILLE_UNSELECTED_DEFAULT = 50;
    public static final java.awt.Color BORDURE_UNSELECTED_DEFAULT = Color.WHITE;
    public static final int BORDURE_SIZE_UNSELECTED_DEFAULT = 10;
    public static final java.awt.Color BACKGROUND_UNSELECTED_DEFAULT = Color.BLACK;
    public static final java.awt.Color FOREGROUND_UNSELECTED_DEFAULT = Color.WHITE;

    //
    public static final int MARGE_LEFT_RIGHT = 80;
    public static final int MARGE_TOP_BOT = 3;
    
    /**
     * Le constructeur ne devra jamais etre utilise !
     */
    private ConstantesMenu() {
        throw new AssertionError();
    }
}
