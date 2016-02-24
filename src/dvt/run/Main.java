package dvt.run;

import java.net.MalformedURLException;

import dvt.menu.Menu;

/**
 * Permet de lancer le jeu
 * @author Justal Kevin
 */
public final class Main {

    /**
     * Le constructeur qui ne sera jamais utiliser.
     */
    private Main() {
    }

    /**
     * La methode Main
     * @param arg
     * @throws MalformedURLException
     */
    public static void main(String[] arg) throws MalformedURLException {
        Menu menu = new Menu();
        menu.loop();
    }
}
