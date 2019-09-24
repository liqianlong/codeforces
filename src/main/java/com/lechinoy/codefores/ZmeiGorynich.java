package com.lechinoy.codefores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by liqianlong
 * 2019 2019/9/23 23:16
 */
public class ZmeiGorynich {

    static class Dragon {
        private int n;
        private int x;

        private List<Blow> blows;

        public Dragon(int n, int x) {
            this.n = n;
            this.x = x;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public List<Blow> getBlows() {
            return blows;
        }

        public void setBlows(List<Blow> blows) {
            this.blows = blows;
        }
    }

    static class Blow {
        private int d;

        private int h;

        private int x;

        private boolean able;


        public Blow(int d, int h) {
            this.d = d;
            this.h = h;
            this.x = d - h;
            this.able = this.d - this.h > 0;

        }

        public int getX() {
            return x;
        }


        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }

        public boolean isAble() {
            return able;
        }

    }


    /**
     * 击杀Zmei Gorynich
     * Zmei Gorynich 原始有x个头，n个技能，技能可以无限次使用
     * 技能包括一次击杀的头数d，和击杀后再长出的头数h
     * 使用技能后剩余的头数小于等于0，则可将它击杀
     * 3     ---要杀的龙的数量为3
     * 3 10  ---第1条龙 n=3 x=10， 有3个技能，原始有10个头
     * 6 3   ---第1条龙 第1个技能 d=6 h=3
     * 8 2   ---第1条龙 第2个技能 d=8 h=2
     * 1 4   ---第1条龙 第3个技能 d=1 h=4
     * 4 10  ---第2条龙 n=4 x=10， 有4个技能，原始有10个头
     * 4 1   ---第2条龙 第1个技能 d=4 h=1
     * 3 2   ---第2条龙 第2个技能 d=3 h=2
     * 2 6   ---第2条龙 第3个技能 d=2 h=6
     * 1 100 ---第2条龙 第4个技能 d=1 h=100
     * 2 15  ---第3条龙 n=2 x=15， 有2个技能，原始有15个头
     * 10 11 ---第3条龙 第1个技能 d=10 h=11
     * 14 100---第3条龙 第2个技能 d=14 h=100
     * 求：击杀一条龙所使用的最少技能数
     * https://codeforces.com/problemset/problem/1217/B
     * <p>
     * 思路:找出技能里面d-h最大 并且d为最大的那一组数据
     */
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        //龙的数据
        int nums = scanner.nextInt();

        List<Dragon> dragonList = new ArrayList<>();

        while (nums > 0) {

            //技能数
            int n = scanner.nextInt();
            //头的数量
            int x = scanner.nextInt();

            Dragon dragon = new Dragon(n, x);

            dragonList.add(dragon);

            List<Blow> blowList = new ArrayList<>();

            dragon.setBlows(blowList);

            while (n > 0) {
                //技能
                int d = scanner.nextInt();
                //生成头的数量
                int h = scanner.nextInt();
                Blow blow = new Blow(d, h);
                blowList.add(blow);
                n--;
            }

            nums--;
        }

        dragonList.forEach(dragon -> {

            final Blow[] best = {null};
            final Blow[] max = {null};

            dragon.getBlows().forEach(blow -> {
                //查找最优的技能
                if ((best[0] == null || blow.getX() > best[0].getX() || blow.getX() == best[0].getX() && blow.getD() > best[0].getD()) && blow.isAble()) {
                    best[0] = blow;
                }
                //查找最后一次使用的技能
                if (max[0] == null || blow.getD() > max[0].getD()) {
                    max[0] = blow;
                }
            });

            int tmp = dragon.getX() - max[0].getD();

            if (tmp <= 0) {
                System.out.println(1);
            } else {
                if (best[0] == null) {
                    System.out.println(-1);
                } else {
                    if (tmp % best[0].getX() == 0) {
                        System.out.println(tmp / best[0].getX() + 1);
                    } else {
                        System.out.println(tmp / best[0].getX() + 1 + 1);
                    }
                }

            }
        });


    }

}
