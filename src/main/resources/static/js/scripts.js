/*!
* Start Bootstrap - Shop Homepage v5.0.6 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

/* ========= customizeNavbar shrink sticky navbar ========= */
var prevScrollpos = window.pageYOffset;
window.onscroll = function() {
  var currentScrollPos = window.pageYOffset;
  if (prevScrollpos > currentScrollPos) {
    document.getElementById("customizeNavbar").style.top = "0";
  } else {
    document.getElementById("customizeNavbar").style.top = "-70px";
  }
  prevScrollpos = currentScrollPos;
}
/* ========= /customizeNavbar shrink sticky navbar ========= */

/* ========= 비밀번호 소문자, 대문자, 개수 확인 js ============= */
var myInput = document.getElementById("userPw");
var letter = document.getElementById("PWletter");
var capital = document.getElementById("PWcapital");
var number = document.getElementById("PWnumber");
var length = document.getElementById("PWlength");

if( (myInput != null) && (letter != null) && (capital != null) && (number != null) && (length != null) ) {
    // When the user clicks on the password field, show the message box
    myInput.onfocus = function() {
      document.getElementById("PWmessage").style.display = "block";
    }

    // When the user clicks outside of the password field, hide the message box
    myInput.onblur = function() {
      document.getElementById("PWmessage").style.display = "none";
    }

    // When the user starts to type something inside the password field
    myInput.onkeyup = function() {
      // Validate lowercase letters
      var lowerCaseLetters = /[a-z]/g;
      if(myInput.value.match(lowerCaseLetters)) {
        letter.classList.remove("PWinvalid");
        letter.classList.add("PWvalid");
      } else {
        letter.classList.remove("PWvalid");
        letter.classList.add("PWinvalid");
      }

      // Validate capital letters
      var upperCaseLetters = /[A-Z]/g;
      if(myInput.value.match(upperCaseLetters)) {
        capital.classList.remove("PWinvalid");
        capital.classList.add("PWvalid");
      } else {
        capital.classList.remove("PWvalid");
        capital.classList.add("PWinvalid");
      }

      // Validate numbers
      var numbers = /[0-9]/g;
      if(myInput.value.match(numbers)) {
        number.classList.remove("PWinvalid");
        number.classList.add("PWvalid");
      } else {
        number.classList.remove("PWvalid");
        number.classList.add("PWinvalid");
      }

      // Validate length
      if(myInput.value.length >= 8) {
        length.classList.remove("PWinvalid");
        length.classList.add("PWvalid");
      } else {
        length.classList.remove("PWvalid");
        length.classList.add("PWinvalid");
      }
    }
} else {
    console.log("비밀번호 개수 확인 관련 태그가 없습니다.")
}

/* ========= /비밀번호 소문자, 대문자, 개수 확인 js ============= */

/* ========= userEmail confirm logic ========= */
const emailInput = document.getElementById("userEmail");
const checkEmailButton = document.getElementById("checkEmailButton");
const emailCheckResult = document.getElementById("emailCheckResult");

console.log(checkEmailButton);

if (checkEmailButton != null) { /* emailCheckButton 이 있을 때 로직 */
    checkEmailButton.addEventListener
    ( "click", function ()
        {
            const userEmail = emailInput.value;

            // AJAX request using Fetch API. EmailController 라는 @RestController 를 만든다음. EmailCheckResponse 객체를 반환시켰다.
            fetch(`/check-email?email=${userEmail}`, {
              method: "GET",
            })
              .then((response) => response.json())
              .then((data) => {
                if (data.exists) {
                  emailCheckResult.textContent = "이미 사용 중인 이메일입니다.";
                  emailCheckResult.style.color = "red";
                } else {
                  emailCheckResult.textContent = "사용 가능한 이메일입니다.";
                  emailCheckResult.style.color = "green";

                  // If email is not found, make the input field readonly
                  emailInput.readOnly = true;
                  checkEmailButton.disabled = true; // Disable the button as well
                }
              })
              .catch((error) => {
                console.error("Error checking email:", error);
                emailCheckResult.textContent = "이메일 확인 중 오류가 발생했습니다.";
                emailCheckResult.style.color = "red";
              });
        }
    );
} else { /* emailCheckButton 가 없을 때 로직 */
    console.log("emailCheckButton 없음");
}

/* ========= /userEmail confirm logic ========= */

/* ========= password confirm logic ========= */
// Get references to the password and password confirmation input fields
const passwordInput = document.getElementById("userPw");
const passwordConfirmInput = document.getElementById("password_confirm");

// Add a keyup event listener to the password confirmation field
passwordConfirmInput.addEventListener("keyup", function () {
const password = passwordInput.value;
const passwordConfirm = passwordConfirmInput.value;
const matchMessage = document.getElementById("password-match-message");

// Check if the passwords match
if (password === passwordConfirm) {
  matchMessage.textContent = ": 비밀번호가 일치합니다.";
  matchMessage.style.color = "green";
} else {
  matchMessage.textContent = ": 비밀번호가 일치하지 않습니다.";
  matchMessage.style.color = "red";
}
});
/* ========= /password confirm logic ========= */


/* ========= 회원정보수정 & login & logout button ========= */
function redirectToEdit() {
    // Redirect to the login page
    window.location.href = '/user/edit'; // Replace '/login' with your login URL
}

function redirectToLogin() {
    // Redirect to the login page
    window.location.href = '/user/customlogin'; // Replace '/login' with your login URL
}

function redirectToLogout() {
    // Redirect to the logout page
    window.location.href = '/logout'; // Replace '/logout' with your logout URL
}
/* ========= /login & logout button ========= */