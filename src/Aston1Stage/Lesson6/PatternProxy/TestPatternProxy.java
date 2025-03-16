package Aston1Stage.Lesson6.PatternProxy;

public class TestPatternProxy {
    public static void main(String[] args) {
        Document document = new XMLDocument("<?xml version=\"1.0\" encoding=\"UTF-8\">");
        ProtectionDocument protectionDocument1 = new ProtectionDocument("Aleksey", "Aleksey", document);
        ProtectionDocument protectionDocument2 = new ProtectionDocument("Alex", "Alex", document);

        protectionDocument1.read();
        protectionDocument1.write();
        protectionDocument1.clear();

        protectionDocument2.read();
        protectionDocument2.write();
        protectionDocument2.clear();
    }
}
