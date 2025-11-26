import java.util.ArrayList;
import java.util.List;
// importamos essas classes do java.util, com elas teremos listas dinâmicas

// o controller é basicamente o cérebro, vai guardar os carros numa lista e faz adicionar,
// remover e editar. Além de listar os carros
// ela não tem botão, não desenha nada, só mexe com a lista de Carro, quem faz botões e visual é o Swing

public class CarroControlador {
    private List<Carro> carros; // lista vai guardar os carros
    private int proximoID = 1; // vamos controlar o ID de forma automática

    public CarroControlador() {
        this.carros = new ArrayList<>(); // criamos a lista vazia
    }

    public void adicionar(Carro carro){
        // o primeiro "Carro" se refere a classe criada por nós, o segundos é variável parâmetro
        carro.setId(proximoID++);
        carros.add(carro);
    }
    //ou seja, criamos um carro, passamos modelo marca e ano, aí define o ID automático e joga na lista

    public List<Carro> listar() {
        return new ArrayList<>(carros);
        // criamos uma nova lista com os mesmos elementos, ela só lê os carros e mostra, não modifica nada na lista interna

    }

    public boolean remover(int id){
        return carros.removeIf(carro -> carro.getId() == id);
        // true se removeu algum carro, false se não tinha carro com aquele id
        // é bom remover por id porque o id é fixo, diferente da posição na tabela que pode mudar
    }

    public boolean atualizar(int id, Carro carroAtualizado) {
        // "Carro" novamente é aquela classe já criada carroAtualizado <parâmetro o carro que vamos passar
        for (int i = 0; i < carros.size(); i++) { // i é posição na lista(id)
            Carro carro = carros.get(i); // pega o carro na posição indicada, carro é nosso carro atual
            if (carro.getId() == id){ // se o id for igual do carro que vamos atualizar, achamos o que queremos
                carroAtualizado.setId(carro.getId()); //deixa o mesmo id mesmo depois do update
                carros.set(i, carroAtualizado); //substitui o carro velho pelo novo
                return true; // se achar carro com id volta true(troca objeto antigo pelo novo)
            }
        }
        return false; // se não achar carro com id volta false
    }
}