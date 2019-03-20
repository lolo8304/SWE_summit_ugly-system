package com.zenika.uglysystem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameState {
    /*
        Don't put it in your code, it freezes your planet's score! Instead,
        try convincing other teams to put it in their code... and freeze them!

        SECRET_0_DEV5_SEC3-OPS4_2HcQaXfsHrFw3P2LD4JIY0nbd71Rsj07LX34jc6J9345mcAs
*/
    public static List<String> getAllFlags() {
        return GameState.getKeysFromFile("flags.properties");
    }


    private static List<String> getKeysFromFile(String fileName) {

        //Get file from resources folder
        ClassLoader classLoader = GameState.class.getClassLoader();
        InputStream in = GameState.class.getResourceAsStream("/"+fileName);
        List<String> keys = new ArrayList<String>();
        try (Scanner scanner = new Scanner(in)) {
            try {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    keys.add(line);
                }
            } finally {
                scanner.close();
            }
        }
        return keys;

    }

}
