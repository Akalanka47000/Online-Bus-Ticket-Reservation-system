
<!DOCTYPE html>
<html>
<head>
    <title>Route Schedules</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script th:src="@{/js/tailwind.config.js}"></script>

</head>
<body>
<div class="" style="display:flex;flex-direction:column;align-items: center; justify-content: center; height:100vh">
<div class="flex justify-center">
    <div class="mb-3 xl:w-96">
        <h1 class="text-2xl">
            Find a Bus
        </h1>
    </div>
</div>
    <div class="flex justify-center">
        <div class="mb-3 xl:w-96">

            <select class="form-select appearance-none
      block
      w-full
      px-3
      py-1.5
      text-base
      font-normal
      text-gray-700
      bg-white bg-clip-padding bg-no-repeat
      border border-solid border-gray-300
      rounded
      transition
      ease-in-out
      m-0
      focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" aria-label="From" name="val">
                <option selected>From</option>
                <option value="1" > Colombo</option>
                <option value="2" >Kandy</option>
                <option value="3">Matara</option>
                <option value="4">Kataragama</option>
                <option value="5">Mannar</option>
                <option value="6">Kurunegala</option>
                <option value="7">Kalpitiya</option>
                <option value="8">Matale</option>
                <option value="9">Theldeniya</option>
                <option value="10">Dayagama</option>

                <option value="11">Medawachchiya</option>
                <option value="12">Nawalapitiya</option>
                <option value="13">Hatton</option>
                <option value="14">Gampola</option>
                <option value="15">Hakmana</option>
                <option value="16">Mahiyanganaya </option>
                <option value="17">Udugama </option>
                <option value="18">Kalmunai</option>
                <option value="19">Trincomalee</option>

                <option value="20">Anuradhapura</option>
                <option value="21">Deniyaya</option>
                <option value="22">Rakwana</option>
                <option value="23">Nuwaraeliya </option>
                <option value="24">Kuliyapitiya</option>
                <option value="25">Akkaraipattu  </option>
                <option value="26">Badulla  </option>
                <option value="27">Rathnapura </option>
                <option value="28">Trincomalee</option>
            </select>
        </div>
    </div>


<div class="flex justify-center">
    <div class="mb-3 xl:w-96">
        <select class="form-select appearance-none
      block
      w-full
      px-3
      py-1.5
      text-base
      font-normal
      text-gray-700
      bg-white bg-clip-padding bg-no-repeat
      border border-solid border-gray-300
      rounded
      transition
      ease-in-out
      m-0
      focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" aria-label="To">

            <option selected>To</option>
            <option value="2" >Kandy</option>
            <option value="3">Matara</option>
            <option value="4">Kataragama</option>
            <option value="5">Mannar</option>
            <option value="6">Kurunegala</option>
            <option value="7">Kalpitiya</option>
            <option value="8">Matale</option>
            <option value="9">Theldeniya</option>
            <option value="10">Dayagama</option>

            <option value="11">Medawachchiya</option>
            <option value="12">Nawalapitiya</option>
            <option value="13">Hatton</option>
            <option value="14">Gampola</option>
            <option value="15">Hakmana</option>
            <option value="16">Mahiyanganaya </option>
            <option value="17">Udugama </option>
            <option value="18">Kalmunai</option>
            <option value="19">Trincomalee</option>

            <option value="20">Anuradhapura</option>
            <option value="21">Deniyaya</option>
            <option value="22">Rakwana</option>
            <option value="23">Nuwaraeliya </option>
            <option value="24">Kuliyapitiya</option>
            <option value="25">Akkaraipattu  </option>
            <option value="26">Badulla  </option>
            <option value="27">Rathnapura </option>
            <option value="28">Trincomalee</option>
        </select>
    </div>
</div>

<div class="flex justify-center">
    <div class="mb-3 xl:w-96">
        <input type="date" id="journey-date" name="journey-date" class="form-select appearance-none
      block
      w-full
      px-3
      py-1.5
      text-base
      font-normal
      text-gray-700
      bg-white bg-clip-padding bg-no-repeat
      border border-solid border-gray-300
      rounded
      transition
      ease-in-out
      m-0
      focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" aria-label="Default select example">

    </div>
</div>

    <div class="flex justify-center">
        <!-- Using utilities: -->
        <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
            Button
        </button>

    </div>



</div>
</body>
</html>