package vn.com.saytruyen.common_service.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import vn.com.saytruyen.common_service.exception.BusinessException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * The type File utils.
 */
@Slf4j
public class FileUtils {

    /**
     * Compress file byte [ ].
     *
     * @param data the data
     * @return the byte [ ]
     */
    public static byte[] compressFile(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignore) {
        }
        return outputStream.toByteArray();
    }

    /**
     * Decompress file byte [ ].
     *
     * @param data the data
     * @return the byte [ ]
     */
    public static byte[] decompressFile(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (DataFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        return outputStream.toByteArray();
    }

    /**
     * Convert multipart file to file file.
     *
     * @param file the file
     * @return the file
     */
    public static File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return convertedFile;
    }

    /**
     * Convert file output stream to file file.
     *
     * @param fileOutputStream the file output stream
     * @return the file
     */
    public static File convertFileOutputStreamToFile(FileOutputStream fileOutputStream) {
        try {
            // Tạo một tệp tạm thời
            File tempFile = File.createTempFile("tempFile", null);

            // Ghi dữ liệu từ FileOutputStream vào tệp tạm thời
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fileOutputStream.getChannel().transferTo(0, fileOutputStream.getChannel().size(), fos.getChannel());
            }

            // Trả về tệp tạm thời
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Extract file zip string.
     *
     * @param file the file
     * @return the string
     */
    public static String extractFileZip(MultipartFile file) {
        // create a temporary directory
        File tempDir;
        try {
            tempDir = File.createTempFile("temp", Long.toString(System.nanoTime()));
            if (!tempDir.delete() || !tempDir.mkdir()) {
                throw new BusinessException("Failed to create temporary directory.");
            }
        } catch (IOException e) {
            throw new BusinessException("Failed to create temporary directory.");
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream())) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    String entryName = entry.getName();
                    File entryFile = new File(tempDir, entryName);
                    entryFile.getParentFile().mkdirs(); // Tạo các thư mục cha nếu cần
                    try (FileOutputStream fileOutputStream = new FileOutputStream(entryFile)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = zipInputStream.read(buffer)) > 0) {
                            fileOutputStream.write(buffer, 0, length);
                        }
                    }
                }
            }
            return tempDir.getAbsolutePath();
        } catch (IOException e) {
            throw new BusinessException("Failed to extract file zip.");
        }
    }

    /**
     * Delete folder.
     *
     * @param fileExtractPath the file extract path
     */
    public static void deleteFolder(String fileExtractPath) {
        File file = new File(fileExtractPath);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        deleteFolder(f.getAbsolutePath());
                    } else {
                        f.delete();
                    }
                }
            }
            file.delete();
        }
    }
}
