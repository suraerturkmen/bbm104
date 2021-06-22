public class Stack<T> {
    private Node<T> firstNode;
    private Node<T> lastNode;
    public String name;

    public Stack() {
        this("stack");
    }
    public Stack(String name){
        this.name=name;
    }
    public boolean isEmpty(){
        return firstNode==null;
    }

    public void push(T insertItem){
        if(isEmpty()){
        firstNode=lastNode=new Node<T>(insertItem);
        }
        else{
            firstNode=new Node<T>(insertItem,firstNode);
        }
    }
    public T pop(){
        T removedItem=firstNode.data;
        if(firstNode==lastNode){
            firstNode=lastNode=null;
        }
        else {
            firstNode=firstNode.nextNode;
        }
        return removedItem;
    }

    public String print(){
        if(isEmpty()){
            return name+":"+"\n"+"\n---------------";
        }
        Node<T> current=firstNode;
        StringBuilder print = new StringBuilder();
        print.append(name).append(":\n");
        while (current!=null){
            print.append(((Items) (current.data)).getID()).append("\n");
            current=current.nextNode;
        }
        print.append("---------------");
        return print.toString();
    }
}

