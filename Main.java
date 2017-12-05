//import com.sun.org.apache.xerces.internal.xs.StringList;
//import jdk.nashorn.internal.ir.WhileNode;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {
    public static void main(String[] args){
        String REGEX = "\\b[^a-z]+";
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
                String mm="";
                String mmchange;
                MatrixDG mDG = new MatrixDG();
                int x = -1;
                if(!file.exists()){
                    throw new NullPointerException();
                }
                else if(file!=null && file.canRead()){
                    try
                    {
                        BufferedReader read = new BufferedReader(new FileReader(file));
                        String str;
                        while ((str = read.readLine()) != null)
                        {
                            if(x>=0){mm=mm+" "+str;}
                            else {mm+=str;}
                            x++;
                        }
                        read.close();
                        mm = mm.toLowerCase();
                        Pattern pattern = Pattern.compile(REGEX);
                        Matcher matcher = pattern.matcher(mm);
                        StringBuffer sb = new StringBuffer();
                        while(matcher.find()){
                            matcher.appendReplacement(sb," ");
                        }
                        matcher.appendTail(sb);
                        mmchange = sb.toString();
                        System.out.println(mmchange);
                        String[] mlist = mmchange.split(" ");
                        mDG.getVexs(mlist);
                        mDG.getEdges(mlist);
                        while (con2){
                            System.out.println("Please choose function(1:print Graph,2:bridge word,3:new sentence,4:randomwalk;0:exit):");
                            int fu = in.nextInt();
                            in.nextLine();
                            switch (fu){
                                case 1:mDG.print();mDG.printlist();break;
                                case 2:System.out.println("Please input two words in one line;");
                                        boolean one = false;boolean two = false;
                                        String words = in.nextLine();
                                        String[] twowords = words.split(" ");
                                        for(int i = 0;i<mDG.VexsList.length;i++){
                                            if(mDG.VexsList[i].equals(twowords[0])){one = true;}
                                            if(mDG.VexsList[i].equals(twowords[1])){two = true;}
                                        }
                                        if(one && two) {
                                            int[] bridge = mDG.queryBridgeWords(twowords[0], twowords[1]);
                                            if (bridge[0] == 0) {
                                                System.out.println("No bridge words from " + twowords[0] + " to " + twowords[1] + "!");
                                            } else if (bridge[0] == 1) {
                                                System.out.println("The bridge word from "+ twowords[0] + " to " + twowords[1] +" is:"+ mDG.VexsList[bridge[1]]);
                                            } else {
                                                System.out.print("The bridge words from "+ twowords[0] + " to " + twowords[1] +" are:");
                                                for(int i = 0;i <bridge[0];i++){
                                                    if(i==bridge[0]-1){System.out.print("and "+mDG.VexsList[bridge[i]]+".");}
                                                    else{System.out.print(mDG.VexsList[bridge[i]]+", ");}
                                                }
                                            }
                                        }
                                        break;
                                case 3:System.out.println("Please input your sentence;");
                                        String sentense = in.nextLine();
                                        String[] sen = sentense.split(" ");
                                        System.out.print("New sentence:");
                                        for(int i = 0;i<sen.length-1;i++){
                                            int[] num = mDG.queryBridgeWords(sen[i],sen[i+1]);
                                            if(num[0] == 0){
                                                System.out.print(sen[i]+" ");
                                            }
                                            else{
                                                Random random = new Random();
                                                int s = random.nextInt(num[0])%(num[0])+1;
                                                System.out.print(sen[i]+" "+mDG.VexsList[num[s]]+" ");
                                            }
                                        }
                                        System.out.print(sen[sen.length-1]);
                                        System.out.println();
                                        break;
                                case 4:mDG.randomWalk();break;
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
