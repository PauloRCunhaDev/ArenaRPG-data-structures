public class LinkedListClasse {
        No head, tail;
    
        public LinkedListClasse(){
            this.head = null;
            this.tail = null;
        }
    
        public void insertHead(String data){
            No newNo = new No(data);
    
            if(head == null){
                this.head = newNo;
                this.tail = newNo;
    
            } else {
                newNo.setNext(this.head);
                this.head.setPrev(head);
                this.head = newNo;
            }
        }
    
        public void insertTail(String data){
    
            No newNo = new No(data);
    
            if(head == null){
                this.head = newNo;
                this.tail = newNo;
    
            } else {
                this.tail.setNext(newNo);
                newNo.setPrev(this.tail);
                this.tail = newNo;
            }
        }
    
        public void printHead(){
            No corrent = this.head;
            
            while(corrent != null){
                System.out.println(corrent.getData());
                corrent = corrent.getNext();
            }
        }
    
        public void printTail(){
            No corrent = this.tail;
            
            while(corrent != null){
                System.out.println(corrent.getData());
                corrent = corrent.getPrev();
            }
        }
    
        public void deleteHead(){
            if(head == null){
                System.out.println("No cell has been registered yet");
    
            } else if(head == tail){
                head = null;
                tail = null;
    
            } else {
                No temp = head;
                this.head = this.head.getNext();
                head.setPrev(null);
                temp.setNext(null);
            }
        }
    
        public void deleteTail(){
            if(head == null){
                System.out.println("No cell has been registered yet");
    
            } else if(head == tail){
                this.head = null;
                this.tail = null;
    
            } else {
                No temp = tail;
                this.tail = this.tail.getPrev();
                tail.setNext(null);
                temp.setPrev(null);
            }
        }
    
        public void insert(String data, int pos){
            No newNo = new No(data);
    
            if(pos == 1){
                insertHead(data);
    
            } else {
                No corrent = head;
                int correntPos = 1;
    
                while(corrent != null && correntPos < pos){
                    corrent = corrent.getNext();
                    correntPos++;
                }
    
                if(corrent == null){
                    insertTail(data);
    
                } else {
                    newNo.setNext(corrent);
                    newNo.setPrev(corrent.getPrev());
                    corrent.getPrev().setNext(newNo);
                    corrent.setPrev(newNo);
                }
            }
        }
    
        public void delete(int pos){
    
            if(this.head == null){
                System.out.println("No cell has been registered yet");
            } else if(head == tail){
                if(pos > 1){
                    System.out.println("There is no cell in that position");
                } else {
                    this.head = null;
                    this.tail = null;
                }
    
            } else {
                No corrent = head;
    
                for(int i = 1; i < pos; i++){
                    if(corrent != null){
                        corrent = corrent.getNext();
                    }
                }
    
                if(corrent == null){
                    System.out.println("There is no cell in that position");
                } else if(corrent.getNext() == null){
                    deleteTail();
                } else if(corrent.getPrev() == null){
                    deleteHead();
                } else {
                    corrent.getPrev().setNext(corrent.getNext());
                    corrent.getNext().setPrev(corrent.getPrev());
                    corrent.setNext(null);
                    corrent.setPrev(null);
                }
            }
        }
}
