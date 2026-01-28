public class EmailNotifier implements Observer {
    @Override
    public void update(String orderStatus) {
        System.out.println("Gửi email: Đơn hàng đang ở trạng thái " + orderStatus);
    }
}
