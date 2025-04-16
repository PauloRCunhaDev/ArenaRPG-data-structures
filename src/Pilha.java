public class Pilha {
    No head;
    No tail;
    int size;

    public Pilha(int size){
        this.head = null;
        this.tail = null;
        this.size = size;
    }

    public void push(Personagem personagem){
        No newNo = new No(personagem);

        if(head == null){
            this.head = newNo;
            this.tail = newNo;
        } else {
            if(isFull()){
                System.out.println("Pilha is full");
            } else {
                newNo.setNext(head);
                this.head.setPrev(newNo);
                this.head = newNo;
            }
        }
    }

    public void pop(){
        if(head == null){
            System.out.println("No have cell in Pilha");
        } else if(head == tail){
            this.head = null;
            this.tail = null;
        } else {
            No temp = head;
            this.head = head.getNext();
            this.head.setPrev(null);
            temp.setNext(null);
        }
    }

    public void peek(){
        if(head == null){
            System.out.println("No have cell in Pilha");
        } else {
            System.out.println(this.head.getData());
        }
    }

    public boolean isEmpty(){
        return head == null;
    }

    public boolean isFull(){
        No corrent = head;
        int contador = 0;

        while(corrent != null){
            contador++;
            corrent = corrent.getNext();
        }

        return contador == size;
    }

    public No getHead() {
        return head;
    }

    public void setHead(No head) {
        this.head = head;
    }

    public No getTail() {
        return tail;
    }

    public void setTail(No tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
