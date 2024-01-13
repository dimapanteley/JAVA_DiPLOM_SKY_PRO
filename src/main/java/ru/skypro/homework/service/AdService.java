package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.io.IOException;

public interface AdService {
    Ads getAllAds();

    Ad addAd(CreateOrUpdateAd properties, MultipartFile image,  Authentication authentication) throws IOException;

    ExtendedAd getAd(int id);

    void removeAd(int id) throws IOException;

    Ad updateAd(int id, CreateOrUpdateAd newAd);

    Ads getAdsByUsername(String username);

    byte[] updateAdImage(int id, MultipartFile image) throws IOException;
}
