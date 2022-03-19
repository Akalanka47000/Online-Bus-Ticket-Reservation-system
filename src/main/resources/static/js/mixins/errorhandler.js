handleErrors = () => {
  if (document.getElementById("errors").innerText !== "") {
    Swal.fire({
      icon: "warning",
      heightAuto: false,
      background: "#f5fdff",
      title: `<div style="font-size:23px">${
        document.getElementById("errors").innerText
      }</div>`,
      showConfirmButton: false,
      timer: 2000,
    });
  }
};
