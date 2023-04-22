import java.io.Serializable;
import java.time.MonthDay;


public class Task implements Serializable{
    private String taskName; //25 Characters long
    private int urgency;    //1
    private String additionalNotes; //50
    private String dueDate; //5
    private MonthDay creationDate; //7 or 8 
    private int taskNum;

    public Task(String name, int urgency, int dueMonth, int dueDay, String notes, int taskNum){
        this.taskName = name;
        this.urgency = urgency;
        this.dueDate = dueMonth + "/" + dueDay;
        this.additionalNotes = notes;
        this.creationDate = MonthDay.now();
        this.taskNum = taskNum;
    }

    public int getTaskNum(){
        return taskNum;
    }

    public String getTaskName(){
        return taskName;
    }

    public int getUrgency(){
        return urgency;
    }

    public String getAdditionalNotes(){
        return additionalNotes;
    }

    public String getDueDate(){
        return dueDate;
    }

    public MonthDay getCreationDate(){
        return creationDate;
    }

    public void setTaskName(String newTaskName){
        if(newTaskName.length() < 24){
            this.taskName = newTaskName;
        }else{
            System.out.println("- Invalid input : taskName is > 25 chars");
        }
    }

    public void setDueDate(int newMonth, int newDay){

        this.dueDate = newMonth + "/" + newDay;
    }

    public void setUrgency(int newUrgency){
        this.urgency = newUrgency;
    }

    public void setAdditionalNotes(String newAdditaionlNotes){
        if(additionalNotes.length() == 0){
            this.additionalNotes += newAdditaionlNotes;    
        }else{
            if(newAdditaionlNotes.length() + additionalNotes.length() > 50){
                System.out.println("- Invalid input : additionalNotes is too long. Has to be < 50 chars -");
            }            
        }
    }

    public String toString(){
        String displayString = "";
        String spaceBetweenName = " ";
        String spaceBetweenAdditionalNotes = " ";
        for(int i = 0; i < 25 - taskName.length(); i++){
            spaceBetweenName += " ";
        }
        for(int a = 0; a < 50 - additionalNotes.length(); a++){
            spaceBetweenAdditionalNotes += " ";
        }
        displayString = "   | " + creationDate + " |  " + displayString + taskName + spaceBetweenName + "   |   " + urgency + "   |   " + dueDate + "   |   " + additionalNotes + spaceBetweenAdditionalNotes + "   |";
        return displayString;
    }
}

