package sendfilesdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sendfilesdemo.model.Message;
import sendfilesdemo.services.exceptions.ResourceNotFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author kit
 */
@Component
public class SendFilesManager {

    @Autowired
    MessageManager messageManager;
    @Autowired
    FilesManager filesManager;

    public String createMessage(Message message) {
        return messageManager.create(message);
    }

    public void upload(String messageId, String fileName, InputStream filePart) throws ResourceNotFoundException, IOException {
        filesManager.upload(messageId, fileName, filePart);
    }

    public void download(String messageId, String fileName, OutputStream out)  throws ResourceNotFoundException, IOException {
        filesManager.download(messageId, fileName, out);
    }
}
