let modal;

const onAddClick = (e) => {
    sessionStorage.removeItem("tempSupportRecordData");
    if (!modal) initModal();
    initModalTitles();
    modal.show();
};

const onEditClick = (e) => {
    const id = e.target.id.split("-")[1];
    const title = document
        .getElementById(`title-${id}`)
        .innerText
        .trim();
    const body = document
        .getElementById(`body-${id}`)
        .innerText
        .trim();

    const tempScheduleData = {
        id: id,
        title,
        body,
    };
    sessionStorage.setItem(
        "tempSupportRecordData",
        JSON.stringify(tempScheduleData)
    );
    if (!modal) initModal();
    initModalTitles();
    modal.show();
};

const initModal = () => {
    const modalEl = document.getElementById("manage_support_modal");
    modal = new Modal(modalEl, {});
};

const initModalTitles = () => {
    const dataToEdit =
        JSON.parse(sessionStorage.getItem("tempSupportRecordData")) || null;
    document.getElementById("manage_support_modal_title").innerHTML = dataToEdit
        ? "Edit Support Ticket"
        : "Add Support Ticket";
    document.getElementById(
        "manage_support_modal_submit_button_text"
    ).innerHTML = dataToEdit ? "Update" : "Add";
    if (dataToEdit) {
        document.getElementById("title").value = dataToEdit.title;
        document.getElementById("body").value = dataToEdit.body;
    } else {
        document.getElementById("title").value = "";
        document.getElementById("body").value = "";
    }
};

const onClickCancel = (e) => {
    modal.hide();
};

const handleSubmit = (e) => {
    e.preventDefault();
    const payload = JSON.stringify({
        id: sessionStorage.getItem("tempSupportRecordData")
            ? JSON.parse(sessionStorage.getItem("tempSupportRecordData")).id
            : null,
        title: e.target.title.value,
        body: e.target.body.value,
    });
    const dataToEdit =
        JSON.parse(sessionStorage.getItem("tempSupportRecordData")) || null;
    if (dataToEdit) {
        sendRequest(
            "/support/update",
            "POST",
            payload,
            "/support",
            [
                () => window.location.reload(),
                () => sessionStorage.removeItem("tempSupportRecordData"),
            ],
            "Support ticket updated successfully"
        );
    } else {
        sendRequest(
            "/support/add",
            "POST",
            payload,
            "/support",
            [() => window.location.reload()],
            "Support Ticket added successfully"
        );
    }
};

onClickDelete = (e) => {
    const id = e.target.id.split("-")[1];
    sendRequest(
        `/support/delete/${id}`,
        "POST",
        {},
        "/support",
        [() => (modal = null)],
        "Support ticket deleted successfully"
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
