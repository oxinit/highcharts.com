package util;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import util.model.TipForEmployee;
import util.model.TooltipEmployeeForExpectedValue;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class OpenCSVReader{

    public static List<TipForEmployee> readFromCSV(String fileName) throws IOException{

        return new CsvToBeanBuilder<TipForEmployee>(new FileReader(fileName))
                .withType(TipForEmployee.class)
                .withSkipLines(1)
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                .withIgnoreLeadingWhiteSpace(true)
                .withFilter(strings -> strings[1]!=null)
                .build().parse();
    }
    public static List<TooltipEmployeeForExpectedValue> readFromCSVExpectedValue(String fileName) throws IOException{
        return new CsvToBeanBuilder<TooltipEmployeeForExpectedValue>(new FileReader(fileName))
                .withType(TooltipEmployeeForExpectedValue.class)
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                .withFilter(strings -> strings[1]!=null)
                .build().parse();
    }
}
