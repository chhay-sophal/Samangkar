// Sample data for shops with images (using Lorem Picsum URLs)
const shops = [
    { name: "Shop A", category: "Premium", image: "https://i.pinimg.com/564x/6b/da/74/6bda745d88326d9d2f35dc0a51c10052.jpg" },
    { name: "Shop B", category: "MiddleClass", image: "https://i.pinimg.com/564x/18/53/8f/18538f0a190385189f9253d9adcd1f21.jpg" },
    { name: "Shop C", category: "Simple", image: "https://i.pinimg.com/564x/f3/4d/2b/f34d2bc33c132f07d8e18265d24a78ec.jpg" },
    // Add more shop objects as needed
];

// Function to initialize the homepage
function initializeHomepage() {
    displaySlides();
    displayShops();
    displayPopularShops();
    displayPackages();
}

// Function to display slideshow
let slideIndex = 0;

function displaySlides() {
    let slidesHtml = "";
    shops.forEach(shop => {
        slidesHtml += `<div class="slide"><img src="${shop.image}" alt="${shop.name}"></div>`;
    });
    document.querySelector('.slides').innerHTML = slidesHtml;
    showSlides();
}

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function showSlides() {
    const slides = document.querySelectorAll('.slide');
    slides[slideIndex % slides.length].style.opacity = 0;
    slideIndex++;
    slides[slideIndex % slides.length].style.opacity = 1;
    setTimeout(showSlides, 5000); // Change slide every 5 seconds
}

// Function to display shop list
function displayShops() {
    let shopListHtml = "";
    shops.forEach(shop => {
        shopListHtml += `
            <div class="shop-item">
                <img src="${shop.image}" alt="${shop.name}">
                <div class="shop-details">
                    <div class="items">${shop.name}</div>
                    <p>${shop.category}</p>
                </div>
            </div>
        `;
    });
    document.getElementById('shopList').innerHTML = shopListHtml;
}

// Function for searching and filtering shops
function searchAndFilter() {
    const searchText = document.getElementById('searchInput').value.toLowerCase();
    const categoryFilter = document.getElementById('categoryFilter').value.toLowerCase();
    
    const filteredShops = shops.filter(shop => 
        (shop.name.toLowerCase().includes(searchText) || shop.category.toLowerCase().includes(searchText)) &&
        (categoryFilter === "" || shop.category.toLowerCase() === categoryFilter)
    );

    let filteredShopListHtml = "";
    filteredShops.forEach(shop => {
        filteredShopListHtml += `
            <div class="shop-item">
                <img src="${shop.image}" alt="${shop.name}">
                <div class="shop-details">
                    <div class="items">${shop.name}</div>
                    <p>${shop.category}</p>
                </div>
            </div>
        `;
    });
    document.getElementById('shopList').innerHTML = filteredShopListHtml;
}

// Add event listener to search input field
document.getElementById('searchInput').addEventListener('input', searchAndFilter);

// Call the initialization function when the page loads
// Sample data for popular shops
const popularShops = [
    { name: "Popular Shop 1", category: "Clothing", image: "https://via.placeholder.com/150" },
    { name: "Popular Shop 2", category: "Electronics", image: "https://via.placeholder.com/150" },
    { name: "Popular Shop 3", category: "Books", image: "https://via.placeholder.com/150" },
];

// Sample data for packages
// Sample data for packages with images
// Sample data for packages with images
const packages = [
    { name: "Package 1", price: "$10", details: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://i.pinimg.com/564x/f3/4d/2b/f34d2bc33c132f07d8e18265d24a78ec.jpg" },
    { name: "Package 2", price: "$20", details: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://i.pinimg.com/564x/f3/4d/2b/f34d2bc33c132f07d8e18265d24a78ec.jpg" },
    { name: "Package 3", price: "$30", details: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://i.pinimg.com/564x/f3/4d/2b/f34d2bc33c132f07d8e18265d24a78ec.jpg" },
    // Add more package objects as needed
];

// Function to display packages
function displayPackages() {
    let packageListHtml = "";
    packages.forEach(pkg => {
        packageListHtml += `
            <div class="package-item">
                <img src="${pkg.image}" alt="${pkg.name}">
                <div class="package-details">
                    <div class="items">${pkg.name}</div>
                    <div>${pkg.price}</div>
                    <p>${pkg.details}</p>
                </div>
            </div>
        `;
    });
    document.getElementById('packageList').innerHTML = packageListHtml;
}





// Function to display popular shops
function displayPopularShops() {
    let popularShopListHtml = "";
    popularShops.forEach(shop => {
        popularShopListHtml += `
            <div class="shop-item">
                <img src="${shop.image}" alt="${shop.name}">
                <div class="shop-details">
                    <div class="items">${shop.name}</div>
                    <p>${shop.category}</p>
                </div>
            </div>
        `;
    });
    document.getElementById('popularShopList').innerHTML = popularShopListHtml;
}

// Call the initialization function when the page loads
initializeHomepage();
