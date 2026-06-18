// ================= HEADER INIT =================

function initSellerHeader() {

    const btn = document.getElementById("profileBtn");
    const dropdown = document.getElementById("profileDropdown");

    if (!btn || !dropdown) return;

    // prevent duplicate binding
    if (btn.dataset.bound === "true") return;
    btn.dataset.bound = "true";

    // OPEN / CLOSE
    btn.onclick = function (e) {
        e.stopPropagation();
        dropdown.classList.toggle("show");
    };

    // OUTSIDE CLICK CLOSE
    document.addEventListener("click", function (e) {
        if (!e.target.closest(".profile-menu")) {
            dropdown.classList.remove("show");
        }
    });

    // ESC CLOSE
    document.addEventListener("keydown", function (e) {
        if (e.key === "Escape") {
            dropdown.classList.remove("show");
        }
    });
}


// ================= HEADER WATCHER =================

function watchHeader() {

    const interval = setInterval(() => {

        const btn = document.getElementById("profileBtn");
        const name = document.getElementById("sellerName");

        if (btn && name) {

            initSellerHeader();
            setSellerName();

            clearInterval(interval);
        }

    }, 100);
}


// ================= SELLER NAME SET =================

function setSellerName() {

    const seller = getSeller();

    if (!seller) return;

    const el1 = document.getElementById("sellerName");
    const el2 = document.getElementById("headerSellerName");

    if (el1) el1.innerText = seller.ownerName || "Seller";
    if (el2) el2.innerText = seller.ownerName || "Account";
}


// ================= AUTH GUARD (GLOBAL SAFE) =================

function sellerAuthGuard() {

    const token = localStorage.getItem("accessToken");
    const role = localStorage.getItem("role");

    if (!token || role !== "SELLER") {
        window.location.href = "seller-login.html";
    }
}


// ================= GET SELLER =================

function getSeller() {

    const sellerData = localStorage.getItem("seller");

    if (!sellerData) return null;

    try {
        return JSON.parse(sellerData);
    } catch (e) {
        return null;
    }
}

// ================= COMPONENT LOADER =================

function loadComponent(id, file) {

    fetch(file)
        .then(response => response.text())
        .then(html => {

            const element = document.getElementById(id);

            if (element) {
                element.innerHTML = html;
            }

            // header load hone ke baad
            if (id === "header") {
                watchHeader();
                setSellerName();
            }
        })
        .catch(error => {
            console.error("Component load error:", error);
        });
}

// ================= LOGOUT =================

function logout() {

    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken");
    localStorage.removeItem("seller");
    localStorage.removeItem("role");

    window.location.href = "seller-login.html";
}