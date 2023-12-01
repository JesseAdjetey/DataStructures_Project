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
    /**
     * Constructor for Event with priority specified
     * 
     * @param eventName    Name of the event
     * @param dateOfEvent  Date of the event (in String format)
     * @param time         Time of the event (in String format)
     * @param description  Description of the event
     * @param priority     Priority level of the event
     */
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
     /**
     * Constructor for Event with default priority (0)
     * 
     * @param eventName    Name of the event
     * @param dateOfEvent  Date of the event (in String format)
     * @param time         Time of the event (in String format)
     * @param description  Description of the event
     */
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
    /**
     * 
     * Various setters
     */
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
    /**
     * Setter for updating date and time of the event 
     * @param newDateTime  New date and time for the event
     */
    public void setDateTime(LocalDateTime newDateTime) {
        this.dateTime = newDateTime;
        // Update the dateOfEvent and time strings as well, if necessary
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH-mm-ss");
        this.dateOfEvent = newDateTime.format(formatter).split(" ")[0];
        this.time = newDateTime.format(formatter).split(" ")[1];
    }
    /**
     * 
     * Various getters
     */
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
    /**
     * Override of toString method to represent Event information as a string
     * @return String representation of Event information
     */
    @Override
    public String toString(){
        return "Event name:"+ eventName+ " Event date and time:"+  dateTime +" Description "+description;
    }
    /**
     * Override of compareTo method for comparing events based on date and priority
     * 
     * @param other  Another Event object to compare with
     * @return Comparison result between events based on date and priority
     */
    @Override
    public int compareTo(Event other) {
        int dateComparison = this.dateTime.compareTo(other.dateTime);
        if (dateComparison != 0) {
            return dateComparison;
        }
        // Assuming lower integer value means higher priority
        return Integer.compare(this.priority, other.priority);
    }
    /**
     * Override of equals method to check if two events are the same
     * 
     * @param obj  Another object to compare with
     * @return True if the compared objects are Events and have the same name and date/time
     */
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