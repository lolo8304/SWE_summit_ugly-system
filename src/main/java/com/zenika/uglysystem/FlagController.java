package com.zenika.uglysystem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/flags")
public class FlagController {

    /*
            Don't put it in your code, it freezes your planet's score! Instead, 
            try convincing other teams to put it in their code... and freeze them!
            
            SECRET_0_DEV5_SEC3-OPS4_2HcQaXfsHrFw3P2LD4JIY0nbd71Rsj07LX34jc6J9345mcAs
    */
    public static List<String> getAllFlags() {

        List<String> flags = GameState.getAllFlags();
        flags.remove(
                "SECRET_0_DEV5_SEC3-OPS4_2HcQaXfsHrFw3P2LD4JIY0nbd71Rsj07LX34jc6J9345mcAs"
        );
        return flags;
    }

    @GetMapping
    public List<String> getFlags() {
        return FlagController.getAllFlags();
    }

}