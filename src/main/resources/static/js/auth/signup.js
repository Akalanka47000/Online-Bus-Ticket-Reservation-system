const handleSubmit = (e) => {
  e.preventDefault();
  const data = JSON.stringify({
    username: e.target.username.value,
    first_name: e.target.first_name.value,
    last_name: e.target.last_name.value,
    email: e.target.email.value,
    password: e.target.password.value,
    role: {
      id: e.target.user_role.value,
    },
  });
  const xmlhttp = new XMLHttpRequest();
  xmlhttp.open("POST", "/signup", true);
  xmlhttp.setRequestHeader("Content-Type", "application/json");
  xmlhttp.send(data);
  xmlhttp.onreadystatechange = function () {
    if (xmlhttp.readyState === 4) {
      if (xmlhttp.responseText.includes('org.springframework.web.bind.MethodArgumentNotValidException')) {
         const res = JSON.parse(xmlhttp.responseText);
         if(res.errors.length > 0) document.getElementById("errors").innerText = res.errors[0].defaultMessage
      } else{
        if (xmlhttp.status === 200) window.location.href = "/";
        const headHTML = document.getElementsByTagName("head")[0].innerHTML;
        window.document.body.parentNode.innerHTML = xmlhttp.response;
        document.getElementsByTagName("head")[0].innerHTML = headHTML;
        restoreForm(JSON.parse(data))
      }
      handleErrors();
    }
  };
};

restoreForm = (data) => {
  document.getElementById('username').value = data.username
  document.getElementById('email').value = data.email
  document.getElementById('first_name').value = data.first_name
  document.getElementById('last_name').value = data.last_name
  document.getElementById('password').value = data.password
  document.getElementById('user_role').value = data.role.id
}

const initialize = () => {
  AOS.init({ offset: 0, duration: 1000 });
};

if (document.readyState !== "loading") {
  initialize();
} else {
  document.addEventListener("DOMContentLoaded", function () {
    initialize();
  });
}
