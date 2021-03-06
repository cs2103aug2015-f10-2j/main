package Tempo.UI.GUI;
//@@author A0145073L
/**
 * 
 * ashish
 * Object used to store information about an entry in the user table.
 * Contains information about events,tasks, and floating tasks
 */
import java.util.Date;

public class TableEntry {
	protected String name;
	protected String done;
	protected String startDate;
	protected String startTime;
	protected String endDate;
	protected String endTime;
	protected int index;
	private static final String COMPLETED_TRUE = "Yes";
	private static final String COMPLETED_FALSE = "No";
	
	
	public TableEntry() {
		
	}

	public TableEntry(int index, String name, String done,String startDate, String startTime, String endDate, String endTime) {
		this.index = index;
		this.name = name;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		System.out.println("d; " + done);
		if(done.equalsIgnoreCase("true")) {
			this.done = "complete.";
		}
		else if(done.equalsIgnoreCase("false")) {
			System.out.println("FALSE");
			this.done = "undone";
		}
		else {
			this.done = " ";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void setDone(boolean done) {
		if(done) {
			this.done = COMPLETED_TRUE;
		}
		else {
			this.done = COMPLETED_FALSE;
		}
	}
  public String getDone() {
	  return this.done;
  }
}
