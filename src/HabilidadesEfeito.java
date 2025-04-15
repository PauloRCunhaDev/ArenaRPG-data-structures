public class HabilidadesEfeito{
    
        public void escolherEfeito (Personagem usuario, Habilidade skill, Personagem alvo) {
        switch (skill.getTipo()) {
            case "dano" -> causarDano(usuario, skill, alvo);

            case "cura" -> curar(skill, alvo);

            case "defesa" -> aumentarDefesa(skill, usuario);

            case "provocar" -> provocar(usuario, alvo);
        
            default -> {
            }
        }
    }

    public void causarDano(Personagem usuario, Habilidade skill, Personagem alvo){
        int valor = skill.getDano();
        if (usuario.getProvocador() != null) {
            alvo = usuario.getProvocador();
        }
        if (alvo.getDefesa() > valor) {
            valor = 0;
        } else {
            valor -= alvo.getDefesa();
        }

        if(alvo.estaVivo()){
            alvo.setVidaAtual(alvo.getVidaAtual() - valor);
            System.out.println(alvo.getNome() + " recebeu " + valor + " de dano");
        }else{
            System.out.println("\nImpossivel causar dano, o personagem nao esta mais vivo!");
        }
    }

    public void provocar (Personagem usuario, Personagem alvo) {
        alvo.setProvocador(usuario);
    }

    public void curar(Habilidade skill, Personagem alvo){
        int valor = skill.getDano();
        if(!alvo.estaVivo()){
            System.out.println("\nImpossivel curar, o personagem nao esta mais vivo!");
        }else if(alvo.getVidaAtual() == alvo.getVidaMaxima()){
            System.out.println("\nImpossivel curar, o personagem esta com a maxima vida possivel!");
        }else if(valor > alvo.getVidaMaxima()){
            alvo.setVidaAtual(alvo.getVidaMaxima());
        }else{
            alvo.setVidaAtual(alvo.getVidaAtual() + valor);
        }
    }

    public void aumentarDefesa (Habilidade skill, Personagem alvo) {
        alvo.setDefesa(alvo.getDefesa() + skill.getDano());
    }
}
