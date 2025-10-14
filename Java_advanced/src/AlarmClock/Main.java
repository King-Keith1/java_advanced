package AlarmClock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //JAVA ALARM CLOCK

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmTime = null;
        String filePath = "src/AlarmClock/rooster alarm _ Sound Effect.wav";

       while(alarmTime == null){
           try{
               System.out.print("Enter alarm time: ");
               String inputTime = scanner.nextLine();

               alarmTime = LocalTime.parse(inputTime, formatter);
               System.out.print("Alarm set for " + alarmTime);
           }
           catch (DateTimeParseException e){
               System.out.println("Invalid time format. Please use HH:MM:SS");
           }

       }

        AlarmClock alarmClock = new AlarmClock(alarmTime, filePath, scanner);
        Thread alarmClockThread = new Thread(alarmClock);
        alarmClockThread.start();

    }

}


