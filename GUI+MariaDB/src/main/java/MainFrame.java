import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class MainFrame extends JFrame {

    private JTable table;
    private MyDBConnection dbConnection;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    public MainFrame() {

        setTitle("Сотрудники");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        dbConnection = new MyDBConnection();
        dbConnection.init();

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        addButton = new JButton("Добавить");
        addButton.addActionListener(e -> showAddDialog());

        editButton = new JButton("Редактировать");
        editButton.setEnabled(false);
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                showEditDialog(selectedRow);
            }
        });

        deleteButton = new JButton("Удалить");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                deleteRow(selectedRow);
            }
        });


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);


        try {

            Connection connection = dbConnection.getMyConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.setColumnIdentifiers(new Object[]{"ID", "Фамилия", "Имя", "Отчество", "Пол", "Дата рождения", "Адрес", "Должность"});

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("id"));
                row.add(resultSet.getString("surname"));
                row.add(resultSet.getString("name"));
                row.add(resultSet.getString("patronymic"));
                row.add(resultSet.getString("sex"));
                row.add(resultSet.getDate("birth_day"));
                row.add(resultSet.getString("address"));
                row.add(resultSet.getString("post"));
                tableModel.addRow(row);
            }


            table.setModel(tableModel);
            table.getSelectionModel().addListSelectionListener(e -> {
                boolean rowSelected = table.getSelectedRow() >= 0;
                editButton.setEnabled(rowSelected);
                deleteButton.setEnabled(rowSelected);
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    }

    private void showAddDialog() {

        JDialog dialog = new JDialog(this, "Добавить запись", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2));


        JLabel surnameLabel = new JLabel("Фамилия:");
        JTextField surnameField = new JTextField();
        JLabel nameLabel = new JLabel("Имя:");
        JTextField nameField = new JTextField();
        JLabel patronymicLabel = new JLabel("Отчество:");
        JTextField patronymicField = new JTextField();
        JLabel sexLabel = new JLabel("Пол:");
        JTextField sexField = new JTextField();
        JLabel birthDayLabel = new JLabel("Дата рождения:");
        JTextField birthDayField = new JTextField();
        JLabel addressLabel = new JLabel("Адрес:");
        JTextField addressField = new JTextField();
        JLabel postLabel = new JLabel("Должность:");
        JTextField postField = new JTextField();

        inputPanel.add(surnameLabel);
        inputPanel.add(surnameField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(patronymicLabel);
        inputPanel.add(patronymicField);
        inputPanel.add(sexLabel);
        inputPanel.add(sexField);
        inputPanel.add(birthDayLabel);
        inputPanel.add(birthDayField);
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);
        inputPanel.add(postLabel);
        inputPanel.add(postField);


        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> {

            String surname = surnameField.getText();
            String name = nameField.getText();
            String patronymic = patronymicField.getText();
            String sex = sexField.getText();
            String birthDay = birthDayField.getText();
            String address = addressField.getText();
            String post = postField.getText();


            try {
                Connection connection = dbConnection.getMyConnection();
                Statement statement = connection.createStatement();
                String query = String.format("INSERT INTO employees (surname, name, patronymic, sex, birth_day, address, post) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", surname, name, patronymic, sex, birthDay, address, post);
                statement.executeUpdate(query);
                dialog.dispose();
                refreshTable();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });

        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(e -> dialog.dispose());


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);

    }

    private void refreshTable() {

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        try {

            Connection connection = dbConnection.getMyConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("id"));
                row.add(resultSet.getString("surname"));
                row.add(resultSet.getString("name"));
                row.add(resultSet.getString("patronymic"));
                row.add(resultSet.getString("sex"));
                row.add(resultSet.getDate("birth_day"));
                row.add(resultSet.getString("address"));
                row.add(resultSet.getString("post"));
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void deleteRow(int selectedRow) {

        int id = (int) table.getValueAt(selectedRow, 0);

        try {
            Connection connection = dbConnection.getMyConnection();
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM employees WHERE id = %d", id);
            statement.executeUpdate(query);
            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void showEditDialog(int selectedRow) {

        int id = (int) table.getValueAt(selectedRow, 0);
        String surname = (String) table.getValueAt(selectedRow, 1);
        String name = (String) table.getValueAt(selectedRow, 2);
        String patronymic = (String) table.getValueAt(selectedRow, 3);
        String sex = (String) table.getValueAt(selectedRow, 4);
        String birthDay = table.getValueAt(selectedRow, 5).toString();
        String address = (String) table.getValueAt(selectedRow, 6);
        String post = (String) table.getValueAt(selectedRow, 7);


        JDialog dialog = new JDialog(this, "Редактировать запись", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2));


        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(Integer.toString(id));
        idField.setEditable(false); // Запрещаем редактирование ID
        JLabel surnameLabel = new JLabel("Фамилия:");
        JTextField surnameField = new JTextField(surname);
        JLabel nameLabel = new JLabel("Имя:");
        JTextField nameField = new JTextField(name);
        JLabel patronymicLabel = new JLabel("Отчество:");
        JTextField patronymicField = new JTextField(patronymic);
        JLabel sexLabel = new JLabel("Пол:");
        JTextField sexField = new JTextField(sex);
        JLabel birthDayLabel = new JLabel("Дата рождения:");
        JTextField birthDayField = new JTextField(birthDay);
        JLabel addressLabel = new JLabel("Адрес:");
        JTextField addressField = new JTextField(address);
        JLabel postLabel = new JLabel("Должность:");
        JTextField postField = new JTextField(post);


        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(surnameLabel);
        inputPanel.add(surnameField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(patronymicLabel);
        inputPanel.add(patronymicField);
        inputPanel.add(sexLabel);
        inputPanel.add(sexField);
        inputPanel.add(birthDayLabel);
        inputPanel.add(birthDayField);
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);
        inputPanel.add(postLabel);
        inputPanel.add(postField);


        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> {

            String newSurname = surnameField.getText();
            String newName = nameField.getText();
            String newPatronymic = patronymicField.getText();
            String newSex = sexField.getText();
            String newBirthDay = birthDayField.getText();
            String newAddress = addressField.getText();
            String newPost = postField.getText();


            try {
                Connection connection = dbConnection.getMyConnection();
                Statement statement = connection.createStatement();
                String query = String.format("UPDATE employees SET surname = '%s', name = '%s', patronymic = '%s', sex = '%s', birth_day = '%s', address = '%s', post = '%s' WHERE id = %d", newSurname, newName, newPatronymic, newSex, newBirthDay, newAddress, newPost, id);
                statement.executeUpdate(query);
                dialog.dispose();
                refreshTable();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });

        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }

}