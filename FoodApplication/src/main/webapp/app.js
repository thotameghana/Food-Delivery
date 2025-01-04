function validate() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var cpassword = document.getElementById("cpassword").value;
    var email = document.getElementById("email").value;
    var address = document.getElementById("address").value;
    
    var isValid = true;
    
    // Reset error messages
    document.getElementById("userNameMessage").innerHTML = "";
    document.getElementById("passwordMessage").innerHTML = "";
    document.getElementById("emailMessage").innerHTML = "";
    document.getElementById("addressMessage").innerHTML = "";
    
    // Username validation
    if (username === "") {
        document.getElementById("userNameMessage").innerHTML = "Username is required.";
        isValid = false;
    }

    // Password validation
    if (password === "") {
        document.getElementById("passwordMessage").innerHTML = "Password is required.";
        isValid = false;
    } else if (password !== cpassword) {
        document.getElementById("passwordMessage").innerHTML = "Passwords do not match.";
        isValid = false;
    }

    // Email validation
    var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (email === "") {
        document.getElementById("emailMessage").innerHTML = "Email is required.";
        isValid = false;
    } else if (!emailRegex.test(email)) {
        document.getElementById("emailMessage").innerHTML = "Invalid email format.";
        isValid = false;
    }

    // Address validation
    if (address === "") {
        document.getElementById("addressMessage").innerHTML = "Address is required.";
        isValid = false;
    }

    return isValid; // Prevent form submission if any validation fails
}
