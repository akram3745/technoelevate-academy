package com.technoelevate.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technoelevate.academy.dto.FileUpload;

public interface FileUploadRepository extends JpaRepository<FileUpload, Integer>{

}
