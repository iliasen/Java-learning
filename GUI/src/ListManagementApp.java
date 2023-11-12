import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListManagementApp extends JFrame {
    private JList<String> firstList;
    private JList<String> secondList;
    private JList<String> thirdList;
    private DefaultListModel<String> firstListModel;
    private DefaultListModel<String> secondListModel;
    private DefaultListModel<String> thirdListModel;

    public ListManagementApp() {
        setTitle("Перетащика");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 300);
        setLocationRelativeTo(null);

        // Create list models
        firstListModel = new DefaultListModel<>();
        secondListModel = new DefaultListModel<>();
        thirdListModel = new DefaultListModel<>();

        // Add items
        firstListModel.addElement("Item 1");
        firstListModel.addElement("Item 2");
        firstListModel.addElement("Item 3");

        // Create JLists
        firstList = new JList<>(firstListModel);
        secondList = new JList<>(secondListModel);
        thirdList = new JList<>(thirdListModel);

        enableDragAndDrop(firstList);
        enableDragAndDrop(secondList);
        enableDragAndDrop(thirdList);

        // Create buttons
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newItem = JOptionPane.showInputDialog("Enter a new item:");
                if (newItem != null && !newItem.isEmpty()) {
                    firstListModel.addElement(newItem);
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = firstList.getSelectedValue();
                if (selectedItem != null) {
                    String editedItem = JOptionPane.showInputDialog("Edit item:", selectedItem);
                    if (editedItem != null && !editedItem.isEmpty()) {
                        firstListModel.setElementAt(editedItem, firstList.getSelectedIndex());
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = firstList.getSelectedValue();
                if (selectedItem != null) {
                    firstListModel.removeElement(selectedItem);
                }
            }
        });

        JButton secondListButton = new JButton("Move from 1st to 2nd");
        secondListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = firstList.getSelectedValue();
                if (selectedItem != null && !secondListModel.contains(selectedItem)) {
                    moveItem(firstListModel, secondListModel, selectedItem);
                }
            }
        });

        JButton thirdListButton = new JButton("Move from 2nd to 3rd");
        thirdListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = secondList.getSelectedValue();
                if (selectedItem != null && !thirdListModel.contains(selectedItem)) {
                    moveItem(secondListModel, thirdListModel, selectedItem);
                }
            }
        });

        JButton firstListButton = new JButton("Move from 3rd to 1st");
        firstListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = thirdList.getSelectedValue();
                if (selectedItem != null && !firstListModel.contains(selectedItem)) {
                    moveItem(thirdListModel, firstListModel, selectedItem);
                }
            }
        });

        // Create buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(secondListButton);
        buttonPanel.add(thirdListButton);
        buttonPanel.add(firstListButton);

        // Create list containers
        JPanel listContainer = new JPanel(new GridLayout(1, 3));
        listContainer.add(new JScrollPane(firstList));
        listContainer.add(new JScrollPane(secondList));
        listContainer.add(new JScrollPane(thirdList));

        // Create main container
        JPanel container = new JPanel(new BorderLayout());
        container.add(listContainer, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);

        // Set the container as the content pane of the frame
        setContentPane(container);
    }

    private void enableDragAndDrop(JList<String> list) {
        list.setDragEnabled(true);
        list.setTransferHandler(new TransferHandler() {
        });
    }

    private void moveItem(DefaultListModel<String> sourceModel, DefaultListModel<String> destinationModel, String item) {
        sourceModel.removeElement(item);
        destinationModel.addElement(item);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ListManagementApp app = new ListManagementApp();
            app.setVisible(true);
        });
    }
}