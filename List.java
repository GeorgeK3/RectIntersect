public class List<T> {
    private class Node<T>{
        protected T item;
        protected Node next;
        Node(T item){
            this.item = item;
            next = null;
        }
        Node<T> getNext(){
            return next;
        }
        void setNext(Node<T> n){
            next = n;
        }
    }
    Node head;
    List(Node h){
        head = h;
    }
    List(){
        head = null;
    }

    public void insert (T t){
        Node<T> n = new Node<>(t);
        if (head==null){
            head = n;
            return;
        }
        Node<T> temp = head;
        while(temp != null){
            if (temp.getNext()==null){
                temp.setNext(n);
                return ;
            }
            temp=temp.next;
        }

    }

    public void erase (){
        head.next=null;
        head=null;
    }

    public String toString(){
        String s="";
        Node<T> temp = head;
        while(temp != null){
           s+=temp.item.toString();
           s+=" ";
           temp=temp.next;
        }
        return s;
    }
}
