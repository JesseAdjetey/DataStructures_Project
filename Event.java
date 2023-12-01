import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Event implements Comparable<Event>{
    String eventName;
    String dateOfEvent;
    String description;
    String time;
    String status = "Pending";
    private int priority;

    LocalDateTime dateTime;

    public Event(String eventName, String dateOfEvent, String time,String description, int priority){
        this.eventName= eventName;
        this.dateOfEvent = dateOfEvent;
        this.description = description;
        this.time = time;
        this.priority = priority;
        StringBuilder dateTimeString = new StringBuilder(dateOfEvent+ " "+ time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH-mm-ss");
        this.dateTime = LocalDateTime.parse(dateTimeString, formatter);
    }
    
    public Event(String eventName, String dateOfEvent, String time,String description){
        this.eventName= eventName;
        this.dateOfEvent = dateOfEvent;
        this.description = description;
        this.time = time;
        this.priority = 0;
        StringBuilder dateTimeString = new StringBuilder(dateOfEvent+ " "+ time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH-mm-ss");
        this.dateTime = LocalDateTime.parse(dateTimeString, formatter);
    }

    public void setDateOfEvent(String dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDateTime(LocalDateTime newDateTime) {
        this.dateTime = newDateTime;
        // Update the dateOfEvent and time strings as well, if necessary
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH-mm-ss");
        this.dateOfEvent = newDateTime.format(formatter).split(" ")[0];
        this.time = newDateTime.format(formatter).split(" ")[1];
    }

    public String getDateOfEvent() {
        return dateOfEvent;
    }

    public int getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public String getEventName() {
        return eventName;
    }

    public String getTime() {
        return time;
    }
    public LocalDateTime getDateTime(){
        return dateTime;
    }

    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public String toString(){
        return "Event name:"+ eventName+ " Event date and time:"+  dateTime +" Description "+description;
    }

    @Override
    public int compareTo(Event other) {
        int dateComparison = this.dateTime.compareTo(other.dateTime);
        if (dateComparison != 0) {
            return dateComparison;
        }
        // Assuming lower integer value means higher priority
        return Integer.compare(this.priority, other.priority);
    }

    @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Event event = (Event) obj;
    return Objects.equals(eventName, event.eventName) &&
           Objects.equals(dateTime, event.dateTime);
}

    

    // checking if events are the same

}