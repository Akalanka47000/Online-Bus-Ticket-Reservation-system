<%@ page import="breezingbolt.http.services.AuthService" %>
<%@ page import="breezingbolt.http.principals.UserPrincipal" %>
<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html>
  <head>
      <script src="https://cdn.tailwindcss.com"></script>
      <script src="/js/tailwind.config.js"></script>
  </head>
  <body>
  <% Optional<UserPrincipal> currentUser = AuthService.getCurrentUser();%>
    <header class="bg-gradient-to-r from-blue-600 to-blue-500 text-white">
        <div class="container mx-auto px-6 py-3">
            <div class="flex items-center justify-between">
                <div class="hidden w-full md:flex md:items-center">
                    <svg class="h-5 w-5" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M16.2721 10.2721C16.2721 12.4813 14.4813 14.2721 12.2721 14.2721C10.063 14.2721 8.27214 12.4813 8.27214 10.2721C8.27214 8.06298 10.063 6.27212 12.2721 6.27212C14.4813 6.27212 16.2721 8.06298 16.2721 10.2721ZM14.2721 10.2721C14.2721 11.3767 13.3767 12.2721 12.2721 12.2721C11.1676 12.2721 10.2721 11.3767 10.2721 10.2721C10.2721 9.16755 11.1676 8.27212 12.2721 8.27212C13.3767 8.27212 14.2721 9.16755 14.2721 10.2721Z" fill="currentColor" /><path fill-rule="evenodd" clip-rule="evenodd" d="M5.79417 16.5183C2.19424 13.0909 2.05438 7.39409 5.48178 3.79417C8.90918 0.194243 14.6059 0.054383 18.2059 3.48178C21.8058 6.90918 21.9457 12.6059 18.5183 16.2059L12.3124 22.7241L5.79417 16.5183ZM17.0698 14.8268L12.243 19.8965L7.17324 15.0698C4.3733 12.404 4.26452 7.97318 6.93028 5.17324C9.59603 2.3733 14.0268 2.26452 16.8268 4.93028C19.6267 7.59603 19.7355 12.0268 17.0698 14.8268Z" fill="currentColor" />
                    </svg>
                    <span class="mx-1 text-sm">LK</span>
                </div>
                <div class="w-full md:text-center text-2xl font-semibold">
                    Breezing Bolt
                </div>
                <div class="flex items-center justify-end w-full">
                    <button class="focus:outline-none">
                        <svg class="h-6 w-6 text-white"  xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                            <path d="M256 0C114.6 0 0 114.6 0 256s114.6 256 256 256s256-114.6 256-256S397.4 0 256 0zM256 128c39.77 0 72 32.24 72 72S295.8 272 256 272c-39.76 0-72-32.24-72-72S216.2 128 256 128zM256 448c-52.93 0-100.9-21.53-135.7-56.29C136.5 349.9 176.5 320 224 320h64c47.54 0 87.54 29.88 103.7 71.71C356.9 426.5 308.9 448 256 448z"/>
                        </svg>
                    </button>
                </div>
            </div>
            <nav :class="isOpen ? '' : 'hidden'" class="sm:flex sm:justify-center sm:items-center mt-4">
                <div class="flex flex-col sm:flex-row">
                    <a class="mt-3 text-gray-100 hover:underline hover:text-white sm:mx-3 sm:mt-0 transition ease duration-300" href="/">Home</a>
                    <a class="mt-3 text-gray-100 hover:underline hover:text-white sm:mx-3 sm:mt-0 transition ease duration-300" href="/schedule">Schedule</a>
                    <a class="mt-3 text-gray-100 hover:underline hover:text-white sm:mx-3 sm:mt-0 transition ease duration-300" href="/booking">Booking</a>
                    <a class="mt-3 text-gray-100 hover:underline hover:text-white sm:mx-3 sm:mt-0 transition ease duration-300" href="/support">Support</a>
                    <% if (!currentUser.isPresent()) { %>
                        <a class="mt-3 text-gray-100 hover:underline hover:text-white sm:mx-3 sm:mt-0 transition ease duration-300" href="/login">Login</a>
                        <a class="mt-3 text-gray-100 hover:underline hover:text-white sm:mx-3 sm:mt-0 transition ease duration-300" href="/signup">Signup</a>
                    <% } else {
                        if (currentUser.get().getRole().getId() == 1) { %>
                            <a class="mt-3 text-gray-100 hover:underline hover:text-white sm:mx-3 sm:mt-0 transition ease duration-300" href="/management">Management</a>
                        <% } %>
                        <form id="logout_form" action="/logout" method="POST">
                            <a class="mt-3 text-gray-100 hover:underline hover:text-white sm:mx-3 sm:mt-0 transition ease duration-300 cursor-pointer" onclick="document.getElementById('logout_form').submit()">Logout</a>
                        </form>
                    <% } %>
                </div>
            </nav>
            <div class="relative mt-6 max-w-lg mx-auto">
            </div>
        </div>
    </header>
  </body>
</html>
