/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.notes;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pepe
 */
public interface NotesRepository extends CrudRepository<Notes, Long> {
    
}
