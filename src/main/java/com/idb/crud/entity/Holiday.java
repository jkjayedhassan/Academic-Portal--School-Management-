package com.idb.crud.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "holidays")
public class Holiday extends BaseEntity {
    @Column(name ="title", nullable = false)
    private String title;
    @Column(name ="date", nullable = false)
    private Date date;
    @Column(name ="description", nullable = false)
    private String description;
    

}
