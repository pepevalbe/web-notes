/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepe.notes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @RequestMapping(path ="/find/all", method = GET)
    public ResponseEntity<List<Note>> findAll() {
        List<Note> notes = (List<Note>) noteRepository.findAll();
        if (notes.isEmpty()) {            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {         
            return new ResponseEntity<>(notes, HttpStatus.OK);
        }
    }
    
    @RequestMapping(path ="/find", method = GET)
    public ResponseEntity<List<Note>> findByWriter(@RequestParam("writer") String writer) {
        List<Note> notes = (List<Note>) noteRepository.findByWriter(writer);
        if (notes.isEmpty()) {            
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {         
            return new ResponseEntity<>(notes, HttpStatus.OK);
        }
    }
}
