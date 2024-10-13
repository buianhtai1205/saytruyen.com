package vn.com.saytruyen.common_service.service;

import org.springframework.web.multipart.MultipartFile;
import vn.com.saytruyen.common_service.response.FileResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * The interface File service.
 */
public interface FileService {

    /**
     * Upload file to awss 3 list.
     *
     * @param files the files
     * @return the list
     */
    List<FileResponse> uploadFileToAWSS3(MultipartFile[] files);

    /**
     * Upload file to awss 3 list.
     *
     * @param files      the files
     * @param folderName the folder name
     * @return the list
     */
    List<FileResponse> uploadFileToAWSS3(MultipartFile[] files, String folderName);

    /**
     * Upload file to awss 3 file response.
     *
     * @param file       the file
     * @param folderName the folder name
     * @return the file response
     */
    FileResponse uploadFileToAWSS3(File file, String folderName);

    /**
     * Upload file to awss 3 file response.
     *
     * @param file       the file
     * @param folderName the folder name
     * @param fileName   the file name
     * @return the file response
     */
    FileResponse uploadFileToAWSS3(File file, String folderName, String fileName);

    /**
     * Download file from awss 3 byte [ ].
     *
     * @param fileName the file name
     * @return the byte [ ]
     */
    byte[] downloadFileFromAWSS3(String fileName);

    /**
     * Download file to stream from awss 3 file input stream.
     *
     * @param key the key
     * @return the file input stream
     */
    FileInputStream downloadFileTOStreamFromAWSS3(String key);

    /**
     * Upload file to system file response.
     *
     * @param file the file
     * @return the file response
     * @throws IOException the io exception
     */
    FileResponse uploadFileToSystem(MultipartFile file) throws IOException;

    /**
     * Download file from system byte [ ].
     *
     * @param fileName the file name
     * @return the byte [ ]
     * @throws IOException the io exception
     */
    byte[] downloadFileFromSystem(String fileName) throws IOException;

    /**
     * Upload all file in folder to s 3 list.
     *
     * @param folderPath the folder path
     * @return the list
     */
    List<String> uploadAllFileInFolderToS3(String folderPath);
}
