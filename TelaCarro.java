import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaCarro extends JFrame {
    private JPanel mainPanel;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextField txtAno;
    private JButton btnAdd;
    private JButton btnRemover;
    private JButton btnAtualizar;
    private JButton btnLista;
    private JList<Carro> ListaCarro;
    private JLabel lblMarca;
    private JLabel lblModelo;
    private JLabel lblAno;

    // Model da lista (DefaultListModel) para manipular elementos do JList
    private final DefaultListModel<Carro> listModel = new DefaultListModel<>(); // modelo da JList

    // Controlador que contém a lógica do CRUD em memória
    private final CarroControlador controlador = new CarroControlador(); // instancia do controlador

    // Construtor da janela — inicializa interface e eventos
    public TelaCarro() {
        setTitle("Cadastro de Carros");        // título da janela
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // intercepta fechamento
        setSize(600, 480);                     // tamanho inicial da janela
        setLocationRelativeTo(null);           // centraliza na tela

        // Aqui assumimos que mainPanel e componentes foram criados (se não, ver nota abaixo).
        setContentPane(mainPanel);             // define o painel principal da janela

        // Listener para interceptar o fechamento e imprimir a lista no terminal
        addWindowListener(new WindowAdapter() { // adiciona um WindowListener anônimo
            @Override
            public void windowClosing(WindowEvent e) { // quando o usuário fecha a janela
                System.out.println("=== LISTA DE CARROS AO FECHAR ==="); // header no terminal
                for (Carro c : controlador.listar()) { // itera sobre a lista do controlador
                    System.out.println(c);           // imprime cada carro (usa toString)
                }
                dispose();                          // libera recursos da janela
                System.exit(0);                     // encerra a aplicação
            }
        });

        // Conecta o modelo ao JList para uso em runtime
        ListaCarro.setModel(listModel);          // vincula o DefaultListModel ao JList
        // Ajuste visual / tamanho: força tamanho do scroll (se seu JList estiver dentro de JScrollPane)
        // Configura os listeners dos botões e da lista
        configurarEventos();
    }

    private void configurarEventos() {

        //ADICIONAR
        btnAdd.addActionListener(e -> {          // ao clicar em Adicionar
            try {
                String marca = txtMarca.getText().trim(); // pega e limpa marca
                String modelo = txtModelo.getText().trim(); // pega e limpa modelo
                int ano = Integer.parseInt(txtAno.getText().trim()); // converte ano para int

                if (marca.isEmpty() || modelo.isEmpty()) { // valida campos obrigatórios
                    JOptionPane.showMessageDialog(this, "Preencha Marca e Modelo."); // alerta
                    return; // interrompe a operação
                }
                Carro carro = new Carro(modelo, marca, ano);
                controlador.adicionar(carro); // chama controlador para adicionar
                atualizarLista();                 // atualiza a JList visual
                limparCampos();                   // limpa campos do formulário
                JOptionPane.showMessageDialog(this, "Veículo adicionado!"); // confirma ação
            } catch (NumberFormatException ex) {      // trata erro na conversão do ano
                JOptionPane.showMessageDialog(this, "Ano deve ser numérico."); // mostra erro
            }
        });

        //REMOVER
        btnRemover.addActionListener(e -> {        // ao clicar em Remover
            Carro c = ListaCarro.getSelectedValue(); // pega o item selecionado na JList
            if (c == null) {                        // se nada selecionado
                JOptionPane.showMessageDialog(this, "Selecione um veículo."); // alerta
                return;
            }
            controlador.remover(c.getId());         // solicita remoção ao controlador
            atualizarLista();                       // atualiza visual
            limparCampos();                         // limpa campos
            JOptionPane.showMessageDialog(this, "Veículo removido!"); // confirma ação
        });

        //ATUALIZAR
        btnAtualizar.addActionListener(e -> {      // ao clicar em Atualizar
            Carro c = ListaCarro.getSelectedValue(); // pega selecionado
            if (c == null) {                        // valida seleção
                JOptionPane.showMessageDialog(this, "Selecione um veículo."); // alerta
                return;
            }
            try {
                int ano = Integer.parseInt(txtAno.getText().trim()); // converte ano
                String marca = txtMarca.getText().trim();           // pega marca
                String modelo = txtModelo.getText().trim();         // pega modelo

                Carro carroAtualizado = new Carro(modelo, marca, ano);
                boolean ok = controlador.atualizar(c.getId(), carroAtualizado); // tenta atualizar

                if (ok) {                                  // se atualizado com sucesso
                    atualizarLista();                     // atualiza visual
                    limparCampos();                       // limpa campos
                    JOptionPane.showMessageDialog(this, "Veículo atualizado!"); // confirma
                } else {
                    JOptionPane.showMessageDialog(this, "Erro: ID não encontrado."); // raramente
                }
            } catch (NumberFormatException ex) {            // erro conversão ano
                JOptionPane.showMessageDialog(this, "Ano inválido."); // mostra erro
            }
        });

        //LISTAR/ATUALIZAR
        btnLista.addActionListener(e -> atualizarLista()); // força recarregar a JList

        //SELECIONAR UM ITEM NA LISTA
        ListaCarro.addListSelectionListener(e -> {         // quando seleção muda
            Carro sel = ListaCarro.getSelectedValue();     // pega carro selecionado
            if (sel != null) {                             // se houver seleção
                txtMarca.setText(sel.getMarca());          // marca
                txtModelo.setText(sel.getModelo());        // modelo
                txtAno.setText(String.valueOf(sel.getAno())); // ano
            }
        });
    }

    // Atualiza visual da JList a partir do controlador
    private void atualizarLista() {
        listModel.clear();                         // limpa o modelo visual
        for (Carro c : controlador.listar()) {     // pega todos os carros do controlador
            listModel.addElement(c);               // adiciona cada carro ao modelo
        }
    }

    // Limpa os campos de entrada (usado após operações)
    private void limparCampos() {
        txtMarca.setText("");                      // limpa marca
        txtModelo.setText("");                     // limpa modelo
        txtAno.setText("");                        // limpa ano
    }

}
