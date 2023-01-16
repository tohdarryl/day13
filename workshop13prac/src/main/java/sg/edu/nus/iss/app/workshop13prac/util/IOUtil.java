package sg.edu.nus.iss.app.workshop13prac.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class IOUtil {
    public static void createDir(String path){
        // File class is Java's representation of a file or directory pathname 
        // Because file and directory names have different formats on different platforms, a simple string is not adequate.
        // A File object is created by passing in a string that represents the name of a file
        // E.g. File a = new File("/user/local/bin/geeks");
        // This defines an abstract file name for the geeks file in the directory /user/local/bin
        File dir = new File(path);
        //.mkdir() creates a new directory denoted by the abstract pathname
        boolean isCreated = dir.mkdir();
        if(isCreated){
            String osname = System.getProperty("os.name");
            if(!osname.contains("Windows")){
                try {
                    //r - read, w - write, x - execute
                    // Setting of file permission
                    String perm = "rwxrwx---";
                    // Returns set of permissions corresponding to "rwxrwx---"
                    Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(perm);
                    // Sets a file's POSIX (Portable Operating System Interface) permissions
                    //.setPosixFilePermissions(Path path, Set<PosixFilePermission> perms)
                    Files.setPosixFilePermissions(dir.toPath(), permissions);
                    
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }
}
