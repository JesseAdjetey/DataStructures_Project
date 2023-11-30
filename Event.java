public class Event{
    String eventName;
    String dateOfEvent;
    String description;
    String time;

    public Event(String eventName, String dateOfEvent,String description,String time){
        this.eventName= eventName;
        this.dateOfEvent = dateOfEvent;
        this.description = description;
        this.time = time;
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

    @Override
    public String toString(){
        return "Event name:"+ eventName+ " Event date:"+ "Event time:" +time+ dateOfEvent +" Description";
    }

    // checking if events are the same

}