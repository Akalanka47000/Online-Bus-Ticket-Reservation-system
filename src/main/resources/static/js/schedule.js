let selectedOriginCity
let selectedDestinationCity

const onOriginCitySelect = (e) => {
    if(selectedOriginCity) document.getElementById(`destination-${selectedOriginCity}`).style.display = 'flex'
    selectedOriginCity = e.target.value
    document.getElementById(`destination-${e.target.value}`).style.display = 'none'
}

const onDestinationCitySelect = (e) => {
    if(selectedDestinationCity) document.getElementById(`origin-${selectedDestinationCity}`).style.display = 'flex'
    selectedDestinationCity = e.target.value
    document.getElementById(`origin-${e.target.value}`).style.display = 'none'
}

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
