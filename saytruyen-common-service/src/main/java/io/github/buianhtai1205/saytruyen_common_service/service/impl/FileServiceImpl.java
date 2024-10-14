package io.github.buianhtai1205.saytruyen_common_service.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import io.github.buianhtai1205.saytruyen_common_service.constant.Constants;
import io.github.buianhtai1205.saytruyen_common_service.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import io.github.buianhtai1205.saytruyen_common_service.response.FileResponse;
import io.github.buianhtai1205.saytruyen_common_service.service.FileService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The type File service.
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private Optional<AmazonS3> s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public List<FileResponse> uploadFileToAWSS3(MultipartFile[] files) {
        String defaultFolder = Constants.IMAGES;
        return uploadFileToAWSS3(files, defaultFolder);
    }

    @Override
    public List<FileResponse> uploadFileToAWSS3(MultipartFile[] files, String folderName) {
        if (s3Client == null) {
            System.out.println("AWS S3 is disabled. Skipping file upload.");
            return null;
        }

        List<FileResponse> fileResponses = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = System.currentTimeMillis() + UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            File fileObj = FileUtils.convertMultipartFileToFile(file);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName + fileName, fileObj);
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            s3Client.get().putObject(putObjectRequest);
            fileObj.delete();
            fileResponses.add(FileResponse.builder()
                    .fileName(fileName)
                    .fileUrl(s3Client.get().getUrl(bucketName, folderName + fileName).toString())
                    .fileType(file.getContentType())
                    .size(file.getSize())
                    .build());
        }
        return fileResponses;
    }

    @Override
    public FileResponse uploadFileToAWSS3(File file, String folderName) {
        String fileName = System.currentTimeMillis() + UUID.randomUUID().toString() + file.getName();
        return uploadFileToAWSS3(file, folderName, fileName);
    }

    @Override
    public FileResponse uploadFileToAWSS3(File file, String folderName, String fileName) {
        if (s3Client == null) {
            System.out.println("AWS S3 is disabled. Skipping file upload.");
            return null;
        }

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName + fileName, file);
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        s3Client.get().putObject(putObjectRequest);
        file.delete();
        return FileResponse.builder()
                .fileName(fileName)
                .fileUrl(s3Client.get().getUrl(bucketName, folderName + fileName).toString())
                .build();
    }

    @Override
    public byte[] downloadFileFromAWSS3(String fileName) {
        if (s3Client == null) {
            System.out.println("AWS S3 is disabled. Skipping file upload.");
            return null;
        }

        S3Object s3Object = s3Client.get().getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileInputStream downloadFileTOStreamFromAWSS3(String key) {
        if (s3Client == null) {
            System.out.println("AWS S3 is disabled. Skipping file upload.");
            return null;
        }

        S3Object s3Object = s3Client.get().getObject(bucketName, key);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            // Tạo một tệp tạm thời để ghi dữ liệu từ S3ObjectInputStream
            File tempFile = File.createTempFile("tempFile", null);
            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);

            // Đọc dữ liệu từ S3ObjectInputStream và ghi vào tệp tạm thời
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            // Đóng luồng
            fileOutputStream.close();
            inputStream.close();

            // Trả về FileInputStream của tệp tạm thời
            return new FileInputStream(tempFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileResponse uploadFileToSystem(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        String filePath = uploadPath + fileName;
        file.transferTo(new File(filePath));
        // Create a URI object from the file path
        URI fileUri = URI.create("file://" + filePath);
        return FileResponse.builder()
                .fileName(fileName)
                .fileType(file.getContentType())
                .size(file.getSize())
                .fileUrl(fileUri.toURL().toString())
                .build();
    }

    @Override
    public byte[] downloadFileFromSystem(String fileName) throws IOException {
        String filePath = uploadPath + fileName;
        return Files.readAllBytes(new File(filePath).toPath());
    }

    @Override
    public List<String> uploadAllFileInFolderToS3(String folderPath) {
        List<String> fileUrls = new ArrayList<>();
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                FileResponse fileResponse = uploadFileToAWSS3(file, Constants.IMAGES);
                fileUrls.add(fileResponse.getFileUrl());
            }
        }
        return fileUrls;
    }
}
