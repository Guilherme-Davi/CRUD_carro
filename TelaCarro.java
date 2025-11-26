import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JTable tabelaCarro;
    private JScrollPane scrollTable;
    private JLabel lblMarca;
    private JLabel lblModelo;
    private JLabel lblAno;

    // Model da JTable
    private DefaultTableModel tableModel;

    // Controlador (CRUD)
    private final CarroControlador controlador = new CarroControlador();

    public TelaCarro() {

        setTitle("Cadastro de Carros");
        setContentPane(mainPanel);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Ao fechar a janela, imprimir a lista no terminal
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("\n=== LISTA DE CARROS AO FECHAR ===");
                for (Carro c : controlador.listar()) {
                    System.out.println(c);
                }
                dispose();
                System.exit(0);
            }
        });

        configurarTabela();
        configurarEventos();
    }

    // ============================================================
    // CONFIGURA JTABLE
    // ============================================================
    private void configurarTabela() {

        // Colunas da tabela
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Marca", "Modelo", "Ano"}, 0
        );

        tabelaCarro.setModel(tableModel);
        tabelaCarro.setDefaultEditor(Object.class, null); // Bloqueia edição manual

        // Evento ao selecionar linha
        tabelaCarro.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tabelaCarro.getSelectedRow();
                if (row != -1) {
                    txtMarca.setText(tableModel.getValueAt(row, 1).toString());
                    txtModelo.setText(tableModel.getValueAt(row, 2).toString());
                    txtAno.setText(tableModel.getValueAt(row, 3).toString());
                }
            }
        });
    }

    // ============================================================
    // CONFIGURA BOTÕES
    // ============================================================
    private void configurarEventos() {

        // ADICIONAR
        btnAdd.addActionListener(e -> {
            try {
                String marca = txtMarca.getText().trim();
                String modelo = txtModelo.getText().trim();
                int ano = Integer.parseInt(txtAno.getText().trim());

                if (marca.isEmpty() || modelo.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha Marca e Modelo.");
                    return;
                }

                Carro carro = new Carro(marca, modelo, ano);
                controlador.adicionar(carro);

                atualizarTabela();
                limparCampos();

                JOptionPane.showMessageDialog(this, "Veículo adicionado!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ano deve ser numérico.");
            }
        });

        // REMOVER
        btnRemover.addActionListener(e -> {
            int row = tabelaCarro.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um veículo.");
                return;
            }

            int id = (int) tableModel.getValueAt(row, 0);

            controlador.remover(id);
            atualizarTabela();
            limparCampos();

            JOptionPane.showMessageDialog(this, "Veículo removido!");
        });

        // ATUALIZAR
        btnAtualizar.addActionListener(e -> {
            int row = tabelaCarro.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um veículo.");
                return;
            }

            try {
                int id = (int) tableModel.getValueAt(row, 0);
                String marca = txtMarca.getText().trim();
                String modelo = txtModelo.getText().trim();
                int ano = Integer.parseInt(txtAno.getText().trim());

                Carro atualizado = new Carro(marca, modelo, ano);

                boolean ok = controlador.atualizar(id, atualizado);

                if (ok) {
                    atualizarTabela();
                    limparCampos();
                    JOptionPane.showMessageDialog(this, "Veículo atualizado!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ano inválido.");
            }
        });

        // LISTAR
        btnLista.addActionListener(e -> atualizarTabela());
    }

    // ============================================================
    // ATUALIZA TABELA COM DADOS DO CONTROLADOR
    // ============================================================
    private void atualizarTabela() {
        tableModel.setRowCount(0);

        for (Carro c : controlador.listar()) {
            tableModel.addRow(new Object[]{
                    c.getId(),
                    c.getModelo(),
                    c.getMarca(),
                    c.getAno()
            });
        }
    }

    // ============================================================
    // LIMPAR CAMPOS
    // ============================================================
    private void limparCampos() {
        txtMarca.setText("");
        txtModelo.setText("");
        txtAno.setText("");
    }
}
