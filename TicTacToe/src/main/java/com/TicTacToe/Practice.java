package com.TicTacToe;

import java.util.*;

public class Practice {

    public static void main(String[] args) {

        List<List> groupsTogether = new ArrayList<>();
        for (int i = 1; i <= 3; i += 2) {
            for (int j = 1; j <= i * 2 + 1; j += i) {  //1st loop j = 1; j <= 3; j += 1 -- 2nd loop j = 1; j <= 7; j += 3
                List<Integer> group = Arrays.asList(j, j + Math.abs(i - 4), j + Math.abs(i - 4) * 2);  //1st loop
                groupsTogether.add(group);
            }
        }
        for (int i = 1; i <=3; i += 2) {
            int j = 5 - i;
            List<Integer> group = Arrays.asList(i, i + j, i + j * 2);
            groupsTogether.add(group);
        }


        System.out.println(groupsTogether);

        Set<List> winningSets = new HashSet<>();
        for (int i = 1; i <= 3; i += 2) {
            for (int j = 1; j <= i * 2 + 1; j += i) {  //1st loop j = 1; j <= 3; j += 1 -- 2nd loop j = 1; j <= 7; j += 3
                List<Integer> group = Arrays.asList(j, j + Math.abs(i - 4), j + Math.abs(i - 4) * 2);  //1st loop
                winningSets.add(group);
            }
        }
        for (int i = 1; i <=3; i += 2) {
            int j = 5 - i;
            List<Integer> group = Arrays.asList(i, i + j, i + j * 2);
            winningSets.add(group);
        }

        for (List win : winningSets) {
            System.out.println(win);
        }

        List<Integer> scoringList = Arrays.asList( 2, 3, 5, 9 );

        System.out.println(scoringList);
        boolean win = false;
        if (scoringList.size() >= 3) {
            for (int i = 0; i < scoringList.size() - 2; i++) {
                for (int j = i + 1; j < scoringList.size() - 1; j ++) {
                    for (int k = j + 1; k < scoringList.size(); k++) {
                        List<Integer> testList = Arrays.asList(scoringList.get(i), scoringList.get(j), scoringList.get(k));
                        if (winningSets.contains(testList)) {
                            win = true;
                        }

                    }
                }
            }

        }
        System.out.println(win);

    }
}
