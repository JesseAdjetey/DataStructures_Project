import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;




public class EVC {
    public static void main(String[] args) {

        EventManagement manager = new EventManagement();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 7) {
            System.out.println("Welcome to our task manager for the year 2023!");
            System.out.println("\nTask Management System Menu:");
            System.out.println("1. Create event");
            System.out.println("2. Modify event");
            System.out.println("3. DailyView");
            System.out.println("4. Monthly View");
            System.out.println("5. Reminders");
            System.out.println("6. Show Task");
            System.out.println("7. Quit");


            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            //Event newEvent; 
            
            if (choice == 1) { //creating and adding task to queue or stack

                System.out.print("Enter the event Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter the date(dd-mm-yy): ");
                String date = scanner.nextLine();

                System.out.print("Enter the time(hh:mm:ss): ");
                String time = scanner.nextLine();

                System.out.print("Enter the task description: ");
                String description = scanner.nextLine();

                System.out.println("Enter the priority: ");
                int priority = scanner.nextInt();
                StringBuilder dateTimeString = new StringBuilder(date+ " "+ time);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH-mm-ss");
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                // Check if the parsed date is a current date or upcoming date
                LocalDateTime currentDateTime = LocalDateTime.now();
                if (dateTime.isBefore(currentDateTime)) {
                    System.out.println("Please enter a current or upcoming date and time.");
                } else {
                    // Create an Event object using the provided information
                    Event event = new Event(name, dateTime, description, priority);
                    try {
                        int year = dateTime.getYear();
                        if(year == 2023){
                            System.out.println(manager.addEvent(event));
                        }else{
                            System.out.println("Oops, the year must be in 2023!");
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Incorrect date and time");
                    }
                }

            }

            
            else if (choice == 2) {
                System.out.println("Modify Event: Enter event Name");
                String eventName = scanner.nextLine();

                //Find Event Using search method
                //if event is in list; ALL CODE BELOW HSOULD BE IN CONDITION STATEMENT AND RUN ONLY IF EVENT IS IN LIST

                    System.out.println("1. Change name 2.Change date and time 3. delete event ");
                    int processChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (processChoice == 1) {
                    
                    System.out.print("Enter new Name ");
                    String newName = scanner.nextLine();
                    manager.findAndModifyEventByName(eventName,newName);
                    }
                    else if (processChoice == 2) {
                    
                    System.out.println("Enter new date");
                    String newDate = scanner.nextLine();
                    System.out.print("Enter new time");
                    String newTime = scanner.nextLine();
                    manager.findAndModifyEventDate(eventName,newDate,newTime);

                    }
                    else if (processChoice == 3) {
                        
                    System.out.print("Enter new description");
                    // int Completed1Choice = scanner.nextInt(); ALLOW USER INPUT
                    //use setter to change description
                    
                    }

                    else if (processChoice == 4) {
                        //DELETE THE EVENT FROM THE LIST
                        System.out.println("Specify event date(dd-MM-yy): ");
                        String dateString = scanner.nextLine();
                        System.out.println("Specify event time(HH-mm-ss): ");
                        String timeString = scanner.nextLine();


                        StringBuilder dateTimeString = new StringBuilder(dateString+ " "+ timeString);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH-mm-ss");
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

                        Event eventFound = manager.searchEvent(eventName, dateTime);
                        if(eventFound==null) {
                            System.out.println("Event does not exist");
                        }
                        else {
                            manager.deleteEvent(eventFound);
                        }
                    }
                    }


                //ELSE IF TASK IS NOT FOUND, PRINT TASK NOT FOUND


            else if (choice == 3) {//DAILY VIEW IMPLEMENTATION BASED ON THE DATE THE USER ENTERS
                System.out.print("Enter date");
                String dateString = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
                try {
                    LocalDate date = LocalDate.parse(dateString, formatter);
                    int day = date.getDayOfMonth();
                    Month month = date.getMonth();
                    manager.dailyView(month, day);
                } catch (DateTimeParseException e) {
                    System.out.println("Error parsing date: " + e.getMessage());
                }




            } 
            
            else if (choice == 4) {//MONTHLY VIEW IMPLEMENTATION BASED ON THE MONTH THE USER ENTERS
                System.out.print("Enter MONTH:");
                String monthString = scanner.next();
                try {
                    // Convert the string to a Month enum
                    Month month = Month.valueOf(monthString.toUpperCase());
                    manager.monthlyView(month);
                } catch (IllegalArgumentException e) {
                    // Handle the case where the string is not a valid month
                    System.out.println("Invalid month: " + monthString);
                }

            } 

            
            else if (choice == 5) {//REMINDS USER OF THE MOST RECENT EVENTS 
                //CREATE A SORTED LIST OF ALL THE CLOSEST EVENTS (TO CURRENT DATE) AND PRINT A LIST OF THE FIRST FEW (5-10)
                manager.reminder();
            } 




            else if (choice == 6) {//EXITS THE EVENT CALENDER SYSTEM
                //PRINT STATEMENT TO TELL USER IT IS EXITING OPTIONAL: CONFIRMATION MESSAGE(ARE YOU SURE YOU WANT TO QUIT? IF YES CHOICE = 5, IF NO CHOICE = 0)
                System.out.println("Goodbye, thanks for using our services.");
            }

            else {
                System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }
    }



