import java.util.ArrayList;

public class Gerenciador {
    ArrayList<Jogador> jogadores = new ArrayList<>();

    public Jogador buscarJogador(String nome){
        for(Jogador jogador : this.jogadores){
            if(jogador.getNome().equals(nome)){
                return jogador;
            }
        }
        return null;
    }

    public boolean verificarJogadorExistente(String nome){
        Jogador jogador = buscarJogador(nome);
        return jogador != null;
    }

    public boolean validarSenha(String nome, String senha){
        Jogador jogador = buscarJogador(nome);
        return jogador.getSenha().equals(senha);
    }

    public void alertarHabilidadeDesbloqueada(Personagem personagem){
        if(personagem.getNivel() == 10 || personagem.getNivel() == 20 || personagem.getNivel() == 30){
            personagem.desbloquearHabilidade();
            System.out.println("\nO personagem " + personagem.getNome() + " desbloqueou uma nova habilidade");
        }
    }
}