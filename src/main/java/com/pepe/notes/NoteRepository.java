/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepe.notes;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pepe
 */
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findByWriter(String writer);
    
}
