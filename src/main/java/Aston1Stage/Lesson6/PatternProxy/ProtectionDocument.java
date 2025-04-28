package Aston1Stage.Lesson6.PatternProxy;

public class ProtectionDocument implements Document {
    private String user;
    private String password;
    private Document document;

    public ProtectionDocument(String user, String password, Document document) {
        this.user = user;
        this.password = password;
        this.document = document;
    }


    @Override
    public void read() {
        document.read();
    }

    @Override
    public void write() {
        document.write();
    }

    @Override
    public void clear() {
        if(user.equals("Aleksey") && password.equals("Aleksey")) {
            document.clear();
        }
        else {
            System.out.println("Unauthorized user.");
        }
    }
}
