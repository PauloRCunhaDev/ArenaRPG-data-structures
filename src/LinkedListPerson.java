public class LinkedListPerson {
    //Adicionei uma nova lista para os personagens com as adaptações pro tipo de variavel recebida(Personagem)
    private No head;
    private No tail;

    public LinkedListPerson(){
        this.head = null;
        this.tail = null;
    }

    public void inserirHead(Personagem personagem){
        No novo = new No(personagem);

        if(this.head == null){
            this.head = novo;
            return;
        }
        novo.setNext(this.head);
        this.head = novo;
    }

    public void inserirTail(Personagem personagem){
        No novo = new No(personagem);

        if(this.tail == null){
            this.tail = novo;
            return;
        }
        this.tail.setNext(novo);
        this.tail = novo;
    }

    public void removerHead(){
        if(this.head == null){
            System.out.println("\nA lista de personagens esta vazia!\n");
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
            System.out.println("\nA lista de personagens esta vazia!\n");
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
        System.out.println("\nINICIO LISTA PERSONAGENS:");
        while(current != null){
            System.out.println("Nome: " + current.getPersonagem().getNome());
            System.out.println("Nivel: " + current.getPersonagem().getNivel());
            System.out.println("ID: " + current.getPersonagem().getIdPersonagem());
            current = current.getNext();
        }
        System.out.println("\nFIM LISTA PERSONAGENS:");
    }

    public void printListaTail(){
        No current = this.tail;
        System.out.println("\nINICIO LISTA PERSONAGENS:");
        while(current != null){
            System.out.println("Nome: " + current.getPersonagem().getNome());
            System.out.println("Nivel: " + current.getPersonagem().getNivel());
            System.out.println("ID: " + current.getPersonagem().getIdPersonagem());
            current = current.getPrev();
        }
        System.out.println("\nFIM LISTA PERSONAGENS:");
    }

    public void adicionar(Personagem personagem, int posicao){
        No novo = new No(personagem);

        if(this.head == null || posicao < 0){
            inserirHead(personagem);
        }else{
            No current = this.head;
            int contador = 1;

            while(current != null && contador < posicao){
                current = current.getNext();
                contador++;
            }

            if(current == null){
                inserirTail(personagem);
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
            System.out.println("\nA lista de personagens esta vazia!\n");
            return;
        }

        if(posicao <= 0){
            removerHead();
        }else{
            No current = this.head;
            int contador = 1;

            while(current != null && contador < posicao){
                current = current.getNext();
                contador++;
            }

            if(current == null){
                removerTail();
            }else{
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
                current.setNext(null);
                current.setPrev(null);
            }
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
