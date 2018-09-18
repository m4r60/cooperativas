<%@include file="pageAttributes.jsp" %>
<%@include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ES">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css"/>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>

<ul class="main-navigation">
    <li><a href="#">Home</a></li>
    <li><a href="#">Clientes</a></li>
    <li><a href="#">Agricultores</a></li>
    <li><a href="#">Recogidas</a>
        <ul>
            <li><a href="#">Facturas</a></li>
            <li><a href="#">Albaranes</a></li>
        </ul>
    </li>
    <li><a href="#">Ventas</a>
        <ul>
            <li><a href="#">Facturas</a></li>
            <li><a href="#">Albaranes</a></li>
        </ul>
    </li>
    <li><a href="#">Variedades</a></li>
    <li><a href="#">Contacto</a></li>
</ul>
<%@include file="BootStrapJS.jsp" %>
</body>
</html>