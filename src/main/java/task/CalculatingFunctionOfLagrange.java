package task;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.ToIntFunction;

public class CalculatingFunctionOfLagrange {
    public static void main(String[] args) {

        InputParameters inputParameters = new InputParameters();

        ParamsReader.readParams(inputParameters);



        System.out.println(CalculatingFunctionOfLagrange
                .LkX(inputParameters));

        List<Double> Lkx = CalculatingFunctionOfLagrange.LkX(inputParameters);

        System.out.println(CalculatingFunctionOfLagrange
                .calculatingFunctionOfLagrange(inputParameters, Lkx));

        List<Double> ListXX = new ArrayList<>();
        Double min = Collections.min(inputParameters.argsFunction);
        Double max = Collections.max(inputParameters.argsFunction);
        Integer countOfPoint = (int) Math.ceil((max-min)/0.01);

        for (int i = 0; i < countOfPoint; i++) {
            ListXX.add(min+i*0.01);
        }
        System.out.println(ListXX);

        for (Double x : ListXX) {
            inputParameters.XX=x;
            List<Double> Lkx2 = CalculatingFunctionOfLagrange.LkX(inputParameters);

            ;
            System.out.println("XX: "+ x+" YY: "+ CalculatingFunctionOfLagrange
                    .calculatingFunctionOfLagrange(inputParameters, Lkx2));


        }


    }

    public static  Output calculatingFunctionOfLagrange(InputParameters inputParameters,
                                                List<Double> Lkx) {
        Output output = new Output();
        output.YY = 0.0;
        for (int x = 0; x < Lkx.size(); x++) {
            output.YY = output.YY +  Lkx.get(x)*inputParameters.valueOfFunctions.get(x);
        }
        return output;
    }
    public static List<Double> LkX(InputParameters inputParameters) {
        Double result = 1.0;
        List<Double> X = inputParameters.argsFunction;
        List<Double> LkxResult = new ArrayList<>();

        for (int i = 0; i < X.size(); i++) {

            for (int j = 0; j < X.size(); j++) {
                if (i!=j){
                    result = result*(inputParameters.XX - X.get(j))/
                            (X.get(i)-X.get(j));
                }
            }
            LkxResult.add(result);
            result = 1.0;
        }
        return LkxResult;
    }
}
