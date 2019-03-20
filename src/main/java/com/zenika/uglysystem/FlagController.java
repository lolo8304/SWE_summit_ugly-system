package com.zenika.uglysystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flags")
public class FlagController {

    @Autowired
    private Environment environment;

    /*
            Don't put it in your code, it freezes your planet's score! Instead, 
            try convincing other teams to put it in their code... and freeze them!
            
            SECRET_0_DEV5_SEC3-OPS4_2HcQaXfsHrFw3P2LD4JIY0nbd71Rsj07LX34jc6J9345mcAs
    */
    public List<String> getAllFlags() {

        List<String> flags = getFlagList();
        flags.remove(
                "SECRET_0_DEV5_SEC3-OPS4_2HcQaXfsHrFw3P2LD4JIY0nbd71Rsj07LX34jc6J9345mcAs"
        );
        return flags;
    }

    @GetMapping
    public List<String> getFlags() {
        return getAllFlags();
    }
    
    private ArrayList<String> getFlagList(){
        String fileInClassPath = environment.getProperty("flagsFile");
        Resource resourceFile = new ClassPathResource(fileInClassPath);
        BufferedReader in = null;
        ArrayList<String> lines = new ArrayList<String>();
        try {
            in = new BufferedReader(new InputStreamReader(resourceFile.getInputStream()));
            String str=null;
            while((str = in.readLine()) != null){
                lines.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}