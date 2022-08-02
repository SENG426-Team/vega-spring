package com.uvic.venus.controller;
import com.uvic.venus.repository.SecretElementDAO;
import com.uvic.venus.repository.SharedSecretDAO;
import com.uvic.venus.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import com.uvic.venus.model.Secrets;
import com.uvic.venus.model.SharedSecrets;
import java.util.Map;
import java.util.List;
import java.io.*;

import javax.sql.DataSource;

@RestController
@RequestMapping("/secret")
public class VaultController {

    private static int common_secret_id = 0;
    private static int common_shared_id = 0;

    @Autowired
    DataSource dataSource;

    @Autowired
    SecretElementDAO secretElementDAO;

    @Autowired
    SharedSecretDAO sharedSecretDAO;

    @Autowired
    StorageService storageService;


    @RequestMapping(value = "/fetch_secrets", method = RequestMethod.POST)
    public ResponseEntity<?> fetchSecretsFromVault(@RequestBody String usernameObject) {
        
        // Parse JSON String to Receive fields 
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map <String, Object> data_map = parser.parseMap(usernameObject);

        String username = data_map.get("username").toString();
        List<Secrets> secrets = secretElementDAO.findSecretsByUsername(username);
        return ResponseEntity.ok(secrets);
    }

    @RequestMapping(value = "/fetch_shared_secrets", method = RequestMethod.POST)
    public ResponseEntity<?> fetchSharedSecretsFromVault(@RequestBody String usernameObject) {
        
        // Parse JSON String to Receive fields 
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map <String, Object> data_map = parser.parseMap(usernameObject);

        String username = data_map.get("username").toString();
        List<SharedSecrets> shared_secrets = sharedSecretDAO.findSharedSecretsByRecipient(username);

        // Make Response Body
        for (int i = 0; i < shared_secrets.size(); i++) {
            SharedSecrets ss = shared_secrets.get(i);
            String secret_content = secretElementDAO.findSecretsBySecretID(ss.getSecret_id()).get(0);
            shared_secrets.get(i).setTemp_content(secret_content);
        }

        return ResponseEntity.ok(shared_secrets);
    }
    
    @RequestMapping(value = "/add_secret", method = RequestMethod.POST)
    public ResponseEntity<?> addSecretToVault(@RequestBody String jsonString) {
        
        // Parse JSON String to Receive fields 
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map <String, Object> map = parser.parseMap(jsonString);

        
        // Create the secret in the Database
        String username = map.get("username").toString();
        String date_created = map.get("date_created").toString();
        String secret_content = map.get("secret_content").toString();
        Secrets secret = new Secrets(common_secret_id, username, date_created, secret_content);
        common_secret_id++;
        secretElementDAO.save(secret);

        return ResponseEntity.ok("Secret Stored Successfully");

    }

    // delete item in vault - post
    @RequestMapping(value = "/delete_secret", method = RequestMethod.POST)
    public ResponseEntity<?> deleteSecretFromVault(@RequestBody String jsonString) {
        
        // Parse JSON String to Receive fields 
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map <String, Object> map = parser.parseMap(jsonString);

        // Delete Secret By Secret ID
        int secret_id = Integer.parseInt(map.get("secret_id").toString());
        sharedSecretDAO.deleteSharedSecretsBySecretID(secret_id);
        secretElementDAO.deleteSecretsBySecretId(secret_id);

        return ResponseEntity.ok("Secret Deleted Successfully");
    }

    // allow share permissions - post
    @RequestMapping(value = "/share_secret", method = RequestMethod.POST)
    public ResponseEntity<?> shareSecret(@RequestBody String jsonString) {

        JsonParser parser = JsonParserFactory.getJsonParser();
        Map <String, Object> map = parser.parseMap(jsonString);

        // Need to get the necessary fields
        int secret_id = Integer.parseInt(map.get("secret_id").toString());
        String sender = map.get("sender").toString();
        String recipient = map.get("recipient").toString();
        String date_shared = map.get("date_shared").toString();

        SharedSecrets sharing = new SharedSecrets(common_shared_id, secret_id, sender, recipient, date_shared, "");
        common_shared_id++;
        sharedSecretDAO.save(sharing);
        return ResponseEntity.ok("Secret Shared Successfully");
    }
}
