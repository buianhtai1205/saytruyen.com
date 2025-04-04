package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerRequest {

    private String name;
    
    private String url;
    
    private String bannerType;
    
    private String bannerLinkedId;
    
    private String bgDesktop;
    
    private String bannerDesktop;
    
    private String bannerMobile;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    private LocalDateTime deletedAt;
    
    private boolean isDeleted;
    
    private String others;
    
}