handleResponse = (
  xmlhttp,
  redirectUrl,
  afterFunctions = [],
  successMessage
) => {
  if (xmlhttp.readyState === 4) {
    if (
      xmlhttp.responseText.includes(
        "org.springframework.web.bind.MethodArgumentNotValidException"
      )
    ) {
      const res = JSON.parse(xmlhttp.responseText);
      if (res.errors.length > 0)
        document.getElementById("errors").innerText =
          res.errors[0].defaultMessage;
    } else {
      if (xmlhttp.status === 200) window.history.pushState("", "", redirectUrl);
      const headHTML = document.getElementsByTagName("head")[0].innerHTML;
      window.document.body.parentNode.innerHTML = xmlhttp.response;
      document.getElementsByTagName("head")[0].innerHTML = headHTML;
      if (xmlhttp.status === 200)
        document.getElementById("successMessage").innerText = successMessage;
      afterFunctions.forEach((func) => {
        try {
          func();
        } catch (e) {
          console.log(e);
        }
      });
    }
    handleErrors();
    handleSuccess();
  }
};

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

handleSuccess = () => {
  if (document.getElementById("successMessage").innerText !== "") {
    Swal.fire({
      icon: "success",
      heightAuto: false,
      background: "#f5fdff",
      title: `<div style="font-size:23px">${
        document.getElementById("successMessage").innerText
      }</div>`,
      showConfirmButton: false,
      timer: 2000,
    });
  }
};
