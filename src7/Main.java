import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader parts_txt = new BufferedReader(new FileReader(args[0])); BufferedReader items_txt = new BufferedReader(new FileReader(args[1]));BufferedReader tokens_txt=new BufferedReader(new FileReader(args[2]));BufferedReader tasks_txt= new BufferedReader(new FileReader(args[3])); BufferedWriter output_txt = new BufferedWriter(new FileWriter(args[4]))) {

            ArrayList<Items> itemsArrayList=new ArrayList<>();
            Method.Readitems(items_txt,itemsArrayList);

            ArrayList<Stack<Items>> stacks=new ArrayList<>();
            while (true){
                String partsReadline=parts_txt.readLine();
                if(partsReadline==null){
                    break;
                }
                //Create stack for each Items
                Stack<Items> stack=new Stack<>(partsReadline);
                for (Items item:itemsArrayList) {
                    if(item.getName().equals(partsReadline)){
                        stack.push(item);
                    }
                }
                //Keep the Item's stack in "stacks" array list
                stacks.add(stack);
            }
            //Create priority Queue for tokens
            Queue priorityqueue=new Queue();
            //Read the Tokens txt
            while (true){
                String tokensReadline=tokens_txt.readLine();
                if(tokensReadline==null){
                    break;
                }
                String[] split_readline=tokensReadline.split(" ");
                //Add the tokens in priority Queue
                priorityqueue.enqueue(new Tokens(split_readline[0],split_readline[1],Integer.parseInt(split_readline[2])));
            }
            //Read the tasks and apply them
            while (true){
                String tasksReadline=tasks_txt.readLine();
                if(tasksReadline==null){
                    break;
                }

                String[] split_tasks=tasksReadline.split("\t");
                //BUY task
                if(split_tasks[0].equals("BUY")) {
                    int length = 0;
                    //Apply the task steps in order
                    while (length != split_tasks.length - 1) {
                        String[] task = split_tasks[length + 1].split(",");
                        String part = task[0];
                        int count = Integer.parseInt(task[1]);//
                        priorityqueue.dequeue(part, count);
                        //Decrease the used items in stacks
                        for (Stack<Items> s : stacks) {
                            if (s.name.equals(part)) {
                                for (int i = 0; i < count; i++) {
                                    s.pop();
                                }
                            }
                        }
                        length++;
                    }
                }
                //PUT task
                else if(split_tasks[0].equals("PUT")){
                    int length=0;
                    while (length != split_tasks.length - 1) {
                        String[] task = split_tasks[length + 1].split(",");
                        String part = task[0];
                        //Push the added Items in the stacks
                        for (Stack<Items> s : stacks) {
                            if (s.name.equals(part)) {
                                for (int i = 1; i < task.length; i++) {
                                    s.push(new Items(task[i], part));
                                }
                            }
                        }
                        length++;
                    }
                }
            }
            //Write everything
            for (Stack<Items> s: stacks) {
                output_txt.write(s.print()+"\n");
            }
            output_txt.write(priorityqueue.print());
        }
    }
}
