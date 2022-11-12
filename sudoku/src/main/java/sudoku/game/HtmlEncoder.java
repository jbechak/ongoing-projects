package sudoku.game;

import sudoku.game.shapes.Row;
import sudoku.game.shapes.TripleRow;

import java.util.List;

public class HtmlEncoder {

    public String htmlHeader() {
        String header = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"\n" +
                "          integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\"/>\n" +
                "\n" +
                "    <title>Sudoku</title>\n" +
                "    <style>\n" +
                "\n" +
                "        h1 {\n" +
                "            font-family: Comic Sans MS, Helvetica;\n" +
                "            font-style: bold;\n" +
                "            font-size: 5vw;\n" +
                "            margin-top: 5vw;\n" +
                "            margin-bottom: 4vw;\n" +
                "            }\n" +
                "\n" +
                "        table {\n" +
                "            width: 70%;\n" +
                "            border: .6vw solid black;\n" +
                "            margin-left: auto;\n" +
                "            margin-right: auto;\n" +
                "            }\n" +
                "\n" +
                "        tr {\n" +
                "            border: .2vw solid black;\n" +
                "            height: 7vw;\n" +
                "            font-family: sans-serif, monospace;\n" +
                "            font-size: 5vw;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        td {\n" +
                "            border: .2vw solid black;\n" +
                "        }\n" +
                "\n" +
                "        .third-cell {\n" +
                "            border-right: .5vw solid black;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        .bottom-row {\n" +
                "            border-bottom: .5vw solid black;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "    </style>\n" +
                "\n" +
                "\n" +
                "</head>\n";
        return header;
    }

    public String htmlTitle(String title) {
        return "<body>\n" +
                "    <div>\n" +
                "        <h1 class=\"text-center\">\n" + title + "</h1>\n" +
                "    </div>\n";
    }

    public String htmlPuzzle(List<Row> rowList) {
        String htmlBoard = "<table>\n";

        for (int i = 0; i < 9; i++) {
            if (i == 2 || i == 5) {
                htmlBoard += "<tr class =\"bottom-row\">\n";
            } else {
                htmlBoard += "<tr>\n";
            }
            htmlBoard += rowList.get(i).toHtml();
        }
        htmlBoard += "</table>\n" +
                "</body>\n" +
                "</html>";
        return htmlBoard;
    }
}
