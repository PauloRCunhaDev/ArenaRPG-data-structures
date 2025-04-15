public class Habilidade {
    private String id;
    private String nome;
    private int custoMana;
    private String efeito;
    
    public Habilidade(String id, String nome, int custoMana, String efeito) {
        this.id = id;
        this.nome = nome;
        this.custoMana = custoMana;
        this.efeito = efeito;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCustoMana() {
        return custoMana;
    }

    public void setCustoMana(int custoMana) {
        this.custoMana = custoMana;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }
}