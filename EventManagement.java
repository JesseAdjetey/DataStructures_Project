import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.time.LocalDateTime;

public class EventManagement {

    private int numberOfEvents = 0;
    PriorityQueue<Event> events;
    private Scanner scanner;

    public EventManagement(){
         events = new PriorityQueue<>();
         this.scanner = new Scanner(System.in);
    }



    public String addEvent(Event event) {
        // Check if the event already exists in the queue
        for (Event existingEvent : events) {
            if (existingEvent.equals(event)) {
                return "Event already exists";
            }
        }
    
        // Handle event conflict and rescheduling
        if (isConflict(event)) {
            // Prompt user for rescheduling decision
            System.out.println("Conflict detected with another event. Would you like to reschedule this event? (y/n)");
            String userInput = scanner.nextLine();
            if ("y".equalsIgnoreCase(userInput)) {
                // Try to reschedule the event
                if (!tryRescheduleEvent(event)) {
                    // If unable to reschedule normally, force reschedule
                    forceRescheduleEvent(event);
                }
                return "Event rescheduled";
            } else {
                // If user chooses not to reschedule
                return "Event addition cancelled due to conflict";
            }
        }
    
        // Add the event to the queue if not already present
        events.add(event);
        numberOfEvents++;
        return "Event added";
    }


    private void updateEventInQueue(Event event) {
        events.remove(event); // Remove the event from the queue
        events.add(event);    // Re-add the event so it's placed correctly in the queue
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

    public String reminder() {
        Event nextEvent = events.poll(); // This removes the event from the queue
        if (nextEvent != null) {
            return nextEvent.toString(); // Assuming Event has a properly overridden toString method
        }
        return "No more events.";
    }

    private boolean isConflict(Event newEvent) {
        for (Event existingEvent : events) {
            if (newEvent.getDateTime().isEqual(existingEvent.getDateTime())) {
                return true; // Direct clash in timing
            }
            // Add additional checks if needed, based on your event scheduling logic
        }
       return false;
    }

    private boolean tryRescheduleEvent(Event event) {
        LocalDateTime originalDateTime = event.getDateTime();
        for (int i = 1; i <= 3; i++) {
            LocalDateTime newDateTime = originalDateTime.plusHours(i);
            if (!isDateTimeTaken(newDateTime, event)) {
                events.remove(event); // Remove event before updating its DateTime
                event.setDateTime(newDateTime);
                events.add(event);    // Re-add event to update its position in PriorityQueue
                return true;
            }
        }
        return false;
    }

    


    private boolean isDateTimeTaken(LocalDateTime dateTime, Event eventToExclude) {
        for (Event event : events) {
            if (!event.equals(eventToExclude) && event.getDateTime().isEqual(dateTime)) {
                return true;
            }
        }
       return false;
    }

    private void forceRescheduleEvent(Event event) {
        LocalDateTime newDateTime = event.getDateTime();
        boolean rescheduled = false;
        while (!rescheduled) {
            newDateTime = newDateTime.plusHours(1);
            if (!isDateTimeTaken(newDateTime, event) || canMoveEventAtDateTime(newDateTime, event)) {
                events.remove(event); // Remove event before updating its DateTime
                event.setDateTime(newDateTime);
                events.add(event);    // Re-add event to update its position in PriorityQueue
                rescheduled = true;
            }
        }
    }
    
    private boolean canMoveEventAtDateTime(LocalDateTime dateTime) {
        for (Event event : events) {
            if (event.getDateTime().isEqual(dateTime) && event.getPriority() == 0) {
                LocalDateTime newDateTimeForExistingEvent = dateTime.plusHours(1);
                event.setDateTime(newDateTimeForExistingEvent);
                return true;
            }
        }
       return false;
    }




}