package wordSearch.puzzle;

import wordSearch.crossword.SortByStartingX;
import wordSearch.crossword.SortByStartingY;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordList {
    private List<Word> words = new ArrayList<>();
    private String title;
    private int longestWord;
    private final int FULL_WIDTH = 81;

    public WordList() {
    }

    public WordList(List<Word> words) {
        this.words = words;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<Word> populateFromStringList(List<String> wordCollection) {
        words.clear();
        for (String word : wordCollection) {
            String upperCaseWord = word.toUpperCase();
            String[] brokenWord = upperCaseWord.split("");
            Word newWord = new Word(brokenWord);
            words.add(newWord);
        }
        return words;
    }

    @Override
    public String toString() {
        String wordListString = tabToCenter("List of words") + "\n\n";

        int wordCounter = 0;
        int columns = FULL_WIDTH / 20;
        for (Word word : words) {
            wordListString += (word + addSpace(word.toString()));
            wordCounter++;
            if (wordCounter == columns) {
                wordListString += "\n";
                wordCounter = 0;
            }
        }
        return wordListString;
    }

    private String tabToCenter(String word) {
        String space = "";
        int spaces = (FULL_WIDTH - word.length()) / 2;
        for (int i = 0; i < spaces; i++) {
            space += " ";
        }
        return space + word;
    }

    private String addSpace(String word) {
        String space = "";
        int spaces = 20 - word.length();
        for (int i = 0; i < spaces; i++) {
            space += " ";
        }
        return space;
    }

    public int longestWord() {
        longestWord = 0;
        for (Word word : words) {
            if (word.length() > longestWord) {
                longestWord = word.length();
            }
        }
        return longestWord;
    }


    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> newWords) {
        words = new ArrayList<>();
        for (Word word : newWords) {
            words.add(word);
        }

        this.words = newWords;
    }

    public Word getWord(int index) {
        return words.get(index);
    }

    public void setWord(int index, Word word) {
        Word newWord = new Word(word.getLetterArray());
        newWord.setStartingX(word.getStartingX());
        newWord.setStartingY(word.getStartingY());
        newWord.setInGrid(word.isInGrid());
        newWord.setDirection(word.getDirection());

        words.set(index, newWord);
    }

    public void addWord(Word word) {
        Word newWord = new Word(word.getLetterArray());
        newWord.setStartingX(word.getStartingX());
        newWord.setStartingY(word.getStartingY());
        newWord.setInGrid(word.isInGrid());
        newWord.setDirection(word.getDirection());
        newWord.setNumber(word.getNumber());
        newWord.setClue(word.getClue());
        words.add(newWord);
    }



    public int size() {
        return words.size();
    }

    public void printResults() {
        for (Word word : words) {
            if (word.isInGrid()) {
                System.out.println(word.asString() + " has been added to the word search.");
            }
        }
        for (Word word : words) {
            if (!word.isInGrid()) {
                System.out.println(word.asString() + " has not been added.");
            }
        }
    }

    public void add(Word word) {
        words.add(word);
    }

    public void resetInGrid() {
        for (Word word : words) {
            word.setInGrid(false);
        }
    }

    public void clearWordList() {
        words.clear();
    }

    private int maxStartingY(List<Word> listToCheck) {
        int maxY = 0;
        for (Word word : listToCheck) {
            if (word.getStartingY() > maxY) {
                maxY = word.getStartingY();
            }
        }
        return maxY;
    }

    private List<Word> findWordsAtY (List<Word> listToCheck, int y) {
        List<Word> wordsAtY = new ArrayList<>();
        for (Word word : listToCheck) {
            if (word.getStartingY() == y) {
                wordsAtY.add(word);
            }
        }
        return wordsAtY;
    }

    public int wordNumberAtXY(int x, int y) {
        for (Word word : words) {
            if (word.getStartingX() == x && word.getStartingY() == y) {
                return word.getNumber();
            }
        }
        return 0;
    }

    public void sortWordList() {
        Collections.sort(words, new SortByStartingY());
        List<Word> newList = new ArrayList<>();
        int wordNumber = 1;

        for (int y = 0; y <= maxStartingY(words); y++) {
            if (findWordsAtY(words, y).size() == 1) {
                newList.add(findWordsAtY(words, y).get(0));
                newList.get(newList.size() - 1).setNumber(wordNumber);
                wordNumber++;
            } else if (findWordsAtY(words, y).size() > 1) {
                List<Word> listToSort = findWordsAtY(words, y);
                Collections.sort(listToSort, new SortByStartingX());
                for (int i = 0; i < listToSort.size(); i++) {
                    newList.add(listToSort.get(i));
                    if (i > 0 && listToSort.get(i).getStartingX() == listToSort.get(i - 1).getStartingX()) {
                        newList.get(newList.size() - 1).setNumber(wordNumber - 1);
                    } else {
                        newList.get(newList.size() - 1).setNumber(wordNumber);
                        wordNumber++;
                    }

                }

//                for (Word word : listToSort) {
//                    newList.add(word);
//                    newList.get(newList.size() - 1).setNumber(wordNumber);
//                    wordNumber++;
//                }
            }
        }



        this.words = newList;
    }

//    public void manualWordListSort(List<Word> wordGroup) {
//        boolean sorted = false;
//
//        //divide in half
//        List<Word> bottomHalf = new ArrayList<>();
//        List<Word> topHalf = new ArrayList<>();
//        for (int i = 0; i < wordGroup.size() / 2 + 1; i++) {
//            bottomHalf.add(wordGroup.get(i));
//        }
//        for (int i = wordGroup.size() / 2 + 1; i < wordGroup.size(); i++) {
//            topHalf.add(wordGroup.get(i));
//        }
//
//        mergeSort(bottomHalf, topHalf, sorted);
//        //mergeSort(topHalf);
//
//    }
//
//    public List<Word> mergeSort(List<Word> bottomHalf, List<Word> topHalf, boolean sorted) {
//
//        if (sorted) {
//            //merge
//            List<Word> sortedList = new ArrayList<>();
//            Word[] sortedArray = new Word[bottomHalf.size() + topHalf.size()];
//
//            boolean isTopHigher = true;
//            if (bottomHalf.get(0).getStartingY() > topHalf.get(0).getStartingY()) {
//                isTopHigher = false;
//                sortedArray[0] = bottomHalf.get(0);
//                sortedArray[1] = topHalf.get(0);
//            } else {
//                sortedArray[0] = topHalf.get(0);
//                sortedArray[1] = bottomHalf.get(0);
//            }
//
//            for (int i = 2, j = 1; i < bottomHalf.size(); i += 2, j++) {
//                if (isTopHigher) {
//                    //test next bottom number against last index in array
//                    if (bottomHalf.get(j).getStartingY() < sortedArray[i - 1].getStartingY()) {
//                        sortedArray[i] = sortedArray[i - 1];
//                        sortedArray[i - 1] = bottomHalf.get(j);
//                        sortedArray[i + 1] = topHalf.get(j);
//                    } else {
//                        sortedArray[i] = bottomHalf.get(j);
//                        sortedArray[i + 1] = topHalf.get(j);
//                    }
//                } else {
//                    if (topHalf.get(j).getStartingY() < sortedArray[i - 1].getStartingY()) {
//                        sortedArray[i] = sortedArray[i - 1];
//                        sortedArray[i - 1] = topHalf.get(j);
//                    } else {
//                        sortedArray[i] = topHalf.get(j);
//                    }
//
//
//                }
//                if (bottomHalf.get(j).getStartingY() < topHalf.get(j).getStartingY()) {
//                    sortedArray[i] = bottomHalf.get(j);
//                    sortedArray[i + 1] = topHalf.get(j);
//                } else {
//                    sortedArray[i] = topHalf.get(j);
//                    sortedArray[i + 1] = bottomHalf.get(j);
//                }
//
//            }
//
//            return sortedList;
//
//        } else {
//            if (bottomHalf.size() > 2) {
//                splitAndMerge(bottomHalf, sorted);
//            }
//            if (topHalf.size() > 2) {
//                splitAndMerge(topHalf, sorted);
//            }
//
//        } else{
//            //sort size 2 or less
//            if (wordGroup.size() == 2 && wordGroup.get(0).getStartingY() > words.get(1).getStartingY()) {
//                Word temp = wordGroup.get(0);
//                wordGroup.set(0, wordGroup.get(1));
//                wordGroup.set(1, wordGroup.get(2));
//
//            }
//            return wordGroup;
//
//        }
//    }

//
//    private void splitAndMerge(List<Word> listToSplit, boolean sorted) {
//        List<Word> bottomHalf = new ArrayList<>();
//        List<Word> topHalf = new ArrayList<>();
//        for (int i = 0; i < listToSplit.size() / 2 + 1; i++) {
//            bottomHalf.add(bottomHalf.get(i));
//        }
//        for (int i = listToSplit.size() / 2 + 1; i < listToSplit.size(); i++) {
//            topHalf.add(bottomHalf.get(i));
//        }
//        if (bottomHalf.size() <= 2) {
//            bottomHalf = sortList(bottomHalf);
//            sorted = true;
//        }
//        if (topHalf.size() <= 2) {
//            topHalf = sortList(topHalf);
//            sorted = true;
//        }
//
//        mergeSort(bottomHalf, topHalf, sorted);
//    }
//
//    private List<Word> sortList(List<Word> listToSort) {
//        if (listToSort.size() == 2 && listToSort.get(0).getStartingY() > words.get(1).getStartingY()) {
//            Word temp = listToSort.get(0);
//            listToSort.set(0, listToSort.get(1));
//            listToSort.set(1, listToSort.get(2));
//        }
//        return listToSort;
//    }
}
