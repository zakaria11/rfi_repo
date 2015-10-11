package ma.eventmanager.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
		eventManagerDao.addUser(new User("zakaria", "zakaria","+212677665512"),new ArrayList<String>(Arrays.asList("ADMIN","REPORTING","BORDER","EVENT")));
		eventManagerDao.addUser(new User("ev", "ev","+212635665511"),new ArrayList<String>(Arrays.asList("ADMIN")));
		
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
		List<Event> events = new ArrayList<Event>();
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date()); 
		events.add(new Event(cal.getTime(), 200D, "Evenet 1", "Desc 1 : also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishi", "A", 22D, roomA));
		cal.add(Calendar.DATE, 1);
		events.add(new Event(cal.getTime(), 100D, "Event 2","Desc 2 : Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scramb", "A", 100D, roomA));
		cal.add(Calendar.DATE, 3);
		events.add(new Event(cal.getTime(), 20D, "Evenet 3", "Desc 3 : Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scramb", "A", 150D, roomB));
		cal.add(Calendar.DATE, 8);
		events.add(new Event(cal.getTime(), 50D, "Evenet 4", "Desc 4 : Sit sint velit at. Vix in invidunt dissentiet, ex eam malis aliquid. Falli mundi democritum te est, an wisi disputando sed. Pri solum facer iracundia at. Duo no latine aperiri.", "A", 40D, roomB));
		cal.add(Calendar.DATE, 10);
		events.add(new Event(cal.getTime(), 40D, "Evenet 5", "Desc 5 : Choro definitiones eu usu. Amet nostrud abhorreant per in. Ei pro munere essent, est habeo nostrud electram an. Ad aeque vivendum urbanitas pri, vel dolore principes definitionem an.", "A", 40D, roomB));		
		
		for (int i = 0; i < 100; i++){
			events.add(new Event(cal.getTime(), 40D, "Evenet loop"+i, "Descr "+i+" : Choro definitiones eu usu. Amet nostrud abhorreant per in. Ei pro munere essent, est habeo nostrud electram an. Ad aeque vivendum urbanitas pri, vel dolore principes definitionem an.", "A"+i, i+1D, roomB));
		}
		eventManagerDao.addEvents(events);
		
		
		
		//Add Clients
		eventManagerDao.addClient(new Client("Ali","Mohamed","CNE0001","CIN0001","Etudiant","order1","mail@mail.com","Mr"));
		eventManagerDao.addClient(new Client("Hassan","Omar","CNE0002","CIN0002","Chercheur","order1","mail@mail.com","Mr"));
		eventManagerDao.addClient(new Client("Khadija","Hajar","CNE0003","CIN0003","Etudiant","order1","mail@mail.com","Mr"));
		eventManagerDao.addClient(new Client("Adam","Ismail","CNE0004","CIN0004","Etudiant","order1","mail@mail.com","Mr"));
		eventManagerDao.addClient(new Client("Adam","Abdellah","CNE0005","CIN0005","Chercheur","order1","mail@mail.com","Mr"));
		eventManagerDao.addClient(new Client("Adam","Zineb","CNE0006","CIN0006","Etudiant","order1","mail@mail.com","Mr"));
		eventManagerDao.addClient(new Client("Monir","Zakaria","CNE0007","CIN0007","Etudiant","order1","mail@mail.com","Mr"));
		eventManagerDao.addClient(new Client("Houssain","Sadik","CNE0008","CIN0008","Etudiant","order1","mail@mail.com","Mr"));
		eventManagerDao.addClient(new Client("Yahia","Youssef","CNE0009","CIN0009","Etudiant","order1","mail@mail.com","Mr"));
		eventManagerDao.addClient(new Client("Youssef","Sadik","CNE00010","CIN00010","Etudiant","order1","mail@mail.com","Mr"));
		eventManagerDao.addClient(new Client("Ali","Youssef","CNE00011","CIN00011","Etudiant","order1","mail@mail.com","Mr"));
		
		
	}

	public EventManagerDao getEventManagerDao(){
		return eventManagerDao;
	}

	public void setEventManagerDao(EventManagerDao eventManagerDao){
		this.eventManagerDao = eventManagerDao;
	}
	
	
	

}
