public class Personagem {
    private static LinkedListHabi habilidades = new LinkedListHabi(); //Lista para as habilidades de cada personagens criado
    private static int idPersonagem = 0;
    private String nome;
    private String classe; //Classe do personagem
    private int agilidade = 0;
    private int nivel = 0;
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
        this.nivel = 1;
        
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

    public Personagem(String nome, int nivel) {
        Personagem.idPersonagem = idPersonagem++;
        this.nome = nome;
        this.nivel = nivel;
        
        //Definição de status por classe
        if(null != classe)switch (nome) {
            case "Golem" -> {
                this.vidaMaxima = 220;
                this.manaMaxima = 75;
                this.agilidade = 25;
                this.defesa = 15;
                if(nivel <= 15){
                    habilidades.inserirTail(new Habilidade("gl01", "Martelo de Pedra", 0, "Dano moderado", 40, "dano"));
                    habilidades.inserirTail(new Habilidade("gl02", "Defesa Ferrea", 30, "Anula um ataque", 500, "defesa"));
                } else {
                    habilidades.inserirTail(new Habilidade("gl01", "Martelo de Pedra", 0, "Dano moderado", 60, "dano"));
                    habilidades.inserirTail(new Habilidade("gl02", "Defesa Ferrea", 30, "Anula um ataque", 500, "defesa"));
                    habilidades.inserirTail(new Habilidade("gl03", "Tremor Terrestre", 45, "Dano alto", 75, "dano"));
                }
            }
            case "Lobo de Prata" -> {
                this.vidaMaxima = 125;
                this.manaMaxima = 90;
                this.agilidade = 90;
                this.defesa = 10;
                if(nivel <= 10){
                    habilidades.inserirTail(new Habilidade("lp01", "Mordida Crescente", 0, "Dano baixo", 35, "dano"));
                } else {
                    habilidades.inserirTail(new Habilidade("lp01", "Mordida Crescente", 0, "Dano baixo", 55, "dano"));
                    habilidades.inserirTail(new Habilidade("lp02", "Eclipse de Prata", 45, "Restaura vida", 30, "cura"));
                }
            }
            case "Guardião Esqueleto" -> {
                this.vidaMaxima = 75;
                this.manaMaxima = 200;
                this.agilidade = 50;
                this.defesa = 5;
                if(nivel <= 15){
                    habilidades.inserirTail(new Habilidade("ge01", "Olhar Temido", 0, "Evasão alta", 20, "defesa"));
                    habilidades.inserirTail(new Habilidade("ge02", "Ataque Especial", 50, "Dano moderado", 30, "dano"));
                } else {
                    habilidades.inserirTail(new Habilidade("ge01", "Olhar Temido", 0, "Evasão alta", 40, "defesa"));
                    habilidades.inserirTail(new Habilidade("ge02", "Piedade Vingativa", 25, "Dano moderado", 45, "dano"));
                    habilidades.inserirTail(new Habilidade("ge02", "Tempo Ruim", 120, "Dano explosivo", 100, "dano"));
                }
            }
        }
    }
    public void desbloquearHabilidade(){
        switch (classe) {
            case "mago" -> {
                adicionarHabilidadesMago();
            }
            case "guerreiro" -> {
                adicionarHabilidadesGuerreiro();
            }
            case "arqueiro" -> {
                adicionarHabilidadesArqueiro();
            }
            case "assassino" -> {
                adicionarHabilidadesAssassino();
            }
            case "tank" -> {
                adicionarHabilidadesTank();
            }
        }
    }
    
    private void adicionarHabilidadesMago() {
        switch (getNivel()) {
            case 1 -> habilidades.inserirTail(new Habilidade("m01", "Choque", 0, "Dano baixo", 30, "dano"));
            case 10 -> habilidades.inserirTail(new Habilidade("m02", "Bola de Fogo", 50, "Dano alto", 60, "dano"));
            case 20 -> habilidades.inserirTail(new Habilidade("m03", "Cura", 75, "Restaura vida", 40, "cura"));
            case 30 -> habilidades.inserirTail(new Habilidade("m04", "Barreira Mágica", 35, "Reduz levemente dano", 10, "defesa"));
            default -> {
            }
        }
    }

    private void adicionarHabilidadesGuerreiro() {
        switch (getNivel()) {
            case 1 -> habilidades.inserirTail(new Habilidade("g01", "Murro", 0, "Dano baixo", 35, "dano"));
            case 10 -> habilidades.inserirTail(new Habilidade("g02", "Golpe Poderoso", 10, "Dano alto", 40, "dano"));
            case 20 -> habilidades.inserirTail(new Habilidade("g03", "Levantar Guarda", 20, "Aumenta drasticamente defesa", 30, "defesa"));
            case 30 -> habilidades.inserirTail(new Habilidade("g04", "Provocação", 15, "Atrai inimigos", 0, "provocar"));
            default -> {
            }
        }
    }

    private void adicionarHabilidadesArqueiro() {
        switch (getNivel()) {
            case 1 -> habilidades.inserirTail(new Habilidade("a01", "Flechada", 0, "Dano baixo", 30, "dano"));
            case 10 -> habilidades.inserirTail(new Habilidade("a02", "Tiro Preciso", 25, "Dano crítico", 50, "dano"));
            case 20 -> habilidades.inserirTail(new Habilidade("a03", "Primeiros Socorros", 20, "Restaura Vida", 25, "cura"));
            case 30 -> habilidades.inserirTail(new Habilidade("a04", "Fuga Ágil", 15, "Aumenta levemente evasão", 15, "defesa"));
            default -> {
            }
        }
    }

    private void adicionarHabilidadesAssassino() {
        switch (getNivel()) {
            case 1 -> habilidades.inserirTail(new Habilidade("as01", "Apunhalar", 0, "Dano baixo", 35, "dano"));
            case 10 -> habilidades.inserirTail(new Habilidade("as02", "Ataque Furtivo", 25, "Dano moderado", 45, "dano"));
            case 20 -> habilidades.inserirTail(new Habilidade("as03", "Investida", 35, "Dano alto", 55, "dano"));
            case 30 -> habilidades.inserirTail(new Habilidade("as04", "Invisibilidade", 45, "Fica invisível", 0, "defesa"));
            default -> {
            }
        }
    }

    private void adicionarHabilidadesTank() {
        switch (getNivel()) {
            case 1 -> habilidades.inserirTail(new Habilidade("t01", "Empurrão", 0, "Dano leve", 30, "dano"));
            case 10 -> habilidades.inserirTail(new Habilidade("t02", "Defesa Fortificada", 15, "Aumenta defesa drasticamente", 30, "defesa"));
            case 20 -> habilidades.inserirTail(new Habilidade("t03", "Bloqueio agressivo", 5, "Dano moderado", 45, "dano"));
            case 30 -> habilidades.inserirTail(new Habilidade("t04", "Grito de Guerra", 10, "Atrai inimigo", 0, "provocar"));
            default -> {
            }
        }
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

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
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

    public void setManaMaxima(int manaMaxima) {
        this.manaMaxima = manaMaxima;
    }

    public void setManaAtual(int manaAtual) {
        this.manaAtual = manaAtual;
    }
}