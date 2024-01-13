package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final ImageServiceImpl imageService;

    public AdServiceImpl(AdRepository adRepository, UserRepository userRepository, ImageServiceImpl imageService) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    @Override
    public Ads getAllAds() {
        log.info("Run method getAllAds");
        Collection<Ad> ads = adRepository.findAll().stream()
                .map(AdMapper::toAd)
                .collect(Collectors.toList());
        return new Ads(ads.size(), ads);
    }

    @Override
    @Transactional
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image,
                    Authentication authentication) throws IOException {
        log.info("Run method addAd");
        AdEntity adEntity = new AdEntity();
        adEntity.setTitle(properties.getTitle());
        adEntity.setPrice(properties.getPrice());
        adEntity.setDescription(properties.getDescription());
        adEntity.setAuthor(userRepository.getByUsername(authentication.getName()));
        adEntity.setImage(imageService.updateImage(image));
        return AdMapper.toAd(adRepository.save(adEntity));
    }

    @Override
    public ExtendedAd getAd(int id) {
        log.info("Run method getAd");
        return AdMapper.toExtendedAd(adRepository.getAdById(id));
    }

    @Override
    @Transactional
    public void removeAd(int id) throws IOException {
        log.info("Run method removeAd");
        AdEntity ad = adRepository.getAdById(id);
        adRepository.delete(ad);
        imageService.removeImageById(ad.getImage().getId());
    }

    @Override
    public Ad updateAd(int id, CreateOrUpdateAd newAd) {
        log.info("Run method updateAd");
        AdEntity ad = adRepository.getAdById(id);
        ad.setTitle(newAd.getTitle());
        ad.setPrice(newAd.getPrice());
        ad.setDescription(newAd.getDescription());
        return AdMapper.toAd(adRepository.save(ad));
    }

    @Override
    @Transactional
    public Ads getAdsByUsername(String username) {
        log.info("Run method getAdsByUsername");
        UserEntity user = userRepository.getByUsername(username);
        List<Ad> ads = adRepository.findAllByAuthor(user).stream().map(AdMapper::toAd).collect(Collectors.toList());
        return new Ads(ads.size(), ads);
    }

    @Override
    @Transactional
    public byte[] updateAdImage(int id, MultipartFile image) throws IOException {
        log.info("Run method updateAdImage");
        AdEntity ad = adRepository.getAdById(id);
        imageService.removeImageById(ad.getImage().getId());
        ad.setImage(imageService.updateImage(image));
        return adRepository.save(ad).getImage().getData();
    }

    public boolean isAuthorAd(String username, int adId) {
        log.info("Run method isAuthorAd");
        AdEntity ad = adRepository.getAdById(adId);
        return ad.getAuthor().getUsername().equals(username);
    }
}