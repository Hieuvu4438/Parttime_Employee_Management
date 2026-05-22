package view.user;

import model.User;
import view.registration.SearchEmployeeFrm;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrm extends JFrame implements ActionListener {
    private User user;
    private JButton btnRegister;
    private JButton btnLogout;

    public HomeFrm(User user) {
        super("Home");
        this.user = user;
        btnRegister = new JButton("Register for next week");
        btnLogout = new JButton("Logout");

        JPanel content = new JPanel(new BorderLayout(10, 10));
        String username = user.getUsername() == null ? "" : user.getUsername();
        JLabel lblWelcome = new JLabel("Welcome " + username, JLabel.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnLogout);

        content.add(lblWelcome, BorderLayout.NORTH);
        content.add(buttonPanel, BorderLayout.CENTER);
        setContentPane(content);

        btnRegister.addActionListener(this);
        btnLogout.addActionListener(this);
        setSize(360, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            new SearchEmployeeFrm(user).setVisible(true);
            dispose();
        } else if (e.getSource() == btnLogout) {
            new LoginFrm().setVisible(true);
            dispose();
        }
    }
}
