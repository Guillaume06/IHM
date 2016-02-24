package dvt.score;

import static dvt.devint.ConstantesDevint.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Cette classe permet d'ecrire et de lire le fichier score.
 * @author Justal Kevin
 */
public class Score extends dvt.devint.Jeu  {
    ArrayList<PlayerScore> listPlayerScore;
    private static final long serialVersionUID = 1L;
    private JPanel world;
    private JLabel info;
    private String score;
    
    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getForeground());
        world.setLayout(null);
        
        info = new JLabel("", JLabel.CENTER);
        parseXML();
        info.setFont(getFont());
        info.setVisible(true);
        world.add(info);
        this.add(world);
    }
    
    @Override
    public void update() {
        /** Dont need any update **/
    }

    @Override
    public void render() {
        info.setBounds(0, 0, this.getWidth(), this.getHeight());
        info.setBackground(getBackground());
        info.setForeground(getForeground());
        info.setFont(getFont());
        world.setBackground(getBackground());
    }
    
    /**
     * Permet de parcourir le fichier de score et d'afficher les 5 meilleurs scores du jeu 2
     */
    public void parseXML() {
        File inputFile = new File("../ressources/score.xml");
        listPlayerScore = new ArrayList<PlayerScore>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
           
            NodeList nList = doc.getElementsByTagName("joueur");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    
                    PlayerScore ps = new PlayerScore(eElement.getElementsByTagName("name").item(0).getTextContent(),
                            eElement.getElementsByTagName("score").item(0).getTextContent());
                    listPlayerScore.add(ps);
                }
            }
            
            Collections.sort(listPlayerScore);
            
            score = "<html><center>SCORE";
            
            score += "<br />____________________<br />";
            score += "<table>";

            for(int i=0;i<5 && i<listPlayerScore.size();i++) {
                score += "<tr><td><center>"+listPlayerScore.get(i).getScore()+"</center></td></tr>";
            }
            score +="</table></center></html>";
            info.setText(score);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Permet d'ecrire un nom et score dans le fichier XML de maniere bien organise
     * @param name Le nom du joueur
     * @param score Le score du joueur
     */
    public static void writeXML(String name, int score) {
        File inputFile = new File("../ressources/score.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            Element element = doc.getDocumentElement();
            Node node = doc.createElement("joueur");
            Node node2 = doc.createElement("score");
            Node node3 = doc.createElement("name");
            node3.setTextContent(name);
            node2.setTextContent(String.valueOf(score));
            node.appendChild(node2);
            node.appendChild(node3);
            element.appendChild(node);        
            
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            OutputStream out = new FileOutputStream("../ressources/score.xml");
            trans.transform(new DOMSource(doc), new StreamResult(out));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }        
    }
    
    @Override
    public void reset() {
        String tmp = "";
        for(int i=0;i<5 && i<listPlayerScore.size();i++) {
            if(i == 0) {
                tmp += "Le meilleur score est de ";
            }
            if(i == 1) {
                tmp += "Le second score est de ";
            }
            if(i == 2) {
                tmp += "Le troisieme score est de ";
            }
            if(i == 3) {
                tmp += "Le Quatrieme score est de ";
            }
            if(i == 4) {
                tmp += "Le cinquieme score est de ";
            }
            tmp+=listPlayerScore.get(i).getScore();
        }
        this.getSIVOX().playText(tmp,SYNTHESE_MAXIMALE);
    }

    public void valid() {
        
    }
    
    class PlayerScore implements Comparator<Object>,Comparable<Object>{ 
        String name;
        int score;
        
        public PlayerScore(String name,String score) {
            this.name = name;
            this.score = Integer.valueOf(score);
        }
        
        public String getName() {
            return name;
        }
        
        public int getScore() {
            return score;
        }

        @Override
        public int compare(Object arg0, Object arg1) {
            PlayerScore ps1 = (PlayerScore) arg0;
            PlayerScore ps2 = (PlayerScore) arg1;
            if(ps1.getScore() == ps2.getScore()) {
                return 0;
            } else if(ps1.getScore() < ps2.getScore()) {
                return -1;
            } else {
                return 1;
            }
        }

        @Override
        public int compareTo(Object arg0) {
            PlayerScore ps1 = (PlayerScore) arg0;
            if(ps1.getScore() == this.getScore()) {
                return 0;
            } else if(ps1.getScore() < this.getScore()) {
                return -1;
            } else {
                return 1;
            }
        }
        
        
    }
}
