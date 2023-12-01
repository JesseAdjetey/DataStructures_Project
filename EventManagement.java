import java.time.DayOfWeek;
import java.time.Month;
import java.util.*;
import java.time.LocalDateTime;

public class EventManagement {

    private int numberOfEvents = 0;
    PriorityQueue<Event> events;

    private Stack<Event> eventsInADay;
    Map<Month, Map<Integer, Stack<Event>>> monthlyHashtable = new HashMap<>();
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
        Month month = event.getDateTime().getMonth();
        monthlyHashtable.putIfAbsent(month, new HashMap<>());
        // Get the hashtable for the month
        Map<Integer, Stack<Event>> dayHashtable = monthlyHashtable.get(month);
        // If the day is not in the hashtable, create a new stack for the day
        dayHashtable.putIfAbsent(event.getDateTime().getDayOfMonth(), new Stack<>());
        // Get the stack for the day and add the event
        Integer day= event.getDateTime().getDayOfMonth();
        Stack<Event> eventStack = dayHashtable.get(day);
        eventStack.push(event);
        return "Event added";
    }

    public String deleteEvent(Event eventToDelete) {
        // Remove the event from the priority queue
        boolean removedFromQueue = events.remove(eventToDelete);

        // Remove the event from the monthlyHashtable
        if (monthlyHashtable.containsKey(eventToDelete.getDateTime().getMonth())) {
            Map<Integer, Stack<Event>> dayHashtable = monthlyHashtable.get(eventToDelete.getDateTime().getMonth());
            if (dayHashtable.containsKey(eventToDelete.getDateTime().getDayOfMonth())) {
                Stack<Event> eventStack = dayHashtable.get(eventToDelete.getDateTime().getDayOfMonth());

                // Remove the event from the stack
                eventStack.removeIf(e -> e.equals(eventToDelete));

                // If the stack is now empty, remove the day entry from the dayHashtable
                if (eventStack.isEmpty()) {
                    dayHashtable.remove(eventToDelete.getDateTime().getDayOfMonth());
                }

                // If the dayHashtable is now empty, remove the month entry from the monthlyHashtable
                if (dayHashtable.isEmpty()) {
                    monthlyHashtable.remove(eventToDelete.getDateTime().getMonth());
                }

                if (removedFromQueue) {
                    return "Event deleted";
                } else {
                    return "Event not found in the priority queue";
                }
            }
        }

        return "Event not found in the monthlyHashtable";
    }


    private void updateEventInQueue(Event event) {
        events.remove(event); // Remove the event from the queue
        events.add(event);    // Re-add the event so it's placed correctly in the queue
    }

    public Event searchEvent(String name, LocalDateTime dateTime) {
        for (Event event : events) {
            if (event.getEventName().equals(name) && event.getDateTime().isEqual(dateTime)) {
                return event;
            }
        }
        return null; // Event not found
    }

    public String reminder() {
        Event nextEvent = events.peek(); // This removes the event from the queue
        if (nextEvent != null) {
            return nextEvent.toString();
        }
        return "No events.";
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
                deleteEvent(event); // Remove event before updating its DateTime
                event.setDateTime(newDateTime);
                addEvent(event);    // Re-add event to update its position in PriorityQueue
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
            if (!isDateTimeTaken(newDateTime, event) || canMoveEventAtDateTime(newDateTime)) {
                deleteEvent(event); // Remove event before updating its DateTime
                event.setDateTime(newDateTime);
                addEvent(event);    // Re-add event to update its position in PriorityQueue
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

    public void monthlyView(Month month) {
            if (monthlyHashtable.containsKey(month)) {
                Map<Integer, Stack<Event>> dayHashtable = monthlyHashtable.get(month);

                System.out.println("All events for " + month + ":");

                // Iterate through days in the month
                for (Map.Entry<Integer, Stack<Event>> entry : dayHashtable.entrySet()) {
                    int day = entry.getKey();
                    Stack<Event> eventStack = entry.getValue();
                    System.out.println("Day " + day + ":");

                    // Print all events for the day
                    while (!eventStack.isEmpty()) {
                        System.out.println(eventStack.pop());
                    }
                }
            } else {
                System.out.println("No events for " + month);
            }
    }
    public void dailyView(Month month,int day){
            if (monthlyHashtable.containsKey(month)) {
                Map<Integer, Stack<Event>> dayHashtable = monthlyHashtable.get(month);
                if(dayHashtable.containsKey(day)){
                // Iterate through events in the day
                Stack<Event> eventStack= dayHashtable.get(day);
                System.out.println("Events of the Day " + day + ":");
                // Print all events for the day
                while (!eventStack.isEmpty()) {
                    System.out.println(eventStack.pop());
                }

            } else {
                System.out.println("No events for " + day);
            }
        }

    }



}


