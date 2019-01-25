package Ball;


import java.io.*;
import java.util.Properties;

/**
 * Klasa przechowujaca rozne parametry obiektow obecnych w grze,
 * zawiera funkcje zapisu parametrow do pliku konfiguracyjnego,
 * odczytu parametrow z pliku konfiguracyjnego,
 * funkcje zwracania parametrow.
 */
public class Configurator {

    private Properties prop;
    private OutputStream output;
    private InputStream input;
    private static int livesAmount;
    private String playerName;
    private int ballType;
    private static float gravityConstant;
    private static int scoreAmount;
    private int maxLivesAmount;
    private static float gameDifficulty;

    public Configurator(){}

    public Configurator(int la, String pn, int bt, float gc, int sa, int mla, float gd){
        prop = new Properties();
        input = null;
        this.livesAmount = la;
        this.playerName = pn;
        this.ballType = bt;
        this.gravityConstant = gc;
        this.scoreAmount = sa;
        this.maxLivesAmount = mla;
        this.gameDifficulty = gd;
    }

    public void writeProperties(int configChoice){

        try {
            output = new FileOutputStream("config.properties");
            switch(configChoice){
                case 1:
                    prop.setProperty("liczba_zyc", Integer.toString(livesAmount));
                    break;
                case 2:
                    prop.setProperty("nazwa_uzytkownika", playerName);
                    break;
                case 3:
                    prop.setProperty("rodzaj_kulki", Integer.toString(ballType));
                    break;
                case 4:
                    prop.setProperty("stala_grawitacji", Float.toString(gravityConstant));
                    break;
                case 5:
                    prop.setProperty("liczba_punktow", Integer.toString(scoreAmount));
                    break;
                case 6:
                    prop.setProperty("maksymalna_liczba_zyc", Integer.toString(maxLivesAmount));
                    break;
                case 7:
                    prop.setProperty("poziom_trudnosci", Float.toString(gameDifficulty));
                    break;
            }
            prop.store(output, null);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadProperties(){

        try {
            input = new FileInputStream("config.properties");
            prop.load(input);
            this.livesAmount = Integer.parseInt(prop.getProperty("liczba_zyc"));
            String playerName = prop.getProperty("nazwa_uzytkownika");
            this.ballType = Integer.parseInt(prop.getProperty("rodzaj_kulki"));
            this.gravityConstant = Float.parseFloat(prop.getProperty("stala_grawitacji"));
            this.scoreAmount = Integer.parseInt(prop.getProperty("liczba_punktow"));
            this.maxLivesAmount = Integer.parseInt(prop.getProperty("maksymalna_liczba_zyc"));
            this.gameDifficulty = Float.parseFloat(prop.getProperty("poziom_trudnosci"));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getLivesAmount(){
        return this.livesAmount;
    }
    public String getPlayerName(){
        return this.playerName;
    }
    public int getBallType(){ return this.ballType; }
    public float getGravityConstant(){return this.gravityConstant; }
    public int getScoreAmount(){return this.scoreAmount;}
    public void setLivesAmount(int la){
        this.livesAmount = la;
    }
    public void setPlayerName(String pn){
        this.playerName = pn;
    }
    public void setBallType(int bt) {this.ballType = bt; }
    public void setGravityConstant(float gc) { this.gravityConstant = gc; }
    public void setScoreAmount(int sa){ this.scoreAmount = sa;}
    public int getMaxLivesAmount(){ return this.maxLivesAmount;}
    public void setMaxLivesAmount(int mla){this.maxLivesAmount = mla;}
    public float getGameDifficulty(){return this.gameDifficulty;}
    public void setGameDifficulty(float gd){this.gameDifficulty = gd;}
}