//import com.sun.javafx.geom.Edge;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Graph {
    public String[] VexsList;       // 顶点集合
    private int[][] EdgeList;    // 邻接矩阵
    private int EdgeNum = 0;
    public int[] bridgeWords;
    public int bridgeNum;

    public Graph(String[] letters){
        int n = letters.length;
        String[] ltmp = Arrays.copyOf(letters,n);

        for(int i = 0;i<n;i++){
          if(ltmp[i].equals("#"));
          else{
            for(int j = i+1;j<n;j++){
              if(ltmp[i].equals(ltmp[j])){
                n--;
                ltmp[j] = "#";
              }
            }
          }
        }
        VexsList = new String[n];
        for(int i = 0,j = 0;i<ltmp.length;i++) {
            if (!ltmp[i].equals("#")) {
                VexsList[j] = ltmp[i];
                j++;
            }
        }
        EdgeList = new int[n][n];
        for(int i = 0;i<letters.length-1;i++){
            int p1 = getPosition(letters[i]);
            int p2 = getPosition(letters[i+1]);
            EdgeList[p1][p2] += 1;
            EdgeNum++;
        }
    }

    private  int getPosition(String ch) {
        for(int i=0; i<VexsList.length; i++)
            if(VexsList[i].equals(ch))
                return i;
        return -1;
    }

    // private int readInt() {
    //     Scanner scanner = new Scanner(System.in);
    //     return scanner.nextInt();
    // }

    public void showDirectedGraph(){
      System.out.printf("Martix Graph:\n");
      for (int i = 0; i < VexsList.length; i++) {
          for (int j = 0; j < VexsList.length; j++){
              System.out.print(EdgeList[i][j]+"  ");
          }
          System.out.printf("\n");
      }
      System.out.print("List:\n");
      for(int i = 0;i<VexsList.length;i++){
          for(int j = 0;j<VexsList.length;j++){
              if(EdgeList[i][j] >= 1){
                  System.out.print(VexsList[i]);
                  System.out.print("->");
                  System.out.print(VexsList[j]+" ");
              }
          }System.out.println();
      }
    }

    public String queryBridgeWords(String word1,String word2){
        int[] bridge = new int[VexsList.length];
        String result = "";
        int n = 0;
        int p1 = getPosition(word1);
        int p2 = getPosition(word2);
        if(p1 != p2&&p1!=-1&&p2!=-1){
        for(int i = 0;i<VexsList.length;i++){
            if(EdgeList[p1][i]>=1&&EdgeList[i][p2]>=1){
                bridge[n] = i;
                n++;
            }
          }
        }
        if(n==0) result = "No bridge words from \""+ word1 +"\" to \""+word2 +"\"!";
        else if(n==1) result = "The bridge word from \""+ word1 +"\" to \""+word2 +"\" is: "+VexsList[bridge[0]];
        else if(n>1){
          result = "The bridge word from \""+ word1 +"\" to \""+word2 +" are: ";
          for(int i=0;i<n;i++){
            result += VexsList[bridge[i]] + ", ";
          }
        }
        bridgeNum = n;
        bridgeWords = bridge;
        return result;
    }

    public void randomWalk(){
        Random random = new Random();
        int[][] Edgeclone = new int[VexsList.length][VexsList.length];
        for(int i = 0;i<VexsList.length;i++){
            for(int j = 0;j<VexsList.length;j++){
                Edgeclone[i][j] = EdgeList[i][j];
            }
        }
        int j = random.nextInt(VexsList.length-1)%(VexsList.length);
        System.out.print(VexsList[j]);
        int n = EdgeNum;
        for(int i = 0;i<n;i++){
            int count = 0;
            int[] num = new int[VexsList.length];
            for(int k = 0;k<VexsList.length-1;k++){
                if(Edgeclone[j][k]>=1&&Edgeclone[j][k] == EdgeList[j][k])
                {
                    count +=1;
                    num[count] = k;
                }
            }
            num[0] = count;
            if(num[0] == 0){
                System.out.println();
                return;
            }
            int m = random.nextInt(num[0])%(num[0])+1;
            Edgeclone[j][num[m]]+=1;
            j=num[m];
            System.out.print(" "+VexsList[j]);
        }

    }
}
