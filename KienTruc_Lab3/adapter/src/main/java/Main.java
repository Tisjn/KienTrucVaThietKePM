public class Main {
    public static void main(String[] args) {
        JsonService service =
                new XmlToJsonAdapter(new XmlService());

        System.out.println(service.getJson());
    }
}
