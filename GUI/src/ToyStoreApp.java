import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ToyStoreApp extends JFrame {
    private DefaultListModel<String> toyListModel;
    private JList<String> toyList;
    private JTextField toyNameField;
    private JTextArea logTextArea;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JComboBox<Integer> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<Integer> yearComboBox;
    private final String dataFilePath = "src/res/toyStore.txt";

    public ToyStoreApp() {
        setTitle("Регистрация поступлений в магазин игрушек");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        // Create list model
        toyListModel = new DefaultListModel<>();

        // Create JList
        toyList = new JList<>(toyListModel);

        // Create text field
        toyNameField = new JTextField();

        // Create text area
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);

        // Create radio buttons
        maleRadioButton = new JRadioButton("Мальчиков");
        femaleRadioButton = new JRadioButton("Девочек");

        // Create day combo box
        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) {
            days[i] = i + 1;
        }
        dayComboBox = new JComboBox<>(days);

        // Create month combo box
        String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        monthComboBox = new JComboBox<>(months);

        // Create year combo box
        Integer[] years = new Integer[100];
        for (int i = 0; i < 100; i++) {
            years[i] = 2023 - i;
        }
        yearComboBox = new JComboBox<>(years);

        // Create buttons
        JButton addButton = new JButton("Добавить");
        JButton saveButton = new JButton("Сохранить");
        JButton loadButton = new JButton("Загрузить");

        // Add button listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String toyName = toyNameField.getText();
                String category = maleRadioButton.isSelected() ? "Для мальчиков" : "Для девочек";
                int day = (int) dayComboBox.getSelectedItem();
                String month = (String) monthComboBox.getSelectedItem();
                int year = (int) yearComboBox.getSelectedItem();
                String entry = toyName + " (" + category + ") - " + day + " " + month + " " + year;

                if (!toyName.isEmpty()) {
                    toyListModel.addElement(entry);
                    logTextArea.append("Добавлена игрушка: " + entry + "\n");
                    toyNameField.setText("");
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToyListToFile();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadToyListFromFile();
            }
        });

        // Create labels
        JLabel toyNameLabel = new JLabel("Название игрушки:");
        JLabel categoryLabel = new JLabel("Предназначена для:");
        JLabel dateLabel = new JLabel("Дата поступления:");
        JLabel itemLabel = new JLabel("Товары:");
        JLabel logLabel = new JLabel("Журнал событий:");

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(toyNameLabel);
        inputPanel.add(toyNameField);
        inputPanel.add(categoryLabel);
        inputPanel.add(maleRadioButton);
        inputPanel.add(new JLabel());
        inputPanel.add(femaleRadioButton);
        inputPanel.add(dateLabel);
        JPanel datePanel = new JPanel();
        datePanel.add(dayComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(yearComboBox);
        inputPanel.add(datePanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.add(itemLabel, BorderLayout.NORTH);
        itemPanel.add(new JScrollPane(toyList), BorderLayout.CENTER);

        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.add(logLabel, BorderLayout.NORTH);
        logPanel.add(new JScrollPane(logTextArea), BorderLayout.CENTER);



        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(itemPanel);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(logPanel, BorderLayout.EAST);

        setContentPane(mainPanel);
    }

    private void saveToyListToFile() {
        File file = new File(dataFilePath);
        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < toyListModel.size(); i++) {
                writer.write(toyListModel.getElementAt(i) + "\n");
            }
            writer.flush();
            logTextArea.append("Сохранено в файл: " + file.getAbsolutePath() + "\n");
        } catch (IOException e) {
            logTextArea.append("Ошибка при сохранении файла\n");
        }
    }

    private void loadToyListFromFile() {
        File file = new File(dataFilePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            toyListModel.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                toyListModel.addElement(line);
            }
            logTextArea.append("Загружено из файла: " + file.getAbsolutePath() + "\n");
        } catch (IOException e) {
            logTextArea.append("Ошибка при загрузке файла\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToyStoreApp app = new ToyStoreApp();
            app.setVisible(true);
        });
    }
}