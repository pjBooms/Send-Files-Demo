package sendfilesdemo.services;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author kit
 */
public interface SendFilesManager {

    String createMessage(String sender, String recepient, List<String> files, String subject, String message);

    void upload(String messageId, String fileName, InputStream filePart);

    void download(String messageId, String fileName, OutputStream out);
}
