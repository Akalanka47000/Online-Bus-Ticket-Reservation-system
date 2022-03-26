const handleSubmit = (e) => {
  e.preventDefault();
  const payload = JSON.stringify({
    origin: e.target.origin_city.value,
    destination: e.target.destination_city.value,
  });
  sendRequest("/schedule/availability", "POST", payload);
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
