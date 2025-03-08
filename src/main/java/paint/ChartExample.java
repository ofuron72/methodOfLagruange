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
import task.InputParameters;
import task.ParamsReader;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
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
            for (int i = 0; i < inputParameters.argsFunction.size(); i++) {
                series.add(inputParameters.argsFunction.get(i), inputParameters.valueOfFunctions.get(i));
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
