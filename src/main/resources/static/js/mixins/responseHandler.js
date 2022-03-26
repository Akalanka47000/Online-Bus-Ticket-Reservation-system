handleResponse = (xmlhttp, afterFunctions = []) => {
  if (xmlhttp.readyState === 4) {
    if (xmlhttp.responseText.includes("org.springframework.web.bind.MethodArgumentNotValidException")) {
      const res = JSON.parse(xmlhttp.responseText);
      if (res.errors.length > 0) document.getElementById("errors").innerText = res.errors[0].defaultMessage;
    } else {
      if (xmlhttp.status === 200) window.location.href = "/";
      const headHTML = document.getElementsByTagName("head")[0].innerHTML;
      window.document.body.parentNode.innerHTML = xmlhttp.response;
      document.getElementsByTagName("head")[0].innerHTML = headHTML;
      afterFunctions.forEach((func) => func());
    }
    handleErrors();
  }
};
