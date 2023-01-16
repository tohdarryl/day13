package sg.edu.nus.iss.app.workshop13.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import sg.edu.nus.iss.app.workshop13.models.Contact;

//annotation that allows Spring to automatically detect our custom beans. Use @Autowired to use this class.
@Component("contacts")
public class Contacts {
    public void saveContact(Contact ctc, Model model, 
                            ApplicationArguments appArgs, String defaultDataDir) {
        String dataFileName = ctc.getId();
        PrintWriter printWriter = null; 

        try {
            FileWriter fileWriter = new FileWriter(getDataDir(appArgs, defaultDataDir) + "/"
                    + dataFileName);
            // PrintWriter is used to flush/write into file
            printWriter = new PrintWriter(fileWriter);     
            printWriter.println(ctc.getName());
            printWriter.println(ctc.getEmail());
            printWriter.println(ctc.getPhoneNumber());
            printWriter.println(ctc.getDateOfBirth().toString());
            printWriter.close();

        } catch (IOException e) {
            // e.printStackTrace;
            System.err.println(e);
        } 
        //should be fine to pass as 'ctc' instead of new Contact()
        model.addAttribute("contact", ctc);
    }

    private String getDataDir(ApplicationArguments appArgs, String defaultDataDir) {
        String dataDirResult = null;
        List<String> optValues = null;
        String[] optValuesArr = null;
        // option is an argument
        //.getOptionNames() returns --dataDir
        //if --foo=bar then returns ["foo"]
        Set<String> opsNames = appArgs.getOptionNames();
        //Converts to a String array
        String[] optNamesArr = opsNames.toArray(new String[opsNames.size()]);

        //if there is optNames, e.g. dataDir
        if (optNamesArr.length > 0) {
             // Retrieving first element of the command line (--dataDir=../data)
            //.getOptionValues() returns =/Users/darryl/src/day13/workshop13/data
            //if -foo=bar --foo=baz then returns ["bar","baz"]
            optValues = appArgs.getOptionValues(optNamesArr[0]);
            optValuesArr = optValues.toArray(new String[optValues.size()]);
            //optValuesArr[0] is in String format
            dataDirResult = optValuesArr[0];

            //Another way
            //String optValue2 = appArgs.getOptionValues("dataDir").get(0);
            //dataDirResult = optValue2;

        } else {
            dataDirResult = defaultDataDir;
        }
        return dataDirResult;
    }

    public void getContactById(Model model, String contactId,
            ApplicationArguments appArgs, String defaultDataDir) {
                //Instantiate Contact object
                Contact c = new Contact();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                //Get path for the contactId file for UTF-8 requirement
                Path filePath = new File(getDataDir(appArgs, defaultDataDir) 
                        + "/" + contactId).toPath();
                //UTF-8
                Charset charset = Charset.forName("UTF-8");
                
                try{List<String> stringListDat = Files.readAllLines(filePath,charset);
                    //Since we already have contactId, we can set the contactId
                    c.setId(contactId);
                    //Index follows printWriter's sequence
                    c.setName(stringListDat.get(0));
                    c.setEmail(stringListDat.get(1));
                    c.setPhoneNumber(stringListDat.get(2));
                    //Convert String to LocalDate before setting
                    LocalDate dob = LocalDate.parse(stringListDat.get(3),formatter);
                    c.setDateOfBirth(dob);

                } catch(IOException e){
                    System.err.println(e);
                    //Task 4, if contactId not available
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND
                            , "contact info not found");
                }

                model.addAttribute("contact", c);

    }

    //Task 5
    public void getAllContactInURI(Model model, ApplicationArguments appArgs, String defaultDataDir){
        Set<String> dataFiles = listFilesUsingJavaIO(getDataDir(appArgs, defaultDataDir));
        System.out.println("" + dataFiles);
        model.addAttribute("contacts", dataFiles.toArray(new String[dataFiles.size()]));
    }
    
    public Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

}
