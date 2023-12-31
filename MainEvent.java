public class MainEvent {
    public static void main(String[] args) {
        EventManagement manager = new EventManagement();

        // Normal event with default (low) priority
        Event event1 = new Event("Breakfast", "12-03-23", "09-00-00", "Eating breakfast",5);

        // Event with low priority, initially conflicting with event1
        Event event2 = new Event("Lunch", "12-03-23", "09-00-00", "Eating lunch",8);
        event2.setPriority(0); // Explicitly setting low priority

        // High-priority event, conflicting with the above events
        Event event3 = new Event("Important Meeting", "12-03-23", "09-00-00", "Meeting with clients",0);
        event3.setPriority(1); // High priority

        System.out.println(manager.addEvent(event1)); // Should be added normally
        System.out.println(manager.addEvent(event2)); // Should be rescheduled due to conflict with event1
        System.out.println(manager.addEvent(event3)); // Will force event2 to be rescheduled due to its high priority

        // Display the current state of the event queue
        System.out.println("Scheduled Events:");
        if (!manager.events.isEmpty()) {
            System.out.println(manager.reminder());
 }
}
}