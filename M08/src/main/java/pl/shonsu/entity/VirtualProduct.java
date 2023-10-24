package pl.shonsu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("VIRTUAL")
public class VirtualProduct extends BaseProduct {
    private boolean downloadable;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "file_name")
    private String fileName;

    public VirtualProduct() {
    }

    public VirtualProduct(Long id, String name, String description, LocalDateTime created, ProductType productType, boolean downloadable, String filePath, String fileName) {
        super(id, name, description, created, productType);
        this.downloadable = downloadable;
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public boolean isDownloadable() {
        return downloadable;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "VirtualProduct{" +
                "downloadable=" + downloadable +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
