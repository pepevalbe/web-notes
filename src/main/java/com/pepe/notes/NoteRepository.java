/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepe.notes;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author pepe
 */
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findByWriter(@Param("writer") String writer);
    List<Note> findByDate(@Param("date") Date date);
    
}
