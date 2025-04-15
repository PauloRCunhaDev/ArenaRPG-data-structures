import java.util.Scanner;

public class Batalha {
    private static int idBtalha;
    private Jogador jogadorAdversario;
    private int contadorTurnoAtual = 0;
    private boolean estadoBatlha = false;
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
        while(ordemMaisRapidos != null){
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
        System.out.println("\nNome do jogador adversario: ");
        String nome = sc.nextLine();

        this.jogadorAdversario = gerenciador.buscarJogador(nome);

        if(jogadorAdversario == null){
            System.out.println("\nNao ha nenhum jogador com esse nome");
        }else{
            System.out.println("\nQuantidade de personagens partcipantes: ");
            int quantidade = sc.nextInt();

            System.out.println("\nJogador desafiante:\n\n\t");
            for(int i = 0; i < quantidade; i++){
                Personagem personagem = jogador.selecionarPersonagem();
                if(personagem == null || verificarPersonagemRepetido(personagem)){
                    limparOrdemTurnos();
                    break;
                }
                ordemTurnos.add(personagem);
            }
            
            System.out.println("\nJogador adversario:\n\n\t");
            for(int i = 0; i < quantidade; i++){
                Personagem personagem = jogadorAdversario.selecionarPersonagem();
                if(personagem == null){
                    limparOrdemTurnos();
                    break;
                }
                ordemTurnos.add(personagem);
            }
            estadoBatlha = true;
        }
    }

    public void executarTurno(){
        Personagem personagem = ordemTurnos.remove().getPersonagem();
        System.out.println("\nPERSONAGEM ALVO: ");
        Personagem personagemAlvo = null;

        while(personagemAlvo == null){
            System.out.println("\nPERSONAGEM ALVO: ");
            personagemAlvo = jogadorAdversario.selecionarPersonagem();

            if(personagemAlvo == null){
                System.out.println("\nO personagem escolhido nao esta no time adversario, escolha novamente");
            }
        }


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
}