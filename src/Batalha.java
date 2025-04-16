
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Batalha {
    private static int idBtalha;
    private Jogador jogadorAdversario;
    private int contadorTurnoAtual = 1;
    private boolean estadoBatlha = false;
    private ArrayList<Personagem> exibicaoPersonagens = new ArrayList<>();
    private ArrayList<Personagem> bots = new ArrayList<>();
    private Fila ordemTurnos = new Fila();
    private Pilha colocacaoFinal = new Pilha(1);
    private static Gerenciador gerenciador = new Gerenciador();
    private Scanner sc = new Scanner(System.in);

    public Batalha() {
        Batalha.idBtalha = idBtalha++;
    }

    public static void inicializarGerenciadorBatalha(Gerenciador gerenciador){
        Batalha.gerenciador = gerenciador;
    }

    public boolean verificarPersonagemRepetido(Personagem personagem){
        No current = ordemTurnos.getHead();
        while(current != null){
            if(current.getPersonagem().equals(personagem)){
                System.out.println("\nErro, esse personagem ja foi selecionado para a batalha");
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void limparOrdemTurnos(){
        while(ordemTurnos.getHead() != null){
            ordemTurnos.remove();
        }
    }

    public void ordenarTurnosPorAgilidade(){
        LinkedListPerson ordemMaisRapidos = new LinkedListPerson();
        
        while(ordemTurnos.getHead() != null){
            ordemMaisRapidos.inserirTail(ordemTurnos.remove().getPersonagem());
        }

        No personagemMaisRapido = ordemMaisRapidos.getHead();
        int posicaoPersonagem = 0;
        while(ordemMaisRapidos.getHead() != null){
            int contador = 1;
            No current = ordemMaisRapidos.getHead();
            while(current != null){
                if(current.getPersonagem().getAgilidade() > personagemMaisRapido.getPersonagem().getAgilidade()){
                    personagemMaisRapido = current;
                    posicaoPersonagem = contador;
                }
                contador++;
                current = current.getNext();
            }
            ordemMaisRapidos.remover(posicaoPersonagem);
            ordemTurnos.add(personagemMaisRapido.getPersonagem());
        }
    }

    public void iniciarBatalha(Jogador jogador){
        ordemTurnos = new Fila();

        System.out.println("\n\n\t   SISTEMA PVP");
        System.out.println("\n\nNome do jogador adversario: ");
        String nome = sc.nextLine();

        this.jogadorAdversario = gerenciador.buscarJogador(nome);

        if(jogadorAdversario == null){
            System.out.println("\nNao ha nenhum jogador com esse nome");
        }else{
            System.out.println("\nQuantidade de personagens partcipantes: ");
            int quantidade = sc.nextInt();
            this.colocacaoFinal.setSize(quantidade);

            System.out.println("\nJogador desafiante:\n\n\t");
            for(int i = 1; i <= quantidade; i++){
                Personagem personagemD = jogador.selecionarPersonagem();
                if(personagemD == null || verificarPersonagemRepetido(personagemD)){
                    limparOrdemTurnos();
                    break;
                }
                ordemTurnos.add(personagemD);
                exibicaoPersonagens.add(personagemD);
            }
            
            System.out.println("\nJogador adversario:\n\n\t");
            for(int i = 1; i <= quantidade; i++){
                Personagem personagemA = jogadorAdversario.selecionarPersonagem();
                if(personagemA == null || verificarPersonagemRepetido(personagemA)){
                    limparOrdemTurnos();
                    break;
                }
                ordemTurnos.add(personagemA);
                exibicaoPersonagens.add(personagemA);
            }
            ordenarTurnosPorAgilidade();
            this.estadoBatlha = true;
        }
    }

    public int gerarNivelBot(Personagem personagem){
        return  personagem.getNivel();
    }
    
    public void aumentarAtributosBot(Personagem personagem){
        personagem.setManaMaxima(personagem.getManaMaxima() + personagem.getNivel() * 10);
        personagem.setVidaMaxima(personagem.getManaMaxima() + personagem.getNivel() * 10);
        personagem.setDefesa(personagem.getManaMaxima() + personagem.getNivel() * 10);
        personagem.setAgilidade(personagem.getManaMaxima() + personagem.getNivel() * 10);
    }

    public void iniciarBatalhaBot(Jogador jogador){
        System.out.println("\n\n\t   SISTEMA PVE");
        Random ramdom = new Random();
        ordemTurnos = new Fila();
        Personagem bot;
        int botAleatorio;

        System.out.println("\nQuantidade de personagens partcipantes: ");
        int quantidade = sc.nextInt();
        this.colocacaoFinal.setSize(quantidade);

        System.out.println("\nJogador desafiante:\n\t");
        for(int i = 1; i <= quantidade; i++){
            Personagem personagemD = jogador.selecionarPersonagem();
            if(personagemD == null || verificarPersonagemRepetido(personagemD)){
                limparOrdemTurnos();
                break;
            }
            botAleatorio = ramdom.nextInt(3);
            switch (botAleatorio) {
                case 0 ->{
                    bot = new Personagem("Golem", gerarNivelBot(personagemD));
                }
                case 1 ->{
                    bot = new Personagem("Lobo de Prata", gerarNivelBot(personagemD));
                }
                default ->{
                    bot = new Personagem("Guardião Esqueleto", gerarNivelBot(personagemD));
                }
            }
            aumentarAtributosBot(bot);
            this.bots.add(bot);
            ordemTurnos.add(personagemD);
            exibicaoPersonagens.add(personagemD);
            if(i == quantidade){
                for(Personagem inimigo : this.bots){
                    ordemTurnos.add(inimigo);
                    exibicaoPersonagens.add(inimigo);
                }
            }
        }
        ordenarTurnosPorAgilidade();
        this.estadoBatlha = true;
    }

    public void exibirPersonagensBatalha(){
        ArrayList<Personagem> personagensAliados = new ArrayList<>();
        ArrayList<Personagem> personagensAdversarios = new ArrayList<>();
        int contador = 1;
        for(Personagem personagem : this.exibicaoPersonagens){
            if(contador <= (ordemTurnos.size() / 2)){
                personagensAliados.add(personagem);
            }else{
                personagensAdversarios.add(personagem);
            }
            contador++;
        }
        System.out.println("\t   BATALHA\n\nTURNO " + this.contadorTurnoAtual + ":");
        for(Personagem personagem : personagensAliados){
            System.out.println("\n" + personagem.getNome() + "\t");
            System.out.println("\nID: " + personagem.getIdPersonagem() + "\t");
            System.out.println("\nNivel: " + personagem.getNivel() + "\t");
            System.out.println("\nVida Atual: " + personagem.getVidaAtual() + "\t");
            System.out.println("\nMana Atual: " + personagem.getManaAtual() + "\t");
            System.out.println("\nAgilidade: " + personagem.getAgilidade() + "\t");
        }
        for(Personagem personagem : personagensAdversarios){
            System.out.println("\n" + personagem.getNome() + "\t");
            System.out.println("\nID: " + personagem.getIdPersonagem() + "\t");
            System.out.println("\nNivel: " + personagem.getNivel() + "\t");
            System.out.println("\nVida Atual: " + personagem.getVidaAtual() + "\t");
            System.out.println("\nMana Atual: " + personagem.getManaAtual() + "\t");
            System.out.println("\nAgilidade: " + personagem.getAgilidade() + "\t");
        }
    }

    public void atualizarExibicaoPersonagens(Personagem personagem){
        this.exibicaoPersonagens.remove(personagem);
    }

    public void empilharPersonagemParaColocacao(Personagem personagem){
        this.colocacaoFinal.push(personagem);
    }

    public void removerPersonagensDerrotados(Personagem personagemAlvo, Jogador desafiante, Jogador adversario){
        Fila filaAuxPersonagens = new Fila();

        No current = this.ordemTurnos.getHead();
        while(current != null){
            if(current.getPersonagem().equals(personagemAlvo)){
                this.ordemTurnos.remove();
                atualizarExibicaoPersonagens(current.getPersonagem());
                if(desafiante.ePersonagem(personagemAlvo)){
                    desafiante.receberXP(10);
                }else{
                    adversario.receberXP(10);
                }
            }else{
                filaAuxPersonagens.add(this.ordemTurnos.remove().getPersonagem());
            }
            current = current.getNext();
        }
        current = filaAuxPersonagens.getHead();
        while(current != null){
            this.ordemTurnos.add(current.getPersonagem());
            current = current.getNext();
            filaAuxPersonagens.remove();
        }
    }

    public void removerPersonagensDerrotadosEBots(Personagem personagemAlvo, Jogador desafiante){
        Fila filaAuxPersonagens = new Fila();
    
        No current = this.ordemTurnos.getHead();
        while(current != null){
            if(current.getPersonagem().equals(personagemAlvo)){
                if(eBot(current.getPersonagem())){
                    this.bots.remove(personagemAlvo);
                }
                this.ordemTurnos.remove();
                atualizarExibicaoPersonagens(personagemAlvo);
            }else{
                filaAuxPersonagens.add(this.ordemTurnos.remove().getPersonagem());
            }
            current = current.getNext();
        }

        current = filaAuxPersonagens.getHead();
        while(current != null){
            this.ordemTurnos.add(current.getPersonagem());
            current = current.getNext();
            filaAuxPersonagens.remove();
        }
    }

    public boolean verificarJogadorVencedor(Jogador desafiante){
        ArrayList<Personagem> personagensAliados = new ArrayList<>();
        ArrayList<Personagem> personagensAdversarios = new ArrayList<>();
        int contador = 1;
        for(Personagem personagem : this.exibicaoPersonagens){
            if(contador <= (ordemTurnos.size() / 2)){
                personagensAliados.add(personagem);
            }else{
                personagensAdversarios.add(personagem);
            }
            contador++;
        }
        if(personagensAliados.isEmpty()){
            while(!personagensAdversarios.isEmpty()){
                for(Personagem personagem : personagensAdversarios){
                    empilharPersonagemParaColocacao(personagem);
                    personagensAdversarios.remove(personagem);
                }
            }
            System.out.println("\n\nPARTIDA ENCERRADA: O JOGADOR " + desafiante.getNome() + " VENCEU!");
            return true;
        }
        if(personagensAdversarios.isEmpty()){
            while(!personagensAliados.isEmpty()){
                for(Personagem personagem : personagensAliados){
                    empilharPersonagemParaColocacao(personagem);
                    personagensAliados.remove(personagem);
                }
            }
            System.out.println("\n\nPARTIDA ENCERRADA: O JOGADOR DESAFIANTE VENCEU!");
            return true;
        }
        this.estadoBatlha = false;
        return false;
    }

    public void executarTurno(Jogador jogador){
        Personagem personagem = ordemTurnos.remove().getPersonagem();
        Personagem personagemAlvo = null;

        exibirPersonagensBatalha();

        System.out.println("\n\nPERSONAGEM ATACANTE: " + personagem.getNome());

        while(personagemAlvo == null || !personagemAlvo.estaVivo()){
            System.out.println("\nPERSONAGEM ALVO: ");
            personagemAlvo = jogadorAdversario.selecionarPersonagem();

            if(personagemAlvo == null){
                System.out.println("\nO personagem escolhido nao esta no time adversario, escolha novamente");
            }
        }

        if(!personagemAlvo.estaVivo()){
            removerPersonagensDerrotados(personagemAlvo, jogador, this.jogadorAdversario);
        }

        ordemTurnos.add(personagem);

        //implementar sistema de ataque com descricao do que foi feito e atualizacao dos status de cada personagem
    }

    public Personagem selecionarBot(){
        System.out.println("Digite o ID do bot: ");
        int id = sc.nextInt();

        for(Personagem bot : this.bots){
            if(bot.getIdPersonagem() == id){
                return bot;
            }
        }
        return null;
    }

    public Personagem selecaoBotAdversario(){
        if(exibicaoPersonagens.isEmpty()){
            return null;
        }
        ArrayList<Personagem> personagensAliados = new ArrayList<>();
        int metade = (exibicaoPersonagens.size() / 2);

        for(int i = 0; i < metade; i++){
            Personagem p = exibicaoPersonagens.get(i);
            if(p.estaVivo()){
                personagensAliados.add(p);
            }
        }

        if(personagensAliados.isEmpty()){
            return null;
        }

        Random ramdom = new Random();
        return personagensAliados.get(ramdom.nextInt(personagensAliados.size()));
    }

    public boolean eBot(Personagem personagem){
        for(Personagem bot : this.bots){
            if(bot.getIdPersonagem() == personagem.getIdPersonagem()){
                return true;
            }
        }
        return false;
    }

    public void executarTurnoBot(Jogador jogador){
        Personagem personagem = ordemTurnos.remove().getPersonagem();
        Personagem personagemAlvo = null;

        exibirPersonagensBatalha();

        System.out.println("\n\nPERSONAGEM ATACANTE: " + personagem.getNome());

        if(eBot(personagem)){
            personagemAlvo = selecaoBotAdversario();

            //implementação do ataque do bot
        }else{
            while(personagemAlvo == null || !personagemAlvo.estaVivo()){
                System.out.println("\nPERSONAGEM ALVO: ");
                personagemAlvo = selecionarBot();
    
                if(personagemAlvo == null){
                    System.out.println("\nO monstro escolhido nao esta no time inimigo, escolha novamente");
                }
            }

            //implementação do ataque do jogador
        }

        if(!personagemAlvo.estaVivo()){
            removerPersonagensDerrotadosEBots(personagemAlvo, jogador);
        }

        ordemTurnos.add(personagem);
    }

    public static int getIdBtalha() {
        return idBtalha;
    }

    public static void setIdBtalha(int idBtalha) {
        Batalha.idBtalha = idBtalha;
    }

    public int getContadorTurnoAtual() {
        return contadorTurnoAtual;
    }

    public void setContadorTurnoAtual(int contadorTurnoAtual) {
        this.contadorTurnoAtual = contadorTurnoAtual;
    }

    public boolean isEstadoBatlha() {
        return estadoBatlha;
    }

    public void setEstadoBatlha(boolean estadoBatlha) {
        this.estadoBatlha = estadoBatlha;
    }

    public Fila getOrdemTurnos() {
        return ordemTurnos;
    }

    public void setOrdemTurnos(Fila ordemTurnos) {
        this.ordemTurnos = ordemTurnos;
    }

    public Pilha getColocacaoFinal() {
        return colocacaoFinal;
    }

    public void setColocacaoFinal(Pilha colocacaoFinal) {
        this.colocacaoFinal = colocacaoFinal;
    }

    public Gerenciador getGerenciador() {
        return gerenciador;
    }

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    public ArrayList<Personagem> getExibicaoPersonagens() {
        return exibicaoPersonagens;
    }

    public void setExibicaoPersonagens(ArrayList<Personagem> exibicaoPersonagens) {
        this.exibicaoPersonagens = exibicaoPersonagens;
    }

    public ArrayList<Personagem> getBots() {
        return bots;
    }

    public void setBots(ArrayList<Personagem> bots) {
        this.bots = bots;
    }
}