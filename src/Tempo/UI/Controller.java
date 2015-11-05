package Tempo.UI;

import java.util.ArrayList;
import java.util.HashMap;

import Tempo.CalendarObjects.CalendarObject;
import Tempo.CalendarObjects.Event;
import Tempo.CalendarObjects.FloatingTask;
import Tempo.CalendarObjects.Task;
import Tempo.Commands.Result;
import Tempo.Logic.Calendar;
import Tempo.Logic.Display;
import Tempo.Logic.RequestHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Controller {
	@FXML
	HBox display;
	@FXML
	TabPane layout;
	@FXML
	ComboBox inputBox;
	@FXML
	TableView table;
	@FXML 
	TableView todayTable;
	@FXML
	TableColumn id;
	@FXML
	TableColumn name;
	@FXML
	TableColumn startTime;
	@FXML
	TableColumn startDate;
	@FXML
	TableColumn endTime;
	@FXML
	TableColumn endDate;
	@FXML
	TableColumn done;


	@FXML
	TableColumn idToday;
	@FXML
	TableColumn nameToday;
	@FXML
	TableColumn startTimeToday;
	@FXML
	TableColumn startDateToday;
	@FXML
	TableColumn endTimeToday;
	@FXML
	TableColumn endDateToday;
	@FXML
	TableColumn doneToday;

	
	@FXML
	TableView upcomingTable;
	@FXML
	ComboBox inputBoxToday;

	@FXML
	TableColumn upcomingId;
	@FXML
	TableColumn upcomingName;
	@FXML
	TableColumn upcomingDone;
	@FXML
	TableColumn upcomingStartDate;
	@FXML
	TableColumn upcomingStartTime;
	@FXML
	TableColumn upcomingEndDate;
	@FXML
	TableColumn upcomingEndTime;
	
	@FXML
	ComboBox inputBoxUpComing;
	
	@FXML
	ComboBox missedInputBox;
	
	@FXML
	TableView missedTable;
	
	@FXML
	TableColumn missedId;
	
	@FXML
	TableColumn missedName;
	
	@FXML
	TableColumn missedDone;
	
	@FXML
	TableColumn missedStartDate;
	
	@FXML
	TableColumn missedStartTime;
	
	@FXML
	TableColumn missedEndDate;
	
	@FXML
	TableColumn missedEndTime;
	
	
	
//
	
	@FXML
	ComboBox undoneInputBox;
	
	@FXML
	TableView undoneTable;
	
	@FXML
	TableColumn undoneId;
	
	@FXML
	TableColumn undoneName;
	
	@FXML
	TableColumn undoneDone;
	
	@FXML
	TableColumn undoneStartDate;
	
	@FXML
	TableColumn undoneStartTime;
	
	@FXML
	TableColumn undoneEndDate;
	
	@FXML
	TableColumn undoneEndTime;

	
	@FXML
	TabPane tabView;
	RequestHandler tempRH;
	Calendar calendar;
	String view  = "all";
	ArrayList<ComboBox> inputBoxes;
	ArrayList<TableView> views;
	
	
	public ComboBox getCurrentSearchBox() {
		int currTab = getCurrentTab();
		ComboBox currentBox = inputBoxes.get(currTab);
		return currentBox;	
	}


	public int getCurrentTab() {
		SingleSelectionModel<Tab> selected = tabView.getSelectionModel();	
		return selected.getSelectedIndex();
	}
	
	
	
	
	private void refresh(String view) {

		SingleSelectionModel<Tab> selected = tabView.getSelectionModel();
		int currIndex = selected.getSelectedIndex();
		Page currPage = null;
		System.out.println("PAGE SEL " + currIndex);
		if(currIndex == 0) {
			currPage = new Page();
			populateAllPage(currPage);
		}

		else if (currIndex == 1) {

			currPage = new Page();
			populateTodayPage(currPage);	
		}
		
		else if (currIndex == 2) {

			currPage = new Page();
			populateUpcommingPage(currPage);	
		}

		else if(currIndex == 3) {
			currPage = new Page();
			populateMissedPage(currPage);
		}
		
		else if(currIndex == 4) {
			currPage = new Page();
			populateUndonePage(currPage);
				
		}
		
		tempRH = RequestHandler.getInstance();
		calendar = tempRH.getCalendar();



		System.out.println("tableEvent size " + currPage.tableEvents.size());

		ObservableList<TableEntry> events;
		if(view.equals("task")) {
			events = FXCollections.observableArrayList(currPage.tableTasks);
			System.out.println("SIZE OF " + currPage.tableTasks.size());
			System.out.println("DISPLAY TASKS");
		}
		else if(view.equals("event")) {
			System.out.println("DISPLAY EVENTS");
			events = FXCollections.observableArrayList(currPage.tableEvents);
			System.out.println("SIZE OF " + currPage.tableEvents.size());


		}
		else if (view.equals("float")) {
			System.out.println("DISPLAY FLOAT");
			System.out.println("SIZE OF " + currPage.tableFloatingTasks.size());
			events = FXCollections.observableArrayList(currPage.tableFloatingTasks);
		}
		else if(view.equals("all")){
			System.out.println("displaying all");
			System.out.println("currPage.entries size : \t" + currPage.entries.size());
			events = FXCollections.observableArrayList(currPage.entries);
			events = FXCollections.observableArrayList(currPage.entries);
		}
		else {
			System.out.println("currPage.entries size : \t" + currPage.entries.size());
			events = FXCollections.observableArrayList(currPage.entries);
		}


		System.out.println("TABLE ENTRY EVENTS SIZE " + events.size());

		setAttributes();

		TableView currentTable = views.get(currIndex);
		currentTable.setItems(events);		
		currentTable.refresh();
		inputBox.setValue("add event do something from 10/10/2015 at 10:00 to 10/11/2015 at 11:00");
	}
	
	
	
	
	
	private void populateUpcommingPage(Page currPage) {
		// TODO Auto-generated method stub
		populateUpCommingEvents(currPage);
		populateUpCommingTasks(currPage);
		coalesce(currPage.entries,currPage.tableEvents,currPage.tableTasks);
		System.out.println("UPCOMING CURR PAGE ENTRIES: " + currPage.entries.size());
		
	}
	
	/**System.out.println("SIZE TODAYS TASK" + Display.getInstance().getTasksToday());
		Result todaysCalendarObjects = Display.getInstance().getTasksToday();
		ArrayList<CalendarObject> todaysTasksCalendarObjects = todaysCalendarObjects.getResults().get("Tasks");
		ArrayList<Task> todaysTasks = toTasks(todaysTasksCalendarObjects);
		fillTasks(currPage,todaysTasks);
	 * 
	 * 
	 * @param currPage
	 */

	
	
	
	
	private void populateMissedPage(Page currPage) {
		populateMissedTasks(currPage);
		combine(currPage.entries,currPage.tableEvents);
	}

	private void populateUndoneTasks(Page currPage) {
		// TODO Auto-generated method stub
		System.out.println("SIZE TODAYS TASK" + Display.getInstance().getMissedTasks());
		Result CalendarObjects = Display.getInstance().getUndoneFloatingTasks();
		ArrayList<CalendarObject> todaysTasksCalendarObjects = CalendarObjects.getResults().get("Tasks");
		ArrayList<FloatingTask> todaysTasks = toFloatingTasks(todaysTasksCalendarObjects);
		fillFloatingTasks(currPage,todaysTasks);
		
	}
	
	private void populateUndonePage(Page currPage) {
		// TODO Auto-generated method stub
		populateUndoneTasks(currPage);
		combine(currPage.entries,currPage.tableFloatingTasks);
		
	}
	
	private void populateMissedTasks(Page currPage) {
		// TODO Auto-generated method stub
		System.out.println("SIZE TODAYS TASK" + Display.getInstance());
		Result CalendarObjects = Display.getInstance().getMissedTasks();
		ArrayList<CalendarObject> todaysTasksCalendarObjects = CalendarObjects.getResults().get("Tasks");
		ArrayList<Task> todaysTasks = toTasks(todaysTasksCalendarObjects);
		fillTasks(currPage,todaysTasks);
		
	}
	
	
	
	
	
	private void populateUpCommingTasks(Page currPage) {
		// TODO Auto-generated method stub
		System.out.println("SIZE TODAYS TASK" + Display.getInstance().getTasksToday());
		Result CalendarObjects = Display.getInstance().getUpcomingTasks();
		ArrayList<CalendarObject> todaysTasksCalendarObjects = CalendarObjects.getResults().get("Tasks");
		ArrayList<Task> todaysTasks = toTasks(todaysTasksCalendarObjects);
		fillTasks(currPage,todaysTasks);
		
	}

	private void populateUpCommingEvents(Page currPage) {
		// TODO Auto-generated method stub
		System.out.println("SIZE TODAYS TASK" + Display.getInstance().getTasksToday());
		Result todaysCalendarObjects = Display.getInstance().getEventsToday();
		ArrayList<CalendarObject> todaysTasksCalendarObjects = todaysCalendarObjects.getResults().get("Events");
		ArrayList<Event> upComingTasks = toEvents(todaysTasksCalendarObjects);
		fillEvents(currPage,upComingTasks);
		//fillEvent(currPage,upComingTasks);
	}

	
	private void populateTodayPage(Page currPage) {
		// TODO Auto-generated method stub
		populateTodaysEvents(currPage);
		populateTodaysTasks(currPage);
		coalesce(currPage.entries,currPage.tableEvents,currPage.tableTasks);



	}
	
	
	private void populateTodaysTasks(Page currPage) {
		System.out.println("SIZE TODAYS TASK" + Display.getInstance().getTasksToday());
		Result todaysCalendarObjects = Display.getInstance().getTasksToday();
		ArrayList<CalendarObject> todaysTasksCalendarObjects = todaysCalendarObjects.getResults().get("Tasks");
		ArrayList<Task> todaysTasks = toTasks(todaysTasksCalendarObjects);
		fillTasks(currPage,todaysTasks);
	}

	private void populateTodaysEvents(Page currPage) {
		System.out.println("SIZE TODAYS TASK" + Display.getInstance().getEventsToday().getResults().get("Events").size());
		Result todaysEventsCalendarObjects = Display.getInstance().getEventsToday();
		ArrayList<Event> todaysEvents = toEvents(todaysEventsCalendarObjects.getResults().get("Events"));
		fillEvents(currPage,todaysEvents);


	}

	
	
	
	@FXML
	public void handleEnterPressed(KeyEvent evt){
		ComboBox currentBox = getCurrentSearchBox();
		String userInput =  currentBox.getValue().toString();
		if(evt.getCode().equals(KeyCode.ENTER)) {
			if(userInput.equals("all")) {
				SingleSelectionModel<Tab> selected = tabView.getSelectionModel();
				selected.select(0);

			}
			
			
			else if(userInput.equals("td")) {
				SingleSelectionModel<Tab> selected = tabView.getSelectionModel();
				selected.select(1);

			} 
			else if(userInput.equals("up")) {
				SingleSelectionModel<Tab> selected = tabView.getSelectionModel();
				selected.select(2);

			}
			
			else if (userInput.equals("m")) {
				SingleSelectionModel<Tab> selected = tabView.getSelectionModel();
				selected.select(3);
			}
			else if (userInput.equals("d")) {
				SingleSelectionModel<Tab> selected = tabView.getSelectionModel();
				selected.select(4);
			}
			
			else if(userInput.equals("ud")) {
				SingleSelectionModel<Tab> selected = tabView.getSelectionModel();
				selected.select(4);
			}
			else if (userInput.equals("evt")) {
				view = "event";
				refresh(view);
				return;
			}
			else if(userInput.equals("flt")) {
				view = "float";
				refresh(view);
				return;
			}
			
			else if(userInput.equals("tsk")) {
				view = "task";
				refresh(view);
				return;
			}
			
			System.out.println("size before : " + tempRH.getCalendar().getEventsList().size());

			System.out.println("USER ENTERED : " + inputBox.getValue().toString());
			tempRH.processCommand(userInput);

			refresh(view);

			table.refresh();
			System.out.println("size after : " + tempRH.getCalendar().getEventsList().size());
			return;
		}

		else if(evt.getCode() == KeyCode.KP_LEFT) {
			System.out.println("left");

		}

		System.out.println("something else");

	
	
	}	

	
	
	@FXML 
	public void handleType(KeyEvent evt) {
		System.out.println("SOMETHING PRESSED");
	}

	

	private void setAttributes() {

		id.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("index"));
		name.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("name"));
		startTime.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("startTime"));
		startDate.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("startDate"));
		endTime.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("endTime"));
		endDate.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("endDate"));
		done.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("done"));


		idToday.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("index"));
		nameToday.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("name"));
		startTimeToday.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("startTime"));
		startDateToday.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("startDate"));
		endTimeToday.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("endTime"));
		endDateToday.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("endDate"));
		doneToday.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("done"));
		
		
		upcomingId.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("index"));
		upcomingName.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("name"));
		upcomingStartTime.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("startTime"));
		upcomingStartDate.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("startDate"));
		upcomingEndTime.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("endTime"));
		upcomingEndDate.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("endDate"));
		upcomingDone.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("done"));


		

		missedId.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("index"));
		missedName.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("name"));
		missedStartTime.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("startTime"));
		missedStartDate.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("startDate"));
		missedEndTime.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("endTime"));
		missedEndDate.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("endDate"));
		missedDone.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("done"));

	
		undoneId.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("index"));
		undoneName.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("name"));
		undoneStartTime.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("startTime"));
		undoneStartDate.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("startDate"));
		undoneEndTime.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("endTime"));
		undoneEndTime.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("endDate"));
		undoneDone.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("done"));

		
	}	
	

	
	@FXML
	public void initialize() {
		inputBoxes = new ArrayList<ComboBox>();
		inputBoxes.add(inputBox);
		inputBoxes.add(inputBoxToday);
		inputBoxes.add(inputBoxUpComing);
		inputBoxes.add(missedInputBox);
		inputBoxes.add(undoneInputBox);
		views = new ArrayList<TableView>();
		views.add(table);
		views.add(todayTable);
		views.add(upcomingTable);
		views.add(missedTable);
		views.add(undoneTable);
	

		Page allPage = new Page();

		tempRH = RequestHandler.getInstance();
		calendar = tempRH.getCalendar();


		populateAllPage(allPage);
		System.out.println("tableEvent size " + allPage.tableEvents.size());
		ObservableList<TableEntry> events = FXCollections.observableArrayList(allPage.entries);
		System.out.println("TABLE ENTRY EVENTS SIZE " + events.size());

		setAttributes();

		table.setItems(events);
		inputBox.setValue("add event do something from 10/10/2015 at 10:00 to 10/11/2015 at 11:00");
		table.refresh();
	}  
	
	
	
	private void populateAllPage(Page allPage) {
		populateEvents(allPage);
		populateTasks(allPage);
		populateFloatingTasks(allPage);
		coalesce(allPage.entries,allPage.tableEvents,allPage.tableTasks);
		combine(allPage.entries,allPage.tableFloatingTasks);
//		combine(allPage.entries,allPage.tableEvents);

	}
	private ArrayList<Event> toEvents(ArrayList<CalendarObject> list) {
		ArrayList<Event> userEvents = new ArrayList<Event>();
		for(int i = 0; i < list.size(); i++) {
			CalendarObject currentCalendarObject = list.get(i);
			Event currentEvent = (Event) currentCalendarObject;
			userEvents.add(currentEvent);
		}
		return userEvents;
	}
	
	private ArrayList<Task> toTasks(ArrayList<CalendarObject> list) {
		ArrayList<Task> userEvents = new ArrayList<Task>();
		for(int i = 0; i < list.size(); i++) {
			CalendarObject currentCalendarObject = list.get(i);
			Task currentEvent = (Task) currentCalendarObject;
			userEvents.add(currentEvent);
		}
		return userEvents;
	}
	
	private ArrayList<FloatingTask> toFloatingTasks(ArrayList<CalendarObject> list) {
		ArrayList<FloatingTask> userEvents = new ArrayList<FloatingTask>();
		for(int i = 0; i < list.size(); i++) {
			CalendarObject currentCalendarObject = list.get(i);
			FloatingTask currentEvent = (FloatingTask) currentCalendarObject;
			userEvents.add(currentEvent);
		}
		return userEvents;
	}
	
	
	private void populateEvents(Page page) {
		//ArrayList<Event> userEvents = calendar.getEventsList();
		ArrayList<CalendarObject> userCalendarObjects = calendar.getEventsList();
		System.out.println("userCalenderObjects Size: " + calendar.getEventsList().size());
		ArrayList<Event> userEvents = toEvents(userCalendarObjects);
		for(int i = 0; i < userEvents.size(); i++) {
			TableEntry entry = newTableEventEntry(userEvents.get(i));
			page.tableEvents.add(entry);
		}

	}

	
	private void populateTasks(Page page) {
		ArrayList<CalendarObject> userCalendarObjects = calendar.getTasksList();
		ArrayList<Task> userTasks = toTasks(userCalendarObjects);
		//ArrayList<Task> userTasks = calendar.getTasksList();
		System.out.println("TASK LIST SIZE : "  + userTasks.size());
		for(int i = 0; i < userTasks.size(); i++) {
			System.out.println("ADDING "  + userTasks.get(i) + "to tasks" );
			TableEntry entry = newTableTaskEntry(userTasks.get(i));
			page.tableTasks.add(entry);
		}
	}

	private TableEntry newTableFloatingTaskEntry(FloatingTask t) {
		TableEntry entry = new TableEntry(t.getIndex(),t.getName(),Boolean.toString(t.isDone())," "," "," "," ");
		System.out.println(entry.getDone());
		return entry;
	}

	

	private TableEntry newTableTaskEntry(Task t) {
		TableEntry entry = new TableEntry(t.getIndex(),t.getName()," "," "," ",t.getDueDate()," ");
		return entry;
	}

	
	
	
	private void populateFloatingTasks(Page page) {

	//	ArrayList<FloatingTask> userFloatingTasks = calendar.getFloatingTasksList();
	
		ArrayList<CalendarObject> userCalendarObjects = calendar.getFloatingTasksList();
		System.out.println("userCalenderObjects Size: " + calendar.getFloatingTasksList().size());
		ArrayList<FloatingTask> userFloatingTasks = toFloatingTasks(userCalendarObjects);
		for(int i = 0; i < userFloatingTasks.size(); i++) {
			System.out.println("ADDING "  + userFloatingTasks.get(i) + "to tasks" );
			TableEntry entry = newTableFloatingTaskEntry(userFloatingTasks.get(i));
			page.tableFloatingTasks.add(entry);
		}
	} 

	
	


	private void coalesce(ArrayList<TableEntry> result, ArrayList<TableEntry> copyOne, ArrayList<TableEntry> copyTwo) {
		System.out.println("before c : " + result.size());
		combine(result,copyOne);
		combine(result,copyTwo);
		System.out.println("after c " + result.size());
	}

	private void combine(ArrayList<TableEntry>mergedList,ArrayList<TableEntry>listToCopy) {
		for(int i = 0; i < listToCopy.size(); i++) {
			mergedList.add(listToCopy.get(i));
		}

	}

	public TableEntry newTableEventEntry(Event e) {
		TableEntry entry = new TableEntry(e.getIndex(),e.getName()," ",e.getStartDate(),e.getStartTime(),e.getEndDate(),e.getEndTime());
		return entry;
	}
		
	
	private Page fillEvents(Page page,ArrayList<Event> schedule) {

		for(int i = 0; i < schedule.size(); i++) {
			TableEntry entry = newTableEventEntry(schedule.get(i));
			page.tableEvents.add(entry);
		}
		return page;

	}


	private Page fillFloatingTasks(Page page,ArrayList<FloatingTask> schedule) {

		for(int i = 0; i < schedule.size(); i++) {
			TableEntry entry = newTableFloatingTaskEntry(schedule.get(i));
			page.tableFloatingTasks.add(entry);
		}
		return page;

	}

	
	
	private Page fillTasks(Page page,ArrayList<Task> schedule) {

		for(int i = 0; i < schedule.size(); i++) {
			TableEntry entry = newTableTaskEntry(schedule.get(i));
			page.tableEvents.add(entry);
		}
		return page;

	}
		
	/**



	private void populateTodayPage(Page currPage) {
		// TODO Auto-generated method stub
		populateTodaysEvents(currPage);
		populateTodaysTasks(currPage);
		coalesce(currPage.entries,currPage.tableEvents,currPage.tableTasks);



	}
	private void populateTodaysTasks(Page currPage) {
		System.out.println("SIZE TODAYS TASK" + DisplayTempo.getInstance().getTasksToday().size());
		ArrayList<Task> todaysEvents = DisplayTempo.getInstance().getTasksToday();
		fillTasks(currPage,todaysEvents);
	}

	private void populateTodaysEvents(Page currPage) {
		HashMap<String,ArrayList<CalendarObject>> result = Display.getInstance().getEventsToday().getResults();
		ArrayList<CalendarObject> todaysEvents = result.get("events");
		for(int i = 0; i <todaysEvents.size(); i++) {
		Event a = 	(Event) todaysEvents.get(i);
		}
		fillEvents(currPage,todaysEvents);


	}

	private Page fillEvents(Page page,ArrayList<Event> schedule) {

		for(int i = 0; i < schedule.size(); i++) {
			TableEntry entry = newTableEventEntry(schedule.get(i));
			page.tableEvents.add(entry);
		}
		return page;

	}

	private Page fillTasks(Page page,ArrayList<Task> schedule) {

		for(int i = 0; i < schedule.size(); i++) {
			TableEntry entry = newTableTaskEntry(schedule.get(i));
			page.tableEvents.add(entry);
		}
		return page;

	}




	private void populateEvents(Page page) {
		ArrayList<Event> userEvents = calendar.getEventsList();
		for(int i = 0; i < userEvents.size(); i++) {
			TableEntry entry = newTableEventEntry(userEvents.get(i));
			page.tableEvents.add(entry);
		}

	}

	private void populateTasks(Page page) {

		ArrayList<Task> userTasks = calendar.getTasksList();
		System.out.println("TASK LIST SIZE : "  + userTasks.size());
		for(int i = 0; i < userTasks.size(); i++) {
			System.out.println("ADDING "  + userTasks.get(i) + "to tasks" );
			TableEntry entry = newTableTaskEntry(userTasks.get(i));
			page.tableTasks.add(entry);
		}
	}

	private void populateFloatingTasks(Page page) {

		ArrayList<FloatingTask> userFloatingTasks = calendar.getFloatingTasksList();
		System.out.println("TASK LIST SIZE : "  + userFloatingTasks.size());
		for(int i = 0; i < userFloatingTasks.size(); i++) {
			System.out.println("ADDING "  + userFloatingTasks.get(i) + "to tasks" );
			TableEntry entry = newTableFloatingTaskEntry(userFloatingTasks.get(i));
			page.tableFloatingTasks.add(entry);
		}
	}


	

	@FXML 
	public void handleType(KeyEvent evt) {
		System.out.println("SOMETHING PRESSED");
	}


	@FXML
	public void handleEnterPressed(KeyEvent evt){
		ComboBox currentBox = getCurrentSearchBox();
		String userInput =  currentBox.getValue().toString();
		if(evt.getCode().equals(KeyCode.ENTER)) {
			if(userInput.equals("td")) {
				SingleSelectionModel<Tab> selected = tabView.getSelectionModel();
				selected.select(1);

			} 
			else if (userInput.equals("tsk")) {
				view = "task";
				refresh(view);
				System.exit(0);
				return;
			}
			else if (userInput.equals("evt")) {
				view = "event";
				refresh(view);
				return;
			}
			else if(userInput.equals("flt")) {
				view = "float";
				refresh(view);
				return;
			}

			System.out.println("size before : " + tempRH.getCalendar().getEventsList().size());

			System.out.println("USER ENTERED : " + inputBox.getValue().toString());
			tempRH.processCommand(userInput);

			refresh(view);

			table.refresh();
			System.out.println("size after : " + tempRH.getCalendar().getEventsList().size());
			return;
		}

		else if(evt.getCode() == KeyCode.KP_LEFT) {
			System.out.println("left");

		}

		System.out.println("something else");

		/**layout.setMinWidth(800);
		layout.setPrefWidth(800);
		layout.setMaxWidth(800);
		System.out.println("SETTING");
		display.setSpacing(30.0);
		   System.out.println("test") ;

		 //  display.setVgap(4);
		 //  display.setHgap(4);
		   create(0,0,"a");
		  // create(0,1);
		   //create(0,2);

		   //create(1,0);
		  // create(1,1);
		   //create(1,2);

		   //create(2,0);

		 










		// create(2,0);
		// create(2,1);
		// create(2,2);
		//create(3,0);





	
	}	

	public void displayEventsToday() {

	}

	private TableEntry newTableFloatingTaskEntry(FloatingTask t) {
		TableEntry entry = new TableEntry(t._index,t._name,Boolean.toString(t._done)," "," "," "," ");
		System.out.println(entry.getDone());
		return entry;
	}

	public void hello() {
		System.out.println("hello");
	}


	public void create(int x, int y,String name) {



	}

	*/
}