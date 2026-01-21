// Interface chung
interface Material {
    void display();
}

// Các lớp thực thi cụ thể
class VideoMaterial implements Material {
    public void display() { System.out.println("Đang phát Video bài giảng..."); }
}

class DocumentMaterial implements Material {
    public void display() { System.out.println("Đang hiển thị tài liệu PDF..."); }
}

// Factory Class
class MaterialFactory {
    public static Material createMaterial(String type) {
        if (type.equalsIgnoreCase("VIDEO")) return new VideoMaterial();
        if (type.equalsIgnoreCase("PDF")) return new DocumentMaterial();
        throw new IllegalArgumentException("Loại học liệu không hợp lệ");
    }
}