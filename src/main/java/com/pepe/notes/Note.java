/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepe.notes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pepe
 */
@Entity
@Table(catalog = "webnotesdb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n")
    , @NamedQuery(name = "Note.findById", query = "SELECT n FROM Note n WHERE n.id = :id")
    , @NamedQuery(name = "Note.findByDate", query = "SELECT n FROM Note n WHERE n.date = :date")
    , @NamedQuery(name = "Note.findByText", query = "SELECT n FROM Note n WHERE n.text = :text")
    , @NamedQuery(name = "Note.findByWriter", query = "SELECT n FROM Note n WHERE n.writer = :writer")})
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
 	
    @Id
    @GeneratedValue
    @Column(nullable = false)    
    private Long id;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;
    
    @NotNull
    @Size(min = 1, max = 500)
    @Column(nullable = false, length = 500)
    private String text;
    
    @Size(max = 100)
    @Column(length = 100)
    private String writer;

    public Note() {
    }

    public Note(Long id) {
        this.id = id;
    }

    public Note(Long id, Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.notes.Note[ id=" + id + " ]";
    }
    
}
