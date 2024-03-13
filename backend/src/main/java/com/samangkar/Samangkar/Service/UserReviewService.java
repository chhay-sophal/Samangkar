package com.samangkar.Samangkar.service;

import com.samangkar.Samangkar.dto.UserReviewDto;
import com.samangkar.Samangkar.model.Role;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.model.UserEntity;
import com.samangkar.Samangkar.model.UserReview;
import com.samangkar.Samangkar.repository.ShopRepository;
import com.samangkar.Samangkar.repository.UserRepository;
import com.samangkar.Samangkar.repository.UserReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserReviewService {


    @Autowired
    UserReviewRepository userReviewRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    UserRepository userRepository;

    //GET ALL USER REVIEW
    public List<UserReviewDto> getAllUserReview(){
        List<UserReview> results = userReviewRepository.findAllByDeletedAtIsNull();
        if(results.isEmpty()){
            throw new RuntimeException("No rows return");
        }
        return results.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private UserReviewDto convertToDto(UserReview userReview){
        UserReviewDto urdto = new UserReviewDto();
        urdto.setId(userReview.getId());
        urdto.setUser_id(userReview.getUser().getId());
        urdto.setShop_id(userReview.getShop().getId());
        urdto.setTitle(userReview.getTitle());
        urdto.setDescription(userReview.getDescription());
        urdto.setStars(userReview.getStars());
        urdto.setCreatedAt(userReview.getCreatedAt());
        urdto.setDeletedAt(userReview.getDeletedAt());
        urdto.setUpdatedAt(userReview.getUpdatedAt());
        return urdto;
    }

    //GET ALL USER REVIEW SHORT BY STAR DES
    public List<UserReviewDto> getAllUserReviewShortByStarDesc(){
        List<UserReview> results = userReviewRepository.findAllByDeletedAtIsNullOrderByStarsDesc();
        if(results.isEmpty()){
            throw new RuntimeException("No rows return");
        }
        return results.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //GET USER REVIEW BY SHOP ID => WHERE IS DELETE_AT = NULL
    @SuppressWarnings("null")
    public List<UserReviewDto> getAllUserReviewByShopId(Long ShopId){
        Optional<Shop> shop = shopRepository.findById(ShopId);
        if(shop.isPresent()){
            List<UserReview> results = userReviewRepository.findByShopIdAndDeletedAtIsNull(ShopId);
            return results.stream().map(this::convertToDto).collect(Collectors.toList());
        }else{
            throw new IllegalStateException("Shop with id "+ ShopId + "does not exist");
        }
    }

    //INSERT REVIEW BY SHOP_ID AND USER_ID
    @SuppressWarnings("null")
    public void saveUserReview(UserReviewDto userReviewDto) {

        if(userReviewDto.getUser_id() == null){
            throw new IllegalStateException("user_id can not be null");
        }
        if(userReviewDto.getShop_id() == null){
            throw new IllegalStateException("shop_id can not be null");
        }
        Optional<UserEntity> userOptional = userRepository.findById(userReviewDto.getUser_id());
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with ID " + userReviewDto.getUser_id() + " not found");
        }else{
            UserEntity user = userOptional.get();
            Role userRole = user.getUserRole();
            System.out.println(userRole);
            if (userRole.getId() != 3) {
                throw new IllegalStateException("User does not have role ID 2");
            }
        }
        Optional<Shop> shopOptional = shopRepository.findById(userReviewDto.getShop_id());
        if (shopOptional.isEmpty()) {
            throw new EntityNotFoundException("Shop with ID " + userReviewDto.getShop_id() + " not found");
        }
        if(userReviewDto.getStars() > 5){
            throw new IllegalStateException("stars can not be greater than 5");
        }

        UserReview urw = new UserReview();
        urw.setUser(userOptional.get());
        urw.setShop(shopOptional.get());
        urw.setTitle(userReviewDto.getTitle());
        urw.setDescription(userReviewDto.getDescription());
        urw.setStars(userReviewDto.getStars());
        urw.setCreatedAt(new Date());
        userReviewRepository.save(urw);

    }


    //UPDATE REVIEW => SET DESCRIPTION, UPDATED_AT = NOW
    @SuppressWarnings("null")
    public void updateUserReview(UserReviewDto userReviewDto){

        Optional<UserReview> existingUserReviewOptional = userReviewRepository.findById(userReviewDto.getId());
        if (existingUserReviewOptional.isEmpty()) {
            throw new EntityNotFoundException("User Review with ID " + userReviewDto.getId() + " not found");
        }
        if(userReviewDto.getStars() > 5){
            throw new IllegalStateException("stars can not be greater than 5");
        }
        UserReview existingUserReview = existingUserReviewOptional.get();
        existingUserReview.setTitle(userReviewDto.getTitle());
        existingUserReview.setDescription(userReviewDto.getDescription());
        existingUserReview.setStars(userReviewDto.getStars());
        existingUserReview.setUpdatedAt(new Date());
        userReviewRepository.save(existingUserReview);
    }

    //DELETE REVIEW => SET DELETED_AT TO NOW
    @SuppressWarnings("null")
    public void deletedUserReview(Long id){

        Optional<UserReview> existingUserReviewOptional = userReviewRepository.findById(id);
        if (existingUserReviewOptional.isEmpty()) {
            throw new EntityNotFoundException("User Review with ID " + id + " not found");
        }
        UserReview existingUserReview = existingUserReviewOptional.get();
        existingUserReview.setDeletedAt(new Date());
        userReviewRepository.save(existingUserReview);
    }

}
