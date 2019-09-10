package com.lechinoy.codefores;

import java.util.Scanner;

/**
 * @author liqianlong
 * @date 2019/9/10 11:04
 */
public class GasPipeline {


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        int groupCount = scanner.nextInt();

        while (groupCount > 0) {

            int length = scanner.nextInt();

            int a = scanner.nextInt();//管道

            int b = scanner.nextInt();//支柱

            String data = scanner.next();

            getMinCost(length, a, b, data);

            groupCount--;
        }


    }


    private static void getMinCost(int length, int a, int b, String data) {

        String[] array = data.split("");

        if (length == 1) {
            //头尾不需要通车
            System.out.println(b * 2 + a);
        }

        int state = 0;// 0 or 1 记录当前位置

        long sum = 0; //总花费

        sum = sum + b;

        for (int i = 0; i < length; i++) {

            if (state == 0) {//高度为1

                if (i + 1 >= length) {
                    sum = sum + a + b;
                    break;
                }

                if (array[i + 1].equals("1") && array[i].equals("0")) {//
                    long item = a >= b ? 2 * b + a + b : 2 * b + 2 * a;
                    sum = sum + item;
                    state = 1;
                } else {
                    sum = sum + a + b;
                }

            } else {//高度为2

                if (i + 1 >= length) {
                    int item = a >= b ? 2 * b + a : 2 * a + b;
                    sum = sum + item;
                    break;
                }

                if (array[i + 1].equals("0") && array[i].equals("0")) {
                    long item = a >= b ? 2 * b + a : 2 * a + b;
                    sum = sum + item;
                    state = a == b ? 1 : 0;
                } else {
                    sum = sum + a + 2 * b;
                }
            }

        }

        System.out.println(sum);

    }


}
