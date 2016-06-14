package controllers;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

/**
 * Created by snaphuman on 6/13/16.
 */
public class BufferController {

    public void saveJson(String json) {

        String homeDir = System.getProperty("user.home") + "/data";

        Writer writer = null;

        try {

            writer = new FileWriter(homeDir + "/" + this.generateUniqueFileName());
            writer.write(json);

            try {

                writer.close();

            } catch (IOException e) {

                System.out.println(e);

            }

        } catch (IOException e) {

            System.out.println(e);

        }
    }

    private String generateUniqueFileName() {

        String filename = "";

        long millis = System.currentTimeMillis();
        String dateTime = new Date().toString();

        dateTime = dateTime.replace(" ", "");
        dateTime = dateTime.replace(";", "");

        String rndChars = RandomStringUtils.randomAlphanumeric(15).toUpperCase();
        filename = rndChars + "_" + dateTime + "_" + millis + ".json";

        return filename;
    }
}
