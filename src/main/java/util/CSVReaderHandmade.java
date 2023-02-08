package util;

import util.model.TipForEmployee;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderHandmade {
    public static void main(String... args) {
        List<TipForEmployee> tooltips = readBooksFromCSV("src/test/resources/tooltipSpanTextForGraphCoordinate");
        for (TipForEmployee element : tooltips) {
            System.out.println(element);
        }
    }

    public static List<TipForEmployee> readBooksFromCSV(String fileName) {
        List<TipForEmployee> tips = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] attributes = line.split("(?!\\B\"[^\"]*),(?![^\"]*\"\\B)");

            /*    for (String part : attributes) {
                    System.out.println(part);
                }
                System.out.println(attributes.length);
                were using this to see if line were splitted and recorded to array correctly
                */
                TipForEmployee tip = createTipForEmployee(attributes);
                tips.add(tip);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return tips;
    }

    private static TipForEmployee createTipForEmployee(String[] metadata) {
        //care for amount of elements in line of csv
        String date = metadata[0].replace("\"", "");//for "" in line not needed we delete it
        String name = metadata[1];
        String amount = metadata[2];

        return new TipForEmployee(date, name, amount);
    }
}

