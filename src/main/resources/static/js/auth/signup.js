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
    if (xmlhttp.readyState == 4) {
      if (xmlhttp.status == 200) window.location.href = "/";
      window.document.body.parentNode.innerHTML = xmlhttp.response;
    }
  };
};

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
