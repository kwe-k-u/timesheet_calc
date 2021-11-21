package com.duala;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {




        controller();




    }

    public static void controller(){
        int option;
        ArrayList day = new ArrayList<Double>();
        ArrayList week = new ArrayList<Double>();
        boolean run = true;
        while (run){
            Scanner input = new Scanner(System.in);

            System.out.println("CONTROL");
            System.out.println("0 -> Calculate day duration");
            System.out.println("1 -> Calculate week sum (Resets records for day)");
            System.out.println("2 -> Calculate month sum (Resets records for week)");
            System.out.println("9 -> Remove last day calculation");
            System.out.println("99 -> Terminate program");
            System.out.print("Your selection >> ");

            try {
                option = input.nextInt();
//                input.close();
            } catch (Exception e){
                System.out.println("ERROR:: Check your input");
                continue;
            }



            switch (option){
                case 0: //day duration
                    day.add(runScript());
                    break;

                case 1: //week sum
                { //scoping sum variable
                    double sum = calSum(day);
                    System.out.println("Sum for the week is " + sum);
                    week.add(sum);
                    day.clear();
                }
                    break;
                case 2: //month sum
                { //scoping sum variable
                    double sum = calSum(week);
                    System.out.println("Sum for the month is " + sum);
                }
                    break;
                case 9: //removing last week sum
                    if (!day.isEmpty())
                        day.remove(day.size()-1);
                    break;
                case 99:
                    run = false;
                    break;
                default:
                    System.out.println("Unrecognised entry!!");
            }

            System.out.println("\n");
        }
    }


    public  static double runScript(){
        //Get the start and end times
        String startTimeStr = getUserInput("start time");
        String endTimeStr = getUserInput("end time");


        //convert the times to minutes
        int startTime = timeToMinutes(startTimeStr);
        int endTime = timeToMinutes(endTimeStr);

        double result = getRatio(startTime, endTime);

        System.out.println(result);

        return result;

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
        System.out.print("Eg:2:15 or 17:50 >> ");
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



//    finds the sum of the elements in the array
    public static double calSum(ArrayList<Double> list){
        double sum = 0;
        for (double amount :
                list) {
            sum += amount;
        }


        return sum;
    }


    public static void test_isBadFormation(){

        String[] testCases = {"22:00", "22", "22 50", "", "44:23"};

        for (String test :
                testCases) {
            System.out.println(isBadFormat(test));
        }
    }
}
