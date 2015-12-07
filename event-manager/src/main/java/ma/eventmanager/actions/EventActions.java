package ma.eventmanager.actions;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;












import ma.evenetmanager.criteria.CriteriaModel;
import ma.eventmanager.constant.Constants;
import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Event;
import ma.eventmanager.entitys.Room;
import ma.eventmanager.entitys.Subscription;
import ma.eventmanager.model.DataTableResponseObject;
import ma.eventmanager.model.EntityGroup;
import ma.eventmanager.model.EntityGroupedBy;
import ma.eventmanager.model.EventVo;
import ma.eventmanager.util.ProjectHelper;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;


@Namespace("/event")
@ResultPath(value = "/")
@ParentPackage("json-default")
public class EventActions extends AbstractAction{
	
	private static final long serialVersionUID = 8397678142447406483L;
	private List<Event> list= new ArrayList<Event>();
	private DataTableResponseObject resp = new DataTableResponseObject();
	private EventVo event;
	private String errorNotification;
	private static Logger logger = Logger.getLogger(EventActions.class);
	
	//Event fields	
	private Integer id;
	private String name;
	private Double price;
	private String description;
	private String state;
	private Date date;
	private Integer roomId;
	private String rating;
	
	private String selectedDate;
	private String zoom;
	private String sourceView;
	
	@Autowired private EventManagerDao eventManagerDao;
	private Subscription subscription;
	
	
	@Action(value = "choice", results = {@Result(name="home",location="/event/choice.jsp")})
	public String  choice() {
		list = eventManagerDao.listEvents(0,Constants.DEFAULT_ROWS_NUM,new Date(),null,"CHOICE");				
		return "home";
	}
	
	@Action(value = "administration", results = {@Result(name="administration",location="/event/administration.jsp")})
	public String  administration() {
		try{
			list = eventManagerDao.listEvents(0,Constants.DEFAULT_ROWS_NUM,new Date(),null,"ADMIN");				
			return "list";
		}catch(DataAccessResourceFailureException e){
			e.printStackTrace();
			this.errorNotification = "Error accured during listing the events : DataAccessResourceFailureException";
			return "administration";
		}
	}

	

	
	private List<CriteriaModel> usedSearchFields(){
		List<CriteriaModel> criterias = new ArrayList<CriteriaModel>();
		criterias.add(new CriteriaModel("name",getName()));
		criterias.add(new CriteriaModel("description",getDescription()));
		criterias.add(new CriteriaModel("state",getState()));
		
		return criterias;
	}


	@Action(value = "list", results = { @Result(name = "success", location = "null") })
	public String list() throws IOException, ParseException{
		int offset;
		try {
			offset = (rows) * (page - 1);
		} catch (NullPointerException e) {
			offset = 0;
			rows = Constants.DEFAULT_ROWS_NUM;
		}

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);
		Date from = c.getTime();

		if("YES".equals(getGrouped())){
			//Default group by criteria
			EntityGroupedBy gr =null;
			if(getGroupBy() == null){
				setGroupBy("ROOM");
			}
			if("DATE".equals(getGroupBy())){
				if("day".equals(getZoom())){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					gr = eventManagerDao.getEventsInDay(sdf.parse(selectedDate),sourceView);
					if(gr.getGroups().size() > 0){
						gr.setTitle(gr.getGroups().get(0).getTitle());						
					}
				}else if("month".equals(getZoom())){
					gr = eventManagerDao.getEventsInMonth(selectedDate,sourceView);
				}
			}else{
				gr = eventManagerDao.getEventsGroupedBy(getGroupBy(),sourceView);				
			}
			ProjectHelper.sendObjectAsJsonResponse(gr,ServletActionContext.getResponse());
			return null;
		}
		
		if(get_search() != null && get_search().equals("true")){
			list = eventManagerDao.listEvents(offset, rows,usedSearchFields(), from,null);
			resp.setData(list);
		}else{
			list = eventManagerDao.listEvents(offset, rows,from,null,sourceView);
			resp.setData(list);
		}
		records = eventManagerDao.getEventsCount();
		total = (int) Math.ceil((double) records / (double) rows);
		ProjectHelper.sendObjectAsJsonResponse(resp,ServletActionContext.getResponse());
		return null;
	}
	

	public List<Event> getList() {
		return list;
	}

	public void setList(List<Event> list) {
		this.list = list;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public EventVo getEvent()
	{
		return event;
	}

	public void setEvent(EventVo event)
	{
		this.event = event;
	}

	public EventManagerDao getEventDao(){
		return eventManagerDao;
	}

	public void setEventDao(EventManagerDao eventDao){
		this.eventManagerDao = eventDao;
	}

	public String getErrorNotification(){
		return errorNotification;
	}

	public void setErrorNotification(String errorNotification){
		this.errorNotification = errorNotification;
	}

	public Subscription getSubscription(){
		return subscription;
	}

	public void setSubscription(Subscription subscription){
		this.subscription = subscription;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getState(){
		return state;
	}

	public void setState(String state){
		this.state = state;
	}
	
	

	public String getDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.date);

	}

	public void setDate(String date)
	{
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.date = sdf.parse(date);
		}catch (ParseException e){
			e.printStackTrace();
		}
	}

	public Double getPrice()
	{
		return price;
	}

	public void setPrice(Double price)
	{
		this.price = price;
	}

	public DataTableResponseObject getResp()
	{
		return resp;
	}


	public void setResp(DataTableResponseObject resp)
	{
		this.resp = resp;
	}

	public Integer getRoomId()
	{
		return roomId;
	}

	public void setRoomId(Integer roomId)
	{
		this.roomId = roomId;
	}

	public String getRating()
	{
		return rating;
	}

	public void setRating(String rating)
	{
		this.rating = rating;
	}

	public String getSelectedDate()
	{
		return selectedDate;
	}

	public void setSelectedDate(String selectedDate)
	{
		this.selectedDate = selectedDate;
	}

	public String getZoom()
	{
		return zoom;
	}

	public void setZoom(String zoom)
	{
		this.zoom = zoom;
	}

	public String getSourceView()
	{
		return sourceView;
	}

	public void setSourceView(String sourceView)
	{
		this.sourceView = sourceView;
	}
	
	
}
