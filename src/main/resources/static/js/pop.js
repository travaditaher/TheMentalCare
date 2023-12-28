let confirmbtn = document.getElementById("confirmbtn");
confirmbtn.addEventListener("click", function() {
    let email = document.getElementById("to-email-input").value;
    let subject = document.getElementById("subject-input").value;
    let msg = document.getElementById("message-input").value;

    if (email == "" || subject == "" || msg == "") {
        swal.fire({
            title: "Field(s) empty.",
            text: "Check if you missed something.",
            icon: "warning",
            button: "Ok"
        });
    } else {
        Swal.fire({
            title: "Sent!",
            text: "The therapist will contact you ASAP.",
            icon: "success"
        })
    }
})