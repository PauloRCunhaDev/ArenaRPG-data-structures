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
    private Gerenciador gerenciador = new Gerenciador();
    private Scanner sc = new Scanner(System.in);

    public Batalha() {
        Batalha.idBtalha = idBtalha++;
    }

    public boolean verificarPersonagemRepetido(Personagem personagem){
        No current = ordemTurnos.getHead();
        while(current != null){
            if(current.getPersonagem().equals(personagem)){
                System.out.println("\nErro, esse personagem ja foi selecionado para a batalha");
                return true;
            }
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
        int contador = 1;
        int posicaoPersonagem = 0;
        while(ordemMaisRapidos.getHead() != null){
            No current = ordemMaisRapidos.getHead();
            while(current != null){
                if(current.getPersonagem().getAgilidade() > personagemMaisRapido.getPersonagem().getAgilidade()){
                    personagemMaisRapido = current;
                    posicaoPersonagem = contador;
                }
                contador++;
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
            for(int i = 0; i < quantidade; i++){
                Personagem personagem = jogador.selecionarPersonagem();
                if(personagem == null || verificarPersonagemRepetido(personagem)){
                    limparOrdemTurnos();
                    break;
                }
                ordemTurnos.add(personagem);
                exibicaoPersonagens.add(personagem);
            }
            
            System.out.println("\nJogador adversario:\n\n\t");
            for(int i = 0; i < quantidade; i++){
                Personagem personagem = jogadorAdversario.selecionarPersonagem();
                if(personagem == null){
                    limparOrdemTurnos();
                    break;
                }
                ordemTurnos.add(personagem);
                exibicaoPersonagens.add(personagem);
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
        int inserirBots;

        System.out.println("\nQuantidade de personagens partcipantes: ");
        int quantidade = sc.nextInt();
        this.colocacaoFinal.setSize(quantidade);

        System.out.println("\nJogador desafiante:\n\n\t");
        for(int i = 0; i < quantidade; i++){
            Personagem personagem = jogador.selecionarPersonagem();
            if(personagem == null || verificarPersonagemRepetido(personagem)){
                limparOrdemTurnos();
                break;
            }
            botAleatorio = ramdom.nextInt(3);
            switch (botAleatorio) {
                case 0 ->{
                    bot = new Personagem("Golem", gerarNivelBot(personagem));
                }
                case 1 ->{
                    bot = new Personagem("Lobo de Prata", gerarNivelBot(personagem));
                }
                default ->{
                    bot = new Personagem("Guardião Esqueleto", gerarNivelBot(personagem));
                }
            }
            aumentarAtributosBot(bot);
            this.bots.add(bot);
            ordemTurnos.add(personagem);
            exibicaoPersonagens.add(personagem);
            if(i == (quantidade - 1)){
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
        System.out.println("\t   BATALHA\n\nTURNO " + this.contadorTurnoAtual + ":\n\n");
        contador = 1;
        while(contador <= 12){
            for(Personagem personagem : personagensAliados){
                if(contador == 1){
                    System.out.println(personagem.getNome() + "\t");
                }
                if(contador == 2){
                    System.out.println("\nID: " + personagem.getIdPersonagem() + "\t");
                }
                if(contador == 3){
                    System.out.println("\nNivel: " + personagem.getNivel() + "\t");
                }
                if(contador == 4){
                    System.out.println("\nVida Atual: " + personagem.getVidaAtual() + "\t");
                }
                if(contador == 5){
                    System.out.println("\nMana Atual: " + personagem.getManaAtual() + "\t");
                }
                if(contador == 6){
                    System.out.println("\nAgilidade: " + personagem.getAgilidade() + "\t");
                }
            }
            for(Personagem personagem : personagensAdversarios){
                if(contador == 7){
                    System.out.println("\n\n\n" + personagem.getNome() + "\t");
                }
                if(contador == 8){
                    System.out.println("\nID: " + personagem.getIdPersonagem() + "\t");
                }
                if(contador == 9){
                    System.out.println("\nNivel: " + personagem.getNivel() + "\t");
                }
                if(contador == 10){
                    System.out.println("\nVida Atual: " + personagem.getVidaAtual() + "\t");
                }
                if(contador == 11){
                    System.out.println("\nMana Atual: " + personagem.getManaAtual() + "\t");
                }
                if(contador == 12){
                    System.out.println("\nAgilidade: " + personagem.getAgilidade() + "\t");
                }
            }
            contador++;
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
        }
        current = current.getNext();

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
        Random ramdom = new Random();
        int escolha = ramdom.nextInt((ordemTurnos.size() / 2));
        int escolhido = 0;

        No current = ordemTurnos.getHead();
        while(current != null){
            for(Personagem bot : this.bots){
                if(bot.getIdPersonagem() != current.getPersonagem().getIdPersonagem()){
                    if(escolha == escolhido){
                        return current.getPersonagem();
                    }else{
                        escolhido++;
                    }
                }
            }
        }
        return null;
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