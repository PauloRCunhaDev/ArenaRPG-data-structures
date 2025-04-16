import java.util.Scanner;

public class Jogador {
    private static int idJogador = 0;
    private String nome;
    private String senha;
    private double saldoCristais = 0;
    private int saldoXP = 0;
    private LinkedListClasse classeDisponivel = new LinkedListClasse(); //LinkedListClasse para as classes disponiveis para a criação de personagem
    private LinkedListClasse classePaga = new LinkedListClasse(); //LinkedListClasse para as classes que serão desbloqueadas pela compra
    //Retirei a lista de habilidades pois ela ja está implementada dentro de cada personagem(Se necessario, pode fazer alteração nessa logica das listas)
    private LinkedListPerson personagens = new LinkedListPerson();
    private static Gerenciador gerenciador = new Gerenciador();
    private static Scanner sc = new Scanner(System.in);

    public Jogador(String nome, String senha) {
        idJogador++; //alterei para ele só aumentar o id(Se estiver errado pode corrigir)
        this.nome = nome;
        this.senha = senha;
        classeDisponivel.insertTail("mago");
        classeDisponivel.insertTail("guerreiro");
        classeDisponivel.insertTail("arqueiro");
        classePaga.insertTail("assassino");
        classePaga.insertTail("tank");
    }

    public static void cadastrar(){
        Jogador jogador = null;

        while(jogador == null){
            System.out.println("\nCrie seu nome: ");
            String nome = sc.nextLine();

            if(!gerenciador.verificarJogadorExistente(nome)){
                System.out.println("\nCrie sua senha: ");
                String senha = sc.nextLine();

                jogador = new Jogador(nome, senha);
                gerenciador.jogadores.add(jogador);
                System.out.println("\nSua conta foi criada com sucesso!\n");
            }else{
                System.out.println("\nEsse nome ja existe, crie outro nome!");
                sc.nextLine();
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
                System.out.println("\nSenha invalida!\n");
                return null;
            }
        }else{
            System.out.println("\nO nome informado não foi encontrado, verifique se esta correto ou se realmente uma conta foi criada!\n");
        }
        return null;
    }

    public void criarPersonagem(){
        //laço para cancelamento de criação
        boolean laco = true;

        while(laco){
            personagens.prinListaHead();
            System.out.println("\nCrie o nome do personagem: ");
            String nomePer = sc.nextLine();

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
                while(laco){
                    System.out.println("\nDefina a Classe:");
                    classeDisponivel.printHead();
                    String classePer = sc.nextLine().toLowerCase();
                    
                    No currentCls = classeDisponivel.head;

                    while(currentCls != null && !currentCls.getData().equals(classePer)){
                        currentCls = currentCls.getNext();
                    }

                    if(currentCls == null){
                        System.out.println("\nEssa classe não existe!\nDeseja cancelar a criação?\n1)sim\noutro)não");
                        int cancelar = sc.nextInt();
                        if(cancelar == 1){
                            sc.nextLine();
                            return;
                        }
                    } else {
                        laco = false;
                        Personagem personagem = new Personagem(nomePer, classePer);
                        personagens.inserirTail(personagem);
                        System.out.println("\nPersonagem criado com sucesso!");
                    }
                }
            }
        }
    }

    public Personagem selecionarPersonagem(){
        if(this.personagens.getHead() == null){
            System.out.println("\nA sua conta ainda não possui nenhum personagem!");
            return null;
        }

        System.out.println("\nNome do personagem: ");
        String nomePer = sc.nextLine();

        No current = personagens.getHead();
        while(current != null && !current.getPersonagem().getNome().equals(nomePer)){
            current = current.getNext();
        }

        if(current == null){
            System.out.println("\nNao ha nenhum personagem com o nome informado!");
            return null;
        }
        return current.getPersonagem();
    }

    public void loja(){
        while(true){
            No current = classePaga.head;

            System.out.println("Classes:");
            while(current != null){
                if(current.getData().equals("assassino")){
                    System.out.println("Assassino - 2000 cristais");
                } else if(current.getData().equals("tank")){
                    System.out.println("Tank - 3500");
                }
            }
            System.out.println("(Qualquer) sair");

            String comprar = sc.nextLine().toLowerCase();

            switch (comprar) {
                case "assassino" -> {
                    if (getSaldoCristais() < 2000) {
                        System.out.println("Cristais insuficientes");
                    } else {
                        int contador = 1;
                        current = classePaga.head;

                        while(current != null && !current.getData().equals(comprar)){
                            contador++;
                        }
                        classePaga.delete(contador);
                        classeDisponivel.insertTail(comprar);
                        setSaldoCristais(getSaldoCristais() - 2000);
                    }
                    //add classe a classeDisponivel
                }
                case "tank" -> {
                    if (getSaldoCristais() < 2000) {
                        System.out.println("Cristais insuficientes");
                    } else {
                        int contador = 1;
                        current = classePaga.head;

                        while(current != null && !current.getData().equals(comprar)){
                            contador++;
                        }
                        classePaga.delete(contador);
                        classeDisponivel.insertTail(comprar);
                        setSaldoCristais(getSaldoCristais() - 3500);
                    }
                    //add classe a classeDisponivel
                }
                default -> {
                    return;
                }
            }
        }
    }

    //Revisar os metodos para os cristais
    public boolean verificarSaldoXP(){
        return this.saldoXP >= 30;
    }

    public void atribuirPontosAtributo(){
        Personagem personagem = selecionarPersonagem();
        if(verificarSaldoXP()){
            personagem.subirNivel();
        }else{
            System.out.println("\nSaldo insuficiente de xp para subir o nivel do personagem");
        }
    }

    public boolean ePersonagem(Personagem personagem){
        No current = this.personagens.getHead();
        while(current != null){
            if(personagem.equals(current.getPersonagem())){
                return true;
            }
        }
        return false;
    }

    public boolean podeUpar(Personagem personagem, int quantxp){
        if(this.saldoXP < 30 || this.saldoXP < quantxp){
            System.out.println("Impossivel upar, o saldo de xp e insuficiente");
            return false;
        }
        if(personagem.getNivel() == 30){
            System.out.println("Impossivel upar, esse personagem esta no nivel maximo");
            return false;
        }
        return true;
    }

    public void modificarStatusPersonagem(Personagem personagem){
        System.out.println("\n\nPERSONAGEM: " + personagem.getNome());
        System.out.println("\n1) Visualizar Habilidades\n2) Subir nivel\n3) Sair");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1 -> {
                System.out.println("\nHABILIDADES DISPONIVEIS:\n\n");
                personagem.mostrarHabilidades();
            }
            case 2 -> {
                System.out.println("\nSaldo de XP: " + this.saldoXP);
                System.out.println("\nQuantidade de xp que voce deseja dar ao personagem: ");
                int quantxp = sc.nextInt();

                if(sc.hasNextInt()){
                    if(podeUpar(personagem, quantxp)){
                        for(int i = 0; i < (quantxp / 30); i++){
                            personagem.subirNivel();
                            gerenciador.alertarHabilidadeDesbloqueada(personagem);
                        }
                    }
                }else{
                    System.out.println("\nValor invalido, digite um valor inteiro da proxima");
                }
            }
            case 3 -> {
                break;
            }
            default -> {
                System.out.println("\nOpcao invalida");
            }
        }
        sc.nextLine();
    }

    public void receberXP(int xp){
        this.saldoXP += xp;
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
    
    public LinkedListPerson getPersonagens() {
        return personagens;
    }
    
    public void setPersonagens(LinkedListPerson personagens) {
        this.personagens = personagens;
    }
    
    public double getSaldoCristais() {
        return saldoCristais;
    }
    
    public void setSaldoCristais(double saldoCristais) {
        this.saldoCristais = saldoCristais;
    }

    public int getSaldoXP() {
        return saldoXP;
    }

    public void setSalodXP(int salodXP) {
        this.saldoXP = salodXP;
    }
}