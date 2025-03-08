package task;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConnectCsv {
    public static void main(String[] args) {
        String filePath = "src/main/java/task/inputData.csv";
        List<Double> XX = new ArrayList<>();
        List<Double> YY = new ArrayList<>();
        Double X;
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            // Считываем первую строку (например, заголовок)
            reader.readNext();
            String[] firstRow = reader.readNext();
            if (firstRow != null) {
                String value = firstRow[2];
                X= Double.parseDouble(value);
                System.out.println(X);
            }
        }catch(IOException | CsvException e){
            e.printStackTrace();
        }

        try(CSVReader reader = new CSVReader(new FileReader(filePath))){
            List<String[]> records = reader.readAll();
            for(String[] record : records.subList(1, records.size())){
                XX.add(Double.parseDouble(record[0]));
                YY.add(Double.parseDouble(record[1]));
            }

        }catch(IOException | CsvException e){
            e.printStackTrace();
        }
        System.out.println(XX);
        System.out.println(YY);
    }
}
