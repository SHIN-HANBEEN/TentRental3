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