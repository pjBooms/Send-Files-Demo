package sendfilesdemo.services;

import org.springframework.stereotype.Component;
import sendfilesdemo.model.Message;

import java.util.HashMap;

/**
 * @author kit
 */
@Component
public class MessageManager {

    HashMap<String, Message> messages = new HashMap<>();
    private int idCounter;

    public String create(Message message) {
        String id = Integer.toString(idCounter++);
        messages.put(id, message);
        return id;
    }

    public Message get(String messageId) {
        return messages.get(messageId);
    }
}
