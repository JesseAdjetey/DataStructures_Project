import java.util.ArrayList;
import java.util.PriorityQueue;

public class EventManagementClass {

    private int numberOfEvents = 0;
    PriorityQueue<Event> events = new PriorityQueue<>();

    public EventManagementClass(){
        this.events = null;
    }


    public Boolean createEvent(String eventName, String dateOfEvent,String description,String time,int priority){
        //checks if the event already exists
        ArrayList<Event> sameEvents = searchEvent(eventName,dateOfEvent,description,time, priority);
        if(sameEvents!=null){
            return false;
        }
        Event newEvent = new Event(eventName,dateOfEvent,description,time,priority);
        events.add(newEvent);
        numberOfEvents++;
        return true;
    }


    public ArrayList<Event> searchEvent(String name) {
        ArrayList<Event> listOfEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getEventName().equals(name)) {
                listOfEvents.add(event);
            }
        }
        return listOfEvents;}
    public ArrayList<Event> searchEvent(String name,String dateOfEvent,String description,String time, int priority) {
        Event other = new Event(name,dateOfEvent,description,time, priority);
        ArrayList<Event> listOfEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.toString().compareTo(other.toString())==0){
                listOfEvents.add(event);
            }
        }
        return listOfEvents;}

    public String reminder(Event name, String dateOfEvent,String time){
        return
    }




}
