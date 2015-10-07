package ma.eventmanager.model;

import java.util.List;

public class GraphModel
{
	private String name;
	private String title;
	private List<List<Object[]>> seriesData;
	private List<String> labels;
	private List<String> colors;
	

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final List<List<Object[]>> getSeriesData() {
		return seriesData;
	}

	public final void setSeriesData(List<List<Object[]>> seriesData) {
		this.seriesData = seriesData;
	}

	public final List<String> getLabels() {
		return labels;
	}

	public final void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public final List<String> getColors() {
		return colors;
	}

	public final void setColors(List<String> colors) {
		this.colors = colors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
