<!DOCTYPE html>
<html>

<head>
    <title>Profile | Breezing Bolt</title>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.0.2/aos.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <script th:src="@{/js/tailwind.config.js}"></script>
    <script src="./js/mixins/http.js"></script>
    <script src="./js/mixins/responseHandler.js"></script>
    <script src="./js/profile.js"></script>
    <script src="https://unpkg.com/flowbite@1.4.2/dist/flowbite.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/flowbite@1.4.2/dist/flowbite.min.css" />
</head>

<body>
    <%@include file="../common/navbar.jsp" %>
        <div class="min-h-85vh bg-gradient-to-b from-gray-800 to-black flex justify-center items-center">
            <div id="errors" class="fixed top-0 opacity-0">${errors}</div>
            <div id="successMessage" class="fixed top-0 opacity-0">${message}</div>

            <div class="block md:flex">

                <div class="w-full md:w-2/5 p-4 sm:p-6 lg:p-8 bg-gray-800 shadow-md">
                    <div class="flex justify-between">
                        <span class="text-xl font-semibold block text-white">Profile</span>
                    </div>
                    <div class="w-full p-8 flex justify-center mt-24">
                        <svg class="h-3/4 w-3/4 invert transition duration-300" xmlns="http://www.w3.org/2000/svg"
                            viewBox="0 0 512 512">
                            <path
                                d="M256 0C114.6 0 0 114.6 0 256s114.6 256 256 256s256-114.6 256-256S397.4 0 256 0zM256 128c39.77 0 72 32.24 72 72S295.8 272 256 272c-39.76 0-72-32.24-72-72S216.2 128 256 128zM256 448c-52.93 0-100.9-21.53-135.7-56.29C136.5 349.9 176.5 320 224 320h64c47.54 0 87.54 29.88 103.7 71.71C356.9 426.5 308.9 448 256 448z" />
                        </svg>
                    </div>
                </div>

                <div class="w-full md:w-3/5 p-8 bg-gray-800 lg:ml-4 shadow-md">
                    <form class="rounded shadow p-6" onsubmit="handleSubmit(event)">
                        <div class="pb-4">
                            <div class="flex">
                                <div class="w-1/2 mr-1">
                                    <label for="username" class="font-semibold text-white block pb-1">Username</label>
                                    <input id="username" class="border-1  rounded-r px-4 py-2 w-full" type="text"
                                        value="<%= currentUser.get().getUsername()%>" />
                                </div>
                                <div class="w-1/2 ml-1">
                                    <label for="email" class="font-semibold text-white block pb-1">Email</label>
                                    <input id="email" class="border-1  rounded-r px-4 py-2 w-full" type="email"
                                        value="<%= currentUser.get().getEmail()%>" />
                                </div>
                            </div>
                        </div>
                        <div class="pb-4">
                            <div class="flex">
                                <div class="w-1/2 mr-1">
                                    <label for="first_name" class="font-semibold text-white block pb-1">First
                                        Name</label>
                                    <input id="first_name" class="border-1  rounded-r px-4 py-2 w-full" type="text"
                                        value="<%= currentUser.get().getFirstName()%>" />
                                </div>
                                <div class="w-1/2 ml-1">
                                    <label for="last_name" class="font-semibold text-white block pb-1">Last Name</label>
                                    <input id="last_name" class="border-1  rounded-r px-4 py-2 w-full" type="text"
                                        value="<%= currentUser.get().getLastName()%>" />
                                </div>
                            </div>
                        </div>
                        <div class="pb-4">
                            <label for="password" class="font-semibold text-white block pb-1">Password</label>
                            <input id="password" class="border-1  rounded-r px-4 py-2 w-full" type="password"
                                value="<%= currentUser.get().getPassword()%>" />
                            <span class="text-white pt-4 block opacity-70">Personal login information of your
                                account</span>
                        </div>
                        <button
                            class="w-full py-4 bg-blue-600 hover:bg-blue-700 mb-6 rounded text-sm font-bold text-gray-50 transition duration-200"
                            type="submit">
                            Update
                        </button>
                        <div
                            class="w-full py-4 bg-red-600 hover:bg-red-700 rounded text-sm text-center font-bold text-gray-50 transition duration-200"
                            onclick="onClickDeleteAcount()">
                            Delete Account
                    </div>
                    </form>
                </div>


            </div>
        </div>
        <%@include file="../common/footer.jsp" %>
</body>

</html>