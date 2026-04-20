import java.sql.*;

public class RestaurantMenuCRUD {

    // Change according to your MySQL setup
    static final String URL = "jdbc:mysql://localhost:3306/restaurantdb";
    static final String USER = "root";
    static final String PASSWORD = "password";

    public static void main(String[] args) {

        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Connected to MySQL Database Successfully.\n");

            createTables(con);
            insertRestaurantRecords(con);
            insertMenuRecords(con);

            System.out.println("===== ALL RESTAURANTS =====");
            printTable(con, "SELECT * FROM Restaurant");

            System.out.println("\n===== ALL MENU ITEMS =====");
            printTable(con, "SELECT * FROM MenuItem");

            System.out.println("\n===== MENU ITEMS WHERE PRICE <= 100 =====");
            printTable(con, "SELECT * FROM MenuItem WHERE Price <= 100");

            System.out.println("\n===== MENU ITEMS AVAILABLE IN RESTAURANT 'Cafe Java' =====");
            printTable(con,
                    "SELECT m.* FROM MenuItem m " +
                    "JOIN Restaurant r ON m.ResId = r.Id " +
                    "WHERE r.Name = 'Cafe Java'");

            updateMenuPrices(con);

            System.out.println("\n===== AFTER UPDATE =====");
            printTable(con, "SELECT * FROM MenuItem");

            deleteItemsStartingWithP(con);

            System.out.println("\n===== AFTER DELETE =====");
            printTable(con, "SELECT * FROM MenuItem");

            con.close();
            System.out.println("Connection Closed Successfully.");

        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    // Create Tables
    static void createTables(Connection con) throws SQLException {

        Statement st = con.createStatement();

        st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Restaurant (" +
                        "Id INT PRIMARY KEY," +
                        "Name VARCHAR(50)," +
                        "Address VARCHAR(100))");

        st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS MenuItem (" +
                        "Id INT PRIMARY KEY," +
                        "Name VARCHAR(50)," +
                        "Price DOUBLE," +
                        "ResId INT," +
                        "FOREIGN KEY (ResId) REFERENCES Restaurant(Id))");

        System.out.println("Tables created successfully.\n");

        st.close();
    }

    // Insert Restaurant Records
    static void insertRestaurantRecords(Connection con) throws SQLException {

        Statement clear = con.createStatement();

        clear.executeUpdate("DELETE FROM MenuItem");
        clear.executeUpdate("DELETE FROM Restaurant");

        clear.close();

        String sql = "INSERT INTO Restaurant VALUES (?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        String[][] data = {
                {"1", "Cafe Java", "Pune"},
                {"2", "Food Hub", "Mumbai"},
                {"3", "Spice Villa", "Delhi"},
                {"4", "Burger Point", "Nagpur"},
                {"5", "Pizza Town", "Chennai"},
                {"6", "Tasty Treat", "Hyderabad"},
                {"7", "Meal Box", "Bangalore"},
                {"8", "Quick Bites", "Surat"},
                {"9", "Urban Cafe", "Lucknow"},
                {"10", "Royal Dine", "Indore"}
        };

        for (String[] row : data) {
            ps.setInt(1, Integer.parseInt(row[0]));
            ps.setString(2, row[1]);
            ps.setString(3, row[2]);
            ps.executeUpdate();
        }

        System.out.println("10 Restaurant records inserted successfully.\n");

        ps.close();
    }

    // Insert MenuItem Records
    static void insertMenuRecords(Connection con) throws SQLException {

        String sql = "INSERT INTO MenuItem VALUES (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        Object[][] data = {
                {1, "Pasta", 90, 1},
                {2, "Burger", 120, 2},
                {3, "Pizza", 80, 1},
                {4, "Noodles", 150, 3},
                {5, "Paneer Roll", 70, 4},
                {6, "Coffee", 60, 1},
                {7, "Sandwich", 110, 5},
                {8, "Pastry", 95, 6},
                {9, "Tea", 40, 1},
                {10, "Fries", 130, 7}
        };

        for (Object[] row : data) {

            ps.setInt(1, Integer.parseInt(row[0].toString()));
            ps.setString(2, row[1].toString());
            ps.setDouble(3, Double.parseDouble(row[2].toString()));
            ps.setInt(4, Integer.parseInt(row[3].toString()));

            ps.executeUpdate();
        }

        System.out.println("10 MenuItem records inserted successfully.\n");

        ps.close();
    }

    // Update Prices <=100 to 200
    static void updateMenuPrices(Connection con) throws SQLException {

        String sql = "UPDATE MenuItem SET Price = 200 WHERE Price <= 100";

        PreparedStatement ps = con.prepareStatement(sql);

        int rows = ps.executeUpdate();

        System.out.println(rows + " record(s) updated successfully.\n");

        ps.close();
    }

    // Delete Names starting with P
    static void deleteItemsStartingWithP(Connection con) throws SQLException {

        String sql = "DELETE FROM MenuItem WHERE Name LIKE ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, "P%");

        int rows = ps.executeUpdate();

        System.out.println(rows + " record(s) deleted successfully.\n");

        ps.close();
    }

    // Print Table in Tabular Format
    static void printTable(Connection con, String query) throws SQLException {

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(query);

        ResultSetMetaData md = rs.getMetaData();

        int columns = md.getColumnCount();

        for (int i = 1; i <= columns; i++) {
            System.out.printf("%-18s", md.getColumnName(i));
        }

        System.out.println();

        for (int i = 1; i <= columns; i++) {
            System.out.print("------------------");
        }

        System.out.println();

        while (rs.next()) {

            for (int i = 1; i <= columns; i++) {
                System.out.printf("%-18s", rs.getString(i));
            }

            System.out.println();
        }

        System.out.println();

        rs.close();
        st.close();
    }
}