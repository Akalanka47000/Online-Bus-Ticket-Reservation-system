handleErrors = () => {
  if(window.location.search.includes('error')){
    Swal.fire({
      icon: "warning",
      heightAuto: false,
      background: "#f5fdff",
      title: `<div style="font-size:23px">Invalid Credentials</div>`,
      showConfirmButton: false,
      timer: 1500,
    });
  }
};

const initialize = () => {
  AOS.init({ offset: 0, duration: 1000 });
  handleErrors();
};

if (document.readyState !== "loading") {
  initialize();
} else {
  document.addEventListener("DOMContentLoaded", function () {
    initialize();
  });
}
