public class Carro {
    private String modelo;
    private String marca;
    private int ano;
    private int id;

    public Carro(String modelo, String marca, int ano) { // CONSTRUTOR do objeto Carro
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
    }


    //SETTERS servem para setar(aplicar) os valores no objeto
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setMarca(String marca) { this.marca = marca; }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public void setId(int id){
        this.id = id;
    }


    //GETTERS servem para obter as informações depois de setadas
    public String getModelo() {
        return modelo;
    }
    public String getMarca () { return marca; }
    public int getAno() {
        return ano;
    }
    public int getId() { return id; }

    public String toString() {
        return id + " - " + modelo + " - " + marca + " - " + ano;
    }


// resumidamente, guarda informação do carro
}
