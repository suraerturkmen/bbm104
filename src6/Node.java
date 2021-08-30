public class Node<T>{
    T data;
    Node<T> nextNode;

    Node(T object){
        this(object,null);
    }
    Node(T object, Node<T> node){
        data=object;
        nextNode=node;
    }
}

