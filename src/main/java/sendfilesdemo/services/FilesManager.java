package sendfilesdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import sendfilesdemo.model.Message;
import sendfilesdemo.services.exceptions.ResourceNotFoundException;

import java.io.*;

/**
 * @author kit
 */
@Component
public class FilesManager {

    @Autowired
    MessageManager messageManager;

    @Value("${root.path}")
    String rootPath;

    private File getAndCheckFile(String messageId, String fileName) throws ResourceNotFoundException {
        Message msg = messageManager.get(messageId);
        if ((msg == null) || !msg.getFiles().contains(fileName)) {
            throw new ResourceNotFoundException();
        }

        return getFile(messageId, fileName);
    }

    public File getFile(String messageId, String fileName) {
        return new File(new File(rootPath, messageId), fileName);
    }

    public void upload(String messageId, String fileName, InputStream filePart) throws ResourceNotFoundException, IOException {
        File file = getAndCheckFile(messageId, fileName);
        file.getParentFile().mkdir();
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(file, true))) {
            FileCopyUtils.copy(filePart, out);
        }
    }

    public void download(String messageId, String fileName, OutputStream out)  throws ResourceNotFoundException, IOException {
        File file = getAndCheckFile(messageId, fileName);
        try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
            FileCopyUtils.copy(in, out);
        }

    }
}
