import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.time.LocalDateTime;

public class EventManagement {

    private int numberOfEvents = 0;
    // Priority Queue to store the events
    PriorityQueue<Event> events;
    private Scanner scanner;
/**
 * Event management constructor
 */
    public EventManagement(){
         events = new PriorityQueue<>();
         this.scanner = new Scanner(System.in);
    }


/**
 * addEvent method.
 * @param event
 * @return
 */
    public String addEvent(Event event) {
        // Checks if the event already exists in the queue
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

/**
 * updateEventInQueue method
 * method used to remove and add events after performing rescheduling
 * @param event
 */
    private void updateEventInQueue(Event event) {
        events.remove(event); // Remove the event from the queue
        events.add(event);    // Re-add the event so it's placed correctly in the queue
    }

/**
 * 
 * @param newEvent
 * @return
 */
    public ArrayList<Event> searchEvent(String newEvent) {
        ArrayList<Event> listOfEvents = new ArrayList<>();
        if(events!=null){
        int ind = 1;
        for (Event event : events) {
            if (newEvent.equals(event.getEventName())) {
                listOfEvents.add(event);
                System.out.println(ind + newEvent);
                ind++;
            }
            System.out.println("These are all the events with the name:"+ newEvent+"\nPlease select the event you wish to find by typing the number infront of it:");
            int search_select = scanner.nextInt();
            System.out.println(listOfEvents.get(search_select-1));
            
        }}
        scanner.nextLine();
        return listOfEvents;}
    // public ArrayList<Event> searchEvent(String name,String dateOfEvent,String description,String time) {
    //     Event other = new Event(name,dateOfEvent,time,description);
    //     ArrayList<Event> listOfEvents = new ArrayList<>();
    //     for (Event event : events) {
    //         if (event.toString().compareTo(other.toString())==0){
    //             listOfEvents.add(event);
    //         }
    //     }
    //     return listOfEvents;}
/**
 * reminder method
 * @return
 */
    public String reminder() {
        // This removes the event from the queue
        Event nextEvent = events.poll(); 
        if (nextEvent != null) {
         // Assuming Event has a properly overridden toString method
            return nextEvent.toString();
        }
        return "No more events.";
    }
/**
 * isConflict method
 * Checks to see if any events happen at the same time
 * @param newEvent
 * @return boolean
 */
    private boolean isConflict(Event newEvent) {
        for (Event existingEvent : events) {
            if (newEvent.getDateTime().isEqual(existingEvent.getDateTime())) {
                return true; 
            }
        }
       return false;
    }
/**
 * tryRescheduleEvent method.
 * Tries to reschedule the event by adding an hour consecutively until reaching a spot that is free
 * @param event
 * @return boolean
 */
    private boolean tryRescheduleEvent(Event event) {
        LocalDateTime originalDateTime = event.getDateTime();
        for (int i = 1; i <= 3; i++) {
            // Hourly increment
            LocalDateTime newDateTime = originalDateTime.plusHours(i);
            if (!isDateTimeTaken(newDateTime, event)) {
                // Remove event before updating its DateTime
                events.remove(event); 
                event.setDateTime(newDateTime);
                events.add(event);   
                return true;
            }
        }
        return false;
    }

    

/**
 * isDateTimeTaken method
 * returns a boolean to know whether the time that an event is assigned to during rescheduling is taken or not
 * @param dateTime
 * @param eventToExclude
 * @return boolean
 */
    private boolean isDateTimeTaken(LocalDateTime dateTime, Event eventToExclude) {
        for (Event event : events) {
            if (!event.equals(eventToExclude) && event.getDateTime().isEqual(dateTime)) {
                return true;
            }
        }
       return false;
    }
/**
 * forceRescheduleEvent method
 * Works similarly to the tryRescheduleEvent
 * Whereas tryRescheduleEvent increments hourly for a few times (3) forceReschedule increments hourly until a time is found to be inputted
 * Therefore tryReschedule tries to make sure the rescheduling is as recent and close to the time you wanted to have that event whereas forceReschedule event
 * just makes sure that it slots in an event regardless of how far it was from your previous event time
 * @param event
 */
    private void forceRescheduleEvent(Event event) {
        LocalDateTime newDateTime = event.getDateTime();
        boolean rescheduled = false;
        while (!rescheduled) {
            newDateTime = newDateTime.plusHours(1);
            //Checks if the new incremented newDateTime is not taken by any other event (!isDateTimeTaken) or if the event can be moved at that time
            if (!isDateTimeTaken(newDateTime, event) || canMoveEventAtDateTime(newDateTime, event)) {
                events.remove(event); // Remove event before updating its DateTime
                event.setDateTime(newDateTime);
                events.add(event);    // Re-add event to update its position in PriorityQueue
                rescheduled = true;
            }
        }
    }
/**
 * canMoveEventAtDateTime method
 * 
 * @param dateTime
 * @return boolean
 */
    private boolean canMoveEventAtDateTime(LocalDateTime dateTime) {
        for (Event event : events) {
            // check if an event with a particular date and time (dateTime) and a lower priority (0) can be moved to another time 
            if (event.getDateTime().isEqual(dateTime) && event.getPriority() == 0) {
                // If event is found, it attempts to move it by adding one hour to its tim
                LocalDateTime newDateTimeForExistingEvent = dateTime.plusHours(1);
                event.setDateTime(newDateTimeForExistingEvent);
                return true;
            }
        }
       return false;
    }




}