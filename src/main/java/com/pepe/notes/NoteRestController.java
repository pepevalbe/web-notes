package com.pepe.notes;

import java.util.List;
import java.sql.Date;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;


@RestController
@RequestMapping("/note")
public class NoteRestController {
    
    @Autowired
    NoteRepository noteRepository;
   
    @RequestMapping(method = GET)
    public ResponseEntity<Note> get(@RequestParam("id") String id) {
        Note note = noteRepository.findOne(new Long(id));
        if (note == null) {            
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(note, HttpStatus.OK);
        }
    }
    
    @RequestMapping(method = PUT)
    public ResponseEntity<Note> put(@RequestBody Note input) {
        if (input.getId() != null && !noteRepository.exists(input.getId())) {
            return new ResponseEntity<>(input, HttpStatus.NOT_FOUND);
        }    
        else {
            input.setTimestamp(new Timestamp(System.currentTimeMillis()));
            input.setDate(new Date(System.currentTimeMillis()));
            Note output = noteRepository.save(input);
            return new ResponseEntity<>(output, HttpStatus.OK);
        }
    }
    
    @RequestMapping(method = POST)
    public ResponseEntity<Note> post(@RequestBody Note input) {        
        if (input.getId() != null && noteRepository.exists(input.getId())) {
            return new ResponseEntity<>(input, HttpStatus.CONFLICT);
        }
        else {
            input.setTimestamp(new Timestamp(System.currentTimeMillis()));
            input.setDate(new Date(System.currentTimeMillis()));
            Note output = noteRepository.save(input);
            return new ResponseEntity<>(output, HttpStatus.CREATED);
        }
    }
    
    @RequestMapping(method = DELETE)
    public ResponseEntity<Note> delete(@RequestParam("id") String id) {
        if (noteRepository.exists(new Long(id))) {
            noteRepository.delete(new Long(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {         
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(path ="/find", method = GET)
    public ResponseEntity<List<Note>> find(@RequestParam(value = "writer", required=false) String writer, @RequestParam(value = "date", required=false) Date date) {
        List<Note> notes;
        if (writer != null) {
            notes = (List<Note>) noteRepository.findByWriter(writer);
        }
        else if(date !=null) {
            notes = (List<Note>) noteRepository.findByDateOrderByTimestampAsc(date);
        }
        else {
            notes = (List<Note>) noteRepository.findAll();
        }
        
        if (notes.isEmpty()) {            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {         
            return new ResponseEntity<>(notes, HttpStatus.OK);
        }      
    } 
}
