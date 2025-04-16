// Source code is decompiled from a .class file using FernFlower decompiler.
public class LinkedListPerson {
    private No head;
    private No tail;
 
    public LinkedListPerson() {
      this.head = null;
      this.tail = null;
    }
 
    public void inserirHead(Personagem personagem){
      No novo = new No(personagem);

      if(this.head == null){
          this.head = novo;
          this.tail = novo;
          return;
      }
      novo.setNext(this.head);
      this.head.setPrev(novo);
      this.head = novo;
  }

  public void inserirTail(Personagem personagem){
      No novo = new No(personagem);

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
          System.out.println("\nA lista de personagens esta vazia!\n");
      }
      if(this.head == this.tail){
          this.head = null;
          this.tail = null;
          return;
      }
      No current = this.head;
      this.head = this.head.getNext();
      this.head.setPrev(null);
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
      this.tail.setNext(null);
      current.setPrev(null);
  }

  public void prinListaHead(){
      No current = this.head;
      System.out.println("\nINICIO LISTA PERSONAGENS:");
      while(current != null){
         System.out.println("\nId: " + current.getPersonagem().getIdPersonagem());
         System.out.println("\nNome: " + current.getPersonagem().getNome());
         System.out.println("\nVida Atual: " + current.getPersonagem().getVidaAtual());
         System.out.println("\nMana Atual: " + current.getPersonagem().getManaAtual());
         System.out.println("\nDefesa: " + current.getPersonagem().getDefesa());
         System.out.println("\nAgilidade: " + current.getPersonagem().getAgilidade());
          current = current.getNext();
      }
      System.out.println("\nFIM LISTA PersonagemS:");
  }

  public void printListaTail(){
      No current = this.tail;
      System.out.println("\nINICIO LISTA PERSONAGENS:");
      while(current != null){
          System.out.println("\nId: " + current.getPersonagem().getIdPersonagem());
          System.out.println("\nNome: " + current.getPersonagem().getNome());
          System.out.println("\nVida Atual: " + current.getPersonagem().getVidaAtual());
          System.out.println("\nMana Atual: " + current.getPersonagem().getManaAtual());
          System.out.println("\nDefesa: " + current.getPersonagem().getDefesa());
          System.out.println("\nAgilidade: " + current.getPersonagem().getAgilidade());
          current = current.getPrev();
      }
      System.out.println("\nFIM LISTA PERSONAGENS:");
  }

  public void adicionar(Personagem Personagem, int posicao){
      if(posicao == 1){
         inserirHead(Personagem);
      }else{
         No novo = new No(Personagem);
         No current = head;
         int index = 1;

         while(current != null && index < posicao){
            current = current.getNext();
            index++;
         }

         if(current == null){
            inserirTail(Personagem);
         }else{
            novo.setNext(current);
            novo.setPrev(current.getNext());
            current.getPrev().setNext(novo);
            current.setPrev(novo);
         }
      }
  }

   public void remover(int posicao){
      if (this.head == null) {
          System.out.println("\nNao ha personagem nessa posicao");
      } else if(posicao == 1){
         removerHead();
      }else{
         No current = head;
         int index = 1;

         while(current != null && index < posicao){
            current = current.getNext();
            index++;
         }

         if(current == null){
            removerTail();
         }else if (current.getNext() == null) {
             removerTail();
         }else if (current.getPrev() == null) {
            removerHead();
        }else{
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
            current.setPrev(null);
            current.setNext(null);
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
 