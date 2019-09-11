package com.lechinoy.codefores;

import java.util.ArrayList;
import java.util.List;
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

            long length = scanner.nextInt();

            long a = scanner.nextLong();//管道

            long b = scanner.nextInt();//支柱

            String data = scanner.next();

            getMinCost(length, a, b, data);

            groupCount--;
        }


    }

    private static void getMinCost(long length, long a, long b, String data) {

        String[] array = data.split("");

        if (length == 1) {
            //头尾不需要通车
            System.out.println(b * 2 + a);
        }

        int state = 1;// 0 or 1 记录当前位置

        long sum = 0; //总花费

        sum = sum + b;

        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < length; i++) {

            if (state == 1) {//高度为1

                if (i + 1 >= length) {
                    sum = sum + a + b;
                    break;
                }

                if (array[i + 1].equals("1")) {
                    sum = sum + 2 * a + 2 * b;
                    state = 2;//高度变为2 高度升上去不做改变
                } else {
                    sum = sum + a + b;
                }

            } else {//高度为2
                int size = list.size();

                if (i + 1 >= length) {//超过长度时

                    if (list.size() == 0) {
                        sum = sum + Math.min(a + 2 * b, 2 * a + b);
                    } else {
                        list.add(0);
                        size=list.size();
                        sum = sum + Math.min(size * a + size * 2 * b, size * a + a + size * b);
                        list.clear();
                    }
                    break;
                }

                if (array[i].equals("0")) {
                    list.add(0);
                } else {//当前位置为1
                    if (list.isEmpty() || list.size() == 1) {
                        sum = sum + (size + 1) * (a + 2 * b);
                        list.clear();
                    } else {
                        sum = sum + Math.min(2 * a + size * a + (size - 1) * b + a + 4 * b, (size + 1) * a + (size + 1) * 2 * b);
                        list.clear();
                    }
                }
            }

        }

        System.out.println(sum);

    }


}
