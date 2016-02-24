package dvt.jeuchronometre;

/**
 * Permet de creer et gerer un chronometre
 * @author Justal Kevin
 */
public final class Chronometer {
    private long begin, end;

    /**
     * Lance le chronometre
     */
    public void start() {
        begin = System.currentTimeMillis();
    }

    /**
     * Stop le chronometre pour le calcul (mais le chronometre continu de tourner derriere)
     */
    public void stop() {
        end = System.currentTimeMillis();
    }

    /**
     * Permet d'obtenir le resultat total en milliseconde
     * @return Retoune le temp entre le debut et du dernier arret du chronometre
     */
    public long getTime() {
        return end - begin;
    }

    /**
     * Permet d'obtenir une jolie string qui represente le chronometre de maniere lisible pour l'espece humaine
     * @return Une jolie String sous la forme 00:00:00
     */
    public String getChrono() {
        // Je pense pas que le jeu depassera l'heure Haha ! Inutile de
        // travailler inutilement
        String tmp = getHours() < 10 ? "0" + Integer.toString(getHours()) : ""
                + Integer.toString(getHours());
        tmp = (getMinutes() % 60) < 10 ? tmp + ":0" + (getMinutes() % 60) : tmp
                + ":" + (getMinutes() % 60);
        tmp = (getSeconds() % 60) < 10 ? tmp + ":0" + (getSeconds() % 60) : tmp
                + ":" + (getSeconds() % 60);
        return tmp;
    }

    /**
     * Retourne le temps en milliseconde
     * @return Le temps en milliseconde
     */
    public long getMilliseconds() {
        return end - begin;
    }

    /**
     * Retourne le temps en seconde
     * @return Le temps en secondes
     */
    public int getSeconds() {
        return (int) ((end - begin) / 1000.0);
    }

    /**
     * Retourne le temps en minute
     * @return LE temps en minute
     */
    public int getMinutes() {
        return (int) ((end - begin) / 60000.0);
    }

    /**
     * Retourne le temps en heure
     * @return Le temps en heure
     */
    public int getHours() {
        return (int) ((end - begin) / 3600000.0);
    }
}
