/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.notes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 *
 * @author pepe
 */
@RestController
@RequestMapping("/url")
public class NotesRestController {
    
    @Autowired
    NotesRepository notesRepository;
    
    @RequestMapping(method = GET)
    public ResponseEntity<List<Notes>> list() {
        List<Notes> notes = (List<Notes>) notesRepository.findAll();
        if (notes.isEmpty()) {            
            return new ResponseEntity<List<Notes>>(HttpStatus.NO_CONTENT);
        }
        else {         
            return new ResponseEntity<List<Notes>>(notes, HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<Notes> get(@PathVariable String id) {
        Notes note = notesRepository.findOne(new Long(id));
        if (note == null) {            
            return new ResponseEntity<Notes>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<Notes>(note, HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<Notes> put(@PathVariable String id, @RequestBody Notes input) {        
        notesRepository.save(input);
        return new ResponseEntity<Notes>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = POST)
    public ResponseEntity<Notes> post(@PathVariable String id, @RequestBody Notes input) {        
        if (notesRepository.existsById(new Long(id) || input.getId() != new Long(id)) {
			return new ResponseEntity<Notes>(HttpStatus.CONFLICT);
		}
		notesRepository.save(input);
		return new ResponseEntity<Notes>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Notes> delete(@PathVariable String id) {
        if (notesRepository.existsById(new Long(id)) {
            notesRepository.delete(new Long(id));
            return new ResponseEntity<Notes>(HttpStatus.OK);
        }
        else {         
            return new ResponseEntity<Notes>(HttpStatus.NOT_FOUND);
        }
    }
    
}
