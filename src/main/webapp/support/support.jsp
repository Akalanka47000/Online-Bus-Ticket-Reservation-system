<%@ page import="java.util.List" %>
<%@ page import="breezingbolt.entities.Schedule" %>
<!DOCTYPE html>
<html>

<head>
    <title>Support | Breezing Bolt</title>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.0.2/aos.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <script th:src="@{/js/tailwind.config.js}"></script>
    <script src="./js/support.js"></script>
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
        <div class="w-full flex flex-row justify-end items-center">
            <button
                    class="flex items-center mt-4 bg-blue-600 px-12 py-3 mr-2 mb-2 text-white text-sm uppercase font-medium rounded cursor-pointer focus:outline-none no-underline shadow-md hover:bg-blue-700 transition ease-in-out duration-150"
                    type="button" onclick="onAddClick()">
                Add New
            </button>
        </div>
        <tr
                class="md:border-none block md:table-row absolute -top-full md:top-auto -left-full md:left-auto  md:relative ">
            <th
                    class="bg-gray-900 p-2 text-white font-bold md:border md:border-gray-700 text-left block md:table-cell">
                Ticket ID</th>
            <th
                    class="bg-gray-900 p-2 text-white font-bold md:border md:border-gray-700 text-left block md:table-cell">
                Created By</th>
            <th
                    class="bg-gray-900 p-2 text-white font-bold md:border md:border-gray-700 text-left block md:table-cell">
                Description</th>
            <th
                    class="bg-gray-900 p-2 text-white font-bold md:border md:border-gray-700 text-left block md:table-cell">
                Actions</th>

        </tr>
        </thead>
        <tbody class="block md:table-row-group">
        <% for(Support support : (List<Support>)
                request.getAttribute("support")) { %>
        <tr
                class="bg-gray-800 border border-grey-700 shadow-lg shadow-gray-800/50 md:border-none block md:table-row">
                <td
                    class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell">
                    <span
                        class="inline-block w-1/3 md:hidden font-bold">Time</span><span
                        id="ticketId-<%= support.getId()%>">
                        <%= support.getTicketID()%>
                    </span>
            </td>
            <td
                class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell">
                <span
                        class="inline-block w-1/3 md:hidden font-bold">Origin</span><span
                        id="origin-item-<%= support.getId()%>"
                        name="createdBy-<%= support.getCreatedBy().getId()%>">
                        <%= support.getCreatedBy().getName()%>
                </span>
            </td>
            <td
                    class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell">
                    <span
                            class="inline-block w-1/3 md:hidden font-bold">Destination
                    </span>
                    <span id="destination-item-<%= support.getId()%>"
                            name="destination-id-<%= support.getDescription().getId()%>">
                            <%= support.getDescription().getName()%>
                    </span>
            </td>

        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<%@include file="../common/footer.jsp" %>
</body>

</html>
