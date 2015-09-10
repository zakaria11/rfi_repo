package ma.eventmanager.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Client;
import ma.eventmanager.entitys.Event;
import ma.eventmanager.entitys.Room;
import ma.eventmanager.entitys.User;

public class ApplicationInitialization{
	
	private EventManagerDao eventManagerDao;

	
	@PostConstruct
	public void initApp() throws Exception {
		eventManagerDao.addUser(new User("zakaria", "zakaria"),new ArrayList<String>(Arrays.asList("ROLE_EVENT_ADMIN","ROLE_B")));
		eventManagerDao.addUser(new User("ev", "ev"),new ArrayList<String>(Arrays.asList("BORNE_ROLE","USER_ROLE","ROLE_A")));
		
		//Add rooms
		Room roomA = new Room("Salle A","desc salle A : also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishi","ACTIVATED");
		Room roomB = new Room("Salle B","desc salle B : Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scramb","ACTIVATED");
		eventManagerDao.addRoom(roomA);
		eventManagerDao.addRoom(roomB);
		eventManagerDao.addRoom(new Room("Salle C","desc salle C : ","ACTIVATED"));
		eventManagerDao.addRoom(new Room("Salle D","desc salle D","ACTIVATED"));
		eventManagerDao.addRoom(new Room("Salle E","desc salle E","ACTIVATED"));
		eventManagerDao.addRoom(new Room("Salle F","desc salle F","ACTIVATED"));
		
		//Add Events
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date()); 
		eventManagerDao.addEvent(new Event(cal.getTime(), 200D, "Evenet 1", "Desc 1", "A", 22, roomA));
		cal.add(Calendar.DATE, 1);
		eventManagerDao.addEvent(new Event(cal.getTime(), 100D, "Event 2","Desc 2", "A", 100, roomA));
		cal.add(Calendar.DATE, 3);
		eventManagerDao.addEvent(new Event(cal.getTime(), 20D, "Evenet 3", "Desc 3", "A", 150, roomB));
		cal.add(Calendar.DATE, 8);
		eventManagerDao.addEvent(new Event(cal.getTime(), 50D, "Evenet 4", "Desc 4", "A", 40, roomB));
		cal.add(Calendar.DATE, 10);
		eventManagerDao.addEvent(new Event(cal.getTime(), 40D, "Evenet 5", "Desc 5", "A", 40, roomB));
		
		
		//Add Clients
		eventManagerDao.addClient(new Client("Ali","Mohamed","CNE0001","CIN0001","Etudiant"));
		eventManagerDao.addClient(new Client("Hassan","Omar","CNE0002","CIN0002","Chercheur"));
		eventManagerDao.addClient(new Client("Khadija","Hajar","CNE0003","CIN0003","Etudiant"));
		eventManagerDao.addClient(new Client("Adam","Ismail","CNE0004","CIN0004","Etudiant"));
		eventManagerDao.addClient(new Client("Adam","Abdellah","CNE0005","CIN0005","Chercheur"));
		eventManagerDao.addClient(new Client("Adam","Zineb","CNE0006","CIN0006","Etudiant"));
		eventManagerDao.addClient(new Client("Monir","Zakaria","CNE0007","CIN0007","Etudiant"));
		eventManagerDao.addClient(new Client("Houssain","Sadik","CNE0008","CIN0008","Etudiant"));
		eventManagerDao.addClient(new Client("Yahia","Youssef","CNE0009","CIN0009","Etudiant"));
		eventManagerDao.addClient(new Client("Youssef","Sadik","CNE00010","CIN00010","Etudiant"));
		eventManagerDao.addClient(new Client("Ali","Youssef","CNE00011","CIN00011","Etudiant"));
		
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
