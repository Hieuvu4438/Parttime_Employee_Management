package view.registration;

import dao.RegistrationShiftDAO;
import dao.ShiftDAO;
import model.Employee;
import model.RegistrationShift;
import model.Shift;
import model.User;
import view.user.HomeFrm;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class RegisterShiftFrm extends JFrame implements ActionListener {
    private Employee employee;
    private User user;
    private ArrayList<Shift> listShift;
    private ArrayList<RegistrationShift> listExistingRegistration;
    private JTable tblShift;
    private DefaultTableModel tableModel;
    private JButton btnSave;
    private JButton btnBack;
    private HashSet<Integer> registeredShiftIds;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public RegisterShiftFrm(Employee employee, User user) {
        super("Register Shift");
        this.employee = employee;
        this.user = user;
        this.listShift = new ArrayList<Shift>();
        this.listExistingRegistration = new ArrayList<RegistrationShift>();
        this.registeredShiftIds = new HashSet<Integer>();

        loadData();
        buildView();

        setSize(950, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadData() {
        listShift = new ShiftDAO().getShiftNextWeek();
        listExistingRegistration = new RegistrationShiftDAO().getRegistrationByEmployee(employee);
        for (RegistrationShift registrationShift : listExistingRegistration) {
            if (registrationShift.getShift() != null) {
                registeredShiftIds.add(registrationShift.getShift().getId());
            }
        }
    }

    private void buildView() {
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 5));
        infoPanel.add(new JLabel("Employee code:"));
        infoPanel.add(new JLabel(employee.getCode()));
        infoPanel.add(new JLabel("Full name:"));
        infoPanel.add(new JLabel(employee.getFullName()));
        infoPanel.add(new JLabel("Phone number:"));
        infoPanel.add(new JLabel(employee.getPhoneNumber()));

        tableModel = new DefaultTableModel(new Object[]{"Select", "ID", "Date", "Shift", "Start", "End", "Description", "Registered"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class;
                }
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 && "No".equals(getValueAt(row, 7));
            }
        };
        tblShift = new JTable(tableModel);
        fillShiftTable();

        btnSave = new JButton("Save");
        btnBack = new JButton("Back");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSave);
        buttonPanel.add(btnBack);

        setLayout(new BorderLayout(10, 10));
        add(infoPanel, BorderLayout.NORTH);
        add(new JScrollPane(tblShift), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnSave.addActionListener(this);
        btnBack.addActionListener(this);
    }

    private void fillShiftTable() {
        tableModel.setRowCount(0);
        for (Shift shift : listShift) {
            boolean registered = registeredShiftIds.contains(shift.getId());
            tableModel.addRow(new Object[]{
                    false,
                    String.valueOf(shift.getId()),
                    shift.getDate() == null ? "" : dateFormat.format(shift.getDate()),
                    String.valueOf(shift.getShiftNumber()),
                    shift.getStartDate() == null ? "" : dateTimeFormat.format(shift.getStartDate()),
                    shift.getEndDate() == null ? "" : dateTimeFormat.format(shift.getEndDate()),
                    shift.getDescription(),
                    registered ? "Yes" : "No"
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            saveRegistration();
        } else if (e.getSource() == btnBack) {
            new SearchEmployeeFrm(user).setVisible(true);
            dispose();
        }
    }

    private void saveRegistration() {
        ArrayList<RegistrationShift> selectedRegistrations = new ArrayList<RegistrationShift>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean selected = (Boolean) tableModel.getValueAt(i, 0);
            if (selected != null && selected && !registeredShiftIds.contains(listShift.get(i).getId())) {
                RegistrationShift registrationShift = new RegistrationShift();
                registrationShift.setRegisteredTime(new Date());
                registrationShift.setStatus("registered");
                registrationShift.setDescription("Registered from Module 1");
                registrationShift.setEmployee(employee);
                registrationShift.setShift(listShift.get(i));
                registrationShift.setUser(user);
                selectedRegistrations.add(registrationShift);
            }
        }

        if (selectedRegistrations.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one new shift!");
            return;
        }

        if (new RegistrationShiftDAO().saveRegistration(selectedRegistrations)) {
            JOptionPane.showMessageDialog(this, "Registration successfully saved!");
            new HomeFrm(user).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Cannot save registration!");
        }
    }
}
