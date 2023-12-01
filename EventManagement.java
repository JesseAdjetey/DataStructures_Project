import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.time.format.DateTimeFormatter;

/**
 * EventManagement class manages events, their scheduling, and provides views of events.
 */
public class EventManagement {

    private int numberOfEvents = 0;
    public PriorityQueue<Event> events;

    private Stack<Event> eventsInADay;
    private Map<Month, Map<Integer, Stack<Event>>> monthlyHashtable = new HashMap<>();
    private Scanner scanner;

    /**
     * Constructor to initialize the EventManagement object.
     */
    public EventManagement() {
        events = new PriorityQueue<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Adds an event to the management system.
     *
     * @param event The event to be added.
     * @return A message indicating the success or failure of the operation.
     */
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
                return "Event addition canceled due to conflict";
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
        Integer day = event.getDateTime().getDayOfMonth();
        Stack<Event> eventStack = dayHashtable.get(day);
        eventStack.push(event);
        return "Event added";
    }

    /**
     * Deletes an event from the management system.
     *
     * @param eventToDelete The event to be deleted.
     * @return A message indicating the success or failure of the operation.
     */
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

    /**
     * Updates the position of an event in the priority queue.
     *
     * @param event The event to be updated.
     */
    private void updateEventInQueue(Event event) {
        events.remove(event); // Remove the event from the queue
        events.add(event);    // Re-add the event so it's placed correctly in the queue
    }

    /**
     * Searches for an event based on its name and date-time.
     *
     * @param name     The name of the event.
     * @param dateTime The date-time of the event.
     * @return The found event or null if not found.
     */
    public Event searchEvent(String name, LocalDateTime dateTime) {
        for (Event event : events) {
            if (event.getEventName().equals(name) && event.getDateTime().isEqual(dateTime)) {
                return event;
            }
        }
        return null; // Event not found
    }

    /**
     * Provides a reminder of the next event in the system.
     *
     * @return A reminder message.
     */
    public String reminder() {
        Event nextEvent = events.peek(); // This removes the event from the queue
        if (nextEvent != null) {
            return nextEvent.toString();
        }
        return "No events.";
    }

    /**
     * Checks if there is a scheduling conflict with a new event.
     *
     * @param newEvent The new event to be checked for conflicts.
     * @return True if there is a conflict, false otherwise.
     */
    private boolean isConflict(Event newEvent) {
        for (Event existingEvent : events) {
            if (newEvent.getDateTime().isEqual(existingEvent.getDateTime())) {
                return true; // Direct clash in timing
            }
            // Add additional checks if needed, based on your event scheduling logic
        }
        return false;
    }

    /**
     * Attempts to reschedule an event to resolve a conflict.
     *
     * @param event The event to be rescheduled.
     * @return True if the event was successfully rescheduled, false otherwise.
     */
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

    /**
     * Checks if a specific date-time is already occupied by another event (excluding a specific event).
     *
     * @param dateTime        The date-time to be checked.
     * @param eventToExclude The event to be excluded from the check.
     * @return True if the date-time is taken, false otherwise.
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
     * Forces the rescheduling of an event when other attempts fail.
     *
     * @param event The event to be forcefully rescheduled.
     */
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

    /**
     * Checks if an event with priority 0 can be moved to a different date-time.
     *
     * @param dateTime The date-time to check for availability.
     * @return True if the event can be moved, false otherwise.
     */
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

    /**
     * Displays all events for a specific month.
     *
     * @param month The month for which events are to be displayed.
     */
    public void monthlyView(Month month) {
        if (monthlyHashtable.containsKey(month)) {
            Map<Integer, Stack<Event>> dayHashtable = monthlyHashtable.get(month);

            System.out.println("All events for " + month + ":");

            // Iterate through days in the month
            for (Map.Entry<Integer, Stack<Event>> entry : dayHashtable.entrySet()) {
                int day = entry.getKey();
                Stack<Event> eventStack = entry.getValue();

                // Print all events for the day
                while (!eventStack.isEmpty()) {
                    System.out.println(eventStack.pop());
                }
            }
        } else {
            System.out.println("No events for " + month);
        }
    }

    /**
     * Displays all events for a specific day in a month.
     *
     * @param month The month of the day.
     * @param day   The day for which events are to be displayed.
     */
    public void dailyView(Month month, int day) {
        if (monthlyHashtable.containsKey(month)) {
            Map<Integer, Stack<Event>> dayHashtable = monthlyHashtable.get(month);
            if (dayHashtable.containsKey(day)) {
                // Iterate through events in the day
                Stack<Event> eventStack = dayHashtable.get(day);
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

    /**
     * Finds an event based on its name and modifies it with a new name.
     *
     * @param eventNameToFind The name of the event to find.
     * @param newEventName    The new name for the event.
     * @return A message indicating the success or failure of the operation.
     */
    public String findAndModifyEventByName(String eventNameToFind, String newEventName) {
        for (Event event : events) {
            if (event.getEventName().equalsIgnoreCase(eventNameToFind)) {
                // Modify the name of the existing event
                String oldEventName = event.getEventName();
                event.setEventName(newEventName);

                // Update the event in the monthlyHashtable if applicable
                for (Map<Integer, Stack<Event>> dayHashtable : monthlyHashtable.values()) {
                    for (Stack<Event> eventStack : dayHashtable.values()) {
                        for (Event stackEvent : eventStack) {
                            if (stackEvent.getEventName().equalsIgnoreCase(oldEventName)) {
                                stackEvent.setEventName(newEventName);
                                break;
                            }
                        }
                    }
                }

                // Update the event in the priority queue
                updateEventInQueue(event);

                return "Event name updated successfully.";
            }
        }
        return "Event not found.";
    }
    /**
     * Finds an event based on its name and modifies its date.
     *
     * @param eventNameToFind The name of the event to find.
     * @param newDate         The new date for the event.
     * @param newTime         The new time for the event.
     * @return A message indicating the success or failure of the operation.
     */
    public String findAndModifyEventDate(String eventNameToFind, String newDate, String newTime) {
        for (Event event : events) {
            if (event.getEventName().equalsIgnoreCase(eventNameToFind)) {
                // Store the old date for updating in the hashtable
                String oldDate = event.getDateOfEvent();

                StringBuilder dateTimeString = new StringBuilder(newDate+ " "+ newTime);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH-mm-ss");
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                // Modify the date and time of the existing event
                event.setDateOfEvent(newDate);
                event.setTime(newTime);
                event.setDateTime(dateTime);

                // Update the event in the monthlyHashtable if applicable
                for (Map<Integer, Stack<Event>> dayHashtable : monthlyHashtable.values()) {
                    for (Stack<Event> eventStack : dayHashtable.values()) {
                        for (Event stackEvent : eventStack) {
                            if (stackEvent.getEventName().equalsIgnoreCase(eventNameToFind)) {
                                // Update date in the hashtable
                                stackEvent.setDateOfEvent(newDate);
                                stackEvent.setTime(newTime);
                                stackEvent.setDateTime(dateTime);
                                break;
                            }
                        }
                    }
                }

                // Update the event in the priority queue
                updateEventInQueue(event);

                return "Event date updated successfully.";
            }
        }
        return "Event not found.";
    }

}
