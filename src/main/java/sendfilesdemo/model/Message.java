package sendfilesdemo.model;

import java.util.List;

public class Message {
    private final String sender;
    private final String recepient;
    private final List<String> files;
    private final String subject;
    private final String message;

    public Message(String sender, String recepient, List<String> files, String subject, String message) {
        this.sender = sender;
        this.recepient = recepient;
        this.files = files;
        this.subject = subject;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public String getRecepient() {
        return recepient;
    }

    public List<String> getFiles() {
        return files;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
