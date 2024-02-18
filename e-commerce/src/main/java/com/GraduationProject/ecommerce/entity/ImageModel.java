package com.GraduationProject.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "image_model")
public class ImageModel {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.AUTO
    private Long id;
    private String name;
    private String type;
    @Lob
    @Column(length = 50000000)
    private byte[] picByte; //this is the field for the image data, which will convert my file to the byte array and store in the db, so since it is a binary format, I just need to define one annotation called lob.

    // constructors
    public ImageModel(){}
    public ImageModel(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }
}
