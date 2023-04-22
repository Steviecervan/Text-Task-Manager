import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String userInput = "";
        String[] userCommand = null;
        TaskManager currentTaskManager = null;
        String currentTaskManagerName = "null";

        do{
            System.out.print(currentTaskManagerName + " > ");
            userInput = input.nextLine();
            userCommand = userInput.split(" ");
            
            switch(userCommand[0].toLowerCase()){
                case "view":
                    if(currentTaskManager.equals(null)){
                        System.out.println("\n~ Task manager not loaded \n");
                    }else{
                        currentTaskManager.displayTaskList();
                    }
                    break;
                case "add":
                    if(currentTaskManager.equals(null)){
                        System.out.println("\n~ Task manager not loaded \n");
                    }else{
                        int nameLen = userCommand[1].length();
                        int notesLen = userCommand[5].length();
                        
                        if(nameLen > 24){
                            System.out.println("~ Invalid input: task name > 24 chars");
                        }else{
                            if(notesLen > 49){
                                System.out.println("~ Invalid input: task notes > 49 chars");
                            }else{
                                int noteStarts = userCommand[0].length() + userCommand[1].length() + userCommand[2].length() + userCommand[3].length() + userCommand[4].length() + 5;
                                currentTaskManager.addTask(new Task(userCommand[1], Integer.parseInt(userCommand[2]), Integer.parseInt(userCommand[3]), Integer.parseInt(userCommand[4]), userInput.substring(noteStarts, userInput.length()), currentTaskManager.getTaskListLen()+1));       
                            }
                        }
                    }
                    break;
                case "mk":
                    currentTaskManager = new TaskManager(userCommand[1]);
                    currentTaskManagerName = currentTaskManager.getTaskListName();
                    break;
                case "remove":
                    if(currentTaskManager.equals(null)){
                        System.out.println("\n~ Task manager not loaded \n");
                    }else{
                        currentTaskManager.removeTask(Integer.parseInt(userCommand[1]));
                    }
                    break;
                case "help":
                    System.out.println("");
                    System.out.println("List of commands");
                    System.out.println("");
                    System.out.println("\tMK - [mk taskManagerName] \n \t\tmakes a new task manager with the given name\n");
                    System.out.println("\tVIEW \n \t\tdisplays the current taskManager\n");
                    System.out.println("\tADD - [add taskName(string) urgency(integer) monthDue(integer) dayDue(integer) notes(string)] \n\t\tcreates and adds a new task to the current task manager\n");
                    System.out.println("\tREMOVE - [remove task#(integer)] \n \t\tremoves the task number given from the current task manager\n");
                    System.out.println("\tEDIT - [edit task#(integer) sectionToEdit(string) updatedText(string)] \n \t\tedits the section of the task number selected with the updated text\n\n\t\tsections commands: [name][urgency][due_date mm/dd][additional_notes]\n");
                    System.out.println("\tLOAD - [LOAD fileName(string)] \n\t\tloads a saved task manager from system\n");
                    System.out.println("\tSAVE - [SAVE fileName(string)] \n\t\tsaves the current task manager to system with given file name\n");
                    System.out.println("\tQUIT \n \t\texit the program\n");
                    System.out.println("");
                    break;
                case "edit":
                    if(currentTaskManager.equals(null)){
                        System.out.println("\n~ Task manager not loaded \n");
                    }else{
                        if(currentTaskManager.getTaskListLen() == 0){
                            System.out.println("~ Invalid input : no tasks exist in the current task manager");
                        }else{
                            currentTaskManager.editTask(Integer.parseInt(userCommand[1]), userCommand[2], userCommand[3]);
                        }
                    }
                    break;
                case "load":
                    String fileName = userCommand[1];
                    try{
                        FileInputStream fileInputStream = new FileInputStream(fileName);
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                        TaskManager taskManagerObj = null;
                        
                        taskManagerObj = (TaskManager)objectInputStream.readObject();
                        currentTaskManager = taskManagerObj;
                        currentTaskManagerName = currentTaskManager.getTaskListName();

                        objectInputStream.close();
                    }catch(FileNotFoundException e){
                        System.out.println("~ Invalid file : file not found");
                    }catch(IOException e){
                        System.out.println("~ Invalid input/output");
                    }


                    break;
                case "save":
                    String outFileName = userCommand[1];
                    FileOutputStream fileOutputStream = new FileOutputStream(outFileName);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                    try{
                        TaskManager newTM = currentTaskManager;
                        newTM.setTaskListName(outFileName);
                        
                        objectOutputStream.writeObject(newTM);
                        objectOutputStream.close();
                    }catch(NotSerializableException e){
                        System.out.println("~ Invalid Object : not serializable");
                    }catch(IOException e){
                        System.out.println("~ Invalid input/output");
                    }

                    break;
                case "quit":
                    break;
                default:
                    System.out.println("~ Invalid command");
            }
        }while(!userCommand[0].equals("quit"));
        input.close();
        System.exit(0);
    }
}
