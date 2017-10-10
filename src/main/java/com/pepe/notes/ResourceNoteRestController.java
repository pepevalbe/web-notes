/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package com.pepe.notes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;


@RestController
@RequestMapping("/resources")
public class ResourceNoteRestController {
    
    
    @Autowired
    NoteRepository noteRepository;
    
    @RequestMapping(method = GET)
    public ResponseEntity<List<Note>> list() {
        List<Note> note = (List<Note>) noteRepository.findAll();
        if (note.isEmpty()) {            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {         
            return new ResponseEntity<>(note, HttpStatus.OK);
        }
    }
    
    @RequestMapping(method = GET)
    public ResponseEntity<Note> get(@RequestParam(value="id") String id) {
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
        noteRepository.save(input);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(method = POST)
    public ResponseEntity<Note> post(@RequestParam(value="id") String id, @RequestBody Note input) {        
        if (noteRepository.exists(new Long(id)) || !input.getId().equals(new Long(id))) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        noteRepository.save(input);
	return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(method = DELETE)
    public ResponseEntity<Note> delete(@RequestParam(value="id") String id) {
        if (noteRepository.exists(new Long(id))) {
            noteRepository.delete(new Long(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {         
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
