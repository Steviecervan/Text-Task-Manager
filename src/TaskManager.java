import java.io.Serializable;
import java.time.MonthDay;
import java.util.ArrayList;

public class TaskManager implements Serializable{
    
    private ArrayList<Task> taskList;
    private String taskListName;

    public TaskManager(String taskListName){
        taskList = new ArrayList<Task>();
        this.taskListName = taskListName;
    }

    public void setTaskListName(String newTaskListName){
        taskListName = newTaskListName;
    }

    public String getTaskListName(){
        return taskListName;
    }

    public int getTaskListLen(){
        return taskList.size();
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public void removeTask(int taskNum){
        taskList.remove(taskNum-1);
    }

    public void editTask(int taskIndex, String sectionName, String edit){
        try{
            Task selectedTask = taskList.get(taskIndex);
            if(sectionName.equalsIgnoreCase("name")){
                if(edit.length() > 24){
                    System.out.println("~ Invalid input: task name > 24 chars");
                }else{
                    selectedTask.setTaskName(edit);
                }
            }else if(sectionName.equalsIgnoreCase("urgency")){
                try{
                    selectedTask.setUrgency(Integer.parseInt(edit));
                }catch(NumberFormatException e){
                    System.out.println("~ Invalid input : edit is not an integer");
                }
            }else if(sectionName.equalsIgnoreCase("due_date")){
                try{
                    int dueMonth = Integer.parseInt(edit.substring(0,2));
                    int dueDay = Integer.parseInt(edit.substring(3, 5));

                    selectedTask.setDueDate(dueMonth, dueDay);
                }catch(NumberFormatException e){
                    System.out.println("~ Invalid input : entered date are not integers");
                }
            }else if(sectionName.equalsIgnoreCase("additional_notes")){
                if(edit.length() > 49){
                    System.out.println("~ Invalid input: task notes > 49 chars");
                }else{
                    selectedTask.setAdditionalNotes(edit);
                }
            }else{
                System.out.println("~ Invalid input : non-existent section");
            }
        }catch(NullPointerException e){
            System.out.println("~ Task entered does not exist");
        }
    }

    public void displayTaskList(){
        //Header
        System.out.println();
        System.out.println("|-------|---------------|-------------------------|---------|----------|--------------------------------------------------|");
        System.out.println("| Task  | Date Created  | Assignment Name         | Urgency | Due Date | Additional Notes                                 |");
        System.out.println("|-------|---------------|-------------------------|---------|----------|--------------------------------------------------|");

        //Each Task
        for(int i = 0; i < taskList.size(); i++){
            Task currentTask = taskList.get(i);
            String taskName = currentTask.getTaskName();
            int taskUrgency = currentTask.getUrgency();
            String taskAdditionalNotes = currentTask.getAdditionalNotes();
            String taskDueDate = currentTask.getDueDate();
            MonthDay taskCreationDate = currentTask.getCreationDate();

            System.out.println("|   " + currentTask.getTaskNum() + "   |   " + taskCreationDate + "     | " + taskName + spacerCreator(taskName.length(), 24, " ") + "|    " + taskUrgency + "    |   " + taskDueDate + spacerCreator(taskDueDate.length(), 7, " ") + "| " + taskAdditionalNotes + spacerCreator(taskAdditionalNotes.length(), 49, " ") + "|");
            System.out.println("|-------|---------------|-------------------------|---------|----------|--------------------------------------------------|");
        }

        System.out.println();
    
    }

    private String spacerCreator(int strLen, int maxLen, String charSplitter){
        String finalString = "";
        for(int i = 0; i < (maxLen - strLen); i++){
            finalString += charSplitter;
        }

        return finalString;
    }

}
