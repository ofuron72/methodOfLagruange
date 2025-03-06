package task;

import java.util.ArrayList;
import java.util.List;

public class CalculatingFunctionOfLagrange {
    public static void main(String[] args) {
        List<Double> argsX = new ArrayList<Double>();
        argsX.add(-2.0);
        argsX.add(-4.0/3.0);
        argsX.add(0.0);
        argsX.add(4.0/3.0);
        List<Double> argsY = new ArrayList<Double>();
        argsY.add(0.0);
        argsY.add(0.5);
        argsY.add(1.0);
        argsY.add(0.5);
        Double X = 1.0;
        InputParameters inputParameters = new InputParameters();
        inputParameters.argsFunction = argsX;
        inputParameters.valueOfFunctions = argsY;
        inputParameters.XX = X;

        System.out.println(CalculatingFunctionOfLagrange
                .LkX(inputParameters));

        List<Double> Lkx = CalculatingFunctionOfLagrange.LkX(inputParameters);

        System.out.println(CalculatingFunctionOfLagrange
                .calculatingFunctionOfLagrange(inputParameters, Lkx));





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
