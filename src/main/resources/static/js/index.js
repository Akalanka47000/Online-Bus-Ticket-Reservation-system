const handleSubmit = (e) => {
  e.preventDefault();
  const payload = JSON.stringify({
    origin_city_id: e.target.origin_city.value,
    destination_city_id: e.target.destination_city.value,
  });
  sendRequest("/schedule/availability", "POST", payload, "/", [
    () =>
      Swal.fire({
        icon: document.getElementById("routeAvailable").innerText == "true"
        ? "success" : "error",
        heightAuto: false,
        background: "#f5fdff",
        title: `<div style="font-size:23px">${
          document.getElementById("routeAvailable").innerText == "true"
            ? "Route available"
            : "Route not available"
        }</div>`,
        showConfirmButton: false,
        timer: 2000,
      }),
  ]);
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
