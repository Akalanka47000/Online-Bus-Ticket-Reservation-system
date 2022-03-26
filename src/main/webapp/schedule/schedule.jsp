<%@ page import="breezingbolt.entities.City" %>
    <%@ page import="java.util.List" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Schedules | Breezing Bolt</title>
            <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.0.2/aos.js"></script>
            <script src="https://cdn.tailwindcss.com"></script>
            <script th:src="@{/js/tailwind.config.js}"></script>
            <script src="./js/schedule.js"></script>

        </head>

        <body>
            <div class="bg-gradient-to-b from-gray-800 to-black"
                style="display:flex;flex-direction:column;align-items: center; justify-content: center; height:100vh">
                <div class="flex justify-center">
                    <div class="mb-3 xl:w-96">
                        <h1 class="text-2xl text-white">
                            Find a Bus
                        </h1>
                    </div>
                </div>
                <div class="flex justify-center">
                    <div class="mb-3 xl:w-96">

                        <select class="form-select appearance-none block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding bg-no-repeat border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" aria-label="From" name="val" onchange="onOriginCitySelect(event)">>
                            <option selected disabled hidden>From</option>
                            <% for(City city : (List<City>) request.getAttribute("cityList")) { %>
                                <option id="origin-<%= city.getId() %>" value="<%= city.getId() %>">
                                    <%= city.getName()%>
                                </option>
                                <% } %>
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
      focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" aria-label="To" onchange="onDestinationCitySelect(event)">
                            <option selected disabled hidden>To</option>
                            <% for(City city : (List<City>) request.getAttribute("cityList")) { %>
                                <option id="destination-<%= city.getId() %>" value="<%= city.getId() %>">
                                    <%= city.getName()%>
                                </option>
                                <% } %>
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
      border border-solid border-gray-300 rounded transition
      ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                            aria-label="Default select example">

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