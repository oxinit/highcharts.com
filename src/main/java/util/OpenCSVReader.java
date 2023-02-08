package util;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import util.model.TipForEmployee;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenCSVReader{
    public static void main(String... args) throws IOException, CsvException {
        List<TipForEmployee> tooltips= readFromCSVWithOpenCSV("src/test/resources/tooltips_expected_values.csv");
    for (TipForEmployee element : tooltips) {
        System.out.println(element);
    }
}
    public static List<TipForEmployee> readFromCSVWithOpenCSV(String fileName) throws IOException, CsvException {
        List<TipForEmployee> tips = new ArrayList<>();

        CSVReader reader = new CSVReaderBuilder(new FileReader(fileName)).build();
        String[] nextLine ;
        while ((nextLine= reader.readNext()) != null) {
            System.out.println(nextLine[0] + nextLine[1] + nextLine[2]);
            TipForEmployee tip = createTipForEmployee(nextLine);
            tips.add(tip);
        }
        return tips;
    }
    private static TipForEmployee createTipForEmployee(String[] metadata) {
        String date = metadata[0];
        String name = metadata[1];
        String amount = metadata[2];

        return new TipForEmployee(date, name, amount);
    }
}
