package br.com.orlandoburli.personalerp.facades.reports.vendas.gerencial.chart;

import java.awt.Color;
import java.text.NumberFormat;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.AbstractCategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.TextAnchor;

public class SimpleBarChartCustomizer implements JRChartCustomizer {

	public void customize(JFreeChart chart, JRChart jasperChart) {
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryItemRenderer line = plot.getRenderer();

		line.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		line.setBaseItemLabelsVisible(true);
		line.setBaseItemLabelPaint(Color.BLACK);
		
		//ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER, TextAnchor.CENTER, 55.0);
		ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE9, TextAnchor.BOTTOM_CENTER, TextAnchor.CENTER, 55.0);
		line.setBasePositiveItemLabelPosition(p);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		CategoryItemRenderer catRenderer = ((CategoryItemRenderer) renderer);
		catRenderer.setBaseItemLabelGenerator(new NumberLabelGenerator("#,##0.00", NumberFormat.getInstance()));
	}

	static class NumberLabelGenerator extends AbstractCategoryItemLabelGenerator implements CategoryItemLabelGenerator {
		private static final long serialVersionUID = 1L;

		public NumberLabelGenerator(String labelFormat, NumberFormat formatter, NumberFormat percentFormatter) {
			super(labelFormat, formatter, percentFormatter);
		}

		protected NumberLabelGenerator(String labelFormat, NumberFormat formatter) {
			super(labelFormat, formatter);
		}

		private NumberFormat formatter = NumberFormat.getInstance();

		public String generateLabel(CategoryDataset dataset, int series, int category) {
			Number b = dataset.getValue(series, category);
			formatter.setMaximumFractionDigits(2);
			return formatter.format(b);
		}

	}
}