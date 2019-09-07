package com.lechinoy.codefores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by liqianlong
 * 2019 2019/9/7 19:56
 */
public class SquareFilling {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int x = scanner.nextInt();

        int y = scanner.nextInt();


        int[][] a = new int[x][y];

        int[][] b = new int[x][y];

        List<Integer> m = new ArrayList<Integer>();
        List<Integer> n = new ArrayList<Integer>();


        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                a[i][j] = scanner.nextInt();
                b[i][j] = 0;
            }
        }

        for (int i = 0; i < x; i++) {

            for (int j = 0; j < y; j++) {

                if (i + 1 < x && j + 1 < y && a[i][j] == 1 && a[i][j + 1] == 1 && a[i + 1][j] == 1 && a[i + 1][j + 1] == 1) {
                    b[i][j] = b[i][j + 1] = b[i + 1][j] = b[i + 1][j + 1] = 1;
                    m.add(i+1);
                    n.add(j+1);
                }
            }
        }

        int result = 1;

        for (int i = 0; i < x; i++) {

            for (int j = 0; j < y; j++) {

                if(a[i][j]!=b[i][j]){
                    result = -1;
                    break;
                }
            }
        }

        if (result==-1){
            System.out.println(-1);
        }else {
            System.out.println(m.size());
            if (m.size()>0){
                for (int i=0;i<m.size();i++){
                    System.out.println(m.get(i));
                    System.out.println(n.get(i));
                }
            }

        }
    }


}
