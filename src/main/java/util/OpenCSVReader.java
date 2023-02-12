package util;

import com.opencsv.bean.CsvToBeanBuilder;
import util.model.TipForEmployee;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class OpenCSVReader{

    public static List<TipForEmployee> readFromCSV(String fileName) throws IOException{
        return new CsvToBeanBuilder<TipForEmployee>(new FileReader(fileName))
                .withType(TipForEmployee.class)
                .build().parse();
    }

}
