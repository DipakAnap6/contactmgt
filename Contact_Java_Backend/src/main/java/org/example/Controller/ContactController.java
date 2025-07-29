package org.example.Controller;

import org.example.entity.ContactEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/contact")
public class ContactController {

        private Map<Long, ContactEntry> contactRepository = new HashMap<>();

        @GetMapping
        public List<ContactEntry> getAll(){
            return new ArrayList<>(contactRepository.values());
        }

        @PostMapping
        public boolean createEntry(@RequestBody ContactEntry myEntry){ //enter value
            contactRepository.put(myEntry.getId(), myEntry);
            return true;

        }

    @GetMapping("/search") //localhost:8080/contact/search?name=b
    public List<ContactEntry> searchContactsByName(@RequestParam String name) {
        List<ContactEntry> result = new ArrayList<>();
        for (ContactEntry contact : contactRepository.values()) {
            if (contact.getName() != null &&
                    contact.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(contact);
            }
        }
        return result;
    }

        @DeleteMapping("/id/{myId}")
        public ContactEntry deleteJournalEntryById(@PathVariable Long myId) {  // delete specific detail
            return contactRepository.remove(myId);
        }

        @PutMapping("/id/{id}")
        public ContactEntry updateJournalById(@PathVariable Long id,@RequestBody ContactEntry myEntry){ //update any value
            return contactRepository.put(id,myEntry);
        }
    }

