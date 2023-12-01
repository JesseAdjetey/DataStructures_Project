import java.util.ArrayList;
import java.util.PriorityQueue;

public class EventManagement {

    private int numberOfEvents = 0;
    PriorityQueue<Event> events;

    public EventManagement(){
        this.events = events = new PriorityQueue<>();
    }


    public String addEvent(Event event){
//        //checks if the event already exists
//        ArrayList<Event> sameEvents = searchEvent(event);
//        if(sameEvents!=null){
//            return "Event already exists";
//        }
        events.add(event);
        numberOfEvents++;
        return "Event added";
    }


    public ArrayList<Event> searchEvent(Event newEvent) {
        ArrayList<Event> listOfEvents = new ArrayList<>();
        if(events!=null){
        for (Event event : events) {
            if (newEvent.toString().equals(event.toString())) {
                listOfEvents.add(newEvent);
            }
        }}
        return listOfEvents;}
    public ArrayList<Event> searchEvent(String name,String dateOfEvent,String description,String time) {
        Event other = new Event(name,dateOfEvent,time,description);
        ArrayList<Event> listOfEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.toString().compareTo(other.toString())==0){
                listOfEvents.add(event);
            }
        }
        return listOfEvents;}
//
    public String reminder(){
        return events.poll().toString();
    }




}
