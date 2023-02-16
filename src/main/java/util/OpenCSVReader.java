package util;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import util.model.TipForEmployee;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class OpenCSVReader{
    public static void main(String[] args) throws IOException {
        List<TipForEmployee> tooltips = readFromCSV("src/test/resources/highcharts-and-highsoft.csv");
       tooltips.forEach(e-> System.out.println(e.toString()));
    }
    public static List<TipForEmployee> readFromCSV(String fileName) throws IOException{

        return new CsvToBeanBuilder<TipForEmployee>(new FileReader(fileName))
                .withType(TipForEmployee.class)
                .withSkipLines(1)
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                .withIgnoreLeadingWhiteSpace(true)
                .withFilter(strings -> strings[1]!=null)
                .build().parse();
    }

}
