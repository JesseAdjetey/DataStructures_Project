import java.time.Month;

public class Main {
    public static void main(String[] args){
        EventManagement manager = new EventManagement();

        Event event1 = new Event("Eat banku","12-03  -23", "09-00-00" ,"eating",5);
        Event event2 = new Event("Eat banku","12-03-23", "10-00-00" ,"eating",7);
        Event event3 = new Event("Eat banku","10-03-23", "09-00-00" ,"eating",8);

        System.out.println(manager.addEvent(event1));
        System.out.println(manager.addEvent(event2));
        System.out.println(manager.addEvent(event3));
        System.out.println(manager.reminder());
//        manager.monthlyView(Month.MARCH);
        manager.dailyView(Month.MARCH, 12);




    }


}
