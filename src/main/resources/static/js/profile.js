let edit = false;

const onEditClick = () => {
  edit = !edit;
  document.getElementById(`username`).disabled = !edit;
  document.getElementById(`email`).disabled = !edit;
  document.getElementById(`password`).disabled = !edit;
};

const handleSubmit = (e) => {
  e.preventDefault();
  const payload = JSON.stringify({
    username: e.target.username.value,
    email: e.target.email.value,
    password: e.target.password.value,
    first_name: e.target.first_name.value,
    last_name: e.target.last_name.value,
    role: {
      id: 1,
    },
  });
  sendRequest(
    "/auth/user/update",
    "POST",
    payload,
    "/profile",
    [],
    "Profile updated successfully"
  );
};

onClickDeleteAcount = () => {
  sendRequest(
    `/auth/user/delete`,
    "POST",
    {},
    "/login",
    [],
    "Account deleted successfully"
  );
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
