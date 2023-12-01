import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event implements Comparable<Event>{
    String eventName;
    String dateOfEvent;
    String description;
    String time;
    String status = "Pending";

    LocalDateTime dateTime;

    public Event(String eventName, String dateOfEvent, String time,String description){
        this.eventName= eventName;
        this.dateOfEvent = dateOfEvent;
        this.description = description;
        this.time = time;
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

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDateOfEvent() {
        return dateOfEvent;
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
    public int compareTo(Event other){
        return this.dateTime.compareTo(other.getDateTime());
    }

    // checking if events are the same

}