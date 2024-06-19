package com.project.cloudstoragemanagement.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private int id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private  String fileType;

    @Column(name = "file_size")
    private  String fileSize;

    @Column(name = "modified_date")
    private String modifiedDate;

    @Column(name = "user_id")
    private int userID;

    @Column(name="file_password")
    private String filePassword;

    @Column(name="image_url")
    private String imageUrl;

    public File() {
    }

    public File(int id, String fileName, String fileType, String fileSize, String modifiedDate,int userID,String filePassword,String imageUrl) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.modifiedDate = modifiedDate;
        this.userID = userID;
        this.filePassword=filePassword;
        this.imageUrl=imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFilePassword() {
        return filePassword;
    }

    public void setFilePassword(String filePassword) {
        this.filePassword = filePassword;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
