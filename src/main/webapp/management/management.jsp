<%@ page import="java.util.List" %>
<%@ page import="breezingbolt.entities.User" %>
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
<%@include file="./components/manage_user_modal.jsp" %>
<div class="min-h-85vh bg-gradient-to-b from-gray-800 to-black">
    <div class="w-full flex flex-row justify-end items-center">
      <button
        class="flex items-center mt-4 bg-blue-600 px-12 py-3 mr-2 mb-2 text-white text-sm uppercase font-medium rounded cursor-pointer focus:outline-none no-underline shadow-md hover:bg-blue-700 transition ease-in-out duration-150"
        type="button"
        data-modal-toggle="defaultModal"
        >
        Add New
    </button>
    </div>
    <table class="min-w-full border-collapse block md:table">
        <thead class="block md:table-header-group">
            <tr class="md:border-none block md:table-row absolute -top-full md:top-auto -left-full md:left-auto  md:relative ">
                <th class="bg-gray-900 p-2 text-white font-bold md:border md:border-gray-700 text-left block md:table-cell">Name</th>
                <th class="bg-gray-900 p-2 text-white font-bold md:border md:border-gray-700 text-left block md:table-cell">User Name</th>
                <th class="bg-gray-900 p-2 text-white font-bold md:border md:border-gray-700 text-left block md:table-cell">Email Address</th>
                <th class="bg-gray-900 p-2 text-white font-bold md:border md:border-gray-700 text-left block md:table-cell">Actions</th>
            </tr>
        </thead>
        <tbody class="block md:table-row-group">
        <% for(User user : (List<User>) request.getAttribute("admins")) { %>
        <tr class="bg-gray-800 border border-grey-700 shadow-lg shadow-gray-800/50 md:border-none block md:table-row">
            <td class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell"><span class="inline-block w-1/3 md:hidden font-bold">Name</span><%= user.getFirst_name() + " " + user.getLast_name()%></td>
            <td class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell"><span class="inline-block w-1/3 md:hidden font-bold">User Name</span><%= user.getUsername()%></td>
            <td class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell"><span class="inline-block w-1/3 md:hidden font-bold">Email Address</span><%= user.getEmail()%></td>
            <td class="p-2 md:border md:border-gray-700 text-left text-white block md:table-cell">
                <span class="inline-block w-1/3 md:hidden font-bold">Actions</span>
                <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 border border-blue-500 rounded transition ease-in-out duration-150">Edit</button>
                <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 border border-red-500 rounded transition ease-in-out duration-150">Delete</button>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<%@include file="../common/footer.jsp" %>
</body>

</html>