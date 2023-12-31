//js for landing

// 슬라이더 영역
let slideIndex = 0;

function showSlides() {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  let dots = document.getElementsByClassName("dot");
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  slideIndex++;
  if (slideIndex > slides.length) {
    slideIndex = 1;
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex - 1].style.display = "block";
  dots[slideIndex - 1].className += " active";
  setTimeout(showSlides, 2000); // 2초마다 이미지 변경
}

// 리뷰 슬라이더 영역
let reviewSlideIndex = 1;

function showReviewSlides() {
  let i;
  let reviewSlides = document.getElementsByClassName("reviewtext");
  let dots = document.getElementsByClassName("dot2");
  if (reviewSlideIndex > reviewSlides.length) {
    reviewSlideIndex = 1;
  }
  if (reviewSlideIndex < 1) {
    reviewSlideIndex = reviewSlides.length;
  }
  for (i = 0; i < reviewSlides.length; i++) {
    reviewSlides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  reviewSlides[reviewSlideIndex - 1].style.display = "block";
  dots[reviewSlideIndex - 1].className += " active";
}
function currentSlide(n) {
  showReviewSlides(reviewSlideIndex = n);
}
function plusSlides(n) {
  showReviewSlides(reviewSlideIndex += n);
}

// 슬라이드쇼 초기화
showSlides();
showReviewSlides();

// 오버레이
function openNav() {
  document.getElementById("myNav").style.width = "100%";
}

function closeNav() {
  document.getElementById("myNav").style.width = "0%";
}

// ============== 네비게이션 바 스크롤 다운 시 색 변경 ===========================
// Function to change the navigation bar's background color when scrolling
function changeNavbarBackground() {
var navbar = document.querySelector("nav");
var scrollY = window.scrollY || window.pageYOffset;

// Set the background color to black when scrolled more than one page
if (scrollY > window.innerHeight) {
  navbar.style.backgroundColor = "black";
} else {
  navbar.style.backgroundColor = "transparent";
}
}

// Attach the scroll event listener to trigger the function
window.addEventListener("scroll", changeNavbarBackground);

// Initial call to set the background color based on the page load position
changeNavbarBackground();
// ============== /네비게이션 바 스크롤 다운 시 색 변경 ===========================


// ======================= 스크롤 다운 버튼 동작 제어 ===========================
document.addEventListener("DOMContentLoaded", function () {
  // Get the scroll button, navigation bar, and target section
  const scrollButton = document.getElementById("scroll-button");
  const navBar = document.querySelector("nav");
  const scrollTarget = document.getElementById("scroll-target");

  // Function to scroll smoothly to the target section
  function scrollToTarget() {
    // Calculate the top position of the navigation bar and the bottom position of the target section
    const navBarTop = navBar.getBoundingClientRect().top;
    const targetBottom = scrollTarget.getBoundingClientRect().bottom;

    // Calculate the scroll distance
    const scrollDistance = targetBottom - navBarTop + 5; // Adjust the value to control how much to scroll

    window.scrollTo({
      top: scrollDistance,
      behavior: "smooth",
    });
  }

  // Add a click event listener to the scroll button
  scrollButton.addEventListener("click", scrollToTarget);
});

// ======================= /스크롤 다운 버튼 동작 제어 ===========================










