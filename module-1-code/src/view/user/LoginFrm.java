package view.user;

import dao.UserDAO;
import model.User;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrm extends JFrame implements ActionListener {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrm() {
        super("Login");
        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);
        btnLogin = new JButton("Login");

        JPanel content = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(txtUsername);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(txtPassword);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLogin);

        content.add(inputPanel, BorderLayout.CENTER);
        content.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(content);

        btnLogin.addActionListener(this);
        setSize(350, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            User user = new User();
            user.setUsername(txtUsername.getText().trim());
            user.setPassword(new String(txtPassword.getPassword()));

            if (new UserDAO().checkLogin(user)) {
                new HomeFrm(user).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
        }
    }

    public static void main(String[] args) {
        new LoginFrm().setVisible(true);
    }
}
