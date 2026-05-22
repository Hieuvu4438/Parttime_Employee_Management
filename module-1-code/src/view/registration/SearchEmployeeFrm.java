package view.registration;

import dao.EmployeeDAO;
import model.Employee;
import model.User;
import view.user.HomeFrm;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SearchEmployeeFrm extends JFrame implements ActionListener {
    private User user;
    private ArrayList<Employee> listEmployee;
    private JTextField txtKey;
    private JButton btnSearch;
    private JButton btnBack;
    private JTable tblResult;
    private DefaultTableModel tableModel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public SearchEmployeeFrm(User user) {
        super("Search Employee");
        this.user = user;
        listEmployee = new ArrayList<Employee>();
        txtKey = new JTextField(25);
        btnSearch = new JButton("Search");
        btnBack = new JButton("Back");

        tableModel = new DefaultTableModel(new Object[]{"ID", "Code", "Full name", "Phone", "Email", "Contract date"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblResult = new JTable(tableModel);

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Keyword:"));
        topPanel.add(txtKey);
        topPanel.add(btnSearch);
        topPanel.add(btnBack);

        setLayout(new BorderLayout(10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(tblResult), BorderLayout.CENTER);

        btnSearch.addActionListener(this);
        btnBack.addActionListener(this);
        tblResult.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    openRegisterShiftFrm();
                }
            }
        });

        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            searchEmployee();
        } else if (e.getSource() == btnBack) {
            new HomeFrm(user).setVisible(true);
            dispose();
        }
    }

    private void searchEmployee() {
        String key = txtKey.getText().trim();
        if (key.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter employee keyword!");
            return;
        }

        listEmployee = new EmployeeDAO().searchEmployee(key);
        tableModel.setRowCount(0);
        for (Employee employee : listEmployee) {
            tableModel.addRow(new Object[]{
                    employee.getId(),
                    employee.getCode(),
                    employee.getFullName(),
                    employee.getPhoneNumber(),
                    employee.getEmail(),
                    employee.getContractDate() == null ? "" : dateFormat.format(employee.getContractDate())
            });
        }
        if (listEmployee.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No employee found!");
        }
    }

    private void openRegisterShiftFrm() {
        int row = tblResult.getSelectedRow();
        if (row < 0 || row >= listEmployee.size()) {
            return;
        }
        Employee selectedEmployee = listEmployee.get(row);
        new RegisterShiftFrm(selectedEmployee, user).setVisible(true);
        dispose();
    }
}
