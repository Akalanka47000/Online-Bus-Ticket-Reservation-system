const handleSubmit = (e) => {
  e.preventDefault();
  const payload = JSON.stringify({
    username: e.target.username.value,
    first_name: e.target.first_name.value,
    last_name: e.target.last_name.value,
    email: e.target.email.value,
    password: e.target.password.value,
    role: {
      id: e.target.user_role.value,
    },
  });
  sendRequest("/signup", "POST", payload, [restoreForm.bind(this, JSON.parse(payload))]);
};

restoreForm = (data) => {
  document.getElementById("username").value = data.username;
  document.getElementById("email").value = data.email;
  document.getElementById("first_name").value = data.first_name;
  document.getElementById("last_name").value = data.last_name;
  document.getElementById("password").value = data.password;
  document.getElementById("user_role").value = data.role.id;
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
