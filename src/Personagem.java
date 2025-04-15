public class Personagem {
    private static LinkedListHabi habilidades; //Lista para as habilidades de cada personagens criado
    private static int idPersonagem = 0;
    private String nome;
    private String classe; //Classe do personagem
    private int agilidade = 0;
    private int nivel = 1;
    private int vidaMaxima = 0;
    private int vidaAtual = vidaMaxima;
    private int manaMaxima = 0;
    private int manaAtual = manaMaxima;

    public Personagem(String nome, String classe) {
        Personagem.idPersonagem = idPersonagem++;
        this.nome = nome;
        this.classe = classe;
        
        //Definição de status por classe
        if(null != classe)switch (classe) {
            case "mago" -> {
                this.vidaMaxima = 100;
                this.manaMaxima = 250;
                this.agilidade = 60;
                //Colocar habilidade
            }
            case "guerreiro" -> {
                this.vidaMaxima = 200;
                this.manaMaxima = 60;
                this.agilidade = 80;
                //Colocar habilidade
            }
            case "arqueiro" -> {
                this.vidaMaxima = 150;
                this.manaMaxima = 75;
                this.agilidade = 100;
                //Colocar habilidade
            }
            case "assassino" -> {
                this.vidaMaxima = 100;
                this.manaMaxima = 100;
                this.agilidade = 120;
                //Colocar habilidade
            }
            case "tanke" -> {
                this.vidaMaxima = 250;
                this.manaMaxima = 50;
                this.agilidade = 40;
                //Colocar habilidade
            }
        }
    }
    
    public void receberDano(int valor){
        if(estaVivo()){
            this.vidaAtual = this.vidaAtual - valor;
        }else{
            System.out.println("\nImpossivel receber dano, o personagem nao esta mais vivo!");
        }
    }

    public void usarHabilidade(String idHabilidade, Personagem alvo){
        No current = habilidades.getHead();

        while(current != null && !current.getHabilidade().getId().equals(idHabilidade)){
            current = current.getNext();
        }

    }

    public void curar(int valor){
        if(estaVivo()){
            System.out.println("\nImpossivel receber dano, o personagem nao esta mais vivo!");
        }else if(this.vidaAtual == this.vidaMaxima){
            System.out.println("\nImpossivel curar, o personagem esta com a maxima vida possivel!");
        }else{
            this.vidaAtual = this.vidaAtual + valor;
        }
    }

    public boolean estaVivo(){
        return vidaAtual <= 0;
    }

    public void subirNivel(){
        this.nivel++;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public int getManaMaxima() {
        return manaMaxima;
    }

    public int getManaAtual() {
        return manaAtual;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }
}