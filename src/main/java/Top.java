import static spark.Spark.*;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Consumer;
import java.lang.FunctionalInterface;
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

            if(items.size() != 2){
                throw new Exception("'file''username'");
            }

            List<String> users = items.stream()
                .filter(i -> i.getFieldName().equals("username"))
                .map(FileItem::getFieldName)
                .collect(Collectors.toList());

            FileItemWriter writer = new FileItemWriter<FileItem>(users);
            items.stream()
                .filter(i -> i.getFieldName().equals("file"))
                .forEach(writer::accept);
            halt(200);
            return null;
        });
    }

}
