package com.samangkar.Samangkar.config;

import com.samangkar.Samangkar.model.*;
import com.samangkar.Samangkar.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class DatabaseSeederConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase(
            RoleRepository roleRepository,
            UserRepository userRepository,
            ContactTypeRepository contactTypeRepository,
            ShopRepository shopRepository,
            ShopContactRepository shopContactRepository,
            PackageRepository packageRepository,
            ServiceRepository serviceRepository,
            UserCardRepository userCardRepository,
            UserFavoriteRepository userFavoriteRepository,
            UserReviewRepository userReviewRepository
    ) {
        return args -> {
            // Seed user types
            seedUserType(roleRepository, "ADMIN");
            seedUserType(roleRepository, "SHOP_OWNER");
            seedUserType(roleRepository, "USER");

            // Seed users
            seedUser(userRepository, "admin1", "admin1@example.com", "admin1234", roleRepository.findFirstByName("ADMIN"));
            seedUser(userRepository, "admin2", "admin2@example.com", "admin5678", roleRepository.findFirstByName("ADMIN"));
            seedUser(userRepository, "shop_owner1", "shop_owner1@example.com", "shop1234", roleRepository.findFirstByName("SHOP_OWNER"));
            seedUser(userRepository, "shop_owner2", "shop_owner2@example.com", "shop5678", roleRepository.findFirstByName("SHOP_OWNER"));
            seedUser(userRepository, "shop_owner3", "shop_owner3@example.com", "shop8910", roleRepository.findFirstByName("SHOP_OWNER"));
            seedUser(userRepository, "user1", "user1@example.com", "user1234", roleRepository.findFirstByName("USER"));
            seedUser(userRepository, "user2", "user2@example.com", "user5678", roleRepository.findFirstByName("USER"));
            seedUser(userRepository, "user3", "user3@example.com", "user8910", roleRepository.findFirstByName("USER"));
            // Seed More users
            seedUser(userRepository, "shop_owner1a", "shop_owner1a@example.com", "admin1234", roleRepository.findFirstByName("SHOP_OWNER"));
            seedUser(userRepository, "shop_owner1b", "shop_owner1b@example.com", "admin5678", roleRepository.findFirstByName("SHOP_OWNER"));
            seedUser(userRepository, "shop_owner2d", "shop_owner2d@example.com", "shop5678", roleRepository.findFirstByName("SHOP_OWNER"));
            seedUser(userRepository, "shop_owner3c", "shop_owner3c@example.com", "shop8910", roleRepository.findFirstByName("SHOP_OWNER"));
            seedUser(userRepository, "shop_owner1q", "shop_owner1q@example.com", "user1234", roleRepository.findFirstByName("SHOP_OWNER"));
            seedUser(userRepository, "shop_owner1e", "shop_owner1e@example.com", "user5678", roleRepository.findFirstByName("SHOP_OWNER"));
            seedUser(userRepository, "shop_owner1f", "shop_owner1f@example.com", "user8910", roleRepository.findFirstByName("SHOP_OWNER"));

            // Seed contact types
            seedContactType(contactTypeRepository, "Phone Number");
            seedContactType(contactTypeRepository, "Email");
            seedContactType(contactTypeRepository, "Facebook");
            seedContactType(contactTypeRepository, "Instagram");
            seedContactType(contactTypeRepository, "Tik Tok");

            // Seed shops
            seedShop(shopRepository, "shop1", "This is shop 1", "ImageUrl1", userRepository.findFirstByUsername("shop_owner1"));
            seedShop(shopRepository, "shop2", "This is shop 2", "ImageUrl2", userRepository.findFirstByUsername("shop_owner2"));
            seedShop(shopRepository, "shop3", "This is shop 3", "ImageUrl3", userRepository.findFirstByUsername("shop_owner3"));

            // Seed shop contacts
            seedShopContact(shopContactRepository, contactTypeRepository.findFirstByPlatform("Phone Number"), "0987654321", shopRepository.findFirstByName("shop1"));
            seedShopContact(shopContactRepository, contactTypeRepository.findFirstByPlatform("Phone Number"), "0123456789", shopRepository.findFirstByName("shop1"));
            seedShopContact(shopContactRepository, contactTypeRepository.findFirstByPlatform("Email"), "shop1@example.com", shopRepository.findFirstByName("shop1"));
            seedShopContact(shopContactRepository, contactTypeRepository.findFirstByPlatform("Facebook"), "shop2.facebook.com", shopRepository.findFirstByName("shop2"));
            seedShopContact(shopContactRepository, contactTypeRepository.findFirstByPlatform("Instagram"), "shop2.instagram.com", shopRepository.findFirstByName("shop2"));
            seedShopContact(shopContactRepository, contactTypeRepository.findFirstByPlatform("Tik Tok"), "shop3.tiktok.com", shopRepository.findFirstByName("shop3"));
            seedShopContact(shopContactRepository, contactTypeRepository.findFirstByPlatform("Phone Number"), "09878374321", shopRepository.findFirstByName("shop3"));
            seedShopContact(shopContactRepository, contactTypeRepository.findFirstByPlatform("Email"), "shop3@example.com", shopRepository.findFirstByName("shop3"));
            seedShopContact(shopContactRepository, contactTypeRepository.findFirstByPlatform("Phone Number"), "0987602621", shopRepository.findFirstByName("shop2"));

            // Seed packages
            seedPackage(packageRepository, "Standard", "Most affordable", shopRepository.findFirstByName("shop1"));
            seedPackage(packageRepository, "Premium", "Balance", shopRepository.findFirstByName("shop1"));
            seedPackage(packageRepository, "Platinum", "The best of us", shopRepository.findFirstByName("shop1"));
            seedPackage(packageRepository, "Standard", "Most affordable", shopRepository.findFirstByName("shop2"));
            seedPackage(packageRepository, "Legend", "Exclusive package for you", shopRepository.findFirstByName("shop2"));
            seedPackage(packageRepository, "Golden", "Golden package", shopRepository.findFirstByName("shop3"));
            seedPackage(packageRepository, "Silver", "Silver package", shopRepository.findFirstByName("shop3"));
            seedPackage(packageRepository, "Luxury", "Luxury package", shopRepository.findFirstByName("shop3"));

            // Seed service
            seedService(serviceRepository, "Make up", "Make the groom and bride look beautiful", shopRepository.findFirstByName("shop1"), 100.00);
            seedService(serviceRepository, "Place", "Where to put your wedding", shopRepository.findFirstByName("shop1"), 150.50);
            seedService(serviceRepository, "Sound", "Sound make magic", shopRepository.findFirstByName("shop1"), 65.93);
            seedService(serviceRepository, "Food", "Delicious with every bit", shopRepository.findFirstByName("shop2"), 93.04);
            seedService(serviceRepository, "Make up", "Make the groom and bride look beautiful", shopRepository.findFirstByName("shop2"), 100.00);
            seedService(serviceRepository, "Make up", "Make the groom and bride look beautiful", shopRepository.findFirstByName("shop3"), 500.00);
            seedService(serviceRepository, "Environment", "Keep the atmosphere cool and relaxed", shopRepository.findFirstByName("shop3"), 335.03);

            // Seed user card
            seedUserCard(
                    userCardRepository,
                    userRepository.findFirstByUsername("user1"),
                    shopRepository.findFirstByName("shop1"),
                    serviceRepository.findFirstByNameAndShop("Make up", shopRepository.findFirstByName("shop1")),
                    1);
            seedUserCard(
                    userCardRepository,
                    userRepository.findFirstByUsername("user2"),
                    shopRepository.findFirstByName("shop3"),
                    serviceRepository.findFirstByNameAndShop("Environment", shopRepository.findFirstByName("shop3")),
                    3
                    );
            seedUserCard(
                    userCardRepository,
                    userRepository.findFirstByUsername("user3"),
                    shopRepository.findFirstByName("shop2"),
                    serviceRepository.findFirstByNameAndShop("Food", shopRepository.findFirstByName("shop2")),
                    1);
            seedUserCard(
                    userCardRepository,
                    userRepository.findFirstByUsername("user1"),
                    shopRepository.findFirstByName("shop2"),
                    serviceRepository.findFirstByNameAndShop("Make up", shopRepository.findFirstByName("shop2")),
                    1);

            // Seed user favorites
            seedUserFavorite(userFavoriteRepository, userRepository.findFirstByUsername("user1"), shopRepository.findFirstByName("shop1"));
            seedUserFavorite(userFavoriteRepository, userRepository.findFirstByUsername("user1"), shopRepository.findFirstByName("shop2"));
            seedUserFavorite(userFavoriteRepository, userRepository.findFirstByUsername("user2"), shopRepository.findFirstByName("shop2"));
            seedUserFavorite(userFavoriteRepository, userRepository.findFirstByUsername("user2"), shopRepository.findFirstByName("shop3"));
            seedUserFavorite(userFavoriteRepository, userRepository.findFirstByUsername("user3"), shopRepository.findFirstByName("shop3"));

            // Seed user reviews
            seedUserReview(
                    userReviewRepository,
                    userRepository.findFirstByUsername("user1"),
                    shopRepository.findFirstByName("shop1"),
                    5,
                    "This shop is good",
                    "Description of this review."
            );
            ;
            seedUserReview(
                    userReviewRepository,
                    userRepository.findFirstByUsername("user1"),
                    shopRepository.findFirstByName("shop2"),
                    3,
                    "This shop is not good",
                    "Description of this review."
            );
            seedUserReview(
                    userReviewRepository,
                    userRepository.findFirstByUsername("user2"),
                    shopRepository.findFirstByName("shop2"),
                    4,
                    "This shop is almost good",
                    "Description of this review."
            );

            // After seeding data, display this message
            System.out.println("Data Seeding: Data seeded successfully!");

        };
    }

    @Transactional
    private void seedUserType(RoleRepository repository, String name) {
        if (repository.findByName(name).isEmpty()) {
            Role role = new Role();
            role.setName(name);
            repository.save(role);
        }
    }

    @Transactional
    private void seedUser(UserRepository userRepository, String username, String email, String password, Role role) {

        if (userRepository.findByUsername(username).isEmpty()) {
            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setUserRole(role);
            userRepository.save(user);
        }
    }

    @Transactional
    private void seedContactType(ContactTypeRepository repository, String platform) {
        if (repository.findByPlatform(platform).isEmpty()) {
            ContactType contactType = new ContactType();
            contactType.setPlatform(platform);
            repository.save(contactType);
        }
    }

    @Transactional
    private void seedShop(ShopRepository repository, String name, String description, String shopImageUrl, UserEntity owner) {
        if (repository.findByName(name).isEmpty()) {
            Shop shop = new Shop(name, description, shopImageUrl, owner);
            repository.save(shop);
        }
    }

    @Transactional
    private void seedShopContact(ShopContactRepository repository, ContactType contactType, String url, Shop shop) {
        if (repository.findByUrl(url).isEmpty()) {
            ShopContact shopContact = new ShopContact(contactType, url, shop);
            repository.save(shopContact);
        }
    }

    @Transactional
    private void seedPackage(PackageRepository repository, String name, String description, Shop shop) {
        if (repository.findByNameAndShop(name, shop).isEmpty()) {
            PackageModel packageModel = new PackageModel(name, description, shop);
            repository.save(packageModel);
        }
    }

    @Transactional
    private void seedService(ServiceRepository repository, String name, String description, Shop shop, double unitPrice) {
        if (repository.findByNameAndShop(name, shop).isEmpty()) {
            ServiceModel serviceModel = new ServiceModel(name, description, shop, unitPrice);
            repository.save(serviceModel);
        }
    }

    @Transactional
    private void seedUserCard(UserCardRepository repository, UserEntity user, Shop shop, ServiceModel service, int quantity) {
        if (repository.findByUserAndService(user, service).isEmpty()) {
            double total = service.getUnitPrice() * quantity;
            UserCard userCard = new UserCard(user, service, total, quantity);
            repository.save(userCard);
        }
    }

    @Transactional
    private void seedUserFavorite(UserFavoriteRepository repository, UserEntity user, Shop shop) {
        if (repository.findByUserAndShop(user, shop).isEmpty()) {
            UserFavorite userFavorite = new UserFavorite(user, shop);
            repository.save(userFavorite);
        }
    }

    @Transactional
    private void seedUserReview(UserReviewRepository repository, UserEntity user, Shop shop, int stars, String title, String description) {
//        if (repository.findByUserAndShop(user, shop).isEmpty()) {
//            UserReview userReview = new UserReview(user, shop, title, description);
//            repository.save(userReview);
//        }

        UserReview userReview = new UserReview(user, shop, stars, title, description);
        repository.save(userReview);

    }
}
