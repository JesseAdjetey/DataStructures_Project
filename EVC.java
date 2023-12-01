import java.util.Scanner;




public class EVC {
    public static void main(String[] args) {

        //Stack stack = new Stack(30);
        //HashTable HASH = new HashTable(30);
        //LIST list - new list      /FOR REMINDERS


        Scanner scanner = new Scanner(System.in);
        
        int choice = 0;





        while (choice != 5) {
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

                System.out.print("Enter the date(mm-dd-yy): ");
                String date = scanner.nextLine(); 

                System.out.print("Enter the time(hh:mm:ss): ");
                String description = scanner.nextLine();

                System.out.print("Enter the task description: ");
                String time = scanner.nextLine();

//                StringBuilder dateTime = new StringBuilder(date+ " "+ time);



                //newEVENT = new EVENT(ID, description, date); 



                //Insert into list to store




            } 
            

            
            
            else if (choice == 2) {
                System.out.print("Modify Event: Enter event Name");

                //Find Event Using search method
                //if event is in list; ALL CODE BELOW HSOULD BE IN CONDITION STATEMENT AND RUN ONLY IF EVENT IS IN LIST

                    System.out.print("1. Change name 2.Change date 3.Change time 4.Change description 5. delete event ");
                    int processChoice = scanner.nextInt();

                    if (processChoice == 1) {
                    
                    System.out.print("Enter new Name ");
                    //int Completed1Choice = scanner.nextInt(); ALLOW USER INPUT
                    //use setter to change name 
                    }
                    else if (processChoice == 2) {
                    
                    System.out.print("Enter new date");
                    //int Completed1Choice = scanner.nextInt(); ALLOW USER INPUT
                    //use setter to change date 

                    }
                    else if (processChoice == 3) {
                    
                    System.out.print("Enter new time");
                    //int Completed1Choice = scanner.nextInt(); ALLOW USER INPUT
                    //use setter to change time
                    
                    }
                    else if (processChoice == 4) {
                        
                    System.out.print("Enter new description");
                    // int Completed1Choice = scanner.nextInt(); ALLOW USER INPUT
                    //use setter to change description
                    
                    }

                    else if (processChoice == 5) {
                        //DELETE THE EVENT FROM THE LIST
                    }


                //ELSE IF TASK IS NOT FOUND, PRINT TASK NOT FOUND     
                    
                
            }
            
            
            else if (choice == 3) {//DAILY VIEW IMPLEMENTATION BASED ON THE DATE THE USER ENTERS
                System.out.print("Enter date");
                //stack.viewStack(); CREATE VIEW OF ALL EVENTS WITH SPECIFIC DATE IN ORDER OF THE TIME IT IS HAPPENING

            } 
            
            else if (choice == 4) {//MONTHLY VIEW IMPLEMENTATION BASED ON THE MONTH THE USER ENTERS
                System.out.print("Enter MONTH");
                //hashTable().viewHashTable; CREATE VIEW OF ALL EVENTS WITHIN THAT MONTH
            } 

            
            else if (choice == 5) {//REMINDS USER OF THE MOST RECENT EVENTS 
                //CREATE A SORTED LIST OF ALL THE CLOSEST EVENTS (TO CURRENT DATE) AND PRINT A LIST OF THE FIRST FEW (5-10)
                
            } 



            else if (choice == 6) {//FINDS A SPECIFIC TASK AND PRINTS DETAILS
                //USE SHOW EVENT FUNCTION TO FIND A SPECIFIC EVENT 
                
            } 


            else if (choice == 7) {//EXITS THE EVENT CALENDER SYSTEM
                //PRINT STATEMENT TO TELL USER IT IS EXITING OPTIONAL: CONFIRMATION MESSAGE(ARE YOU SURE YOU WANT TO QUIT? IF YES CHOICE = 5, IF NO CHOICE = 0)
                
            } 

            else {
                System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }
}


