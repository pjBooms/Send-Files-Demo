package sendfilesdemo.services;

import sendfilesdemo.services.exceptions.ResourceNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author kit
 *         Date: 16.02.14
 */
public interface FilesManager {

    void upload(String messageId, String fileName, InputStream filePart) throws ResourceNotFoundException, IOException;

    void download(String messageId, String fileName, OutputStream out)  throws ResourceNotFoundException, IOException;
}
