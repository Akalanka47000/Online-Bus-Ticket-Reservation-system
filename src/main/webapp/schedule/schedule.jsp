<%@ page import="java.util.List" %>
    <%@ page import="breezingbolt.entities.Schedule" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <title>Schedules | Breezing Bolt</title>
                    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.0.2/aos.js"></script>
                    <script src="https://cdn.tailwindcss.com"></script>
                    <script th:src="@{/js/tailwind.config.js}"></script>
                    <script src="./js/schedule.js"></script>
                    <script src="https://unpkg.com/flowbite@1.4.2/dist/flowbite.js"></script>
                    <link rel="stylesheet" href="https://unpkg.com/flowbite@1.4.2/dist/flowbite.min.css" />
                </head>

                <body>
                    <%@include file="../common/navbar.jsp" %>
                        <%@include file="./components/manage_schedule_modal.jsp" %>
                            <div class="min-h-85vh bg-gradient-to-b from-gray-800 to-black">
                                <div id="errors" class="fixed top-0 opacity-0">${errors}</div>
                                <div id="successMessage" class="fixed top-0 opacity-0">${message}</div>
                                <% if (currentUser.isPresent()) { if
                                    (currentUser.get().getRole().getName().equals("SuperAdmin") ||
                                    currentUser.get().getRole().getName().equals("BusinessAdmin")) { %>
                                    <div class="w-full flex flex-row justify-end items-center">
                                        <button
                                            class="flex items-center mt-4 bg-blue-600 px-12 py-3 mr-2 mb-2 text-white text-sm uppercase font-medium rounded cursor-pointer focus:outline-none no-underline shadow-md hover:bg-blue-700 transition ease-in-out duration-150"
                                            type="button" onclick="onAddClick()">
                                            Add New
                                        </button>
                                    </div>
                                    <%} } %>
                                        <table class="min-w-full border-collapse block md:table">
                                            <thead class="block md:table-header-group">
                                                <tr
                                                    class="md:border-none block md:table-row absolute -top-full md:top-auto -left-full md:left-auto  md:relative ">
                                                    <th
                                                        class="bg-gray-900 p-2 text-white font-bold md:border md:border-gray-700 text-left block md:table-cell">
                                                        Starts At</th>
                                                    <th
                                                        class="bg-gray-900 p-2 text-white font-bold md:border md:border-gray-700 text-left block md:table-cell">
                                                        Origin</th>
                                                    <th
                                                        class="bg-gray-900 p-2 text-white font-bold md:border md:border-gray-700 text-left block md:table-cell">
                                                        Destination</th>
                                                    <th
                                                        class="bg-gray-900 p-2 text-white font-bold md:border md:border-gray-700 text-left block md:table-cell">
                                                        Capacity</th>
                                                    <% if (currentUser.isPresent()) { %>
                                                        <th
                                                            class="bg-gray-900 p-2 text-white font-bold md:border md:border-gray-700 text-left block md:table-cell">
                                                            Actions</th>
                                                        <th
                                                            class="bg-gray-900 p-2 text-white font-bold md:border md:border-gray-700 text-left block md:table-cell">
                                                            Booking</th>
                                                        <% } %>

                                                </tr>
                                            </thead>
                                            <tbody class="block md:table-row-group">
                                                <% for(Schedule schedule : (List<Schedule>)
                                                    request.getAttribute("schedule")) { %>
                                                    <tr
                                                        class="bg-gray-800 border border-grey-700 shadow-lg shadow-gray-800/50 md:border-none block md:table-row">
                                                        <td
                                                            class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell">
                                                            <span
                                                                class="inline-block w-1/3 md:hidden font-bold">Time</span><span
                                                                id="time-<%= schedule.getId()%>">
                                                                <%= schedule.getTime()%>
                                                            </span>
                                                        </td>
                                                        <td
                                                            class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell">
                                                            <span
                                                                class="inline-block w-1/3 md:hidden font-bold">Origin</span><span
                                                                id="origin-item-<%= schedule.getId()%>"
                                                                name="origin-id-<%= schedule.getOriginCity().getId()%>">
                                                                <%= schedule.getOriginCity().getName()%>
                                                            </span>
                                                        </td>
                                                        <td
                                                            class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell">
                                                            <span
                                                                class="inline-block w-1/3 md:hidden font-bold">Destination
                                                            </span><span id="destination-item-<%= schedule.getId()%>"
                                                                name="destination-id-<%= schedule.getDestinationCity().getId()%>">
                                                                <%= schedule.getDestinationCity().getName()%>
                                                            </span>
                                                        </td>
                                                        <td
                                                            class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell">
                                                            <span
                                                                class="inline-block w-1/3 md:hidden font-bold">Capacity</span><span
                                                                id="bus_capacity-<%= schedule.getId()%>">
                                                                <%= schedule.getBookings()==null ? 0 :
                                                                    schedule.getBookings().size() + " / " +
                                                                    schedule.getBus_capacity()%>
                                                            </span>
                                                        </td>
                                                        <% if (currentUser.isPresent()) {
                                                            if(currentUser.get().getId()==schedule.getCreated_by().getId())
                                                            { %>
                                                            <td
                                                                class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell">
                                                                <span
                                                                    class="inline-block w-1/3 md:hidden font-bold">Actions</span>
                                                                <button id="edit-<%= schedule.getId()%>"
                                                                    class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 border border-blue-500 rounded transition ease-in-out duration-150"
                                                                    onclick="onEditClick(event)">Edit</button>
                                                                <button id="delete-<%= schedule.getId()%>"
                                                                    class="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 border border-red-500 rounded transition ease-in-out duration-150"
                                                                    onclick="onClickDelete(event)">Delete</button>
                                                            </td>
                                                            <% } %>
                                                                <td
                                                                    class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell">
                                                                    <span
                                                                        class="inline-block w-1/3 md:hidden font-bold">Booking</span>
                                                                    <button id="book-<%= schedule.getId()%>"
                                                                        class="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-2 border border-green-500 rounded transition ease-in-out duration-150"
                                                                        onclick="onClickBook(event)">Book</button>
                                                                </td>
                                                                <% } %>
                                                    </tr>
                                                    <% } %>
                                            </tbody>
                                        </table>
                            </div>
                            <%@include file="../common/footer.jsp" %>
                </body>

                </html>
