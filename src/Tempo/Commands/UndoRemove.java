package Tempo.Commands;

import java.util.*;

import Tempo.CalendarObjects.*;
import Tempo.Logic.Calendar;

public class UndoRemove implements Command {
	private static Calendar cal = Calendar.getInstance();
	
	private static final String CMD_UNDO = "undo <remove %1$s %2$s>";
	private static final String OBJ_EVENT = "event";
	private static final String OBJ_TASK = "task";
	private static final String OBJ_FLOATING = "floating task";
	
	private String objType;
	private String nameOfPrevObj;
		
	private Event prevModEvent;
	private Task prevModTask;
	private FloatingTask prevModFloating;
	
	private ArrayList<CalendarObject> prevModEvents;
	private ArrayList<CalendarObject> prevModTasks;
	
	private boolean isEvent = false;
	private boolean isTask = false;
	private boolean isFloatingTask = false;
	private boolean isEventsSeries = false;
	
	public UndoRemove(Event event) {
		prevModEvent = event;
		isEvent = true;
		objType = OBJ_EVENT;
	}
	
	public UndoRemove(Task task) {
		prevModTask = task;
		isTask = true;
		objType = OBJ_TASK;
	}
	
	public UndoRemove(FloatingTask floatingTask) {
		prevModFloating = floatingTask;
		isFloatingTask = true;
		objType = OBJ_FLOATING;
	}
	
	public UndoRemove(ArrayList<CalendarObject> series, 
					  boolean isEventsSeries) {
		this.isEventsSeries = isEventsSeries;
		initialiseSeries(series);
	}
	
	public Result execute() {
		Result result;
		
		if (isEvent) {
			result = cal.addBackEvent(prevModEvent);
		} else if (isTask) {
			result = cal.addBackTask(prevModTask);
		} else if (isFloatingTask) {
			result = cal.addBackFloating(prevModFloating);
		} else if (isEventsSeries) {
			result = cal.addBackRecurrEvent(prevModEvents);
		} else {
			result = cal.addBackRecurrTask(prevModTasks);
		}
		
		initialiseNameOfPrevObj();
		String command = String.format(CMD_UNDO, objType, 
									   nameOfPrevObj);
		result.setCommand(command);
				
		return result;
	}
	
	private void initialiseSeries(ArrayList<CalendarObject> series) {
		if (isEventsSeries) {
			prevModEvents = series;
			objType = OBJ_EVENT;
		} else {
			prevModTasks = series;
			objType = OBJ_TASK;
		}
	}
	
	private void initialiseNameOfPrevObj() {
		if (isEvent) {
			nameOfPrevObj = prevModEvent.getName();
		} else if (isTask) {
			nameOfPrevObj = prevModTask.getName();
		} else if (isFloatingTask) {
			nameOfPrevObj = prevModFloating.getName();	
		} else if (isEventsSeries) {
			Event event = (Event) prevModEvents.get(0);
			nameOfPrevObj = event.getName();
		} else {
			Task task = (Task) prevModTasks.get(0);
			nameOfPrevObj = task.getName();
		}
	}
}
