package task;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ParamsReader {
    public static void readParams(InputParameters parameters) {
        String filePath = "src/main/java/task/inputData.csv";
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            // Считываем первую строку (например, заголовок)
            reader.readNext();
            String[] firstRow = reader.readNext();
            if (firstRow != null) {
                String value = firstRow[2];
                parameters.XX= Double.parseDouble(value);
                System.out.println(parameters.XX);
            }
        }catch(IOException | CsvException e){
            e.printStackTrace();
        }

        try(CSVReader reader = new CSVReader(new FileReader(filePath))){
            List<String[]> records = reader.readAll();
            for(String[] record : records.subList(1, records.size())){
                parameters.argsFunction.add(Double.parseDouble(record[0]));
                parameters.valueOfFunctions.add(Double.parseDouble(record[1]));
            }
        }catch(IOException | CsvException e){
            e.printStackTrace();
        }
    }
}
