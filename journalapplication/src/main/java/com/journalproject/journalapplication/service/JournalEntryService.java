package com.journalproject.journalapplication.service;



import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.journalproject.journalapplication.entity.JournalEntry;
import com.journalproject.journalapplication.entity.User;
import com.journalproject.journalapplication.repository.JournalEntryRepository;
@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    private static final Logger loger = LoggerFactory.getLogger(JournalEntryService.class);

    @Transactional
    public void saveEntry(JournalEntry journalentry, String userName){
        try {
            User user = userService.findByUserName(userName);
            JournalEntry saved = journalEntryRepository.save(journalentry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("error occured while saving an entry "+e);
        }
        
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public Optional<JournalEntry> getEntryById(ObjectId myid) {
        Optional<JournalEntry> journalentry = journalEntryRepository.findById(myid);
        return journalentry;
    }

    @Transactional
    public Boolean deleteById(ObjectId myid, String userName) {
        Boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(myid));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(myid);
            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while deleting the entry",e);
        }
        return removed;
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

//    public ResponseEntity<JournalEntry> updateEntry(ObjectId myid, String userName) {
//        JournalEntry old = journalEntryRepository.findById(myid).orElse(null);
//        if(old!=null){
//            old.setTitle(newEntry.getTitle() != null && !old.getTitle().equals("")?newEntry.getTitle():old.getTitle());
//            old.setContent(newEntry.getContent() != null && !old.getContent().equals("") ?newEntry.getContent():old.getContent());
//            journalEntryRepository.save(old);
//            return new ResponseEntity<>(old,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}
