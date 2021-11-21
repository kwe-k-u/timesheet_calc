package com.duala;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Get the start and end times
        String startTimeStr = getUserInput("start time");
        String endTimeStr = getUserInput("end time");


        //convert the times to minutes
        int startTime = timeToMinutes(startTimeStr);
        int endTime = timeToMinutes(endTimeStr);

        double result = getRatio(startTime, endTime);

        System.out.println(result);



    }

    //checks if the entered date is of the write format
    public static Boolean isBadFormat(){
        //todo print a message to remind user of format
        return false;
    }

    //Gets the users input as a string in the format HH:mm
    public static String getUserInput(String period){
        Scanner input = new Scanner(System.in);


        String userInput = "";
        System.out.println( String.format("Enter the %s in the format HH:mm ",period));
        System.out.print("Eg:2:15 or 17:50 >>");
        userInput= input.nextLine().trim();
        if (isBadFormat()){
            userInput = getUserInput(period);
        }

        return userInput;
    }



    //convert time into minutes
    public static int timeToMinutes(String time){
        int duration = 0;
        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);

        duration = (hour * 60) + minute;

        return duration;
    }

    //find the ration of the times
    public static double getRatio(int startTime, int endTime){
        double top = endTime - startTime;
        double ratio = top / 60;

        return ratio;
    }
}
