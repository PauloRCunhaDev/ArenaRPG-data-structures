import java.util.Scanner;

public class Jogador {
    private static int idJogador = 0;
    private String nome;
    private String senha;
    private double saldoCristais = 0;
    private Pilha classeDisponivel; //Pilha para as classes disponiveis para a criação de personagem
    private Pilha classePaga; //Pilha para as classes que serão desbloqueadas pela compra
    //Retirei a lista de habilidades pois ela ja está implementada dentro de cada personagem(Se necessario, pode fazer alteração nessa logica das listas)
    private LinkedListPerson personagens = new LinkedListPerson();
    private static Gerenciador gerenciador = new Gerenciador();
    private static Scanner sc = new Scanner(System.in);

    public Jogador(String nome, String senha) {
        idJogador++; //alterei para ele só aumentar o id(Se estiver errado pode corrigir)
        this.nome = nome;
        this.senha = senha;
        classeDisponivel.push("mago");
        classeDisponivel.push("guerreiro");
        classeDisponivel.push("arqueiro");
        classePaga.push("assassino");
        classePaga.push("tanke");
    }

    public static void cadastrar(){
        Jogador jogador = null;

        while(jogador != null){
            System.out.println("\nCrie seu nome: ");
            String nome = sc.nextLine();

            if(!gerenciador.verificarJogadorExistente(nome)){
                System.out.println("\nCrie sua senha: ");
                String senha = sc.nextLine();

                jogador = new Jogador(nome, senha);
                gerenciador.jogadores.add(jogador);
                System.out.println("\nSua conta foi criada com sucesso!");
            }else{
                System.out.println("\nEsse nome ja existe, crie outro nome!");
            }
        }
    }

    public static Jogador logar(){
        System.out.println("\nInforme seu nome: ");
        String nome = sc.nextLine();

        if(gerenciador.verificarJogadorExistente(nome)){
            System.out.println("\nInforme sua senha: ");
            String senha = sc.nextLine();

            if(gerenciador.validarSenha(nome, senha)){
                System.out.println("\nBem vindo, " + nome + "!");
                return gerenciador.buscarJogador(nome);
            }else{
                System.out.println("\nSenha invalida!");
                return null;
            }
        }else{
            System.out.println("\nO nome informado não foi encontrado, verifique se esta correto ou se realmente uma conta foi criada!");
        }
        return null;
    }

    public void criarPersonagem(){
        //laço para cancelamento de criação
        boolean laço = true;
        String nomePer = "";
        String classePer = "";

        while(laço){
            System.out.println("\nCrie o nome do personagem: ");
            nomePer = sc.nextLine();

            No current = personagens.getHead();
            while(current != null && current.getPersonagem().getNome().equals(nome)){
                current = current.getNext();
            }

            if(current != null){
                System.out.println("\nVoce ja criou um personagem com esse nome!\nDeseja cancelar a criação?\n1)sim\noutro)não");
                int cancelar = sc.nextInt(); //escolha do cancelamento de criação
                if(cancelar == 1){
                    return;
                }
            } else {
                //laço para definição da classe do personagem
                while(laço){
                    System.out.println("Defina a Classe:");
                    classePer = sc.nextLine().toLowerCase();
                    
                    No currentCls = classeDisponivel.head;

                    while(currentCls != null && !currentCls.getData().equals(classePer)){
                        currentCls = currentCls.getNext();
                    }

                    if(currentCls == null){
                        System.out.println("\nEssa classe não existe!\nDeseja cancelar a criação?\n1)sim\noutro)não");
                        int cancelar = sc.nextInt();
                        if(cancelar == 1){
                            return;
                        }
                    } else {
                        laço = false;
                    }
                }
            }
        }

        Personagem personagem = new Personagem(nomePer, classePer);
        personagens.inserirTail(personagem);
        System.out.println("\nPersonagem criado com sucesso!");
    }

    public Personagem selecionarPersonagem(){
        if(this.personagens.getHead() == null){
            System.out.println("\nA sua conta ainda não possui nenhum personagem!");
            return null;
        }

        System.out.println("\nNome do personagem: ");
        String nome = sc.nextLine();

        No current = personagens.getHead();
        while(current != null && current.getPersonagem().getNome().equals(nome)){
            current = current.getNext();
        }

        if(current == null){
            System.out.println("\nNao ha nenhum personagem com o nome informado!");
            return null;
        }
        return current.getPersonagem();
    }

    public void loja(){
        No current = classePaga.head;

        System.out.println("Classes:");
        while(current != null){
            if(current.getData().equals("assassino")){
                System.out.println("Assassino - 2000 cristais");
            } else {
                System.out.println("Tank - 3500");
            }
        }
        System.out.println("(Qualquer) sair");

        String comprar = sc.nextLine().toLowerCase();

        switch (comprar) {
            case "assassino" -> {
                //add classe a classeDisponivel
            }
            case "tank" -> {
                //add classe a classeDisponivel
            }
        }
    }

    //Revisar os metodos para os cristais
    public boolean verificarSaldoCristais(Personagem personagem){
        return this.saldoCristais >= 5 * personagem.getNivel();
    }

    public void atribuirPontosAtributo(){
        Personagem personagem = selecionarPersonagem();
        if(verificarSaldoCristais(personagem)){
            personagem.subirNivel();
        }else{
            System.out.println("\nSaldo insuficiente de cristais para subir o nivel do personagem");
        }
    }

    public static int getIdJogador() {
        return idJogador;
    }

    public static void setIdJogador(int idJogador) {
        Jogador.idJogador = idJogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldoCristais() {
        return saldoCristais;
    }

    public void setSaldoMoedas(double saldoMoedas) {
        this.saldoCristais = saldoMoedas;
    }

    public LinkedListPerson getPersonagens() {
        return personagens;
    }

    public void setPersonagens(LinkedListPerson personagens) {
        this.personagens = personagens;
    }
}