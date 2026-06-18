// =============================
// AUTH.JS
// =============================

function loadAuthUI() {

    // ✅ "loggedInUser" key use karo (login mein yahi save hota hai)
    let userData = localStorage.getItem("loggedInUser");

    let dropdown = document.getElementById("profileDropdown");
    let wishlistIcon = document.getElementById("wishlistIcon");

    if (userData) {

        let user = JSON.parse(userData);

        // WISHLIST DIKHAO
        if (wishlistIcon) {
            wishlistIcon.style.display = "block";
        }

        dropdown.innerHTML = `
            <h3>Hello ${user.name}</h3>
            <p>Welcome to your account</p>

            <hr>

            <a href="profile.html">
                <i class="fa-solid fa-user"></i>
                My Profile
            </a>

            <a href="#" onclick="openOrders()">
                <i class="fa-solid fa-bag-shopping"></i>
                My Orders
            </a>

            <hr>

            <a href="#" onclick="logoutUser()">
                <i class="fa-solid fa-right-from-bracket"></i>
                Logout
            </a>
        `;

    } else {

        // WISHLIST CHHUPAO
        if (wishlistIcon) {
            wishlistIcon.style.display = "none";
        }

        dropdown.innerHTML = `
            <h3>Hello User</h3>
            <p>To access your account</p>

            <div class="auth-buttons">
                <button onclick="window.location.href='signup.html'">Sign Up</button>
                <button onclick="window.location.href='login.html'">Log In</button>
            </div>

            <hr>

            <a href="#" onclick="openOrders()">
                <i class="fa-solid fa-bag-shopping"></i>
                My Orders
            </a>
        `;
    }
}


function logoutUser() {

    // ✅ Sab kuch clear karo including refreshToken
    localStorage.removeItem("loggedInUser");
    localStorage.removeItem("userId");
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken");
    localStorage.removeItem("role");

    window.location.href = "login.html";
}


function openOrders() {

    let userId = localStorage.getItem("userId");

    if (!userId) {

        Swal.fire({
            icon: "warning",
            title: "Login Required",
            text: "Please login to view your orders"
        }).then(() => {
            window.location.href = "login.html";
        });

        return;
    }

    window.location.href = "orders.html?userId=" + userId;
}