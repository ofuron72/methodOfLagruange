package paint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import task.CalculatingFunctionOfLagrange;
import task.InputParameters;
import task.ParamsReader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartExample {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("График функции");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            XYSeries series = new XYSeries("y(x)");

            InputParameters inputParameters = new InputParameters();
            ParamsReader.readParams(inputParameters);

            List<Double> listXX = new ArrayList<>();
            List<Double> listYY = new ArrayList<>();
            Double min = Collections.min(inputParameters.argsFunction);
            Double max = Collections.max(inputParameters.argsFunction);
            Integer countOfPoint = (int) Math.ceil((max-min)/0.01);

            for (int i = 0; i < countOfPoint; i++) {
                listXX.add(min+i*0.01);
            }
            System.out.println(listXX);

            for (Double x : listXX) {
                inputParameters.XX=x;
                List<Double> Lkx2 = CalculatingFunctionOfLagrange.LkX(inputParameters);

                ;
                System.out.println("XX: "+ x+" YY: "+ CalculatingFunctionOfLagrange
                        .calculatingFunctionOfLagrange(inputParameters, Lkx2));
                listYY.add(CalculatingFunctionOfLagrange
                        .calculatingFunctionOfLagrange(inputParameters, Lkx2).YY);

            }


            for (int i = 0; i < listXX.size(); i++) {
                series.add(listXX.get(i), listYY.get(i));
            }




            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series);

            JFreeChart chart = ChartFactory.createXYLineChart(
                    "График функции",
                    "X", "Y",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false
            );
            // Получаем объект XYPlot
            XYPlot plot = chart.getXYPlot();

            // Получаем рендерер (стиль рисования)
            XYItemRenderer renderer = plot.getRenderer();

            // Устанавливаем маркеры для точек в виде крупных кругов
            XYLineAndShapeRenderer lineRenderer = (XYLineAndShapeRenderer) renderer;
            lineRenderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-4, -4, 8, 8)); // Размер точки
            lineRenderer.setSeriesPaint(0, Color.RED); // Цвет точек
            lineRenderer.setSeriesOutlineStroke(0, new BasicStroke(3)); // Жирный контур точек

            frame.add(new ChartPanel(chart));
            frame.setVisible(true);
        });
    }


}
