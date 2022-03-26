<!DOCTYPE html>
<html>
<head>
    <title>Error | Breezing Bolt</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="/js/tailwind.config.js"></script>
</head>
<body>
<section class="px-4 text-center bg-gradient-to-b from-gray-800 to-black" style="height: 100vh !important;">
    <div id="errors" class="fixed top-0 opacity-0">${errors}</div>
    <div class="flex flex-col justify-center items-center h-full">
        <h2 class="mb-2 text-5xl font-heading text-white">500</h2>
        <p class="mb-6 text-gray-500">
            Internal Server Error! We're working on resolving it at the moment.
        </p>
    </div>
</section>
</body>
</html>
