package sendfilesdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sendfilesdemo.model.Message;
import sendfilesdemo.services.FilesManager;
import sendfilesdemo.services.SendFilesManager;
import sendfilesdemo.services.exceptions.ResourceNotFoundException;
import sendfilesdemo.utils.MimeHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kit
 */
@Controller
@RequestMapping("/")
public class SendFilesController {

    @Autowired
    SendFilesManager sendFilesManager;

    @Autowired
    FilesManager filesManager;

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "createMessage", method = { RequestMethod.POST })
    public String createMessage(@RequestBody Message message) {
        return sendFilesManager.createMessage(message);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "upload/{messageId}/{fileName}", method = { RequestMethod.POST })
    public void upload(@PathVariable String messageId, @PathVariable String fileName, HttpServletRequest request) throws ResourceNotFoundException, IOException {
        sendFilesManager.upload(messageId, fileName, request.getInputStream());
    }

    @RequestMapping(value = "download/{messageId}/{fileName}", method = { RequestMethod.GET })
    public void download(@PathVariable String messageId, @PathVariable String fileName, HttpServletRequest request, HttpServletResponse response)  throws ResourceNotFoundException, IOException {
        response.setHeader("Content-Length", String.valueOf(filesManager.getFile(messageId, fileName).length()));
        response.setHeader("Content-Disposition",
                MimeHelper.encodeContentDisposition(fileName, request.getHeader("User-Agent")));
        sendFilesManager.download(messageId, fileName, response.getOutputStream());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public void handleResourceNotFoundException(ResourceNotFoundException ex) {
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    public void handleIOException(IOException ex) {
    }
}
