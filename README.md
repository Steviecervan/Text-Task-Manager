Java Text Based Task Manager
  Hello! This is a quick little program I coded in a couple of hours to help me stay organized in my final weeks of my freshman year in college.
  This program is written in pure Java as it is the languaged I learned this school year. It utilizes serialization to save and load tasks to the user's
  pc. Additionally, there are try and catch blocks to ensure the program doesn't crash.
  
 How to use the Task Manager
  When running the program in terminal, you are presented with the line "null > ". Null represents the current Task Manager and anything type in after ">"
  is to control the task manager. 
  
  Gain access to possible commands by typing in HELP.
  
  WIP : Task Manager Command List (Below)
  
  Creating a new Task Manager
    As there is no task manager assigned on the program execution, you will most likely need to make a new Task Manager. To do so, follow these steps:
      1. Type "mk " and the name of the Task Manager. Please avoid spaces in the task manager's name.
        Ex "mk School-Task-Manager"
      2. To ensure you new task Manager has been made, you should now see the name of your Task Manager before the ">" character. Your new task manager has
         been loaded and you can now start performing commands within your Task Manager.
  
  Saving the current Task Manager
    To save the current Task Manager to your system, follow these steps:
      1. Type "save " and the name of the file(Task Manager) you want to save. You saved object can be found in the file containing the code of this project.
        Ex "save School-Task-Manager" 
      2. The name of the file does not have to be the same as the name of the task manager. However, it would be best to do so to avoid confusion.
  
  Loading a pre-existing Task Manager
    Now that you know how to save a Task Manager, let's load it from our system!
      1. type "load " and the name you used for the file.
        Ex "load School-Task-Manager"
