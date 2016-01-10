import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alpoloz on 1/7/16.
 * You're given a large file that contains sorted log messages.
 * Each log message is a timestamp followed by a log message. Given a date, find entry in log.
 */
public class FileBinarySearch {

    private static final int LOG_ENTRY_SIZE = 33;
    private static final String FILENAME = "sorted.log";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String... args) throws Exception {
        Date searchDate = DATE_FORMAT.parse("2016-01-10 19:38:37");
        System.out.println(findLogEntry(searchDate));
    }

    private static String findLogEntry(Date date) throws IOException, ParseException {
        try(RandomAccessFile file = new RandomAccessFile(FILENAME, "r")) {
            long fileSize = file.length();
            long lineCount = fileSize / LOG_ENTRY_SIZE;
            return findLogEntry(file, date, 0, lineCount);
        }
    }

    private static String findLogEntry(RandomAccessFile file, Date date, long left, long right) throws IOException,
            ParseException {
        long position = left + (right - left) / 2;
        file.seek(position * LOG_ENTRY_SIZE);
        String logEntry = file.readLine();
        Date entryDate = getDate(logEntry);
        if(date.equals(entryDate)) {
            return logEntry;
        } else if (date.after(entryDate) && position < right) {
            return findLogEntry(file, date, position + 1, right);
        } else if (position > left) {
            return findLogEntry(file, date, left, position - 1);
        }
        return null;

    }

    private static Date getDate(String logEntry) throws ParseException {
        return DATE_FORMAT.parse(logEntry.split(" - ")[0].trim());
    }


    private static void generateLogFile(int size) throws IOException, InterruptedException{
        List<String> logEntries = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            Thread.sleep(1000);
            logEntries.add(DATE_FORMAT.format(new Date()) + " - Entry" + String.format("%05d", i));
        }

        Files.write(Paths.get(FILENAME), logEntries);
    }
}
