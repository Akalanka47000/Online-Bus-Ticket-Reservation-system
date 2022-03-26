sendRequest = function(url, method, data, afterFunctions = []) {
  const xmlhttp = new XMLHttpRequest();
  xmlhttp.open(method, url, true);
  xmlhttp.setRequestHeader("Content-Type", "application/json");
  xmlhttp.send(data);
  xmlhttp.onreadystatechange = function () {
    handleResponse(xmlhttp, afterFunctions);
  };
}