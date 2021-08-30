public class Stack<T> {
    Node<T> firstNode;
    Node<T> lastNode;
    private static final int max=20;
    Stack(){}

    public boolean isEmpty(){
        return firstNode==null;
    }
    public boolean isFull(){
        return Size()==max;
    }
    public int Size(){
        if(firstNode==null){
            return 0;
        }
        int size=0;
        Node<T> current=firstNode;
        while (current!=null){
            current=current.nextNode;
            size++;
        }
        return size;
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
    public T top(){
        return firstNode.data;
    }
}
