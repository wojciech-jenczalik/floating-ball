package Ball;
import java.io.*;
import java.util.Arrays;

public class Scores {
    private int score = 0;
    private String nickname = " ";
    private int[] indexes = new int[5];
    private int[] scores = new int[5];
    private String[] nicknames = new String[5];
    private char[] tempArray = new char[100];
    private String beforeString;
    private String centerString;
    private String[] afterString = new String[4];
    private String afterString2 = "";
    int ii[] =new int[5];
    int iii[] =new int[5];
    int y[] =new int[5];

    public Scores(){

    }

    public void writeScores(){
        try {
            readScores();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("scores.txt"));
            int temp = 5;
            for(int i = 0; i < 5; i++){
                if(scores[i]<score){
                    temp=i;
                    break;
                }
            }
            if(temp<4 && temp!=0) {
                beforeString = new String(tempArray, 0, y[temp] - 1);
                centerString = indexes[temp]+";"+score+";"+nickname+";";

                for(int killMe = 0; killMe < (4-temp); killMe++) {
                    afterString[killMe] = indexes[temp + 1 + killMe] + ";" + scores[temp+killMe] + ";" + nicknames[temp+killMe] + ";";
                }
                for(int kk = 0; kk < 4; kk++){
                    if(afterString[kk]!=null) {afterString2 = afterString2+afterString[kk];} else break;
                }
                out.write(beforeString+centerString+afterString2);
            }else if(temp==0) {
                centerString = indexes[temp]+";"+score+";"+nickname+";";

                for(int killMe = 0; killMe < (4-temp); killMe++) {
                    afterString[killMe] = indexes[temp + 1 + killMe] + ";" + scores[temp+killMe] + ";" + nicknames[temp+killMe] + ";";
                }
                for(int kk = 0; kk < 4; kk++){
                    if(afterString[kk]!=null) {afterString2 = afterString2+afterString[kk];} else break;
                }
                out.write(centerString+afterString2);
            }else if(temp==4) {
                beforeString = new String(tempArray, 0, y[temp] - 1);
                centerString = indexes[temp]+";"+score+";"+nickname+";";
                out.write(beforeString+centerString);
            }else if(temp==5) {
                out.write(new String(tempArray));
            }
            out.close();
        } catch (IOException e) {}
        score = 0;
        nickname = "";
        indexes = new int[5];
        scores = new int[5];
        nicknames = new String[5];
        tempArray = new char[100];
        beforeString ="";
        centerString ="";
        afterString = new String[4];
        afterString2= "";
        ii =new int[5];
        iii =new int[5];
        y =new int[5];

    }

    public void setScoreAndNickname(int sco, String nick){
        this.score = sco;
        this.nickname = nick;
    }

    public void readScores() throws IOException {
        InputStream in = null;

        try {
            in = new FileInputStream("scores.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Reader r = new InputStreamReader(in);

        int c = -1;
        int i = 0;
        int j = 0;
        int counter = 0;

        while ((c = r.read())> -1) {
            tempArray[i] = (char)c;

            if((char)c==';' && counter == 2){
                iii[j]=i;
                counter = 0;
                j++;
            }else if((char)c==';' && counter == 1){
                ii[j]=i;
                counter++;
            }else if((char)c==';' && counter == 0){
               counter++;
               y[j]=i;
            }
            i++;
        }

        for(int m=0; m<5; m++){
            indexes[m]=Integer.parseInt(new String(tempArray, y[m]-1, 1));
            scores[m]=Integer.parseInt(new String(tempArray, y[m]+1, ii[m]-y[m]-1));
            nicknames[m] = new String(tempArray, ii[m]+1, iii[m]-ii[m]-1);
        }
    }

    public int[] getIndexes() {
        return indexes;
    }

    public int[] getScores() {
        return scores;
    }

    public String[] getNicknames() {
        return nicknames;
    }
}



