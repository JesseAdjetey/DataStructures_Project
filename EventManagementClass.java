import java.util.ArrayList;
import java.util.PriorityQueue;

public class EventManagementClass {

    private int numberOfEvents = 0;
    PriorityQueue<Event> events = new PriorityQueue<>();

    public EventManagementClass(){
        this.events = null;
    }


    public Boolean createEvent(String eventName, String dateOfEvent,String description,String time){
        //checks if the event already exists
        ArrayList<Event> sameEvents = searchEvent(eventName,dateOfEvent,description,time);
        if(sameEvents!=null){
            return false;
        }
        Event newEvent = new Event(eventName,dateOfEvent,description,time);
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
    public ArrayList<Event> searchEvent(String name,String dateOfEvent,String description,String time) {
        Event other = new Event(name,dateOfEvent,description,time);
        ArrayList<Event> listOfEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.toString().compareTo(other.toString())==0){
                listOfEvents.add(event);
            }
        }
        return listOfEvents;




}
