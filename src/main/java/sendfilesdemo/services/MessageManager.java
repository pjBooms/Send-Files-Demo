package sendfilesdemo.services;

import sendfilesdemo.model.Message;

/**
 * @author kit
 */
public interface MessageManager {

    public String create(Message message);

    Message get(String messageId);
}
