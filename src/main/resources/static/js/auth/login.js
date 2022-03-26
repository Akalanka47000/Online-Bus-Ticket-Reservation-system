handleLoginError = () => {
  if(window.location.search.includes('error')){
    Swal.fire({
      icon: "warning",
      heightAuto: false,
      background: "#f5fdff",
      title: `<div style="font-size:23px">Invalid Credentials</div>`,
      showConfirmButton: false,
      timer: 2000,
    });
  }
};

const initialize = () => {
  AOS.init({ offset: 0, duration: 1000 });
  handleLoginError();
};

if (document.readyState !== "loading") {
  initialize();
} else {
  document.addEventListener("DOMContentLoaded", function () {
    initialize();
  });
}
