let selectedOriginCity;
let selectedDestinationCity;

let modal;

const onOriginCitySelect = (e) => {
  if (selectedOriginCity)
    document.getElementById(`destination-${selectedOriginCity}`).style.display =
      "flex";
  selectedOriginCity = e.target.value;
  document.getElementById(`destination-${e.target.value}`).style.display =
    "none";
};

const onDestinationCitySelect = (e) => {
  if (selectedDestinationCity)
    document.getElementById(`origin-${selectedDestinationCity}`).style.display =
      "flex";
  selectedDestinationCity = e.target.value;
  document.getElementById(`origin-${e.target.value}`).style.display = "none";
};

const onAddClick = (e) => {
  sessionStorage.removeItem("tempScheduleRecordData");
  if (!modal) initModal();
  initModalTitles();
  modal.show();
};

const onEditClick = (e) => {
  const id = e.target.id.split("-")[1];
  const origin_city_id = document
    .getElementById(`origin-item-${id}`)
    .getAttribute("name")
    .replaceAll("origin-id-", "");
  const destination_city_id = document
    .getElementById(`destination-item-${id}`)
    .getAttribute("name")
    .replaceAll("destination-id-", "");
  const bus_capacity = document
    .getElementById(`bus_capacity-${id}`)
    .innerText.split("/")[1]
    .trim();
  const time = document.getElementById(`time-${id}`).innerText;
  const tempScheduleData = {
    id: id,
    origin_city_id,
    destination_city_id,
    bus_capacity,
    time,
  };
  sessionStorage.setItem(
    "tempScheduleRecordData",
    JSON.stringify(tempScheduleData)
  );
  if (!modal) initModal();
  initModalTitles();
  modal.show();
};

const initModal = () => {
  const modalEl = document.getElementById("manage_schedule_modal");
  modal = new Modal(modalEl, {});
};

const initModalTitles = () => {
  const dataToEdit =
    JSON.parse(sessionStorage.getItem("tempScheduleRecordData")) || null;
  document.getElementById("manage_schedule_modal_title").innerHTML = dataToEdit
    ? "Edit Schedule"
    : "Add Schedule";
  document.getElementById(
    "manage_schedule_modal_submit_button_text"
  ).innerHTML = dataToEdit ? "Update" : "Add";
  if (dataToEdit) {
    console.log(dataToEdit);
    document.getElementById("origin_city_id").value = dataToEdit.origin_city_id;
    document.getElementById("destination_city_id").value =
      dataToEdit.destination_city_id;
    document.getElementById("bus_capacity").value = dataToEdit.bus_capacity;
    document.getElementById("time").value = dataToEdit.time;
  } else {
    document.getElementById("origin_city_id").value = "";
    document.getElementById("destination_city_id").value = "";
    document.getElementById("bus_capacity").value = "";
    document.getElementById("time").value = "";
  }
};

const onClickCancel = (e) => {
  modal.hide();
};

const handleSubmit = (e) => {
  e.preventDefault();
  const payload = JSON.stringify({
    id: sessionStorage.getItem("tempScheduleRecordData")
      ? JSON.parse(sessionStorage.getItem("tempScheduleRecordData")).id
      : null,
    originCity: {
      id: e.target.origin_city_id.value,
    },
    destinationCity: {
      id: e.target.destination_city_id.value,
    },
    bus_capacity: e.target.bus_capacity.value,
    time: e.target.time.value,
  });
  const dataToEdit =
    JSON.parse(sessionStorage.getItem("tempScheduleRecordData")) || null;
  if (dataToEdit) {
    sendRequest(
      "/schedule/update",
      "POST",
      payload,
      "/schedule",
      [
        () => window.location.reload(),
        () => sessionStorage.removeItem("tempScheduleRecordData"),
      ],
      "Schedule updated successfully"
    );
  } else {
    sendRequest(
      "/schedule/add",
      "POST",
      payload,
      "/schedule",
      [() => window.location.reload()],
      "Schedule added successfully"
    );
  }
};

onClickDelete = (e) => {
  const id = e.target.id.split("-")[1];
  sendRequest(
    `/schedule/delete/${id}`,
    "POST",
    {},
    "/schedule",
    [() => (modal = null)],
    "Schedule deleted successfully"
  );
};

onClickBook = (e) => {
  const id = e.target.id.split("-")[1];
  const payload = JSON.stringify({
    bookedSchedule: {
      id,
    },
  });
  sendRequest(
    `/booking/add`,
    "POST",
    payload,
    "/booking",
    [()=>window.location.reload()],
    "Booking successfully"
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
