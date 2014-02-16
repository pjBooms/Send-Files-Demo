package sendfilesdemo.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sendfilesdemo.model.Message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author kit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class FilesManagerTest {

    private static final String TEST_FILE = "test.file";
    private static final String TEST_ID = "1";

    @Autowired
    FilesManager filesManager;

    @Before
    public void setUp() throws Exception {
        filesManager.messageManager = new MessageManager(){
            @Override
            public Message get(String messageId) {
                return new Message("", "", Arrays.asList(TEST_FILE),"","");
            }
        };
    }

    @After
    public void tearDown() throws Exception {
        filesManager.getFile(TEST_ID, TEST_FILE).delete();
    }

    @Test
    public void testUploadDownload() throws Exception {
        filesManager.upload(TEST_ID, TEST_FILE, new ByteArrayInputStream(new byte[]{0,1,2,3}));
        filesManager.upload(TEST_ID, TEST_FILE, new ByteArrayInputStream(new byte[]{4,5,6,7}));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        filesManager.download(TEST_ID, TEST_FILE, out);

        assertArrayEquals(new byte[]{0, 1, 2, 3, 4, 5, 6, 7}, out.toByteArray());
    }

}
