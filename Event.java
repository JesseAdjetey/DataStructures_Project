public class Event{
    String eventName;
    String dateOfEvent;
    String description;
    String time;

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
    
}