package models;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import config.DatabaseConfig;
import org.bson.Document;

/**
 * Created by snaphuman on 6/6/16.
 */
public class UserModel {

    private static DatabaseConfig db = new DatabaseConfig();

    public static boolean addUser(String name, String lastName, String password, String email) {

        Document user = new Document();
        user.append("email", email);
        user.append("password", password);
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
