import java.time.Month;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args){
        EventManagement eventManagement = new EventManagement();

        // Test Case 1: Add Events
        Event event1 = new Event("Meeting", "15-12-23", "10-00-00", "Discuss project", 1);
        Event event2 = new Event("Birthday Party", "15-12-23", "08-20-00", "Celebrate with friends", 2);
        Event event3 = new Event("Conference", "15-12-23", "14-30-00", "Attend industry conference", 0);
        Event event4 = new Event("Dinner", "15-12-23", "19-00-00", "Family dinner", 1);
        Event event5 = new Event("Gym Session", "15-12-23", "18-20-00", "Workout", 2);

        System.out.println("Test Case 1: Add Events");
        System.out.println(eventManagement.addEvent(event1));
        System.out.println(eventManagement.addEvent(event2));
        System.out.println(eventManagement.addEvent(event3));
        System.out.println(eventManagement.addEvent(event4));
        System.out.println(eventManagement.addEvent(event5));

        // Test Case 2: Search for an Event
        System.out.println("\nTest Case 2: Search for an Event");
        Event searchEvent = new Event("Gym Session", "15-12-23", "18-20-00", "Workout", 2);

        StringBuilder dateTimeString = new StringBuilder("15-12-23"+ " "+ "18-20-00");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH-mm-ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        System.out.println(eventManagement.searchEvent("Gym Session",dateTime));

        // Test Case 3: Delete an Event
        System.out.println("\nTest Case 3: Delete an Event");
        System.out.println(eventManagement.deleteEvent(event2));

        // Test Case 4: Reminder
        System.out.println("\nTest Case 4: Reminder");
        System.out.println(eventManagement.reminder());

        // Test Case 5: Monthly View
        System.out.println("\nTest Case 5: Monthly View");
        eventManagement.monthlyView(Month.DECEMBER);

        // Test Case 6: Daily View
        System.out.println("\nTest Case 6: Daily View");
        eventManagement.dailyView(Month.DECEMBER, 23);

        // Test Case 7: Delete Event not in Priority Queue
        System.out.println("\nTest Case 7: Delete Event not in Priority Queue");
        Event nonExistingEvent = new Event("Non-existing Event", "15-12-25", "12-00-00", "Dummy event", 1);
        System.out.println(eventManagement.deleteEvent(nonExistingEvent));

        // Test Case 8: Delete Event not in Monthly Hashtable
        System.out.println("\nTest Case 8: Delete Event not in Monthly Hashtable");
        Event nonExistingEvent2 = new Event("Non-existing Event", "15-12-24", "12-00-00", "Dummy event", 1);
        System.out.println(eventManagement.deleteEvent(nonExistingEvent2));

    }

    }

