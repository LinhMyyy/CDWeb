function vadidateForm() {
    if (checkMail() & checkName() & checkPass() & rewritePass()) {
        return true;
    } else {
        return false;
    }
}

function checkMail() {
    var email = document.getElementById("email").value;
    var result;
    if (email.length == 0) {
        document.getElementById("emailError").innerHTML = "Bạn không thể để trống dữ liệu này!";
        document.getElementById("emailError").style.display = "block";
        return false;
    } else {
        var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        if (email.match(mailformat)) {
            $.ajax({
                method: 'GET',
                url: 'check-mail',
                dataType: 'json',
                cache: 'false',
                data: {
                    email: email
                }
            }).done(function (user) {
                if (user != null && user.email == email) {
                    document.getElementById("emailError").innerHTML = "Email này đã có người đăng ký. Thử lại";
                    document.getElementById("emailError").style.display = "block";
                } else {
                    document.getElementById("emailError").innerHTML = "";
                    document.getElementById("emailError").style.display = "none";
                }
            });


            try {
                var error = document.getElementById("emailError").value;
                return true;
            } catch (e) {
                return false;
            }
        } else {
            document.getElementById("emailError").innerHTML = "Email không hợp lệ!";
            document.getElementById("emailError").style.display = "block";
            return false
        }
    }
}

function checkPass() {
    var pass = document.getElementById("password").value;


    if (pass.length < 8) {
        document.getElementById("passError").innerHTML = "Mật khẩu có ít nhất 8 ký tự. Thử lại";
        document.getElementById("passError").style.display = "block";
        return false;
    } else {
        document.getElementById("passError").innerHTML = "";
        document.getElementById("passError").style.display = "none";
        return true;

    }
}

function rewritePass() {
    var pass = document.getElementById("password").value;
    var rePass = document.getElementById("confirmPassword").value;
    if (rePass.length == 0) {
        document.getElementById("rePassError").innerHTML = "Bạn không thể để trống dữ liệu này!";
        document.getElementById("rePassError").style.display = "block";
        return false;
    } else {
        if (pass != rePass) {
            document.getElementById("rePassError").innerHTML = "Mật khẩu không tương ứng. Thử lại";
            document.getElementById("rePassError").style.display = "block";
            return false;
        } else {
            document.getElementById("rePassError").innerHTML = "";
            document.getElementById("rePassError").style.display = "none";
            return true;
        }
    }
}

function checkName() {
    var name = document.getElementById("fullName").value;
    if (name.length == 0) {
        document.getElementById("nameError").innerHTML = "Bạn không thể để trống dữ liệu này!";
        document.getElementById("nameError").style.display = "block";
        return false;
    } else {
        document.getElementById("nameError").innerHTML = "";
        document.getElementById("nameError").style.display = "none";
        return true;
    }

}
