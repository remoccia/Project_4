import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.Format;
import java.text.NumberFormat;

/**
 * The type Custom j frame.
 */
public class CustomJFrame extends JFrame {
    private JLabel headingLabel = new JLabel("Personal Information");
    private JLabel firstNameLabel = new JLabel("First Name");
    private JLabel lastNameLabel = new JLabel("Last Name");
    private JLabel phoneNumberLabel = new JLabel("Phone Number");
    private JLabel emailLabel = new JLabel("Email");
    private JLabel dietaryLabel = new JLabel("Dietary Questions");
    private JLabel genderLabel = new JLabel("Sex");
    private JLabel waterLabel = new JLabel("How many cups of water do you drink per day?");
    private JLabel mealsLabel = new JLabel("How many meals do you eat per day?");
    private JLabel checkBoxLabel = new JLabel("Do these meals regularly contain?");
    private JLabel walkLabel = new JLabel("How much do you walk per day?");
    private JLabel weightLabel = new JLabel("How much do you weigh?");
    private JTextField firstNameTextField = new JTextField(20);
    private JTextField lastNameTextField = new JTextField(20);
    private JTextField phoneNumberTextField = new JTextField(20);
    private JTextField emailTextField = new JTextField(20);
    private JRadioButton maleRadioButton = new JRadioButton("Male");
    private JRadioButton femaleRadioButton = new JRadioButton("Female");
    private JRadioButton preferRadioButton = new JRadioButton("Prefer not to say");
    private ButtonGroup radioButtonGroup = new ButtonGroup();
    /**
     * The Spinner number mode.
     */
    SpinnerNumberModel spinnerNumberMode = new SpinnerNumberModel(15, 0, 50, 1);
    private JSpinner waterIntakeSpinner = new JSpinner(spinnerNumberMode);
    private JSlider mealSlider = new JSlider(0, 10, 3);
    private JCheckBox wheatCheckBox = new JCheckBox("Wheat");
    private JCheckBox sugarCheckBox = new JCheckBox("Sugar");
    private JCheckBox dairyCheckBox = new JCheckBox("Dairy");
    private String[] walkOptions = {"Less than one mile", "More than one mile but less than two miles",
            "More than two miles but less than three", "More than three miles"};
    private JComboBox walkComboBox = new JComboBox(walkOptions);
    private JFormattedTextField weightFormattedTextField = new JFormattedTextField();
    private JButton clearButton = new JButton("Clear");
    private JButton submitButton = new JButton("Submit");

    private FileHandler fileHandler = new FileHandler();
    private Integer weight = null;

    /**
     * The type Inner action listener.
     */
    public class InnerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton button = (JButton) e.getSource();

            String name = button.getName();

            if (name.equals("submit")) {
                try {
                    String firstName = firstNameTextField.getText();
                    String lastName = lastNameTextField.getText();
                    String phoneNumber = phoneNumberTextField.getText();
                    String email = emailTextField.getText();
                    String gender = getGender();
                    int waterIntake = (Integer) waterIntakeSpinner.getValue();
                    int mealIntake = mealSlider.getValue();
                    boolean dairy = dairyCheckBox.isSelected();
                    boolean wheat = wheatCheckBox.isSelected();
                    boolean sugar = sugarCheckBox.isSelected();
                    String walkAmt = getWalkString();
                    try {
                        weight = Integer.parseInt(weightFormattedTextField.getText());
                    } catch (NumberFormatException ex) {
                        weightFormattedTextField.setText("");
                        weight = null;
                    }

                    fileHandler.writeResults(firstName + "," + lastName + "," + phoneNumber + "," +
                            email + "," + gender + "," + waterIntake + "," + mealIntake + "," + dairy + "," +
                            wheat + "," + sugar + "," + walkAmt + "," + weight);


                } catch (IOException ignored) {

                }
                clearForm();
            } else {
                clearForm();
            }
        }
    }

    private void clearForm() {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        phoneNumberTextField.setText("");
        emailTextField.setText("");
        radioButtonGroup.clearSelection();
        waterIntakeSpinner.setValue(15);
        mealSlider.setValue(3);
        dairyCheckBox.setSelected(false);
        wheatCheckBox.setSelected(false);
        sugarCheckBox.setSelected(false);
        walkComboBox.setSelectedIndex(0);
        weightFormattedTextField.setText("");
    }




    private String getGender() {
        if (maleRadioButton.isSelected()) {
            return maleRadioButton.getText();
        } else if (femaleRadioButton.isSelected()) {
            return femaleRadioButton.getText();
        } else if (preferRadioButton.isSelected()) {
            return preferRadioButton.getText();
        }
        return null;
    }

    private String getWalkString() {
        if (walkComboBox.getSelectedIndex() == 0) {
            return walkOptions[0];
        } else if (walkComboBox.getSelectedIndex() == 1) {
            return walkOptions[1];
        } else if (walkComboBox.getSelectedIndex() == 2) {
            return walkOptions[2];
        } else if (walkComboBox.getSelectedIndex() == 3) {
            return walkOptions[3];
        }
        return null;
    }

    /**
     * Instantiates a new Custom j frame.
     *
     * @throws IOException the io exception
     */
    CustomJFrame() throws IOException {
        setTitle("Dietary Survey");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        //labels
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(headingLabel, gbc);
        gbc.gridy = 1;
        this.add(firstNameLabel, gbc);
        gbc.gridy = 2;
        this.add(lastNameLabel, gbc);
        gbc.gridy = 3;
        this.add(phoneNumberLabel, gbc);
        gbc.gridy = 4;
        this.add(emailLabel, gbc);
        gbc.gridy = 5;
        this.add(genderLabel, gbc);
        gbc.gridy = 9;
        this.add(dietaryLabel, gbc);
        gbc.gridy = 12;
        this.add(waterLabel, gbc);
        gbc.gridy = 14;
        this.add(mealsLabel, gbc);
        gbc.gridy = 17;
        this.add(checkBoxLabel, gbc);
        gbc.gridy = 21;
        this.add(weightLabel, gbc);
        gbc.gridy = 22;
        this.add(walkLabel, gbc);
        gbc.gridy = 24;
        this.add(weightLabel, gbc);

        //text fields
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(firstNameTextField, gbc);
        gbc.gridy = 2;
        this.add(lastNameTextField, gbc);
        gbc.gridy = 3;
        this.add(phoneNumberTextField, gbc);
        gbc.gridy = 4;
        this.add(emailTextField, gbc);
        //buttons
        radioButtonGroup.add(maleRadioButton);
        radioButtonGroup.add(femaleRadioButton);
        radioButtonGroup.add(preferRadioButton);
        gbc.gridy = 5;
        this.add(maleRadioButton, gbc);
        gbc.gridy = 7;
        this.add(femaleRadioButton, gbc);
        gbc.gridy = 8;
        this.add(preferRadioButton, gbc);
        gbc.gridy = 25;
        gbc.gridx = 0;

        weightFormattedTextField.setColumns(10);
        this.add(weightFormattedTextField, gbc);
        gbc.gridy = 26;
        this.add(submitButton, gbc);
        gbc.gridy = 27;
        this.add(clearButton, gbc);

        //spinners
        gbc.gridy = 13;
        gbc.gridx = 0;
        this.add(waterIntakeSpinner, gbc);
        //sliders
        mealSlider.setMajorTickSpacing(1);
        mealSlider.setPaintTicks(true);
        mealSlider.setPaintLabels(true);
        gbc.gridy = 15;
        this.add(mealSlider, gbc);
        //checkbox
        gbc.gridy = 18;
        this.add(dairyCheckBox, gbc);
        gbc.gridy = 19;
        this.add(wheatCheckBox, gbc);
        gbc.gridy = 20;
        this.add(sugarCheckBox, gbc);
        //dropbox
        gbc.gridy = 23;
        this.add(walkComboBox, gbc);
        //done making GUI

        submitButton.setName("submit");
        clearButton.setName("clear");

        InnerActionListener innerActionListener = new InnerActionListener();

        submitButton.addActionListener(innerActionListener);
        clearButton.addActionListener(innerActionListener);


        this.pack();
        setVisible(true);

    }
}

