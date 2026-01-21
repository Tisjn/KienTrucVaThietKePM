public class CourseApp {
    public static void main(String[] args) {
        // 1. Sử dụng Singleton để lấy kết nối DB
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.executeQuery("SELECT * FROM courses");

        // 2. Sử dụng Factory để tạo học liệu dựa trên yêu cầu người dùng
        Material myVideo = MaterialFactory.createMaterial("VIDEO");
        myVideo.display();

        Material myPdf = MaterialFactory.createMaterial("PDF");
        myPdf.display();
    }
}