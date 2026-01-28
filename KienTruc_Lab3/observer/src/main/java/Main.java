public class Main {
    public static void main(String[] args) {
        Order order = new Order();

        Observer email = new EmailNotifier();
        Observer sms = new SmsNotifier();

        order.attach(email);
        order.attach(sms);

        order.setStatus("Mới tạo");
        order.setStatus("Đang giao");
    }
}
