

// ===============================
// LOGOUT HANDLER (FIXED)
// ===============================
function bindLogout() {

    document.addEventListener("click", function (e) {

        if (e.target && e.target.id === "logoutBtn") {

            e.preventDefault();

            Swal.fire({
                title: "Are you sure?",
                text: "You will be logged out!",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#ef4444",
                cancelButtonColor: "#6b7280",
                confirmButtonText: "Yes, Logout"
            }).then((result) => {

                if (result.isConfirmed) {

                    localStorage.removeItem("accessToken");
                    localStorage.removeItem("refreshToken");
                    localStorage.removeItem("admin");
                    localStorage.removeItem("role");

                    sessionStorage.clear();

                    window.location.replace("admin-login.html");
                }
            });
        }
    });
}


// ===============================
// LOAD COMPONENTS
// ===============================
function loadComponent(id, file) {

    fetch(file)
        .then(res => res.text())
        .then(data => {
            document.getElementById(id).innerHTML = data;
        })
        .catch(err => console.log(err));
}

function checkAdminAuth() {

    const token = localStorage.getItem("accessToken");
    const role = localStorage.getItem("role");

    if (!token || role !== "ADMIN") {
        window.location.replace("admin-login.html");
    }
}


// ===============================
// INIT APP
// ===============================
function initAdminApp() {

    checkAdminAuth();

    loadComponent("adminHeader", "admin-header.html");
    loadComponent("sidebar", "admin-sidebar.html");

    bindLogout(); // 🔥 IMPORTANT
}