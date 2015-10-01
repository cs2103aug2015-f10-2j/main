package Tempo;

import java.util.ArrayList;


public class Display {
	private static ArrayList<Event> events;
	private static ArrayList<Task> tasks;
	private static ArrayList<FloatingTask> floatingTasks;

	public Display(String fileName) {
		CalendarImporter calImport = new CalendarImporter(fileName); 
		events = new ArrayList<Event>();
		tasks = new ArrayList<Task>();
		floatingTasks = new ArrayList<FloatingTask>();
		assignObjFromCal(calImport);
	}

	private void assignObjFromCal(CalendarImporter calImport) {
		events = calImport.getEventsList();
		tasks = calImport.getTasksList();
		floatingTasks = calImport.getFloatingTasksList();
	}

	public static boolean manual() {
		// INTRO
		System.out.println("Hello! This is the cheatsheet for tempo!");
		System.out.println("");
		System.out.println("Manage TEMPO with the following KEYWORDS and PARAMETERS");
		System.out.println("The following cheatsheet shows you the function and the keywords you can use");
		System.out.println("<KEY>: keywords you can use for each function");
		System.out.println("<id>: id is the index of the event as supplied by �List all events�");
		System.out.println("<start date>/<end date>: day month year");
		System.out.println("<start time>/<end time>: 24 hours format");
		System.out.println("");

		// how to add
		System.out.println("HOW TO ADD EVENT");
		System.out.println("================");
		System.out.println("<KEY>: add/create/new");
		System.out.println("<KEY> event <name> from <start date> at <start time> to <end date> at <end time>");
		System.out.println("");

		// how to edit
		System.out.println("HOW TO EDIT EVENT");
		System.out.println("=================");
		System.out.println("<KEY>: edit/update/change");
		System.out.println("<KEY> event <id> <name> from <start date> at <start time> to <end date> at <end time>");
		System.out.println("For editing the event name only: <KEY> event <id> name: <name>");
		System.out.println("For editing the start date only: <KEY> event <id> start date: <start date>");
		System.out.println("For editing the start time only: <KEY> event <id> start time: <start time>");
		System.out.println("For editing of end date only: <KEY> event <id> end date: <end date>");
		System.out.println("For editing of end time only: <KEY> event <id> end time: <end time>");
		System.out.println("");

		// how to add recurring events
		System.out.println("HOW TO ADD RECURRING EVENT");
		System.out.println("==========================");
		System.out.println("<KEY>: repeat");
		System.out.println("For daily recurring events: <KEY> event <id> daily until <end date>");
		System.out.println("For weekly recurring events: <KEY> event <id> weekly until <end date>");
		System.out.println("For monthly recurring events: <KEY> event <id> monthly until <end date>");
		System.out.println("For annually recurring events: <KEY> event <id> yearly until <end date>");
		System.out.println("");

		// how to delete existing events
		System.out.println("HOW TO DELETE EXISTING EVENT");
		System.out.println("============================");
		System.out.println("<KEY>: delete/cancel/remove");
		System.out.println("<KEY> event <id>");
		System.out.println("");

		// how to search events
		System.out.println("HOW TO SEARCH EXISTING EVENT");
		System.out.println("============================");
		System.out.println("<KEY>: search/find");
		System.out.println("By name: <KEY> event <keywords>");
		System.out.println("By id: <KEY> event <id>");
		System.out.println("");

		// how to list upcoming events
		System.out.println("HOW TO LIST UPCOMING EVENT/S");
		System.out.println("============================");
		System.out.println("<KEY>: list/all/view/display");
		System.out.println("<KEY> upcoming events");
		System.out.println("");

		// how to list all events
		System.out.println("HOW TO LIST ALL EVENT/S");
		System.out.println("=======================");
		System.out.println("<KEY>: list/all/view/display");
		System.out.println("<KEY> events");
		System.out.println("");

		// how to add tasks
		System.out.println("HOW TO ADD TASK");
		System.out.println("===============");
		System.out.println("<KEY>: add/create/new");
		System.out.println("For tasks with no deadlines: <KEY> task <name>");
		System.out.println("For tasks with deadlines: <KEY> task <name> due <date>");
		System.out.println("");

		// how to edit existing tasks
		System.out.println("HOW TO EDIT EXISTING TASK");
		System.out.println("=========================");
		System.out.println("<KEY>: edit/update/change");
		System.out.println("<KEY> task <id> <name> due <date>");
		System.out.println("For changing of name only: <KEY> task <id> name: <name>");
		System.out.println("For changing of due date only: <KEY> task <id> due: <date>");
		System.out.println("");

		// how to delete existing tasks
		System.out.println("HOW TO DELETE EXISTING TASK");
		System.out.println("===========================");
		System.out.println("<KEY>: delete/cancel/remove");
		System.out.println("<KEY> task <id>");
		System.out.println("");

		// how to search tasks
		System.out.println("HOW TO SEARCH EXISTING TASK");
		System.out.println("===========================");
		System.out.println("<KEY>: search/find");
		System.out.println("<KEY> task <keywords>");
		System.out.println("");

		// how to list undone tasks
		System.out.println("HOW TO LIST UNDONE TASK/S");
		System.out.println("=========================");
		System.out.println("<KEY>: list/all/view/display");
		System.out.println("<KEY> undone tasks");
		System.out.println("");

		// how to list missed tasks
		System.out.println("HOW TO LIST MISSED TASK/S");
		System.out.println("=========================");
		System.out.println("<KEY>: list/all/view/display");
		System.out.println("<KEY> missed tasks");
		System.out.println("");

		// how to list all tasks
		System.out.println("HOW TO LIST ALL TASK/S");
		System.out.println("======================");
		System.out.println("<KEY>: list/all/view/display");
		System.out.println("<KEY> tasks");
		System.out.println("");

		// how to mark task as done
		System.out.println("HOW TO MARK TASK AS DONE");
		System.out.println("========================");
		System.out.println("<KEY>: mark/flag");
		System.out.println("<KEY> task done <id>");
		System.out.println("");

		// how to undo previous operation
		System.out.println("HOW TO UNDO PREVIOUS OPERATION");
		System.out.println("==============================");
		System.out.println("<KEY>: undo");
		System.out.println("<KEY>");
		System.out.println("");

		// how to view today�s events and tasks due
		System.out.println("HOW TO VIEW EVENTS AND TASKS DUE TODAY ");
		System.out.println("=======================================");
		System.out.println("<KEY>: view/display");
		System.out.println("<KEY> today");
		System.out.println("");

		// how to view this week�s events and tasks due
		System.out.println("HOW TO VIEW EVENTS AND TASKS DUE THIS WEEK ");
		System.out.println("===========================================");
		System.out.println("<KEY>: view/display");
		System.out.println("<KEY> week");
		System.out.println("");
		return false;

	}

	public boolean events() {
		System.out.println("These are your events for today!");
		String currentDate =  getDate();
		for(int i=0; i<events.size();i++){
			System.out.print(i+") ");
			if(currentDate.equalsIgnoreCase(events.get(i).getStartDate())){
				System.out.print(events.get(i).toString());
			}		
		}
		System.out.println("These are the rest of your events!");
		printRemainingEvents();
		return false;
	}

	private String getDate() {
		CurrentDateAndTime date = new CurrentDateAndTime();
		return date.getDay()+"/"+ date.getMonth()+"/"+date.getYear();
	}

	private void printRemainingEvents() {
		// TODO Auto-generated method stub
		
	}

	private void printTodayEvents() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean tasks() {
		System.out.println("These are your tasks for today!");
		printTodayTasks();
		System.out.println("These are your tasks with deadlines");
		printTasksWDeadlines();
		System.out.println("These are the rest of your tasks!");
		printRemainingTasks();
		return false;
	}

	private void printTasksWDeadlines() {
		// TODO Auto-generated method stub
		
	}

	private void printRemainingTasks() {
		// TODO Auto-generated method stub
		
	}

	private void printTodayTasks() {
		// TODO Auto-generated method stub
		
	}

	public void all() {
		System.out.println("These are your events for today!");
		printTodayEvents();
		System.out.println("These are the rest of your events!");
		printRemainingEvents();
		
		System.out.println("These are your tasks for today!");
		printTodayTasks();
		System.out.println("These are your tasks with deadlines");
		printTasksWDeadlines();
		System.out.println("These are the rest of your tasks!");
		printRemainingTasks();
	}
	
	private void printArray(ArrayList<String> array){
		for(int i=0; i<array.size(); i++){
			System.out.println(array.get(i));
		}
	}

	public boolean upcomingEvents() {
		return false;
		// TODO Auto-generated method stub
		
	}

	public boolean undoneTasks() {
		return false;
		// TODO Auto-generated method stub
		
	}

	public boolean missedTasks() {
		return false;
		// TODO Auto-generated method stub
		
	}

	public void today() {
		// TODO Auto-generated method stub
		
	}

	public void week() {
		// TODO Auto-generated method stub
		
	}
}
