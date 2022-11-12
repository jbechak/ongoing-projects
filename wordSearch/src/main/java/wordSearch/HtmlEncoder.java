package wordSearch;

import wordSearch.puzzle.Grid;
import wordSearch.puzzle.Word;
import wordSearch.puzzle.WordList;

public class HtmlEncoder {

    public String htmlHeader() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <link href=\"https://fonts.googleapis.com/css?family=Lobster\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\"/>\n" +
                "\n" +
                "        <style>\n" +
                "            body { background-color: #FEFE99; }\n" +
                "            h1 { color: #EE0000; font-family: Comic Sans MS, Lobster, sans serif; font-size: 5vw; }\n" +
                "            h2 { color: #3344AA; font-size: 3vw; white-space: nowrap; margin-top: 1vw; margin-bottom: -1vw; }\n" +
                "            h3 { font-size: 2vw; }\n" +
                "            p { font-size: 2vw; line-height: 2vw; }\n" +
                "            hr { color: #AA0000; background-color: #AA0000; height: .3vw; margin-top: 2vw; margin-bottom: 0vw; }\n" +
                "            ul.no-bullets { list-style-type: none; }\n" +
                "            .title { margin-top: 0vw; margin-bottom: 0vw; }\n" +
                "            .difficulty { font-family: sans-serif; color: #0000FF; font-size: 3vw; }\n" +
                "            .table { margin-top: 4vw; }\n" +
                "            .word-list { column-count: 4; margin-bottom: 1vw; }\n" +
                "            .instructions { color: #DD0000; font-family: sans-serif;\n" +
                "                font-size: 2vw; margin-top: 3vw !important; margin-bottom: -2vw; }\n" +
                "            .word-list-header { font-size: 4vw; }\n" +
                "\n" +
                "            #puzzle { width: fit-content; height: fit-content; font-family: monospace; font-size: 3vw; background-color: beige; border-color: #CC7777; border-width: .5vw;\n" +
                "            border-style: solid; border-radius: .5em; padding: 0vw 1.5vw 2vw 1.5vw; margin: 5vw auto 5vw auto; }\n" +
                "\n" +
                "            #word-list { padding-bottom: 0vw; }\n" +
                "        </style>\n" +
                "    </head>";

    }

    public String htmlTitle(String title, String difficulty) {
        return "<div class=\"container-fluid\">\n" +
                "        <body>\n" +
                "            <div class=\"title\">\n" +
                "                <h1 class=\"text-center\">" + title + "</h1>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"subtitle\">\n" +
                "                <h2 class=\"text-center difficulty\">" + difficulty + "</h2>\n" +
                "            </div>" +
                "            <hr>";
    }

    public String htmlInstructions(String instructions) {

        return "            <div class=\"instructions\">\n" +
                "                <p class=\"text-center\">" + instructions.replace("\n", "</p>\n" +
                "                <p class=\"text-center\">") + "</p>\n" +
                "            </div>";
    }

    public String htmlGrid(Grid grid) {
        String gridHtml = "<div id=\"puzzle\">";
        for (int i = 0; i < grid.getHeight(); i++) {
            gridHtml += "<h2 class=\"text-center\">";
            for (int j = 0; j < grid.getWidth(); j++) {
                gridHtml += (grid.getBlock(j, i) + "  ");
            }
            gridHtml += "</h2>\n";
        }
        gridHtml += "</div>\n";
        return gridHtml;
    }

    public String htmlWordList(WordList wordList) {
        String wordListHtml = "            <hr>\n" +
                              "            <h1 class=\"text-center word-list-header\">List of words</h1>\n" +
                              "            <div class=\"table\">\n" +
                              "                <h3>";

        int wordCounter = 0;
        int columns = 4;

        wordListHtml += "<ul class=\"no-bullets word-list\">";
        for (Word word : wordList.getWords()) {
            wordListHtml += "<li>" + word + "</li>";
            wordCounter++;
            if (wordCounter == columns) {
                wordListHtml += "</ul>\n<ul class=\"no-bullets word-list\">";
                wordCounter = 0;
            }
        }
        wordListHtml += "                    </ul>\n" +
                        "                </h3>\n" +
                        "            </div>\n" +
                        "        </body>\n" +
                        "    </div>\n" +
                        "</html>";
        return wordListHtml;
    }
}
