package tp1.pvz.logic.record;

import tp1.pvz.control.Level;
import tp1.pvz.exceptions.RecordException;

public interface RecordsManager {
	
	void load() throws RecordException; 
	
	void save() throws RecordException; 
	
	int getRecord(Level level);
	
	void updateRecord(Level level, int score) throws RecordException;

}
