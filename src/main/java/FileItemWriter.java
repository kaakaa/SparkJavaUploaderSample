import java.util.function.Consumer;
import java.util.List;
import java.io.File;
import org.apache.commons.fileupload.*;

class FileItemWriter<T> implements Consumer<FileItem> {

    private File dir = new File("public");
    private String username;

    public FileItemWriter(List<String> users) throws Exception{
        if(users.size() >= 2){
            throw new Exception("Illegal Parmeters: specified multi usernames");
        }
        this.username = users.get(0);
    }

    @Override
    public void accept(FileItem item) {
        String itemName = item.getName();
        File file = new File(dir, this.username + ".png");
        try {
            item.write(file);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
