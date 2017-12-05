//import com.sun.org.apache.xerces.internal.xs.StringList;
//import jdk.nashorn.internal.ir.WhileNode;

import java.io.*;
import java.util.*;
// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

public class Main2 {
    public static void main(String[] args){
        // String REGEX = "\\b[^a-z]+";
        Scanner in = new Scanner(System.in);
        boolean con = true;
        boolean con2 = true;
        String Fp="";
        int m;
        while(con){
            System.out.println("Please choose function(1:input file, 0:for exit):");
            m = in.nextInt();
            in.nextLine();
            if(m == 1){
                System.out.println("Please input the file place:");
                Fp = in.nextLine();
                File file = new File(Fp);
                String data = "";
                int x = -1;
                if(!file.exists()){
                    throw new NullPointerException();
                }
                else if(file!=null && file.canRead()){
                    try
                    {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        data = br.readLine();
                        String ss;
                        while((ss = br.readLine()) != null){
                          data += " " + ss;
                        }
                        String[] letters = data.split("[^a-zA-Z]+");
                        Graph G = new Graph(letters);
                        while (con2){
                            System.out.println("Please choose function(1:print Graph,2:bridge word,3:new sentence,4:randomwalk;0:exit):");
                            int fu = in.nextInt();
                            in.nextLine();
                            switch (fu){
                                case 1:G.showDirectedGraph();break;
                                case 2:System.out.println("Please input two words in one line;");
                                        boolean one = false;boolean two = false;
                                        String words = in.nextLine();
                                        String[] twowords = words.split(" ");
                                        for(int i = 0;i<G.VexsList.length;i++){
                                            if(G.VexsList[i].equals(twowords[0])){one = true;}
                                            if(G.VexsList[i].equals(twowords[1])){two = true;}
                                        }
                                        if(one && two) System.out.println(G.queryBridgeWords(twowords[0], twowords[1]));
                                        break;
                                case 3:System.out.println("Please input your sentence;");
                                        String sentense = in.nextLine();
                                        String[] sen = sentense.split(" ");
                                        System.out.print("New sentence:");
                                        for(int i = 0;i<sen.length-1;i++){
                                            G.queryBridgeWords(sen[i],sen[i+1]);
                                            if(G.bridgeNum == 0){
                                                System.out.print(sen[i]+" ");
                                            }
                                            else{
                                                Random random = new Random();
                                                int s = random.nextInt(G.bridgeNum)%(G.bridgeNum);
                                                System.out.print(sen[i]+" "+G.VexsList[G.bridgeWords[s]]+" ");
                                            }
                                        }
                                        System.out.print(sen[sen.length-1]);
                                        System.out.println();
                                        break;
                                case 4:G.randomWalk();break;
                                default:con2=false;break;
                            }
                        }
                    }
                    catch (IOException e)
                    {
                        e.getStackTrace();
                    }

                }
            }
            else if(m == 0){
                System.exit(0);
            }
        }
    }
    //获取图的顶点

    //public void showDirectedGraph(DGraph<String> mGB){

    //}
}
