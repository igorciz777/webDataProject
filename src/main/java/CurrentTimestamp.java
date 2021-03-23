import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTimestamp {
    LocalDateTime currentDateTime;
    Timestamp timestamp;

    CurrentTimestamp(){
        currentDateTime = LocalDateTime.now();
        currentDateTime.format(DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm:ss"));
        timestamp = Timestamp.valueOf(currentDateTime);
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
