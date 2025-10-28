<%--
  Created by IntelliJ IDEA.
  User: Teodora Fleaca
  Date: 10/28/2025
  Time: 9:08 AM
  To change this template use File | Settings | File Templates.
--%>
<footer class="bg-dark text-white text-center py-3 mt-auto" style="width:100%;">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">Parking Lot</a>
        <p class="mb-0">
            &copy; <span id="year"></span> Teodora Fleaca - All Rights Reserved
        </p>
    </div>
</footer>

<script>
    document.getElementById("year").textContent = new Date().getFullYear();
</script>


