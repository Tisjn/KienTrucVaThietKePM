public class DatabaseConnection {
    private static DatabaseConnection instance;

    // Private constructor để ngăn chặn khởi tạo từ bên ngoài
    private DatabaseConnection() {
        System.out.println("Đang thiết lập kết nối Database...");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void executeQuery(String query) {
        System.out.println("Thực thi: " + query);
    }
}