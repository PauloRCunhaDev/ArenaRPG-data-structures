

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
    private Personagem provocador = null;
    int defesaAntes = defesa;

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
    
    

    private void adicionarHabilidadesMago() {
        habilidades.inserirTail(new Habilidade("m01", "Bola de Fogo", 20, "Causa dano mágico", 60, "dano"));
        habilidades.inserirTail(new Habilidade("m02", "Cura", 25, "Restaura vida", 20, "cura"));
        habilidades.inserirTail(new Habilidade("m03", "Barreira Mágica", 15, "Reduz dano recebido", 10, "defesa"));
    }

    private void adicionarHabilidadesGuerreiro() {
        habilidades.inserirTail(new Habilidade("g01", "Golpe Poderoso", 10, "Dano físico alto", 40, "dano"));
        habilidades.inserirTail(new Habilidade("g02", "Defesa Total", 15, "Aumenta defesa", 50, "defesa"));
        habilidades.inserirTail(new Habilidade("g03", "Provocação", 5, "Atrai inimigos", 0, "provocar"));
    }

    private void adicionarHabilidadesArqueiro() {
        habilidades.inserirTail(new Habilidade("a01", "Tiro Preciso", 10, "Dano crítico", 50, "dano"));
        habilidades.inserirTail(new Habilidade("a02", "Chuva de Flechas", 20, "Dano em área", 30, "dano"));
        habilidades.inserirTail(new Habilidade("a03", "Fuga Ágil", 5, "Aumenta evasão", 10, "defesa"));
    }

    private void adicionarHabilidadesAssassino() {
        habilidades.inserirTail(new Habilidade("as01", "Ataque Furtivo", 15, "Dano alto por trás", 50, "dano"));
        habilidades.inserirTail(new Habilidade("as02", "Investida", 10, "Dano direto", 40, "dano"));
        habilidades.inserirTail(new Habilidade("as03", "Invisibilidade", 30, "Fica invisível", 0, "defesa"));
    }

    private void adicionarHabilidadesTank() {
        habilidades.inserirTail(new Habilidade("t01", "Defesa Fortificada", 10, "Aumenta defesa drasticamente", 30, "defesa"));
        habilidades.inserirTail(new Habilidade("t02", "Bloqueio agressivo", 5, "Causa dano com seu escudo", 30, "dano"));
        habilidades.inserirTail(new Habilidade("t03", "Grito de Guerra", 10, "Reduz ataque inimigo", 10, "provocar"));
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
        habilidade.escolherEfeito(this, habilidade, alvo);
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

    public void resetBattle() {
        vidaAtual = vidaMaxima;
        manaAtual = manaMaxima;
        defesa = defesaAntes;
    }

    public boolean estaVivo(){
        return vidaAtual > 0;
    }

    public void normalizarDefesa () {
        this.defesa = defesaAntes;
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

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = vidaAtual;
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

    public Personagem getProvocador() {
        return provocador;
    }

    public void setProvocador(Personagem provocador) {
        this.provocador = provocador;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

}