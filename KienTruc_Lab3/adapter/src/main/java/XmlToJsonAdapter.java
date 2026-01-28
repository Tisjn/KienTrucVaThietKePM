public class XmlToJsonAdapter implements JsonService {

    private XmlService xmlService;

    public XmlToJsonAdapter(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    @Override
    public String getJson() {
        String xml = xmlService.getXml();
        // Giả lập convert
        return "{ \"name\": \"Lee\" }";
    }
}
