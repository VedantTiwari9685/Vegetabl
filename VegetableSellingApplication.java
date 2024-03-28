import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class VegetableSellingApplication {
    private Frame frame;
    private Frame frameSeller;
    private TextArea vegetableTextArea;
    private TextArea billTableTextArea;
    private TextArea totalPriceTextArea;
    public void run(){
        // Frame
        frame = new Frame("Vegetable Selling Application");
        frameSeller = new Frame("Manager");
        // Veg Table
        Label vegTableHeading = new Label("AVAILABLE VEGGIES");
        Label vegIdLabel = new Label("Id");
        Label vegNameLabel = new Label("Name");
        Label vegQuantityLabel = new Label("Quantity(Kg)");
        Label vegPriceLabel = new Label("Price(₹)");

        // 2nd Frame
        Label sellerHeading  = new Label("MANAGE VEGGIES");
        Label sellerIdLabel = new Label("Id");
        TextField sellerIdField = new TextField();
        Label sellerNameLabel = new Label("Name");
        TextField sellerNameField = new TextField();
        Label sellerQuantityLabel = new Label("Quantity(Kg)");
        TextField sellerQuantityField = new TextField();
        Label sellerPriceLabel = new Label("Price(₹)");
        TextField sellerPriceField = new TextField();
        Label sellerBlankLabel = new Label(" ");
        Button sellerAddVegeButton = new Button("New Veggie");
        Button sellerUpdateVegeButton = new Button("Add Quantity");

        sellerHeading.setBounds(70,40,140,20);
        sellerIdLabel.setBounds(40,70,70,20);
        sellerIdField.setBounds(130,70,70,20);
        sellerNameLabel.setBounds(40,100,70,20);
        sellerNameField.setBounds(130,100,70,20);
        sellerQuantityLabel.setBounds(40,130,70,20);
        sellerQuantityField.setBounds(130,130,70,20);
        sellerPriceLabel.setBounds(40,160,70,20);
        sellerPriceField.setBounds(130,160,70,20);
        sellerAddVegeButton.setBounds(40,200,70,30); // Input Button
        sellerUpdateVegeButton.setBounds(130,200,70,30); // Input Button
        sellerBlankLabel.setBounds(20,380,20,20);

        frameSeller.add(sellerHeading);
        frameSeller.add(sellerIdLabel);
        frameSeller.add(sellerIdField);
        frameSeller.add(sellerNameLabel);
        frameSeller.add(sellerNameField);
        frameSeller.add(sellerQuantityLabel);
        frameSeller.add(sellerQuantityField);
        frameSeller.add(sellerPriceLabel);
        frameSeller.add(sellerPriceField);
        frameSeller.add(sellerAddVegeButton);
        frameSeller.add(sellerUpdateVegeButton);
        frameSeller.add(sellerBlankLabel);  // blank
        vegetableTextArea = new TextArea();

        // Input Section
        Label vegIdInputLabel = new Label("ID : ");
        TextField vegIdInputField = new TextField();
        Label vegQuantityInputLabel = new Label("Quantity : ");
        TextField vegQuantityInputField = new TextField();
        Button addVegInputButton = new Button("Add");

        // Bill Table
        Label billTableHeading = new Label("BILL");
        Label billIdLabel = new Label("Id");
        Label billNameLabel = new Label("Name");
        Label billQuantityLabel = new Label("Quantity(kg)");
        Label billPriceLabel = new Label("Price(₹)");
        billTableTextArea = new TextArea();

        Label billTotalPriceLabel = new Label("Total Price: "); // use .setText to set Output
        Label blankLabel = new Label(" ");
        Button confirmPurchaseButton = new Button("Confirm Purchase");
        totalPriceTextArea = new TextArea();

        // Veg Table Bounds
        vegTableHeading.setBounds(110, 40, 140, 20); // Heading
        vegIdLabel.setBounds(40,70,20,20); // Id
        vegNameLabel.setBounds(80,70,40,20); // Name
        vegQuantityLabel.setBounds(145,70,70,20); // Quantity
        vegPriceLabel.setBounds(240,70,50,20); // Price
        vegetableTextArea.setBounds(20,90,290,150);

        // Input Area Bounds
        vegIdInputLabel.setBounds(60,270,60,20); // I'd
        vegIdInputField.setBounds(140,270,115,20); // I'd field
        vegQuantityInputLabel.setBounds(60,300,60,20); // Quantity
        vegQuantityInputField.setBounds(140,300,115,20); // Quantity field

        // Add button
        addVegInputButton.setBounds(130,340,65,20); // Input Button

        // Bill table
        billTableHeading.setBounds(140,370,50,20); // Heading
        billIdLabel.setBounds(40,400,20,20); // Id
        billNameLabel.setBounds(80,400,40,20); // Name
        billQuantityLabel.setBounds(145,400,70,20); // Quantity
        billPriceLabel.setBounds(240,400,50,20); // Price
        billTableTextArea.setBounds(20,420,290,150); // Text Area
        billTotalPriceLabel.setBounds(160,580,60,20); // This will be changed
        confirmPurchaseButton.setBounds(30,650,270,30); // Confirm button
        blankLabel.setBounds(20,670,20,20); // Blank Label
        totalPriceTextArea.setBounds(240,580,70,20); // total price text area


        // Adding Veg objects to frame
        frame.add(vegTableHeading); // Heading
        frame.add(vegIdLabel); // Id
        frame.add(vegNameLabel); // Name
        frame.add(vegQuantityLabel); // Quantity
        frame.add(vegPriceLabel); // Price
        frame.add(vegetableTextArea); // TextArea

        // Adding Input objects to frame
        frame.add(vegIdInputLabel); // Id label
        frame.add(vegIdInputField); //
        frame.add(vegQuantityInputLabel); // Quantity
        frame.add(vegQuantityInputField);

        // Add item button
        frame.add(addVegInputButton); // Add button

        // Bill table
        frame.add(billTableHeading); // Heading
        frame.add(billIdLabel); // Id
        frame.add(billNameLabel); // Name
        frame.add(billQuantityLabel); // Quantity
        frame.add(billPriceLabel); // Price
        frame.add(billTableTextArea); // Text Area
        frame.add(billTotalPriceLabel); // Total price
        frame.add(totalPriceTextArea);
        frame.add(confirmPurchaseButton); // Confirm button
        frame.add(blankLabel);

          // Frame
        frame.setSize(370, 800);
        frame.setBackground(Color.lightGray);
        frame.setVisible(true);
        frameSeller.setSize(270, 300);
        frameSeller.setBackground(Color.pink);
        frameSeller.setVisible(true);

        // Backend

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String JDBC_URL = "jdbc:mysql://localhost:3306/JavaAsn1";
            String USERNAME = "root";
            String PASSWORD = "Aakriti@Tiwari@0550";

            Connection connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
            Statement statement = connection.createStatement();
            getVegetableNames(statement);
            addVegInputButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int inputVegId = Integer.parseInt(vegIdInputField.getText());
                        int inputVegQuantity = Integer.parseInt(vegQuantityInputField.getText());
                        changeVegTable(connection, statement, inputVegQuantity, inputVegId);
                        changeBillTable(connection, statement, inputVegQuantity, inputVegId);
                        getVegBillTable(statement);
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            confirmPurchaseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int totalPrice = totalPriceCalculator(connection, statement);
                        totalPriceTextAdder(totalPriceTextArea,totalPrice);
                        truncateVegBillTable(statement);
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            sellerAddVegeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int inputSellerId = Integer.parseInt(sellerIdField.getText());
                        String inputSellerName = sellerNameField.getText();
                        int inputSellerQuantity = Integer.parseInt(sellerQuantityField.getText());
                        int inputSellerPrice = Integer.parseInt(sellerPriceField.getText());
                        sellerAddButton(connection,statement,inputSellerId,inputSellerName,inputSellerQuantity,inputSellerPrice);

                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            sellerUpdateVegeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int inputSellerId = Integer.parseInt(sellerIdField.getText());
                        int inputSellerQuantity = Integer.parseInt(sellerQuantityField.getText());
                        sellerUpdateButton(connection,statement,inputSellerId,inputSellerQuantity);

                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            frame.setLayout(null);
            frameSeller.setLayout(null);
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new VegetableSellingApplication().run();
    }

    private void getVegetableNames(Statement statement) throws SQLException{
        String getVegetablesInfoQuerry = "select * from vegetables;";
        ResultSet resultSet = statement.executeQuery(getVegetablesInfoQuerry);
        StringBuilder result = new StringBuilder("\n");
        while (resultSet.next()) {
            result.append("   ").append(resultSet.getInt("id"))
                    .append("         ").append(resultSet.getString("name"))
                    .append("                 ").append(resultSet.getInt("quantity"))
                    .append("                     ").append(resultSet.getInt("price"))
                    .append("\n");
        }
        vegetableTextArea.setText(result.toString());
    }
    private void changeVegTable(Connection connection, Statement statement, int inputVegQuantity, int inputVegId) throws NumberFormatException, SQLException {
        // SQL query to subtract quantity
        String sql = "UPDATE vegetables SET quantity = quantity - ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set parameters
            preparedStatement.setInt(1, inputVegQuantity);
            preparedStatement.setInt(2, inputVegId);
            int rowsAffected = preparedStatement.executeUpdate();
            getVegetableNames(statement);
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }
    }

    private void changeBillTable(Connection connection, Statement statement, int inputVegQuantity, int inputVegId) throws SQLException {
        String displayQuery = "SELECT * FROM vegetables WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(displayQuery)) {
            // Set parameter for the placeholder
            preparedStatement.setInt(1, inputVegId);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int price = resultSet.getInt("price");
                    price = price * inputVegQuantity;
                    insertVegBillTable(connection, statement, inputVegQuantity, id, name, price);
                } else {
                    System.out.println("No data found for the given ID.");
                }
            }
        }
    }

    private void insertVegBillTable(Connection connection, Statement statement, int inputVegQuantity, int id, String name, int price) throws NumberFormatException, SQLException {
        // SQL query to subtract quantity
        String sql = "insert into vegBill values ( ?, ? , ? , ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set parameters
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,inputVegQuantity);
            preparedStatement.setInt(4,price);
            int rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }
    }

    private void getVegBillTable(Statement statement) throws SQLException{
        String getVegetablesBillInfoQuerry = "select * from vegBill;";
        ResultSet resultSet = statement.executeQuery(getVegetablesBillInfoQuerry);
        StringBuilder result = new StringBuilder("\n");
        while (resultSet.next()) {
            result.append("   ").append(resultSet.getInt("id"))
                    .append("         ").append(resultSet.getString("name"))
                    .append("                 ").append(resultSet.getInt("quantity"))
                    .append("                     ").append(resultSet.getInt("price"))
                    .append("\n");
        }
        billTableTextArea.setText(result.toString());
    }

    private int totalPriceCalculator(Connection connection, Statement statement) throws SQLException {
        String displayQuery = "SELECT * FROM vegBill;";
        int totalPrice = 0;
        try (ResultSet resultSet = statement.executeQuery(displayQuery)) {
            while (resultSet.next()) {
                totalPrice += resultSet.getInt("price");
            }
            if (totalPrice > 0) {
                // Items are added to the cart
                return totalPrice;
            } else {
                System.out.println("No item added to the cart");
                return totalPrice;
            }
        }
    }

    private void totalPriceTextAdder(TextArea totalPriceTextArea,int totalPrice){
        totalPriceTextArea.append(String.valueOf("₹"+totalPrice));
    }
    private void truncateVegBillTable(Statement statement) throws SQLException{
        String truncateQuerry = "truncate vegBill;";
        statement.execute(truncateQuerry);
    }

    private void sellerAddButton(Connection connection, Statement statement, int id, String name, int quantity, int price) throws NumberFormatException, SQLException {
        // SQL query to subtract quantity
        String sql = "insert into vegetables values ( ?, ? , ? , ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set parameters
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,quantity);
            preparedStatement.setInt(4,price);
            int rowsAffected = preparedStatement.executeUpdate();
            getVegetableNames(statement);
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }
    }

    private void sellerUpdateButton(Connection connection, Statement statement, int id, int quantity) throws NumberFormatException, SQLException {
        // SQL query to subtract quantity
        String sql = "UPDATE vegetables SET quantity = quantity + ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set parameters
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, id);
            int rowsAffected = preparedStatement.executeUpdate();
            getVegetableNames(statement);
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }
    }
}