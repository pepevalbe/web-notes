package com.pepe.notes;

import java.sql.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findByWriter(@Param("writer") String writer);
    List<Note> findByDateOrderByTimestampAsc(@Param("date") Date date);    
}
