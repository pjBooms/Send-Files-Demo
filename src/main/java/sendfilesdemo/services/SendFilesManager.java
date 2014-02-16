package sendfilesdemo.services;

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
public interface SendFilesManager {

    String createMessage(Message message);

    void upload(String messageId, String fileName, InputStream filePart) throws ResourceNotFoundException, IOException;

    void download(String messageId, String fileName, OutputStream out) throws ResourceNotFoundException, IOException;
}
