package Basic;
import java.util.*;

public class TaskApplication 
{
    public static void main(String args[])
    {
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        while (true) 
        {
            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) 
            {
                case 1:
                    taskList.addTask(getTaskName(scanner));
                    break;
                case 2:
                    if (!taskList.isEmpty()) 
                    {
                        taskList.listTasks();
                        int taskNumber = getUserInput(scanner, "Enter the task number to remove: ");
                        if (taskList.isValidTaskNumber(taskNumber))
                        {
                            taskList.removeTask(taskNumber);
                        } 
                        else 
                        {
                            System.out.println("Invalid task number.");
                        }
                    } 
                    else 
                    {
                        System.out.println("No tasks to remove.");
                    }
                    break;
                case 3:
                    if (!taskList.isEmpty()) 
                    {
                        taskList.listTasks();
                    } 
                    else 
                    {
                        System.out.println("No tasks to list.");
                    }
                    break;
                case 4:
                    searchTaskByName(scanner, taskList);
                    break;
                case 5:
                    if (!taskList.isEmpty()) 
                    {
                        taskList.listTasks();
                        int taskNumber = getUserInput(scanner, "Enter the task number to update: ");
                        if (taskList.isValidTaskNumber(taskNumber))
                        {
                            String newTaskName = getTaskName(scanner);
                            taskList.updateTask(taskNumber, newTaskName);
                        } 
                        else 
                        {
                            System.out.println("Invalid task number.");
                        }
                    } 
                    else 
                    {
                        System.out.println("No tasks to update.");
                    }
                    break;
                case 6:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayMenu()
    {
        System.out.println("------------------------");
        System.out.println("Task Application System");
        System.out.println("------------------------");
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. List Tasks");
        System.out.println("4. Search Task by Name");
        System.out.println("5. Update Task");
        System.out.println("6. Quit");
        System.out.print("Select an option to perform: ");
    }

    private static int getUserChoice(Scanner scanner) 
    {
        return scanner.nextInt();
    }

    private static String getTaskName(Scanner scanner) 
    {
        System.out.print("Enter task name: ");
        return scanner.next();
    }

    private static int getUserInput(Scanner scanner, String prompt)
    {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    private static void searchTaskByName(Scanner scanner, TaskList taskList)
    {
        System.out.print("Enter task name to search: ");
        String searchName = scanner.next();
        ArrayList<String> foundTasks = taskList.searchTaskByName(searchName);
        if (foundTasks.isEmpty())
        {
            System.out.println("No tasks found with the given name.");
        }
        else
        {
            System.out.println("Tasks found with the given name:");
            for (String task : foundTasks)
            {
                System.out.println(task);
            }
        }
    }
}

class TaskList 
{
    private ArrayList<String> tasks = new ArrayList<>();

    public void addTask(String name) 
    {
        tasks.add(name);
        System.out.println("Task added.");
    }

    public void removeTask(int taskNumber) 
    {
        tasks.remove(taskNumber - 1);
        System.out.println("Task removed.");
    }

    public void listTasks() 
    {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public boolean isEmpty() 
    {
        return tasks.isEmpty();
    }

    public boolean isValidTaskNumber(int taskNumber) 
    {
        return taskNumber >= 1 && taskNumber <= tasks.size();
    }

    public void updateTask(int taskNumber, String newTaskName)
    {
        tasks.set(taskNumber - 1, newTaskName);
        System.out.println("Task updated.");
    }

    public ArrayList<String> searchTaskByName(String searchName)
    {
        ArrayList<String> foundTasks = new ArrayList<>();
        for (String task : tasks)
        {
            if (task.equalsIgnoreCase(searchName))
            {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
