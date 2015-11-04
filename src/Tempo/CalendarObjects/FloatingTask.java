package Tempo.CalendarObjects;

public class FloatingTask implements CalendarObject{
	protected String _name;
	protected boolean _done;
	protected int _index;
	protected int _seriesIndex;
	
	private static final String DELIMETER = "!!";
	
	public FloatingTask(int index, int seriesIndex, String name){
		_index = index;
		_name = name;
		_done = false;
	}
	
	public FloatingTask(int index, int seriesIndex, String name, String done) {
		_index = index;
		_seriesIndex = seriesIndex;
		_name = name;
		_done = Boolean.parseBoolean(done);
	}
	
	public void update(String field, String newValue) {
		if (field.equals("name")) {
			setName(newValue);
		}
	}
	
	public void setName(String newName) {
		_name = newName;
	}
	
	public int getIndex() {
		return _index;
	}
	
	public int getSeriesIndex(){
		return _seriesIndex;
	}

	public String getName() {
		return _name;
	}

	public boolean isDone() {
		return _done;
	}

	public void markAsDone() {
		_done = true;
	}
	
	public void markAsUndone() {
		_done = false;
	}
	
	public boolean isFloatingTask() {
		return true;
	}
	
	public String toString(){
		return getIndex() + DELIMETER + getSeriesIndex() + DELIMETER + getName() + DELIMETER + isDone();
	}
}
