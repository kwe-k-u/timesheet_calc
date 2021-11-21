package com.duala;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String startTimeStr = getUserInput("start time");
        String endTimeStr = getUserInput("end time");
        System.out.println(startTimeStr);
        System.out.println(endTimeStr);




    }

    //Gets the users input as a string in the format HH:mm
    public static String getUserInput(String period){
        Scanner input = new Scanner(System.in);


        String userInput = "";
        System.out.println( String.format("Enter the %s in the format HH:mm ",period));
        System.out.print("Eg:2:15 or 17:50 >>");
        userInput= input.nextLine();

        return userInput;
    }



    //convert time into minutes
    public int timeToMinutes(){
        int duration = 0;


        return duration;
    }

    //find difference between times

    //find the ration of the times
}
