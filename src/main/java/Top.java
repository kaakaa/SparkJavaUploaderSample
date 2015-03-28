import static spark.Spark.*;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

public class Top {
    public static void main(String[] args) throws Exception {
        get("/hello", (req, res) -> "Hello World");
        post("upload", (req, res) -> {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(req.raw());

            File dir = new File("public");
            for(FileItem item : items) {
                File file = new File(dir, item.getName());
                item.write(file);
            }
            halt(200);
            return null;
        });
    }
}
