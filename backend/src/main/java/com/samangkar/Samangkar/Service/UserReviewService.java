package com.samangkar.Samangkar.service;

import com.samangkar.Samangkar.dto.UserReviewDto;
import com.samangkar.Samangkar.model.Shop;
import com.samangkar.Samangkar.model.UserReview;
import com.samangkar.Samangkar.repository.ShopRepository;
import com.samangkar.Samangkar.repository.UserReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserReviewService {


    @Autowired
    UserReviewRepository userReviewRepository;

    @Autowired
    ShopRepository shopRepository;

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
    public void CreatReview(UserReviewDto userReviewDto){

    }

    //UPDATE REVIEW BY SHOP_ID AND USER_ID => SET DESCRIPRTION , UPDATED_AT = NOW
    
    //DELETE REVIEW => SET DELETED_AT TO NOW


}
