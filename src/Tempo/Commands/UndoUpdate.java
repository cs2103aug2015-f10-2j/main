package Tempo.Commands;

import java.util.*;
import Tempo.CalendarObjects.*;
import Tempo.Logic.Calendar;
import Tempo.Logic.IndexStore;

public class UndoUpdate implements Command {
	private static Calendar calendar = Calendar.getInstance();
	private static IndexStore idxStore = IndexStore.getInstance();
	
	private static final String CMD_UNDO = "undo <update %1$s %2$s>";
	private static final String OBJ_EVENT = "event";
	private static final String OBJ_TASK = "task";
	private static final String OBJ_FLOATING = "floating task";
	
	private String objType;
	private String nameOfPrevObj;
	
	private int prevModIndex;
	
	private Event prevModEvent;
	private Task prevModTask;
	private FloatingTask prevModFloating;
	
	private ArrayList<CalendarObject> prevModEvents;
	private ArrayList<CalendarObject> prevModTasks;

	private boolean isEvent = false;
	private boolean isTask = false;
	private boolean isFloatingTask = false;
	private boolean isEventsSeries = false;
	
	public UndoUpdate(Event event) {
		prevModEvent = event;
		prevModIndex = event.getIndex();
		isEvent = true;
		objType = OBJ_EVENT;
	}
	
	public UndoUpdate(Task task) {
		prevModTask = task;
		prevModIndex = task.getIndex();
		isTask = true;
		objType = OBJ_TASK;
	}
	
	public UndoUpdate(FloatingTask floatingTask) {
		prevModFloating = floatingTask;
		prevModIndex = floatingTask.getIndex();
		isFloatingTask = true;
		objType = OBJ_FLOATING;
	}
	
	public UndoUpdate(ArrayList<CalendarObject> series, boolean isEventsSeries) {
		this.isEventsSeries = isEventsSeries;
		initialiseSeries(series);
	}
	
	private void initialiseSeries(ArrayList<CalendarObject> series) {
		if (isEventsSeries) {
			prevModEvents = series;
			Event event = (Event) series.get(0);
			prevModIndex = event.getIndex();
			objType = OBJ_EVENT;
		} else {
			prevModTasks = series;
			Task task = (Task) series.get(0);
			prevModIndex = task.getIndex();
			objType = OBJ_TASK;
		}
	}

	public Result execute() {
		initialiseNameOfPrevObj();
		Result result;
		
		if (isEvent) {
			result = calendar.removeEvent(prevModIndex, isEventsSeries);
			removeUndoUndoCommand();
			result = calendar.addBackEvent(prevModEvent);
		} else if (isTask) {
			result = calendar.removeTask(prevModIndex, isEventsSeries);
			removeUndoUndoCommand();
			result = calendar.addBackTask(prevModTask);
		} else if (isFloatingTask) {
			result = calendar.removeFloatingTask(prevModIndex, isEventsSeries);
			removeUndoUndoCommand();
			result = calendar.addBackFloating(prevModFloating);
		} else if (isEventsSeries) {
			result = calendar.removeEvent(prevModIndex, isEventsSeries);
			removeUndoUndoCommand();
			result = calendar.addBackRecurrEvent(prevModEvents);
		} else {
			result = calendar.removeTask(prevModIndex, isEventsSeries);
			removeUndoUndoCommand();
			result = calendar.addBackRecurrTask(prevModTasks);
		}
		
		String command = String.format(CMD_UNDO, objType, nameOfPrevObj);
		result.setCommand(command);
		return result;
	}
	
	private void removeUndoUndoCommand() {
		calendar.removeLastUndo();
	}
	
	private void initialiseNameOfPrevObj() {
		if (isEvent) {
			Event event = (Event) idxStore.getEventById(prevModIndex);
			nameOfPrevObj = event.getName();
		} else {
			FloatingTask task = (FloatingTask) idxStore.getTaskById(prevModIndex);
			nameOfPrevObj = task.getName();
		}
	}

}
