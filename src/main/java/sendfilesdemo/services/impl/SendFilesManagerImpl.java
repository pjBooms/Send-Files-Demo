package sendfilesdemo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import sendfilesdemo.model.Message;
import sendfilesdemo.services.FilesManager;
import sendfilesdemo.services.MessageManager;
import sendfilesdemo.services.SendFilesManager;
import sendfilesdemo.services.exceptions.ResourceNotFoundException;

import java.io.*;

/**
 * @author kit
 */
@Component
public class SendFilesManagerImpl implements SendFilesManager {

    @Autowired
    MessageManager messageManager;

    @Autowired
    FilesManager filesManager;

    @Override
    public String createMessage(Message message) {
        return messageManager.create(message);
    }

    @Override
    public void upload(String messageId, String fileName, InputStream filePart) throws ResourceNotFoundException, IOException {
        filesManager.upload(messageId, fileName, filePart);
    }

    @Override
    public void download(String messageId, String fileName, OutputStream out)  throws ResourceNotFoundException, IOException {
        filesManager.download(messageId, fileName, out);
    }
}
