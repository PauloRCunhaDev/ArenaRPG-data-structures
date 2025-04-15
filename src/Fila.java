public class Fila {
    private No head;
    private No tail;

    public Fila() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty(){
        if(this.head == null){
            System.out.println("\nA fila de cartas do jogador esta vazia!\n");
            return true;
        }
        return false;
    }

    public int size(){
        No current = this.head;
        int tamanho = 0;

        while(current != null){
            tamanho++;
            current = current.getNext();
        }

        return tamanho;
    }

    public No peek(){
        return this.head;
    }

    public void add(Personagem personagem){
        No novo = new No(personagem);

        if(this.head == null){
            this.head = novo;
            this.tail = novo;
            return;
        }
        this.tail.setNext(novo);
        this.tail = novo;
    }

    public No remove(){
        if(!isEmpty()){
            No current = this.head;
            if(this.head == this.tail){
                this.head = null;
                this.tail = null;
                return current;
            }
            this.head = this.head.getNext();
            current.setNext(null);
            return current;
        }
        return null;
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
}