package util;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import util.model.TipForEmployee;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class OpenCSVReader{
    public static void main(String... args) throws IOException, CsvException {
        List<TipForEmployee> tooltips= readFromCSVWithOpenCSV("src/test/resources/tooltips_expected_values.csv");
    for (TipForEmployee element : tooltips) {
        System.out.println(element);
    }
}
    public static List<TipForEmployee> readFromCSVWithOpenCSV(String fileName) throws IOException, CsvException {

        List<TipForEmployee> tips = new CsvToBeanBuilder<TipForEmployee>(new FileReader(fileName))
                .withType(TipForEmployee.class)
                .build().parse();
        return tips;
    }

}
