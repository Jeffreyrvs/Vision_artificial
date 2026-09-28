package lectura;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.BasicStroke;

public class Grafica {
    public static void histograma(int[] rojo, int[] verde, int[] azul) {

        // 1. Crear series de datos XY numéricas
        XYSeries seriesRojo = new XYSeries("Rojo");
        XYSeries seriesVerde = new XYSeries("Verde");
        XYSeries seriesAzul = new XYSeries("Azul");

        for (int i = 0; i < 256; i++) {
            seriesRojo.add(i, rojo[i]);
            seriesVerde.add(i, verde[i]);
            seriesAzul.add(i, azul[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesRojo);
        dataset.addSeries(seriesVerde);
        dataset.addSeries(seriesAzul);

        // 2. Crear gráfico de líneas XY
        JFreeChart grafica = ChartFactory.createXYLineChart(
                "Histograma RGB",
                "Intensidad (0 - 255)",
                "Cantidad de Píxeles",
                dataset,
                PlotOrientation.VERTICAL,
                true,  // Leyenda
                true,  // Tooltips
                false  // URLs
        );

        XYPlot plot = grafica.getXYPlot();

        // 3. Ajustar el Eje X (Rango fijo 0 a 255 con marcas legibles cada 32 niveles)
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setRange(0.0, 255.0);
        domainAxis.setTickUnit(new NumberTickUnit(32));

        // 4. Ajustar el Eje Y (Escalado automático con margen superior)
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(true);
        rangeAxis.setUpperMargin(0.08); // 8% de margen superior para que no toque el borde

        // 5. Personalizar colores y líneas
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // Desactivar puntos en las líneas para un trazo limpio
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesShapesVisible(2, false);

        // Colores y grosor de trazo
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, new Color(0, 180, 0)); // Verde legible
        renderer.setSeriesPaint(2, Color.BLUE);

        renderer.setSeriesStroke(0, new BasicStroke(1.8f));
        renderer.setSeriesStroke(1, new BasicStroke(1.8f));
        renderer.setSeriesStroke(2, new BasicStroke(1.8f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(new Color(245, 245, 245));

        // 6. Configurar ventana
        JFrame ventana = new JFrame("Histograma RGB");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(new ChartPanel(grafica));
        ventana.setSize(1000, 600);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
