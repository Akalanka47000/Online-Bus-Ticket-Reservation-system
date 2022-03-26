<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>SignUp | Breezing Bolt</title>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.0.2/aos.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="/js/tailwind.config.js"></script>
    <script src="./js/auth/signup.js"></script>
    <script src="./js/mixins/http.js"></script>
    <script src="./js/mixins/responseHandler.js"></script>
</head>

<body>
    <%@include file="../common/navbar.jsp" %>
        <section class="flex justify-center items-center h-83vh bg-gradient-to-b from-gray-800 to-black">
            <div id="errors" class="fixed top-0 opacity-0">${errors}</div>
            <div id="successMessage" class="fixed top-0 opacity-0">${message}</div>
            <div class="max-w-2xl w-full bg-gray-900 rounded p-6 space-y-4">
                <div class="mb-4">
                    <p class="text-gray-400">Sign Up</p>
                    <h2 class="text-xl font-bold text-white">Breezing BOLT</h2>
                </div>
                <form onsubmit="handleSubmit(event)">
                    <div class="flex flex-row">
                        <input
                            class="w-1/2 p-4 text-sm bg-gray-50 focus:outline-none border border-gray-200 rounded text-gray-600 mb-2 mr-1"
                            type="text" placeholder="Username" id="username" name="username" required>
                        <select
                            class="w-1/2 p-4 text-sm bg-gray-50 focus:outline-none border border-gray-200 rounded text-gray-600 mb-2 ml-1"
                            id="user_role" name="user_role" required>
                            <option value="" selected disabled hidden>Select Registration Type</option>
                            <option value="2">Business User</option>
                            <option value="3">General User</option>
                        </select>
                    </div>
                    <div class="flex flex-row">
                        <input
                            class="w-1/2 p-4 text-sm bg-gray-50 focus:outline-none border border-gray-200 rounded text-gray-600 mb-2 mr-1"
                            type="text" placeholder="First Name" id="first_name" name="first_name" required>
                        <input
                            class="w-1/2 p-4 text-sm bg-gray-50 focus:outline-none border border-gray-200 rounded text-gray-600 mb-2 ml-1"
                            type="text" placeholder="Last Name" id="last_name" name="last_name" required>
                    </div>
                    <div class="flex flex-row">
                        <input
                            class="w-1/2 p-4 text-sm bg-gray-50 focus:outline-none border border-gray-200 rounded text-gray-600 mb-2 mr-1"
                            type="email" placeholder="Email" id="email" name="email" required>
                        <input
                            class="w-1/2 p-4 text-sm bg-gray-50 focus:outline-none border border-gray-200 rounded text-gray-600 mb-2 ml-1"
                            type="password" placeholder="Password" id="password" name="password" required>
                    </div>
                    <div>
                        <button
                            class="w-full py-4 bg-blue-600 hover:bg-blue-700 rounded text-sm font-bold text-gray-50 transition duration-200"
                            type="submit">Sign Up</button>
                    </div>
                </form>
                <div class="flex items-center justify-between">
                    <div>
                        <a class="text-sm text-blue-600 hover:underline" href="#">Forgot password?</a>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="../common/footer.jsp" %>
</body>

</html>