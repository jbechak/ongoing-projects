package wordSearch;

import wordSearch.crossword.CrosswordFileHandler;
import wordSearch.crossword.CrosswordPuzzle;
import wordSearch.puzzle.Grid;
import wordSearch.puzzle.Word;
import wordSearch.puzzle.WordList;

public class HtmlPrintEncoder extends HtmlEncoder {

    @Override
    public String htmlHeader() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <link href=\"https://fonts.googleapis.com/css?family=Lobster\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"\n" +
                "              integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\"/>\n" +
                "\n" +
                "        <style>\n" +
                "            body { background-color: white; }\n" +
                "            h1 { color: black; font-family: Comic Sans MS, Lobster, sans serif; font-size: 5vw; }\n" +
                "            h2 { font-size: 3vw; white-space: nowrap; margin-top: 1vw; margin-bottom: -1vw; }\n" +
                "            h3 { font-size: 2vw; }\n" +
                "            p { font-size: 2vw; line-height: 2vw; }\n" +
                "            hr { color: black; background-color: black; height: .3vw; margin-top: 2vw; margin-bottom: 0vw; }\n" +
                "            ul.no-bullets { list-style-type: none; }\n" +
                "            .title { margin-top: 0vw; margin-bottom: 0vw; }\n" +
                "            .subtitle { margin-bottom: 0vw !important; }\n" +
                "            .difficulty { font-family: sans-serif; color: black; font-size: 3vw; }\n" +
                "            .table { margin-top: 4vw; }\n" +
                "            .word-list { column-count: 4; margin-bottom: 1vw; }\n" +
                "            .instructions { color: black; font-family: sans-serif; font-size: 2vw; margin-top: 3vw !important; margin-bottom: -2vw; }\n" +
                "            .word-list-header { font-size: 4vw; }\n" +
                "\n" +
                "            #puzzle { width: fit-content; height: fit-content; font-family: monospace; font-size: 3vw; border-color: black; border-width: .5vw;\n" +
                "            border-style: solid; border-radius: .5em; padding: 0vw 1.5vw 2vw 1.5vw; margin: 5vw auto 5vw auto; }\n" +
                "\n" +
                "            #word-list { padding-bottom: 0vw; }\n" +
                "        </style>\n" +
                "    </head>";

    }

    public String crossWordHtml(CrosswordPuzzle puzzle) {
        return crosswordHtmlHeader(puzzle.getTitle()) +
                crosswordPuzzleHtml(puzzle.getGrid(), puzzle.getSortedWordList()) +
                puzzle.htmlClueLists() + "</body>\n" + "</html>\n";
    }

    public String crosswordHtmlHeader(String title) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Crossword</title>\n" +
                "    <style>\n" +
                "\n" +
                "        body {\n" +
                "            font-family: arial;\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            font-family: Comic Sans MS, Helvetica;\n" +
                "            font-style: bold;\n" +
                "            font-size: 5vw;\n" +
                "            margin-top: 3vw;\n" +
                "            margin-bottom: 1vw;\n" +
                "            text-align: center;\n" +
                "            }\n" +
                "\n" +
                "        #puzzle {\n" +
                "            width: fit-content;\n" +
                "            /* border: .6vw solid black;\n" +
                "            border-radius: 5%; */\n" +
                "            padding: 3vw;\n" +
                "            margin-left: auto;\n" +
                "            margin-right: auto;\n" +
                "            margin-bottom: 0vw;\n" +
                "            border-spacing: 0vw;\n" +
                "            }\n" +
                "\n" +
                "        .clue-list {\n" +
                "            margin-top: 2vw;\n" +
                "            width: 40vw;\n" +
                "        }\n" +
                "\n" +
                "        h2 {\n" +
                "            text-align: center;\n" +
                "            font-family: Comic Sans MS;\n" +
                "            font-size: 4vw;\n" +
                "            margin-bottom: 0vw;\n" +
                "        }\n" +
                "\n" +
                "        hr {\n" +
                "            margin-bottom: 0vw;\n" +
                "        }\n" +
                "\n" +
                "        tr {\n" +
                "            /*border: .1vw solid black;*/\n" +
                "            height: 3vw;\n" +
                "            font-family: sans-serif, monospace;\n" +
                "            font-size: 3vw;\n" +
                "        }\n" +
                "\n" +
                "        th {\n" +
                "            vertical-align: top;\n" +
                "        }\n" +
                "\n" +
                "        td {\n" +
                "            /*border: .1vw solid black;*/\n" +
                "            width: 3vw;\n" +
                "            font-size: 1vw;\n" +
                "            vertical-align: top;\n" +
                "        }\n" +
                "\n" +
                "        .no-bullets {\n" +
                "            list-style: none;\n" +
                "        }\n" +
                "\n" +
                "        .outside {\n" +
                "            border: none;\n" +
                "        }\n" +
                "\n" +
                "        .no-bottom-border {\n" +
                "            border-bottom: none;\n" +
                "        \n" +
                "        }\n" +
                "\n" +
                "        .no-right-border {\n" +
                "            border-right: none;\n" +
                "        } \n" +
                "\n" +
                "        .left-border {\n" +
                "            border-left: .2vw solid black;\n" +
                "        }\n" +
                "\n" +
                "        .bottom-border {\n" +
                "            border-bottom: .2vw solid black;\n" +
                "        }\n" +
                "\n" +
                "        .right-border {\n" +
                "            border-right: .2vw solid black;\n" +
                "        }\n" +
                "\n" +
                "        .top-border {\n" +
                "            border-top: .2vw solid black;\n" +
                "        }\n" +
                "\n" +
                "        .clue-list {\n" +
                "            display: inline-block;\n" +
                "        }\n" +
                "\n" +
                "        li {\n" +
                "            margin-bottom: 1vw;\n" +
                "            text-align: left;\n" +
                "            font-size: 3vw;\n" +
                "            font-weight: 400;\n" +
                "        }\n" +
                "       \n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div>\n" +
                "    <h1>" + title + "</h1>\n" +
                "</div>";

    }

    public String crosswordPuzzleHtml(Grid grid, WordList wordList) {
        String puzzleHtml = "<table id=\"puzzle\">\n";
        for (int y = 0; y < grid.getHeight(); y++) {
            puzzleHtml += "<tr>\n";
            for (int x = 0; x < grid.getWidth(); x++) {
                String classString = "";
                String number = "";
                if (!grid.getBlock(x, y).equals(".")) {
                    classString = "left-border bottom-border ";
                    if (y == 0 || isTheTopOfAWord(grid, x, y)) {
                        classString += "top-border ";
                    }
                    if (x == grid.getWidth() - 1 || isTheRightEndOfAWord(grid, x, y)) {
                        classString += "right-border";
                    }
                    if (wordList.wordNumberAtXY(x, y) != 0) {
                        number += wordList.wordNumberAtXY(x, y);
                    }

                } else {
                    if (x > 0 && !grid.getBlock(x - 1, y).equals(".") && !isTheRightEndOfAWord(grid, x - 1, y)) {
                        classString = "left-border ";
                    }
                    if (y < grid.getHeight() - 1 && !grid.getBlock(x, y + 1).equals(".") && !isTheTopOfAWord(grid, x, y + 1)) {
                        classString += "bottom-border ";
                    }
                }

                puzzleHtml += "<td class =\"" + classString + "\">" + number + "</td>\n";
            }
            puzzleHtml += "</tr>\n";
        }
        puzzleHtml += "</table>\n";

        return puzzleHtml;
    }


    private boolean isTheTopOfAWord(Grid grid, int x, int y) {
        if ((x > 0 && grid.getBlock(x - 1, y).equals(".")) &&
                (x < grid.getWidth() - 1 && grid.getBlock(x + 1, y).equals(".")) &&
                (y > 0 && grid.getBlock(x, y - 1).equals("."))) {
            return true;
        }
        return false;
    }

    private boolean isTheRightEndOfAWord(Grid grid, int x, int y) {
        if ((x < grid.getWidth() - 1 && grid.getBlock(x + 1, y).equals(".")) &&
                (y > 0 && grid.getBlock(x, y - 1).equals(".")) &&
                (y < grid.getHeight() - 1 && grid.getBlock(x, y + 1).equals("."))) {
            return true;
        }
        return false;
    }

}
