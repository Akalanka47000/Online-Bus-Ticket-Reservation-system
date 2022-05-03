let modal;

const onAddClick = (e) => {
  sessionStorage.removeItem("tempAdminRecordData");
  if (!modal) initModal();
  initModalTitles();
  modal.show();
};

const onEditClick = (e) => {
  const id = e.target.id.split("-")[1];
  const name = document.getElementById(`name-${id}`).innerHTML;
  const username = document.getElementById(`username-${id}`).innerHTML;
  const email = document.getElementById(`email-${id}`).innerHTML;
  const tempAdminData = {
    id: id,
    name: name,
    username: username,
    email: email,
  };
  sessionStorage.setItem("tempAdminRecordData", JSON.stringify(tempAdminData));
  if (!modal) initModal();
  initModalTitles();
  modal.show();
};

const initModal = () => {
  const modalEl = document.getElementById("manage_user_modal");
  modal = new Modal(modalEl, {});
};

const initModalTitles = () => {
  const dataToEdit =
    JSON.parse(sessionStorage.getItem("tempAdminRecordData")) || null;
  document.getElementById("manage_user_modal_title").innerHTML = dataToEdit
    ? "Edit Admin"
    : "Add Admin";
  document.getElementById("manage_user_modal_submit_button_text").innerHTML =
    dataToEdit ? "Update" : "Add";
  if (dataToEdit) {
    document.getElementById("username").value = dataToEdit.username;
    document.getElementById("first_name").value = dataToEdit.name.split(" ")[0];
    document.getElementById("last_name").value = dataToEdit.name.split(" ")[1];
    document.getElementById("email").value = dataToEdit.email;
    document.getElementById("password").required = false;
  } else {
    document.getElementById("username").value = "";
    document.getElementById("first_name").value = "";
    document.getElementById("last_name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").required = true;
  }
};

const onClickCancel = (e) => {
  modal.hide();
};

const handleSubmit = (e) => {
  e.preventDefault();
  const payload = JSON.stringify({
    id: sessionStorage.getItem("tempAdminRecordData")
      ? JSON.parse(sessionStorage.getItem("tempAdminRecordData")).id
      : null,
    username: e.target.username.value,
    first_name: e.target.first_name.value,
    last_name: e.target.last_name.value,
    email: e.target.email.value,
    password: e.target.password.value,
    role: {
      id: 1,
    },
  });
  const dataToEdit =
    JSON.parse(sessionStorage.getItem("tempAdminRecordData")) || null;
  if (dataToEdit) {
    sendRequest(
      "/admin/user/update",
      "POST",
      payload,
      "/management",
      [
        () => (modal = null),
        () => sessionStorage.removeItem("tempAdminRecordData"),
      ],
      "Admin updated successfully"
    );
  } else {
    sendRequest(
      "/admin/user/add",
      "POST",
      payload,
      "/management",
      [() => (modal = null)],
      "Admin added successfully"
    );
  }
};

onClickDelete = (e) => {
  const id = e.target.id.split("-")[1];
  sendRequest(
    `/admin/user/delete/${id}`,
    "POST",
    {},
    "/management",
    [() => (modal = null)],
    "Admin deleted successfully"
  );
};

const initialize = () => {
  AOS.init({ offset: 0, duration: 1000 });
  initModal();
};

if (document.readyState !== "loading") {
  initialize();
} else {
  document.addEventListener("DOMContentLoaded", function () {
    initialize();
  });
}
