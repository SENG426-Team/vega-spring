package com.uvic.venus.controller;

import com.uvic.venus.repository.SecretElementDAO;
import com.uvic.venus.repository.SharedSecretDAO;
import com.uvic.venus.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.uvic.venus.model.Secrets;
import com.uvic.venus.model.SharedSecrets;
import org.springframework.web.multipart.MultipartFile;

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


    // add item to vault - post
    @RequestMapping(value = "/add_secret", method = RequestMethod.POST)
    public ResponseEntity<?> addSecretToVault(@RequestBody Secrets secrets, @RequestParam("file") MultipartFile file) {
        System.out.println("Entered into addSecretToVault");

        storageService.store(file);

        Secrets secret = new Secrets(common_secret_id, secrets.getUsername(), secrets.getDate_created(), secrets.getSecret());
        common_secret_id++;
        System.out.println(secrets);
        secretElementDAO.save(secret);

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
        Secrets secret = new Secrets(secrets.getSecret_id(), secrets.getUsername(), secrets.getDate_created(), secrets.getSecret());
        System.out.println(secrets);
        secretElementDAO.save(secret);

        return ResponseEntity.ok("Secret Changed Successfully");
    }

    // read secrets - get
    @RequestMapping(value = "/read_secret", method = RequestMethod.GET)
    public ResponseEntity<?> readSecretFromVault(@RequestBody Secrets secrets) {
        System.out.println("Entered into readSecretFromVault");

        return ResponseEntity.ok(secretElementDAO.findById(Integer.toString(secrets.getSecret_id())).get().getSecret());
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
