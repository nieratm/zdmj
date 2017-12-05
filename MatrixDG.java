//import com.sun.javafx.geom.Edge;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MatrixDG {
    public String[] VexsList;       // 顶点集合
    private int[][] EdgeList;    // 邻接矩阵

    public MatrixDG(String[] letters){
      
    }

    public void getVexs(String[] mlist){
        int n = mlist.length;
        String[] mmlist = new String[n];
        for(int i = 0;i<n;i++){
            mmlist[i] = mlist[i];
        }
        for(int i = 0;i<mmlist.length;i++){
            for(int j = i+1;j<mmlist.length;j++){
                if(mmlist[i].equals(mmlist[j])&&mmlist[i]!=" "){
                    n= n-1;
                    mmlist[j] = " ";
                }
            }
        }
        VexsList = new String[n];
        for(int i = 0,j = 0;i<mmlist.length;i++) {
            if (mmlist[i] != " ") {
                VexsList[j] = mmlist[i];
                j++;
            }
        }
    }
    public void getEdges(String[] mlist){
        int n = VexsList.length;
        EdgeList = new int[n][n];
        for(int i = 0;i<mlist.length-1;i++){
            int p1 = getPosition(mlist[i]);
            int p2 = getPosition(mlist[i+1]);
            EdgeList[p1][p2] += 1;
        }
    }
    private  int getPosition(String ch) {
        for(int i=0; i<VexsList.length; i++)
            if(VexsList[i].equals(ch))
                return i;
        return -1;
    }

    private int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public void print() {
        System.out.printf("Martix Graph:\n");
        for (int i = 0; i < VexsList.length; i++) {
            for (int j = 0; j < VexsList.length; j++){
                System.out.print(EdgeList[i][j]+"  ");
            }
            System.out.printf("\n");
        }
    }
    public void printlist(){
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
    public int[] queryBridgeWords(String word1,String word2){
        int[] bridge = new int[VexsList.length];
        int n = 0;
        int p1 = getPosition(word1);
        int p2 = getPosition(word2);
        if(p1 != p2&&(p1!=-1&&p2!=-1)){
        for(int i = 0;i<VexsList.length;i++){
            if(EdgeList[p1][i]>=1&&EdgeList[i][p2]>=1){
                bridge[n+1] = i;
                n++;
            }
        }}
        bridge[0] = n;
        return bridge;
    }
    public int Edgecount(){
        int n=0;
        for(int i = 0;i<VexsList.length;i++){
            for (int k = 0;k<VexsList.length;k++){
                if(EdgeList[i][k]>0){
                    n++;
                }
            }
        }
        return n;
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
        int n = Edgecount();
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
