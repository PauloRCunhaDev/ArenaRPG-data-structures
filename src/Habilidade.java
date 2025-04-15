public class Habilidade  extends HabilidadesEfeito{
    private String id;
    private String nome;
    private int custoMana;
    private String efeito;
    private int dano;
    private String tipo;
    
    public Habilidade(String id, String nome, int custoMana, String efeito, int dano, String tipo) {
        this.id = id;
        this.nome = nome;
        this.custoMana = custoMana;
        this.efeito = efeito;
        this.dano = dano;
        this.tipo = tipo;
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

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}