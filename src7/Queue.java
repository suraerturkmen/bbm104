public class Queue {
    private Node<Tokens> firstNode;
    private Node<Tokens> lastNode;
    public Queue(){}
    public boolean isEmpty(){
        return firstNode==null;
    }
    public void enqueue(Tokens object){
        if(isEmpty()){
            firstNode=lastNode=new Node<Tokens>(object);
        }
        else{
            Node<Tokens> current=firstNode;
            Node<Tokens> previous=null;
            while (current!=null){
                if(current.data.getCountOfItems()<object.getCountOfItems()){
                    if(previous==null){
                        firstNode=new Node<Tokens>(object);
                        firstNode.nextNode=current;
                    }
                    else{
                        Node<Tokens> newNode=new Node<Tokens>(object);
                        previous.nextNode=newNode;
                        newNode.nextNode=current;
                    }
                    break;
                }
                else if(current==lastNode){
                    Node<Tokens> newNode=new Node<Tokens>(object);
                    lastNode.nextNode=newNode;
                    lastNode=newNode;
                    break;
                }
                previous=current;
                current=current.nextNode;
            }
        }
    }
    public void dequeue(String part,int number){
        Node<Tokens> current=firstNode;
        Node<Tokens> previous=null;
        int decrease;
        while (current!=null){
            if(current.data.getItemPart().equals(part)){
                decrease=current.data.getCountOfItems();
                current.data.setCountOfItems(current.data.getCountOfItems()-number);
                if(previous==null){
                    firstNode=current.nextNode;
                }
                else{
                    previous.nextNode=current.nextNode;
                }
                if(decrease-number>0){
                    enqueue(current.data);
                }
                else if(decrease-number<0){
                    dequeue(part,number-decrease);
                }
                break;
            }
            previous=current;
            current=current.nextNode;
        }
    }

    public String print(){
        if(isEmpty()){
            return "Token Box:"+"\n";
        }else{
            return "Token Box:\n"+printFirst(firstNode);
        }

    }
    public String printFirst(Node<Tokens> current){
        Node<Tokens> next=current.nextNode;
        if(next==null){
            return current.data.getID()+" "+current.data.getItemPart()+" "+current.data.getCountOfItems();
        }
        else{
            return printFirst(current.nextNode)+"\n"+current.data.getID()+" "+current.data.getItemPart()+" "+current.data.getCountOfItems();
        }
    }
}