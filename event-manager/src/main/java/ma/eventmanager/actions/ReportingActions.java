package ma.eventmanager.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.model.GraphModel;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

@Namespace("/reporting")
@ResultPath(value = "/")
@ParentPackage("json-default")
public class ReportingActions extends AbstractAction{

	private GraphModel graph;
	@Autowired private EventManagerDao eventManagerDao;

	
	@Action(value = "eventHistory", results = { @Result(name = "success", type = "json",params = {"root", "graph" })})
	public String eventHistory() throws IOException{
		graph = new GraphModel();
		ArrayList<String> labels = new ArrayList<String>();
		labels.add("Rapport #1");
		ArrayList<String> colors = new ArrayList<String>();
		colors.add("#00FF00");
		graph.setLabels(labels);
		graph.setColors(colors);
		List<List<Object[]>> listKpiArchive = new ArrayList<List<Object[]>>();
		List<Object[]> eventArchive = eventManagerDao.eventHistory();
		listKpiArchive.add(eventArchive);
		graph.setSeriesData(listKpiArchive);
		return SUCCESS;
	}


	public GraphModel getGraph()
	{
		return graph;
	}


	public void setGraph(GraphModel graph)
	{
		this.graph = graph;
	}


	public EventManagerDao getEventManagerDao()
	{
		return eventManagerDao;
	}


	public void setEventManagerDao(EventManagerDao eventManagerDao)
	{
		this.eventManagerDao = eventManagerDao;
	}


	
}
