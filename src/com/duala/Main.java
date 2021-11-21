package com.duala;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true){

            runScript();
            System.out.println("\n\n Next duration");
        }




    }


    public  static void runScript(){
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
    public static Boolean isBadFormat(String entry){

        //state should be true if the format is acceptable
        boolean state =  entry.contains(":") &&
                entry.split(":").length == 2 && // checking if it contains minutes separated by hours
                entry.toLowerCase() == entry.toUpperCase() &&// returns true if it does not contain alphabets
                !entry.contains(",./*-+()!@#$%^&-_\" \\';`~?><{}[]=") && //does not contain symbols and spaces
                Integer.parseInt(entry.split(":")[0]) < 24 && //hour should be less than 24 (not checking negative because of symbol check)
                Integer.parseInt(entry.split(":")[1]) < 60 //minute count can't be more than 60 (not checking negative because of symbol check)
                ;

        state = !state;

        if (state){
            System.out.println("\nERROR: Wrong time format detected");
        }

        return state;
    }

    //Gets the users input as a string in the format HH:mm
    public static String getUserInput(String period){
        Scanner input = new Scanner(System.in);


        String userInput = "";
        System.out.println( String.format("Enter the %s in the format HH:mm ",period));
        System.out.print("Eg:2:15 or 17:50 >>");
        userInput= input.nextLine().trim();
        if (isBadFormat(userInput)){
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
        if (startTime > endTime){ //doing this because the end time might be in the next day
            endTime += 24*60; //adding 24 hours
        }
        double top = endTime - startTime;
        double ratio = top / 60;

        return ratio;
    }


    public static void test_isBadFormation(){

        String[] testCases = {"22:00", "22", "22 50", "", "44:23"};

        for (String test :
                testCases) {
            System.out.println(isBadFormat(test));
        }
    }
}
