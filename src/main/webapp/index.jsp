<%@ page import="breezingbolt.entities.City" %>
  <%@ page import="java.util.List" %>
    <!DOCTYPE html>
    <html>

    <head>
      <title>Home | Breezing Bolt</title>
      <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.0.2/aos.js"></script>
      <script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
      <script src="https://cdn.tailwindcss.com"></script>
      <script src="/js/tailwind.config.js"></script>
      <script src="./js/index.js"></script>
      <script src="./js/mixins/http.js"></script>
      <script src="./js/mixins/responseHandler.js"></script>
    </head>

    <body class="bg-gradient-to-b from-gray-800 to-black">
      <div id="errors" class="fixed top-0 opacity-0">${errors}</div>
      <div id="successMessage" class="fixed top-0 opacity-0">${message}</div>
      <div id="routeAvailable" class="fixed top-0 opacity-0">${routeAvailable}</div>
      <%@include file="common/navbar.jsp" %>
        <main class="min-h-80vh w-full my-8 flex flex-col justify-center items-center">
          <div class="w-full flex justify-center items-center mb-10">
            <form class="pb-8 w-10/12 md:w-3/4 xl:w-1/2 flex flex-col md:flex-row justify-center items-center"
              onsubmit="handleSubmit(event)">
              <select
                class="form-select appearance-none block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding md:mr-1 mb-2 md:mb-0 bg-no-repeat border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                id="origin_city" name="origin_city_id" required>
                <option value="" selected disabled hidden>Select Origin City</option>
                <% for(City city : (List<City>) request.getAttribute("cityList")) { %>
                  <option id="origin-<%= city.getId() %>" value="<%= city.getId() %>">
                    <%= city.getName()%>
                  </option>
                  <% } %>
              </select>
              <select
                class="form-select appearance-none block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding md:ml-1 md:mr-1 mb-2 md:mb-0 bg-no-repeat border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                id="destination_city" name="destination_city_id" required>
                <option value="" selected disabled hidden>Select Destination City</option>
                <% for(City city : (List<City>) request.getAttribute("cityList")) { %>
                  <option id="destination-<%= city.getId() %>" value="<%= city.getId() %>">
                    <%= city.getName()%>
                  </option>
                  <% } %>
              </select>
              <button
                class="w-full px-3 py-2 bg-blue-600 text-white text-center text-sm uppercase md:ml-1 font-medium rounded hover:bg-blue-500 focus:outline-none focus:bg-blue-500"
                type="submit">
                Check Availability
              </button>
            </form>
          </div>
          <div class="container mx-auto px-6">
            <div class="h-64 rounded-md overflow-hidden bg-cover bg-center" style="
            background-image: url('./assets/images/home/1.jpg');
          ">
              <div class="bg-gray-900 bg-opacity-50 flex items-center h-full">
                <div class="px-10 max-w-xl">
                  <h2 class="text-2xl text-white font-semibold">Route Finding</h2>
                  <p class="mt-2 text-gray-400">
                    Peek into our schedule and find the next bus for your trip from an ordered list of routes.
                  </p>
                  <a href="/schedule">
                    <button
                      class="flex items-center mt-4 px-3 py-2 bg-blue-600 text-white text-sm uppercase font-medium rounded hover:bg-blue-500 focus:outline-none focus:bg-blue-500">
                      <span>See Complete Schedule</span>
                      <svg class="h-5 w-5 mx-2" fill="none" stroke-linecap="round" stroke-linejoin="round"
                        stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
                        <path d="M17 8l4 4m0 0l-4 4m4-4H3"></path>
                      </svg>
                    </button>
                  </a>
                </div>
              </div>
            </div>
            <div class="md:flex mt-8 md:-mx-4">
              <div class="w-full h-64 md:mx-4 rounded-md overflow-hidden bg-cover bg-center md:w-1/2" style="
              background-image: url('./assets/images/home/3.jpg');
            ">
                <div class="bg-gray-900 bg-opacity-50 flex items-center h-full">
                  <div class="px-10 max-w-xl">
                    <h2 class="text-2xl text-white font-semibold">Dedicated Support</h2>
                    <p class="mt-2 text-gray-400">
                      Our customer support team is specially trained to resolve your queries.
                    </p>
                    <a href="/support">
                      <button
                        class="flex items-center mt-4 text-white text-sm uppercase font-medium rounded hover:underline focus:outline-none">
                        <span>Create A Ticket</span>
                        <svg class="h-5 w-5 mx-2" fill="none" stroke-linecap="round" stroke-linejoin="round"
                          stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
                          <path d="M17 8l4 4m0 0l-4 4m4-4H3"></path>
                        </svg>
                      </button>
                    </a>
                  </div>
                </div>
              </div>
              <div class="w-full h-64 mt-8 md:mx-4 rounded-md overflow-hidden bg-cover bg-center md:mt-0 md:w-1/2"
                style="
              background-image: url('./assets/images/home/2.jpg');
            ">
                <div class="bg-gray-900 bg-opacity-50 flex items-center h-full">
                  <div class="px-10 max-w-xl">
                    <h2 class="text-2xl text-white font-semibold">Company Registration</h2>
                    <p class="mt-2 text-gray-400">
                      Register your company with us, add your services to our schedule and get on the road in no time.
                    </p>
                    <a href="/signup">
                      <button
                        class="flex items-center mt-4 text-white text-sm uppercase font-medium rounded hover:underline focus:outline-none">
                        <span>Register Now</span>
                        <svg class="h-5 w-5 mx-2" fill="none" stroke-linecap="round" stroke-linejoin="round"
                          stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
                          <path d="M17 8l4 4m0 0l-4 4m4-4H3"></path>
                        </svg>
                      </button>
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </main>
        <%@include file="common/footer.jsp" %>
    </body>

    </html>