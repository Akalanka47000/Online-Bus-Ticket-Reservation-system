<%@ page import="java.util.List" %>
<%@ page import="breezingbolt.entities.Booking" %>
<!DOCTYPE html>
                <html>

                <head>
                    <title>My Booking | Breezing Bolt</title>
                    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.0.2/aos.js"></script>
                    <script src="https://cdn.tailwindcss.com"></script>
                    <script th:src="@{/js/tailwind.config.js}"></script>
                    <script src="./js/booking.js"></script>
                    <script src="./js/mixins/http.js"></script>
                    <script src="./js/mixins/responseHandler.js"></script>
                    <script src="https://unpkg.com/flowbite@1.4.2/dist/flowbite.js"></script>
                    <link rel="stylesheet" href="https://unpkg.com/flowbite@1.4.2/dist/flowbite.min.css" />
                </head>

                <body>
                    <%@include file="../common/navbar.jsp" %>
                            <div class="min-h-85vh bg-gradient-to-b from-gray-800 to-black">
                                <div id="errors" class="fixed top-0 opacity-0">${errors}</div>
                                <div id="successMessage" class="fixed top-0 opacity-0">${message}</div>
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
                                                        Actions</th>

                                                </tr>
                                            </thead>
                                            <tbody class="block md:table-row-group">
                                                <% for(Booking booking : (List<Booking>)
                                                    request.getAttribute("booking")) { %>
                                                    <tr
                                                        class="bg-gray-800 border border-grey-700 shadow-lg shadow-gray-800/50 md:border-none block md:table-row">
                                                        <td
                                                            class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell">
                                                            <span
                                                                class="inline-block w-1/3 md:hidden font-bold">Time</span><span
                                                                id="time-<%= booking.getId()%>">
                                                                <%= booking.getBookedSchedule().getTime()%>
                                                            </span>
                                                        </td>
                                                        <td
                                                            class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell">
                                                            <span
                                                                class="inline-block w-1/3 md:hidden font-bold">Origin</span><span
                                                                id="origin-item-<%= booking.getId()%>">
                                                                <%= booking.getBookedSchedule().getOrigin_city() == null ? null: booking.getBookedSchedule().getOrigin_city().getName()%>
                                                            </span>
                                                        </td>
                                                        <td
                                                            class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell">
                                                            <span
                                                                class="inline-block w-1/3 md:hidden font-bold">Destination
                                                            </span><span id="destination-item-<%= booking.getId()%>">
                                                                <%= booking.getBookedSchedule().getOrigin_city() == null ? null: booking.getBookedSchedule().getDestination_city().getName()%>
                                                            </span>
                                                        </td>
    
                                                        <td
                                                                class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell">
                                                                <button id="delete-<%= booking.getId()%>"
                                                                    class="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 border border-red-500 rounded transition ease-in-out duration-150"
                                                                    onclick="onClickCancel(event)">Cancel</button>
                                                            </td>
                                                    </tr>
                                                    <% } %>
                                            </tbody>
                                        </table>
                            </div>
                            <%@include file="../common/footer.jsp" %>
                </body>

                </html>