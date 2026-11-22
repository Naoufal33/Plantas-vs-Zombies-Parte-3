package tp1.pvz.logic.record;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tp1.pvz.control.Level;
import tp1.pvz.exceptions.RecordException;
import tp1.pvz.view.Messages;

public class FileRecordsManager implements RecordsManager {

	private int[] records;
	private static final String RECORD_FILENAME = "record.txt";

	public FileRecordsManager() {
		records = new int[Level.values().length];
	}

	@Override
	public void load() throws RecordException {
		String fileName = RECORD_FILENAME;

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty())
					continue;

				String[] parts = line.split(":");
				if (parts.length != 2) {
					throw new RecordException(String.format(Messages.RECORD_FILE_CORRUPTED, fileName, line));

				}
				try {
					Level level = Level.valueOf(parts[0].trim().toUpperCase());
					int score = Integer.parseInt(parts[1].trim());
					if (score < 0) {
						throw new RecordException(String.format(Messages.RECORD_FILE_CORRUPTED, fileName, line));
					}
					this.records[level.ordinal()] = score;
				} catch (IllegalArgumentException e) {
					throw new RecordException(String.format(Messages.RECORD_FILE_CORRUPTED, fileName, line), e);
				}
			}

		} catch (FileNotFoundException e) {
			throw new RecordException(String.format(Messages.RECORD_FILE_NOT_FOUND, fileName), e);
		} catch (IOException e) {
			throw new RecordException(String.format(Messages.RECORD_FILE_NOT_READABLE, fileName), e);
		}

	}

	@Override
	public void save() throws RecordException {
		String fileName = RECORD_FILENAME;

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			for (Level level : Level.values()) {
				String levelName = level.name();
				int score = this.records[level.ordinal()];
				if (score > 0) {
					writer.write(levelName + ":" + score);
					writer.newLine();
				}
			}
		} catch (IOException e) {
			throw new RecordException(String.format(Messages.RECORD_FILE_NOT_WRITABLE, fileName), e);
		}
	}

	@Override
	public int getRecord(Level level) {
		return records[level.ordinal()];
	}

	@Override
	public void updateRecord(Level level, int score) throws RecordException {
		if (score > this.records[level.ordinal()]) {
			this.records[level.ordinal()] = score;
			save(); 
		}

	}

}
