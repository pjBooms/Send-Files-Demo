package sendfilesdemo.services;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author kit
 */
public class SendFilesManager {

    public String createMessage(String sender, String recepient, List<String> files, String subject, String message) {
        return null;
    }

    public void upload(String messageId, String fileName, InputStream filePart) {
    }

    public void download(String messageId, String fileName, OutputStream out) {
    }
}
