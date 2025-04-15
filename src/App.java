import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int tentativas = 0; //Variavel para tentativas

        while(true){
            System.out.println("------ RPG Turn-Based -----\n\n1) Login\n2) Cadastrar\n3) Sair\n\n--------------------------");
            int escolha = sc.nextInt();

            switch (escolha) {
                case 1 -> {
                OUTER:
                    while (true) {
                        Jogador jogador = Jogador.logar();
                        if (jogador != null) {
                            tentativas = 0; //Reinicia o número de tentativas
                            System.out.println("------ RPG Turn-Based -----\n\nPersonagem:\n\t1) Criar Novo Personagem\n2) Selecionar Personagem\n\n\tBatalha:3)PvP\n4)PvE\n5)Loja\n6)Sair\n\n--------------------------");
                            int escolha2 = sc.nextInt();
                            switch (escolha2) {
                                case 1 -> {
                                    jogador.criarPersonagem();
                                }
                                case 2 -> {
                                    jogador.selecionarPersonagem();
                                }
                                case 3 -> {
                                }
                                case 4 -> {
                                }
                                case 5 -> {

                                }
                                case 6 -> {
                                    break OUTER; // Alterei para sair do OUTER
                                }
                                default -> {
                                    System.out.println("\nOpcao invalida!"); //Alterei para colocar opção invalida
                                }
                            }
                        } else {
                            tentativas++; //Caso erre o login conta mais uma tentativa feita
                        }

                        if(jogador == null && tentativas > 2){ //Se o usuario errar o login 3 vezes ele pergunta se deseja retornar para o menu principal
                            System.out.println("Deseja retornar?\n\n1)sim\noutro)não");
                            int escolha3 = sc.nextInt();

                            switch (escolha3) {
                                case 1 -> {
                                    break OUTER;
                                }
                                default -> {
                                    tentativas = 0;
                                    break;
                                }
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
