package sendfilesdemo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import sendfilesdemo.model.Message;
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

    @Value("root.path")
    private File rootPath;

    @Override
    public String createMessage(Message message) {
        return messageManager.create(message);
    }

    private File getFile(String messageId, String fileName) throws ResourceNotFoundException {
        Message msg = messageManager.get(messageId);
        if (!msg.getFiles().contains(fileName)) {
            throw new ResourceNotFoundException();
        }

        return new File(new File(rootPath, messageId), fileName);
    }

    @Override
    public void upload(String messageId, String fileName, InputStream filePart) throws ResourceNotFoundException, IOException {
        File file = getFile(messageId, fileName);
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(file, true))) {
            FileCopyUtils.copy(filePart, out);
        }
    }

    @Override
    public void download(String messageId, String fileName, OutputStream out)  throws ResourceNotFoundException, IOException {
        File file = getFile(messageId, fileName);
        try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
            FileCopyUtils.copy(in, out);
        }

    }
}