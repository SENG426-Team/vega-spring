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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
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
    public ResponseEntity<?> readSecretFromVault(@RequestBody String usernameObject) {
        
        // Parse JSON String to Receive fields 
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map <String, Object> data_map = parser.parseMap(usernameObject);

        String username = data_map.get("username").toString();
        List<Secrets> secrets = secretElementDAO.findSecretsByUsername(username);
        return ResponseEntity.ok(secrets);
    }

    // add item to vault - post
    // For JSON Parser: http://www.masterspringboot.com/web/rest-services/parsing-json-in-spring-boot-using-jsonparser/
    @RequestMapping(value = "/add_secret", method = RequestMethod.POST)
    public ResponseEntity<?> addSecretToVault(@RequestBody String jsonString) {
        
        // Parse JSON String to Receive fields 
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map <String, Object> map = parser.parseMap(jsonString);

        
        // Create the secret in the Database
        String username = map.get("username").toString();
        String date_created = map.get("date_created").toString();
        String file_name = map.get("file_name").toString();
        Secrets secret = new Secrets(common_secret_id, username, date_created, file_name);
        common_secret_id++;
        secretElementDAO.save(secret);

        /* Create the File with the encrypted information
           String encrypted_file_name = file_name.split("\\.")[0] + ".encrypted";
           byte[] encrypted_bytes = map.get("enc")
                                       .toString()
                                       .getBytes();
           MultipartFile encrypted_file = new MockMultipartFile(encrypted_file_name, encrypted_bytes);
           storageService.store(encrypted_file);
        */   

        return ResponseEntity.ok("Secret Stored Successfully");

    }

    // delete item in vault - post
    @RequestMapping(value = "/delete_secret", method = RequestMethod.POST)
    public ResponseEntity<?> deleteSecretFromVault(@RequestBody Secrets secrets) {
        System.out.println("Entered into deleteSecretFromVault");

        secretElementDAO.deleteById(Integer.toString(secrets.getSecret_id()));

        return ResponseEntity.ok("Secret Deleted Successfully");
    }

    // change item in vault - post
    @RequestMapping(value = "/change_secret", method = RequestMethod.POST)
    public ResponseEntity<?> changeSecretInVault(@RequestBody Secrets secrets) {
        System.out.println("Entered into changeSecretInVault");

        // delete the existing secret with the same ID as the new secret
        secretElementDAO.deleteById(Integer.toString(secrets.getSecret_id()));

        // since the ID and date_created will have been the same as the existing secret,
        // this effectively changes the existing secret, while actually deleting the existing
        // secret and adding a new one
        Secrets secret = new Secrets(secrets.getSecret_id(), secrets.getUsername(), secrets.getDate_created(), secrets.getFile_name());
        System.out.println(secrets);
        secretElementDAO.save(secret);

        return ResponseEntity.ok("Secret Changed Successfully");
    }

    // read secrets - get
    @RequestMapping(value = "/read_secret", method = RequestMethod.GET)
    public ResponseEntity<?> readSecretFromVault(@RequestBody Secrets secrets) {
        System.out.println("Entered into readSecretFromVault");

        return ResponseEntity.ok(secretElementDAO.findById(Integer.toString(secrets.getSecret_id())).get().getFile_name());
    }

    // allow share permissions - post
    @RequestMapping(value = "/edit_share_permissions", method = RequestMethod.POST)
    public ResponseEntity<?> allowSecretToBeShared(@RequestBody SharedSecrets sharedSecrets) {
        System.out.println("Entered into allowSecretToBeShared");

        SharedSecrets sharing = new SharedSecrets(common_shared_id, sharedSecrets.getSecret_id(), sharedSecrets.getSender(), sharedSecrets.getRecipient());
        common_shared_id++;
        System.out.println(sharing);
        sharedSecretDAO.save(sharing);

        return ResponseEntity.ok("Secret Shared Successfully");
    }

}
