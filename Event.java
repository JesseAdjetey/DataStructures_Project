import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * The Event class represents a scheduled event with a name, date, time, description, priority, and status.
 */
public class Event implements Comparable<Event> {

    private String eventName;
    private String dateOfEvent;
    private String description;
    private String time;
    private String status = "Pending";
    private int priority;
    private LocalDateTime dateTime;

    /**
     * Constructor for creating an event with LocalDateTime directly.
     *
     * @param eventName   The name of the event.
     * @param dateTime    The date and time of the event.
     * @param description The description of the event.
     * @param priority    The priority of the event.
     */
    public Event(String eventName, LocalDateTime dateTime, String description, int priority) {
        this.eventName = eventName;
        this.description = description;
        this.priority = priority;
        this.dateTime = dateTime;
    }

    /**
     * Constructor for creating an event with date and time strings.
     *
     * @param eventName    The name of the event.
     * @param dateOfEvent  The date of the event.
     * @param time          The time of the event.
     * @param description  The description of the event.
     * @param priority     The priority of the event.
     */
    public Event(String eventName, String dateOfEvent, String time, String description, int priority) {
        this.eventName = eventName;
        this.dateOfEvent = dateOfEvent;
        this.description = description;
        this.time = time;

        // Parse date and time strings to create LocalDateTime
        StringBuilder dateTimeString = new StringBuilder(dateOfEvent + " " + time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH-mm-ss");
        this.dateTime = LocalDateTime.parse(dateTimeString, formatter);
    }

    // Getter and setter methods

    /**
     * Sets the date of the event.
     *
     * @param dateOfEvent The date of the event.
     */
    public void setDateOfEvent(String dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    /**
     * Sets the description of the event.
     *
     * @param description The description of the event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the priority of the event.
     *
     * @param priority The priority of the event.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Sets the name of the event.
     *
     * @param eventName The name of the event.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Sets the time of the event.
     *
     * @param time The time of the event.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Sets the date and time of the event.
     *
     * @param newDateTime The new date and time for the event.
     */
    public void setDateTime(LocalDateTime newDateTime) {
        this.dateTime = newDateTime;

        // Update the dateOfEvent and time strings as well, if necessary
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH-mm-ss");
        this.dateOfEvent = newDateTime.format(formatter).split(" ")[0];
        this.time = newDateTime.format(formatter).split(" ")[1];
    }

    /**
     * Gets the date of the event.
     *
     * @return The date of the event.
     */
    public String getDateOfEvent() {
        return dateOfEvent;
    }

    /**
     * Gets the priority of the event.
     *
     * @return The priority of the event.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Gets the description of the event.
     *
     * @return The description of the event.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the name of the event.
     *
     * @return The name of the event.
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Gets the time of the event.
     *
     * @return The time of the event.
     */
    public String getTime() {
        return time;
    }

    /**
     * Gets the date and time of the event as LocalDateTime.
     *
     * @return The date and time of the event.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets the status of the event.
     *
     * @param status The status of the event.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "Event name: " + eventName + " Event date and time: " + dateTime + " Description: " + description;
    }

    /**
     * Compares this event with another event based on their date and time.
     *
     * @param other The other event to compare to.
     * @return A negative integer, zero, or a positive integer as this event is before, at the same time, or after the other event.
     */
    @Override
    public int compareTo(Event other) {
        return this.dateTime.compareTo(other.getDateTime());
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The reference object with which to compare.
     * @return True if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Event event = (Event) obj;
        return Objects.equals(eventName, event.eventName) &&
                Objects.equals(dateTime, event.dateTime);
    }

    // Additional methods or documentation can be added as needed
}
