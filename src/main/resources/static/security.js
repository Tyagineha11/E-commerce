// =========================
// REFRESH TOKEN
// =========================

async function refreshAccessToken() {

    const refreshToken =
        localStorage.getItem("refreshToken");

    // Guest user ke liye redirect mat karo
    if (!refreshToken) {
        return null;
    }

    try {

        const response = await fetch(
            "http://localhost:8080/api/auth/refresh",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    refreshToken: refreshToken
                })
            }
        );

        const result = await response.json();

        if (result.code === 200) {

            localStorage.setItem(
                "accessToken",
                result.data.accessToken
            );

            return result.data.accessToken;
        }

        return null;

    } catch (e) {

        console.log("Refresh token error:", e);
        return null;
    }
}

// =========================
// COMMON FETCH
// =========================

async function fetchWithAuth(url, options = {}) { 

    let token =
        localStorage.getItem("accessToken");

    options.headers = {
        ...(options.headers || {})
    };

    // Authorization tabhi bhejo jab token ho
    if (token) {

        options.headers["Authorization"] =
            "Bearer " + token;
    }

    let response =
        await fetch(url, options);

    // Refresh sirf logged-in user ke liye
    if (response.status === 401 && token) {

        token =
            await refreshAccessToken();

        if (!token) {

            localStorage.removeItem("accessToken");
            localStorage.removeItem("refreshToken");
            localStorage.removeItem("loggedInUser");
            localStorage.removeItem("userId");
            localStorage.removeItem("role");

            return response;
        }

        options.headers["Authorization"] =
            "Bearer " + token;

        response =
            await fetch(url, options);
    }

    return response;
}


