public class No {
    private Habilidade habilidade;
    private Personagem personagem;
    private String data;
    private No next;
    private No prev;

    public No(Habilidade habilidade) {
        this.habilidade = habilidade;
        this.next = null;
        this.prev = null;
    }

    public No(Personagem personagem){
        this.personagem = personagem;
        this.next = null;
        this.prev = null;
    }

    public No(String data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public No getNext() {
        return next;
    }

    public void setNext(No next) {
        this.next = next;
    }

    public Habilidade getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(Habilidade habilidade) {
        this.habilidade = habilidade;
    }

    public No getPrev() {
        return prev;
    }

    public void setPrev(No prev) {
        this.prev = prev;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}