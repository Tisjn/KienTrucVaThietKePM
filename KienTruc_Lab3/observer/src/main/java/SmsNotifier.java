public class SmsNotifier implements Observer {
    @Override
    public void update(String orderStatus) {
        System.out.println("Gửi SMS: Đơn hàng đang ở trạng thái " + orderStatus);
    }
}
