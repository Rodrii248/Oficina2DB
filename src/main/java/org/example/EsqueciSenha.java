package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class EsqueciSenha {
    JButton botaoCancelar;
    JButton botaoCadastrar;
    JPanel painelEsqueciSenha;
    Login login;

    JTextField campoChave;
    JTextField campoID;
    JTextField campoNovaSenha;
    JTextField campoConfirmarSenha;

    JLabel textoChave;

    JButton botaoAplicar;

    public EsqueciSenha(Login login) {
        this.login = login;

        // Criando modelo de tabela com colunas de nome, idade e número de telefone
        String[] colunas = {"ID", "Nome", "Email", "Senha"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        // Adicionando dados à tabela (podem ser substituídos por dados reais)
        Object[][] dados = {
                {30, "renan", "renangfs77@gmail.com", "2sdasd832"},
                {31, "João", "renangfs77@gmail.com", "sddsfa"},
                {30, "renan", "renangfs77@gmail.com", "2sdasd832"},
                {31, "João", "renangfs77@gmail.com", "sddsfa"},
                {30, "renan", "renangfs77@gmail.com", "2sdasd832"},
                {31, "João", "renangfs77@gmail.com", "sddsfa"},
                {30, "renan", "renangfs77@gmail.com", "2sdasd832"},
                {31, "João", "renangfs77@gmail.com", "sddsfa"},
                {30, "renan", "renangfs77@gmail.com", "2sdasd832"},
                {31, "João", "renangfs77@gmail.com", "sddsfa"},
                {30, "renan", "renangfs77@gmail.com", "2sdasd832"},
                {31, "João", "renangfs77@gmail.com", "sddsfa"},
                {30, "renan", "renangfs77@gmail.com", "2sdasd832"},
                {31, "João", "renangfs77@gmail.com", "sddsfa"},
                {30, "renan", "renangfs77@gmail.com", "2sdasd832"},
                {31, "João", "renangfs77@gmail.com", "sddsfa"},
                {30, "renan", "renangfs77@gmail.com", "2sdasd832"},
                {31, "João", "renangfs77@gmail.com", "sddsfa"},
                {30, "renan", "renangfs77@gmail.com", "2sdasd832"},
                {31, "João", "renangfs77@gmail.com", "sddsfa"},
                {32, "maria", "renangfs77@gmail.com", "283sda2"}
        };

        // Adicionando os dados ao modelo da tabela
        for (Object[] linha : dados) {
            model.addRow(linha);
        }

        // Criando a tabela com o modelo criado
        JTable table = new JTable(model);

        // Adicionando a tabela a um painel de rolagem para permitir a rolagem caso a tabela seja muito grande
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 250));

        // Adicionando o painel de rolagem à janela
        painelEsqueciSenha = new JPanel(new GridBagLayout());
        painelEsqueciSenha.setBackground(Color.white);


        textoChave = new JLabel("Chave de atualização de senha:");
        textoChave.setForeground(new Color(118, 118, 118));
        textoChave.setFont(new Font("Roboto", Font.BOLD, 12));
        JLabel textoID = new JLabel("ID:");
        textoID.setForeground(new Color(118, 118, 118));
        textoID.setFont(new Font("Roboto", Font.PLAIN, 12));

        JLabel textoNovaSenha = new JLabel("Nova senha:");
        textoNovaSenha.setForeground(new Color(118, 118, 118));
        textoNovaSenha.setFont(new Font("Roboto", Font.PLAIN, 12));

        JLabel textoConfirmarSenha = new JLabel("Confirme a senha:");
        textoConfirmarSenha.setForeground(new Color(118, 118, 118));
        textoConfirmarSenha.setFont(new Font("Roboto", Font.PLAIN, 12));

        campoChave = new JTextField();
        campoChave.setPreferredSize(new Dimension(250, 25));

        botaoAplicar = new JButton("Aplicar");
        botaoAplicar.setBounds(50, 50, 100, 30); // (x, y, largura, altura)
        botaoAplicar.setBackground(new Color(0, 30, 253));
        botaoAplicar.setForeground(new Color(255, 255, 255));
        botaoAplicar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        campoID = new JTextField();
        campoID.setPreferredSize(new Dimension(220, 25));
        campoID.setEnabled(false);


        campoNovaSenha = new JTextField();
        campoNovaSenha.setPreferredSize(new Dimension(220, 25));
        campoNovaSenha.setEnabled(false);


        campoConfirmarSenha = new JTextField();
        campoConfirmarSenha.setPreferredSize(new Dimension(220, 25));
        campoConfirmarSenha.setEnabled(false);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBounds(50, 50, 100, 30); // (x, y, largura, altura)
        botaoCancelar.setForeground(new Color(118, 118, 118));
        botaoCancelar.setBackground(new Color(255, 255, 255));
        botaoCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botaoCadastrar = new JButton("Alterar senha");
        botaoCadastrar.setBounds(50, 50, 100, 30); // (x, y, largura, altura)
        botaoCadastrar.setForeground(new Color(230, 230, 230));
        botaoCadastrar.setBackground(new Color(255, 255, 255));
        botaoCadastrar.setEnabled(false);
        botaoCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        gbc.gridwidth = 2;

        // y = Baixo  x = Direita
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 140, 5, 235);
        painelEsqueciSenha.add(textoChave, gbc);

        gbc.insets = new Insets(0, 60, 40, 100);
        gbc.gridy = 1;
        painelEsqueciSenha.add(campoChave, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 350, 40, 5);
        painelEsqueciSenha.add(botaoAplicar, gbc);

        gbc.insets = new Insets(0, 5, 40, 290);
        gbc.gridy = 2;
        gbc.gridx = 0;
        painelEsqueciSenha.add(scrollPane, gbc);

        gbc.insets = new Insets(0, 430, 3, 5);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 2;
        painelEsqueciSenha.add(textoID, gbc);
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 430, 10, 5);
        painelEsqueciSenha.add(campoID, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(60, 430, 3, 5);
        gbc.gridy = 2;
        gbc.gridx = 1;
        painelEsqueciSenha.add(textoNovaSenha, gbc);
        gbc.insets = new Insets(80, 430, 10, 5);
        gbc.gridy = 2;
        gbc.gridx = 1;
        painelEsqueciSenha.add(campoNovaSenha, gbc);
        gbc.insets = new Insets(120, 430, 3, 5);
        gbc.gridy = 2;
        gbc.gridx = 1;
        painelEsqueciSenha.add(textoConfirmarSenha, gbc);
        gbc.insets = new Insets(140, 430, 3, 5);
        gbc.gridy = 2;
        gbc.gridx = 1;
        painelEsqueciSenha.add(campoConfirmarSenha, gbc);

        gbc.insets = new Insets(225, 430, 5, 5);
        gbc.gridy = 2;
        gbc.gridx = 1;
        painelEsqueciSenha.add(botaoCancelar, gbc);
        gbc.insets = new Insets(225, 540, 5, 5);
        painelEsqueciSenha.add(botaoCadastrar, gbc);






        botaoAplicar.addActionListener(this::Aplicar);
        botaoCancelar.addActionListener(this::Cancelar);
        botaoCadastrar.addActionListener(this::Cadastrar);
    }

    public JPanel getPainelEsqueciSenha() {
        return painelEsqueciSenha;
    }

    public void Cancelar(ActionEvent e) {
        login.voltarLogin();
        System.out.println("Cancelar");
    }

    public void Aplicar(ActionEvent e) {
        if (Objects.equals(campoChave.getText(), "123")) {

            botaoAplicar.setForeground(new Color(184, 207, 229));
            botaoAplicar.setBackground(new Color(255, 255, 255));

            botaoCadastrar.setBackground(new Color(0, 30, 253));
            botaoCadastrar.setForeground(new Color(255, 255, 255));

            botaoAplicar.setEnabled(false);
            campoChave.setEnabled(false);
            botaoCadastrar.setEnabled(true);
            campoID.setEnabled(true);

            campoNovaSenha.setEnabled(true);

            campoConfirmarSenha.setEnabled(true);

            JOptionPane.showMessageDialog(null, "Chave aplicada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Chave incorreta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Cadastrar(ActionEvent e) {
        try (Connection connection = ConnectionFactory.recuperarConexao()) {
//            String sql = "INSERT INTO funcionario (id, nome, email, senha) VALUES (?, ?, ?, ?)";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setInt(1, Integer.parseInt(campoID.getText()));
//            statement.setString(2, "Nome do funcionário");
//            statement.setString(3, "email@exemplo.com");
//            statement.setString(4, campoNovaSenha.getText());
//            statement.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
            login.voltarLogin(); // Voltar para a tela de login após cadastrar
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + ex.getMessage());
        }
    }
}
