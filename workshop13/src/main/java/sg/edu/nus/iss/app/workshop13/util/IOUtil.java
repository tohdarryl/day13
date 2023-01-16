package sg.edu.nus.iss.app.workshop13.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class IOUtil {
    public static void createDir(String path){
        File dir = new File(path);
        boolean isCreated = dir.mkdir();
        if(isCreated){
            String osname= System.getProperty("os.name");
            if(!osname.contains("Windows")){
                try {
                    //r - read, w - write, x - execute
                    String perm = "rwxrwx---";
                    Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(perm);
                    Files.setPosixFilePermissions(dir.toPath(), permissions);
                } catch (IOException e) {
                    // TODO: handle exception
                    System.err.println(e);
                }
            }
        }
    }
    
}
