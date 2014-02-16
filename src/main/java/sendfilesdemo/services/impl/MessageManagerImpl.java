package sendfilesdemo.services.impl;

import org.springframework.stereotype.Component;
import sendfilesdemo.model.Message;
import sendfilesdemo.services.MessageManager;

import java.util.HashMap;

/**
 * @author kit
 *         Date: 16.02.14
 */
@Component
public class MessageManagerImpl implements MessageManager {

    private int idCounter;

    HashMap<String, Message> messages = new HashMap<>();

    @Override
    public String create(Message message) {
        String id = Integer.toString(idCounter++);
        messages.put(id, message);
        return id;
    }

    @Override
    public Message get(String messageId) {
        return messages.get(messageId);
    }
}
