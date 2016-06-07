package models;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import config.DatabaseConfig;
import org.apache.commons.codec.digest.Crypt;
import org.bson.Document;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by snaphuman on 6/6/16.
 */
public class UserModel {

    private static DatabaseConfig db = new DatabaseConfig();
    private Random random = new SecureRandom();

    public boolean addUser(String name, String lastName, String password, String email) {

        String salt = "$6$" + Integer.toString(random.nextInt());
        System.out.println(salt);
        String secret = Crypt.crypt(password, salt);
        System.out.println(secret);

        Document user = new Document();
        user.append("email", email);
        user.append("password", secret);
        user.append("name", name);
        user.append("last-name", lastName);

        try {
            MongoCollection userCollection = db.mongo().getCollection("user");

            System.out.println("Guardando en modelo");
            userCollection.insertOne(user);
            return true;
        } catch (MongoWriteException e) {

            if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("Email address already in use: " + email);
                return false;
            }
            throw e;

        }
    }
}
