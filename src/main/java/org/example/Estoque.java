package org.example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Estoque extends Login { // Mudando para JPanel
    Login login;
    JButton botaoVoltar;
    JButton botaoIncluirEntrada;
    JButton botaoincluirSaida;

    JPanel painelSuperiorEstoque;
    JPanel painelTabelaEstoque;

    JLabel textoEstoque;


    JTable table;
    public Estoque(Login login) {
        this.login = login;

        // Criando modelo de tabela com colunas de ID, Nome e Email
        String[] colunas = {"Código produto", "Produto", "Quantidade", "Valor", "Total"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        try (Connection connection = ConnectionFactory.recuperarConexao()) {
            String sql = "SELECT `IDPRODUTO`, `NOME_PRODUTO`, `QTD`, `PRECO`, `QTD` FROM `produto`;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {//Coloca o SQL no terminal do Banco de dados
                try (ResultSet resultSet = statement.executeQuery()) {//executa o SQL e captura o resultado
                    while (resultSet.next()) {//percorre cada tupla do resultado
                        String idproduto = resultSet.getString("IDPRODUTO");
                        String nomeProduto = resultSet.getString("NOME_PRODUTO");
                        String quantidadee = resultSet.getString("QTD");
                        String preco = resultSet.getString("PRECO");
                        String quantidade = resultSet.getString("QTD");


                        // Criar uma linha de dados
                        Object[] linha = {idproduto, nomeProduto, quantidadee, preco, quantidade};
                        // Adicionar a linha ao modelo de tabela
                        model.addRow(linha);

                        System.out.println("IDPRODUTO: " + idproduto + ",IDFORNECEDOR: " + nomeProduto + ",NOME_PRODUTO: " + quantidadee+ " QTD: "+preco+"qtd"+quantidade);//executa a saida no terminal
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao executar consulta: " + ex.getMessage());
        }


        // Criando a tabela com o modelo criado
        table = new JTable(model);
        table.setEnabled(false);//bloqueia a tabela
        table.setVisible(true);//Tabela invisivel

        // Adicionando a tabela a um painel de rolagem para permitir a rolagem caso a tabela seja muito grande
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 450));

        // Adicionando o painel de rolagem à janela
        painelTabelaEstoque = new JPanel(new GridBagLayout());
        painelTabelaEstoque.setBackground(Color.white);



        painelSuperiorEstoque = new JPanel(new GridBagLayout());
        painelSuperiorEstoque.setBackground(Color.WHITE);

        textoEstoque = new JLabel("Estoque");
        textoEstoque.setForeground(new Color(0, 0, 0));
        textoEstoque.setFont(new Font("Roboto", Font.BOLD, 30));

        botaoIncluirEntrada = new JButton("Incluir entrada  + ");
        botaoIncluirEntrada.setPreferredSize(new Dimension(120, 30));
        botaoIncluirEntrada.setBorder(new LineBorder(new Color(49, 66, 216)));
        botaoIncluirEntrada.setBackground(new Color(255, 255, 255));
        botaoIncluirEntrada.setForeground(new Color(49, 66, 216));
        botaoIncluirEntrada.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botaoincluirSaida = new JButton("Incluir Saída   - ");
        botaoincluirSaida.setPreferredSize(new Dimension(120, 30));
        botaoincluirSaida.setBorder(new LineBorder(new Color(49, 66, 216)));
        botaoincluirSaida.setBackground(new Color(255, 255, 255));
        botaoincluirSaida.setForeground(new Color(49, 66, 216));
        botaoincluirSaida.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botaoVoltar = new JButton("<html><u>Voltar</u></html>");
        botaoVoltar.setPreferredSize(new Dimension(40, 30));
        botaoVoltar.setBorder(new LineBorder(new Color(250, 255, 255)));
        botaoVoltar.setBackground(new Color(255, 255, 255));
        botaoVoltar.setForeground(new Color(180, 180, 180));
        botaoVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        painelSuperiorEstoque.add(botaoVoltar, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        painelSuperiorEstoque.add(botaoIncluirEntrada, gbc);
        gbc.gridy = 1;
        gbc.gridx = 1;
        painelSuperiorEstoque.add(botaoincluirSaida, gbc);
        gbc.gridy = 0;
        gbc.gridx = 2;
        gbc.insets = new Insets(5, 200, 5, 5);
        painelSuperiorEstoque.add(textoEstoque, gbc);
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        painelSuperiorEstoque.add(scrollPane, gbc);

        add(painelSuperiorEstoque);

        botaoVoltar.addActionListener(this::Voltar);
        botaoIncluirEntrada.addActionListener(this::IncluirEntrada);
        botaoincluirSaida.addActionListener(this::IncluirSaida);
    }

    public JPanel getPainelEstoque() {
        return painelSuperiorEstoque;
    }

    public void IncluirEntrada(ActionEvent e) {
        System.out.println("Clicou em incluir entrada");
        login.IncluirEntrada();
    }

    public void IncluirSaida(ActionEvent e) {
        System.out.println("Clicou em incluir Saida");
    }

    public void Voltar(ActionEvent actionEvent) {
        login.VoltarMenuConsulta();//
    }

}
