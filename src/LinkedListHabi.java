public class LinkedListHabi {
    private No head;
    private No tail;

    public LinkedListHabi(){
        this.head = null;
        this.tail = null;
    }

    public void inserirHead(Habilidade habilidade){
        No novo = new No(habilidade);

        if(this.head == null){
            this.head = novo;
            this.tail = novo;
            return;
        }
        novo.setNext(this.head);
        this.head.setPrev(novo);
        this.head = novo;
    }

    public void inserirTail(Habilidade habilidade){
        No novo = new No(habilidade);

        if(this.tail == null){
            this.head = novo;
            this.tail = novo;
            return;
        }
        novo.setPrev(this.tail);
        this.tail.setNext(novo);
        this.tail = novo;
    }

    public void removerHead(){
        if(this.head == null){
            System.out.println("\nA lista de habilidades esta vazia!\n");
        }
        if(this.head == this.tail){
            this.head = null;
            this.tail = null;
            return;
        }
        No current = this.head;
        this.head = this.head.getNext();
        current.setNext(null);
    }

    public void removerTail(){
        if(this.tail == null){
            System.out.println("\nA lista de habilidades esta vazia!\n");
        }
        if(this.head == this.tail){
            this.head = null;
            this.tail = null;
            return;
        }
        No current = this.tail;
        this.tail = this.tail.getPrev();
        current.setPrev(null);
    }

    public void prinListaHead(){
        No current = this.head;
        System.out.println("\nINICIO LISTA HABILIDADES:");
        while(current != null){
            System.out.println("\nId: " + current.getHabilidade().getId());
            System.out.println("\nNome: " + current.getHabilidade().getNome());
            System.out.println("\nCusto mana habilidade: " + current.getHabilidade().getCustoMana());
            System.out.println("\nEfeito: " + current.getHabilidade().getEfeito());
            current = current.getNext();
        }
        System.out.println("\nFIM LISTA HABILIDADES:");
    }

    public void printListaTail(){
        No current = this.tail;
        System.out.println("\nINICIO LISTA HABILIDADES:");
        while(current != null){
            System.out.println("\nId: " + current.getHabilidade().getId());
            System.out.println("\nNome: " + current.getHabilidade().getNome());
            System.out.println("\nCusto mana habilidade: " + current.getHabilidade().getCustoMana());
            System.out.println("\nEfeito: " + current.getHabilidade().getEfeito());
            current = current.getPrev();
        }
        System.out.println("\nFIM LISTA HABILIDADES:");
    }

    public void adicionar(Habilidade habilidade, int posicao){
        No novo = new No(habilidade);

        if(this.head == null || posicao < 0){
            inserirHead(habilidade);
        }else{
            No current = this.head;
            int contador = 1;

            while(current != null && contador < posicao){
                current = current.getNext();
                contador++;
            }

            if(current == null){
                inserirTail(habilidade);
            }else{
                novo.setNext(current);
                novo.setPrev(current.getPrev());
                current.getNext().setPrev(novo);
                current.setNext(novo);
            }
        }
    }

    public void remover(int posicao){
        if(this.head == null){
            System.out.println("\nA lista de Habilidades esta vazia!\n");
            return;
        }
  
        if(head == tail){
           if(posicao > 1){
              System.out.println("\nNao existe habilidade nessa posicao");
           }else{
              head = null;
              tail = null;
           }
           return;
        }
  
        No current = head;
  
        for(int i = 1; i < posicao; i++){
           while(current != null){
              current = current.getNext();
           }
        }
  
        if(current == null){
           System.out.println("\nNao existe habilidade nessa posicao");
        }else if(current.getNext() == null){
           removerTail();
        }else if(current.getPrev() == null){
           removerHead();
        }else{
           current.getPrev().setNext(current.getNext());
           current.getNext().setPrev(current.getPrev());
           current.setNext(null);
           current.setPrev(null);
        }
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