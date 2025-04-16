import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Jogador jogador;
        Scanner sc = new Scanner(System.in);
        int tentativas = 0; //Variavel para tentativas

        while(true){
            System.out.println("------ RPG Turn-Based -----\n\n1) Login\n2) Cadastrar\n3) Sair\n\n--------------------------");
            int escolha = sc.nextInt();

            switch (escolha) {
                case 1 -> {
                    jogador = Jogador.logar();
                    if (jogador != null) {
                        while (true) {
                            int flag = 0;
                            tentativas = 0; //Reinicia o número de tentativas
                            System.out.println("\n------ RPG Turn-Based -----\n\nPersonagem:\n\n\t1) Criar Novo Personagem\n\t2) Selecionar Personagem\n\nBatalha:\n\n\t3)PvP\n\t4)PvE\n\t5)Loja\n\t6)Sair\n\n--------------------------");
                            int escolha2 = sc.nextInt();
                            switch (escolha2) {
                                case 1 -> {
                                    jogador.criarPersonagem();
                                }
                                case 2 -> {
                                    Personagem personagem = jogador.selecionarPersonagem();
                                    if(personagem != null){
                                        jogador.modificarStatusPersonagem(personagem);
                                    }
                                }
                                case 3 -> {
                                    Batalha batalha = new Batalha();
                                    batalha.iniciarBatalha(jogador);
                                    while(batalha.verificarJogadorVencedor(jogador)){
                                        batalha.executarTurno(jogador);
                                    }
                                }
                                case 4 -> {
                                    Batalha batalha = new Batalha();
                                    batalha.iniciarBatalhaBot(jogador);
                                    while(batalha.verificarJogadorVencedor(jogador)){
                                        batalha.executarTurnoBot(jogador);
                                    }
                                }
                                case 5 -> {
                                    jogador.loja();
                                }
                                case 6 -> {
                                    flag = 1;
                                    break; // Alterei para sair do OUTER
                                }
                                default -> {
                                    System.out.println("\nOpcao invalida!"); //Alterei para colocar opção invalida
                                }
                            }
                            if(flag == 1){
                                break;
                            }
                        }
                    } else {
                        tentativas++; //Caso erre o login conta mais uma tentativa feita
                    }

                    if(jogador == null && tentativas > 2){ //Se o usuario errar o login 3 vezes ele pergunta se deseja retornar para o menu principal
                        System.out.println("Deseja retornar?\n\n1)sim\n2)não");
                        int escolha3 = sc.nextInt();

                        switch (escolha3) {
                            case 1 -> {
                                break;
                            }
                            default -> {
                                tentativas = 0;
                                break;
                            }
                        }
                    }
                }
                case 2 -> {
                    Jogador.cadastrar();
                }
                case 3 -> {
                    System.out.println("\n\nObrigado por jogar!");
                    System.exit(0);
                }
                default -> {
                    System.out.println("\nOpcao invalida!");
                }
            }
        }
    }
}
