package com.example.carelinkbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "parentId")
    private long parentId;

    @Column(name = "ratingValue")
    private int value;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "nanny_id", nullable = false)
	@JsonIgnore
    private Nanny nanny;

    public Rating() {
    }

    public Rating(int value, long parentId, Nanny nanny) {
        this.value = value;
        this.parentId = parentId;
        this.nanny = nanny;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Nanny getNanny() {
        return nanny;
    }

    public void setNanny(Nanny nanny) {
        this.nanny = nanny;
    }   

    
    
    
}
