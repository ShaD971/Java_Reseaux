/**
 * Created by shad on 23/04/16.
 */

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;


public class Browser extends JFrame{

    JFXPanel fxPanel = new JFXPanel();
    //Les composants JavaFX
    Group group;
    Scene scene;
    WebView webView;
    WebEngine webEngine;
    FXRunnable fxRun;
    final int WIDTH=900, HEIGHT=600;
    public Browser(String title, final String content){
        //On initialise notre fenêtre
        setSize(new Dimension(WIDTH,HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle(title);
        add(new JScrollPane(fxPanel));
        setVisible(true);


        fxRun = new FXRunnable(content);
        //Lance le thread dans un thread dédié à JavaFX
        Platform.runLater(fxRun);

    }

    /**
     * Méthode permettant de mettre à jour le JFXPanel
     * @param content
     */
    public void setContent(String content){

        fxRun = new FXRunnable(content);
        Platform.runLater(fxRun);
    }

    /**
     * une classe interne qui permet de travailler conjointement
     * avec JavaFX et Swing
     */
    private class FXRunnable implements Runnable{
        String content;
        public FXRunnable(String pContent){
            content = pContent;
        }
        public void run(){
            initFX(fxPanel);
            setContent(content);
        }

        /**
         * initialise les composants JavaFX
         * @param fxPanel
         */
        private void initFX(JFXPanel fxPanel){
            group = new Group();
            scene = new Scene(group);
            fxPanel.setScene(scene);

            webView = new WebView();
            webEngine = webView.getEngine();

            group.getChildren().add(webView);

            webView.setMinSize(WIDTH, HEIGHT);
            webView.setMaxSize(WIDTH, HEIGHT);
        }

        public void setContent(String content){
            webEngine.loadContent(content);
            webEngine.reload();
        }
    }
}
