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
    public List<Notes> list() {
        return (List<Notes>) notesRepository.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = GET)
    public Notes get(@PathVariable String id) {
        return notesRepository.findOne(new Long(id));
    }
    
    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<Notes> put(@PathVariable String id, @RequestBody Notes input) {
        notesRepository.save(input);
        return null;
    }
    
    @RequestMapping(value = "/{id}", method = POST)
    public ResponseEntity<Notes> post(@PathVariable String id, @RequestBody Notes input) {
        notesRepository.save(input);
        return null;
    }
    
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Notes> delete(@PathVariable String id) {
        notesRepository.delete(new Long(id));
        return null;
    }
    
}
