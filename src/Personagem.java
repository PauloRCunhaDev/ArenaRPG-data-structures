

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
    private int defesa = 0;

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
                this.defesa = 10;
                adicionarHabilidadesMago();
            }
            case "guerreiro" -> {
                this.vidaMaxima = 200;
                this.manaMaxima = 60;
                this.agilidade = 80;
                this.defesa = 15;
                adicionarHabilidadesGuerreiro();
            }
            case "arqueiro" -> {
                this.vidaMaxima = 150;
                this.manaMaxima = 75;
                this.agilidade = 100;
                this.defesa = 10;
                adicionarHabilidadesArqueiro();
            }
            case "assassino" -> {
                this.vidaMaxima = 100;
                this.manaMaxima = 100;
                this.agilidade = 120;
                this.defesa = 15;
                adicionarHabilidadesAssassino();
            }
            case "tank" -> {
                this.vidaMaxima = 250;
                this.manaMaxima = 50;
                this.agilidade = 40;
                this.defesa = 20;
                adicionarHabilidadesTank();
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

    private void adicionarHabilidadesMago() {
        habilidades.inserirTail(new Habilidade("m01", "Bola de Fogo", 20, "Causa dano mágico"));
        habilidades.inserirTail(new Habilidade("m02", "Cura", 25, "Restaura vida"));
        habilidades.inserirTail(new Habilidade("m03", "Barreira Mágica", 15, "Reduz dano recebido"));
    }

    private void adicionarHabilidadesGuerreiro() {
        habilidades.inserirTail(new Habilidade("g01", "Golpe Poderoso", 10, "Dano físico alto"));
        habilidades.inserirTail(new Habilidade("g02", "Defesa Total", 15, "Aumenta defesa"));
        habilidades.inserirTail(new Habilidade("g03", "Provocação", 5, "Atrai inimigos"));
    }

    private void adicionarHabilidadesArqueiro() {
        habilidades.inserirTail(new Habilidade("a01", "Tiro Preciso", 10, "Dano crítico"));
        habilidades.inserirTail(new Habilidade("a02", "Chuva de Flechas", 20, "Dano em área"));
        habilidades.inserirTail(new Habilidade("a03", "Fuga Ágil", 5, "Aumenta evasão"));
    }

    private void adicionarHabilidadesAssassino() {
        habilidades.inserirTail(new Habilidade("as01", "Ataque Furtivo", 15, "Dano alto por trás"));
        habilidades.inserirTail(new Habilidade("as02", "Veneno", 10, "Dano contínuo"));
        habilidades.inserirTail(new Habilidade("as03", "Invisibilidade", 30, "Fica invisível"));
    }

    private void adicionarHabilidadesTank() {
        habilidades.inserirTail(new Habilidade("t01", "Defesa Fortificada", 10, "Aumenta defesa drasticamente"));
        habilidades.inserirTail(new Habilidade("t02", "Agarrão", 5, "Imobiliza inimigo"));
        habilidades.inserirTail(new Habilidade("t03", "Grito de Guerra", 10, "Reduz ataque inimigo"));
    }

    public void usarHabilidade(String idHabilidade, Personagem alvo) {
        Habilidade habilidade = buscarHabilidade(idHabilidade);
        
        if (habilidade == null) {
            System.out.println("Habilidade não encontrada!");
            return;
        }
        
        if (this.manaAtual < habilidade.getCustoMana()) {
            System.out.println("Mana insuficiente!");
            return;
        }
        
        if (!estaVivo()) {
            System.out.println("Personagem não está vivo para usar habilidades!");
            return;
        }
        
        this.manaAtual -= habilidade.getCustoMana();
        System.out.println(this.nome + " usou " + habilidade.getNome() + " em " + alvo.getNome());
        // Aqui você implementaria o efeito real da habilidade
    }

    private Habilidade buscarHabilidade(String idHabilidade) {
        No current = habilidades.getHead();
        while (current != null) {
            if (current.getHabilidade().getId().equals(idHabilidade)) {
                return current.getHabilidade();
            }
            current = current.getNext();
        }
        return null;
    }

    public void mostrarHabilidades() {
        System.out.println("\nHabilidades de " + this.nome + " (" + this.classe + "):");
        No current = habilidades.getHead();
        while (current != null) {
            Habilidade h = current.getHabilidade();
            System.out.println("- " + h.getId() + ": " + h.getNome() + " (Custo: " + h.getCustoMana() + " mana) - " + h.getEfeito());
            current = current.getNext();
        }
    }

    public void curar(int valor){
        if(estaVivo()){
            System.out.println("\nImpossivel receber dano, o personagem nao esta mais vivo!");
        }else if(this.vidaAtual == this.vidaMaxima){
            System.out.println("\nImpossivel curar, o personagem esta com a maxima vida possivel!");
        }else if(valor > this.vidaMaxima){
            this.vidaAtual = this.vidaMaxima;
        }else{
            this.vidaAtual = this.vidaAtual + valor;
        }
    }

    public boolean estaVivo(){
        return vidaAtual > 0;
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