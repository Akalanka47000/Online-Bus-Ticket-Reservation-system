onClickCancel = (e) => {
  const id = e.target.id.split("-")[1];
  sendRequest(
    `/booking/delete/${id}`,
    "POST",
    {},
    "/booking",
    [],
    "Booking cancelled successfully"
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
