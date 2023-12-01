public class MainEvent {
    public static void main(String[] args) {
        EventManagement manager = new EventManagement();

        // Normal event with default (low) priority
        Event event1 = new Event("Breakfast", "12-03-23", "09-00-00", "Eating breakfast");

        // Event with low priority, initially conflicting with event1
        Event event2 = new Event("Lunch", "12-03-23", "09-00-00", "Eating lunch");
        event2.setPriority(0); // Explicitly setting low priority

        // High-priority event, conflicting with the above events
        Event event3 = new Event("Important Meeting", "12-03-23", "09-00-00", "Meeting with clients");
        event3.setPriority(1); // High priority

        Event event4 = new Event("Jollof Meeting", "12-03-23", "03-00-00", "Meeting with jollofers",1);

        Event event5 = new Event("Jollof Meeting", "12-03-23", "07-00-00", "Radio in Ghana");

        Event event6 = new Event("Jollof Meeting", "12-03-23", "07-00-00", "Radio in Ghana");


        System.out.println(manager.addEvent(event1)); // Should be added normally
        System.out.println(manager.addEvent(event2)); // Should be rescheduled due to conflict with event1
        System.out.println(manager.addEvent(event3)); // Will force event2 to be rescheduled due to its high priority
        System.out.println(manager.addEvent(event4)); // Will force event2 to be rescheduled due to its high priority
        System.out.println(manager.addEvent(event5)); // Will force event2 to be rescheduled due to its high priority
        System.out.println(manager.addEvent(event6)); // Will force event2 to be rescheduled due to its high priority

        // Display the current state of the event queue
        System.out.println("Scheduled Events:");
        if (!manager.events.isEmpty()) {
            System.out.println(manager.reminder());

        manager.searchEvent("Jollof meeting");
 }
}
}