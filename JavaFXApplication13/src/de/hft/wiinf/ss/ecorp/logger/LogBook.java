package de.hft.wiinf.ss.ecorp.logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import static java.util.logging.Level.ALL;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class LogBook {
	
    /**
     * formats a filehandler belonging to a given logger
     * @param root
     */
    public LogBook(Logger root) {
		
		FileHandler txt = null;

		try {
			txt = new FileHandler("LookBooks\\LogBook.txt");
		} catch (IOException | SecurityException ex) {
			Logger.getLogger(LogBook.class.getName()).log(Level.SEVERE, null, ex);
		}
		root.setLevel(Level.INFO);
		txt.setFormatter(new Formatter() {
			@Override
			public String format(LogRecord record) {
				String ret = " ";

				if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
				}
				ret += record.getLevel();
				SimpleDateFormat df = new SimpleDateFormat(" dd MMM yyyy HH:mm:ss.SSS     ::");
				Date date = new Date(record.getMillis());
				ret += df.format(date);
				ret += this.formatMessage(record);
				ret += "::\n";

				return ret;

			}
		});
		root.addHandler(txt);

	}

}
