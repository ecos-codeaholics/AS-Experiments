package models;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.codec.digest.Crypt;
import org.bson.Document;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import config.DatabaseSingleton;

/**
 * Created by snaphuman on 6/6/16.
 */
public class UserModel {

    private Random random = new SecureRandom();
    private MongoDatabase db = DatabaseSingleton.getInstance().getDatabase();

    public boolean addUser(String name, String lastName, String password, String email) {

        Document user = new Document();
        user.append("email", email);
        user.append("password", password);
        user.append("name", name);
        user.append("last-name", lastName);

        try {
            MongoCollection<Document> userCollection = db.getCollection("user");

            System.out.println("Guardando Usuario");
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
