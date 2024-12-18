package org.example;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Todo {
    static ArrayList<Map<String, Object>> newlist = new ArrayList<>();
    static String title;

    static String fileName = "shubham-db";

//    JSONObject jsonObject = new JSONObject(newlist);



    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        loadDataFromFile();
        System.out.println("Select your action");


        while (true) {

            System.out.println("1. List To-Do");
            System.out.println("2.Create New To-Do");
            System.out.println("3.Update To-Do");
            System.out.println("4.Delete");
            System.out.println("5.Search To-Do List");
            System.out.println("6.Exit");

            String action = userInput.nextLine();

            if (action.equals("6")) {
                break;
            }

            switch (action) {

                case "1":
                    displayTask();
                    break;

                case "2":
                    createTask();
                    break;

                case "3":
                    updateTask();
                    break;

                case "4":
                    deleteTask();
                    break;

                case "5":
                    searchTask();
                    break;


            }
        }

    }

    static void loadDataFromFile() {
        //read from file and save in newlist
        //convert from json string to arraylist
//        newlist = filereader
        //

        try (FileReader reader = new FileReader(fileName + ".txt")) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Map<String, Object>>>() {
            }.getType();
            newlist = gson.fromJson(reader, listType);

            if(newlist == null){
                newlist = new ArrayList<>();
            }
            System.out.println("Successfully loaded data from file.");
//            for (Map<String, Object> task : newlist) {
//                System.out.println(task);


//            int character;
//            while ((character = reader.read()) != -1) {
//                System.out.print((char) character);
//
//            }
        } catch (IOException e) {
            //file create
            // new list initiate with empty
            e.printStackTrace();
        }
    }

    static void saveDatatoFile() {

        //read from file and save in newlist
        //convert from arraylist to json string
//      //save in json file

        try {
            FileWriter writer = new FileWriter(fileName +".txt", false);

            //convert to json string
            Gson gson = new Gson();
            String jsonString = gson.toJson(newlist);
            writer.write(jsonString);

//            writer.write(newlist.toString());
            writer.close();
            System.out.println("Successfully wrote to the Storage file."); }
        catch (IOException e ){
            e.printStackTrace();

        }

    }



//
//    public void start() {
//
//    }

    public static void displayTask() {

        for (int i = 0; i < newlist.size(); i++) {
            System.out.print(i+ ". " +newlist.get(i) + " \n");
        }



//        try (FileReader reader = new FileReader(fileName+".txt"))
//        {
//            int character;
//            while ((character = reader.read()) != -1) {
//                System.out.print((char) character);
//
//
//            }
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }


    }

    public static void createTask() {


            System.out.println("Enter Title of the task");
            title = userInput.nextLine();

            System.out.println("Enter Description of the Task");
            String description = userInput.nextLine();

            System.out.println("Enter Due-date YYYY,MM,DD");
//        String duedate = userInput.nextLine();
            LocalDate today = LocalDate.now();
            System.out.println("Today's Date:" + today);

            int dueyear = userInput.nextInt();


            short duemonth = userInput.nextShort();


            short duedate = userInput.nextShort();
            userInput.nextLine();
            LocalDate specificDate = LocalDate.of(dueyear, duemonth, duedate);
            String newdate = specificDate.toString();
            System.out.println("Due Date " + newdate);


            System.out.println("Status Pending as default");
            String status = "pending";

            Map<String, Object> newtaskw = new HashMap<>();
            newtaskw.put("title", title);
            newtaskw.put("description", description);
            newtaskw.put("duedate", newdate);
            newtaskw.put("status", status);

            newlist.add(newtaskw);

            saveDatatoFile();

//        try {
//            FileWriter writer = new FileWriter(title+".txt");
//
//            //convert to json string
//
//            writer.write(newlist.toString());
//            writer.close();
//            System.out.println("Successfully wrote to the Storage file."); }
//            catch (IOException e ){
//            e.printStackTrace();

//        }

    }

    public static void updateTask() {

        displayTask();
        System.out.println("\n Enter index number you want to update");
        int update = userInput.nextInt();
        userInput.nextLine();

        while (true) {

            System.out.println("1. Update Title");
            System.out.println("2.Update Description");
            System.out.println("3.Update due date");
            System.out.println("4.Update Status");
            System.out.println("5.Exit");

            String action1 = userInput.nextLine();

            if (action1.equals("5")) {
                break;
            }

            switch (action1) {

                case "1":
                    System.out.println("Update Title");

                    Object utitle = userInput.nextLine();
                    newlist.get(update).put("title", utitle);
                    break;

                case "2":
                    System.out.println("Update Description");

                    Object udescription = userInput.nextLine();
                    newlist.get(update).put("description", udescription);
                    break;

                case "3":
                    System.out.println("Update Due Date");
                    String newDueDate = userInput.nextLine();
                    newlist.get(update).put("duedate", newDueDate);
                    break;

                case "4":
                    System.out.println("Update Status \n 1- Completed \n 2- Pending");

                    String ustatus = userInput.nextLine();
                    switch (ustatus) {
                        case "1":
                            newlist.get(update).put("status", "Completed");
                            break;
                        case "2":
                            newlist.get(update).put("status", "Pending");
                            break;
                    }
                    break;
                default:
                    System.out.println("Select properly");


            }

            saveDatatoFile();
//            try {
//                FileWriter writer = new FileWriter(title+".txt");
//                writer.write(newlist.toString());
//                writer.close();
//                System.out.println("Successfully wrote to the Storage file."); }
//            catch (IOException e ){
//                e.printStackTrace();
//
//            }


        }
    }

    public static void deleteTask() {

        displayTask();
        System.out.println("Which task you want to delete");
        int delete = userInput.nextInt();
        userInput.nextLine();

        newlist.remove(delete);
        saveDatatoFile();

    }

    public static void searchTask() {
        boolean found = false;
//        List<Map<String, Object>> filtered = new ArrayList<>();

        System.out.println("Write down the title of the task you want to search");
        String search = userInput.nextLine();
//        filtered = newlist.stream().filter(l -> l.get("title").toString().contains(search)).collect(Collectors.toList());

        for (int pl = 0; pl < newlist.size(); pl++) {
            if (newlist.get(pl).get("title").toString().contains(search)) {
//                System.out.println("Task" + search + " found in" + pl + " index number");
                System.out.println("Task found: " + newlist.get(pl));
                found = true;

//                filtered.add(newlist.get(pl));

            }
        }

        if (found==false){
            System.out.println("Task " + search + " not found.");



        }
    }

}





//class List {
//    ArrayList<Map<String, Object>> newlist = new ArrayList<>();
//
//    public void toadd(Map<String , Object> task){
//        newlist.add(task);
//
//    }
//    public void Listeditem() {
//
//        for (int i = 0; i < newlist.size(); i++) {
//            System.out.print(newlist.get(i) + " /n");
//        }
//    }
//}
//
//class Newtask{
//    public void newtask(){
//        Scanner userInput = new Scanner(System.in);
//
//        System.out.println("Enter Title of the task");
//        String title = useri.nextLine();
//
//        System.out.println("Enter Description of the Task");
//        String description = useri.nextLine();
//
//        System.out.println("Enter Title");
//        String duedate = useri.nextLine();
//
//        System.out.println("Status Pending as default");
//        String status= "pending";
//
//        Map<String, Object> newtaskw = new HashMap<>();
//        newtaskw.put("title", title);
//        newtaskw.put("description", description);
//        newtaskw.put("duedate", duedate);
//        newtaskw.put("status", status);
//
//
//        List l1= new List();
//        l1.toadd(newtaskw);
//
//    }
//}
//
//class Update{
//    public void update(){
//
//    }
//}
