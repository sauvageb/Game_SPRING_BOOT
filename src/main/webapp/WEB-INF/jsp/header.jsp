<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="site-header">
    <div class="brand__section">
        <img src="https://img.icons8.com/ios/50/FFFFFF/controller.png"/>
    </div>
    <nav>
        <a href="${pageContext.request.contextPath}/games">Home</a>
        <a href="${pageContext.request.contextPath}/games/add">Add</a>

        <c:choose>
            <c:when test="${not empty sessionScope.username}">
                <form method="post" action="${pageContext.request.contextPath}/logout">
                    <input type="submit" value="Logout">
                </form>
            </c:when>

            <c:otherwise>
                <a href="${pageContext.request.contextPath}/login">Sign In</a>
            </c:otherwise>
        </c:choose>
    </nav>
</header>
