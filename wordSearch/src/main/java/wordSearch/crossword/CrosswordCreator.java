package wordSearch.crossword;

import java.util.List;

public class CrosswordCreator {

    public void findCommonLetters(List<String[]> wordList) {

        for (int i = 0; i < wordList.size() - 1; i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                for (int k = 0; k < wordList.get(j).length; k++) {
                    String letterToCheckAgainst = wordList.get(j)[k];
                    String[] currentWord = wordList.get(i);
                    for (int l = 0; l < currentWord.length; l++) {
                        if (currentWord[l].equals(letterToCheckAgainst)) {
                            currentWord[l] = currentWord[l].toUpperCase();
                            wordList.get(j)[k] = wordList.get(j)[k].toUpperCase();
                        }
                    }
                }
            }
        }

        for (String[] word : wordList) {
            for (String letter : word) {
                System.out.print(letter);
            }
            System.out.println();
        }

    }
}
