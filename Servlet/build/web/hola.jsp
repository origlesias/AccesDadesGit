<%-- 
    Document   : index
    Created on : 19-oct-2016, 20:12:32
    Author     : Oriol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String json= (String) request.getAttribute("json"); %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
        function lista(){
        var text= '<%=json%>';
        obj = JSON.parse(text);
        for(var i=0; i<obj.length;i++){
        document.getElementById("text").innerHTML+="- " + obj[i].nom + "<br>&nbsp;&nbsp;&nbsp;Tutorias:<br>";
            for(var j=0;j<obj[i].tutorias.length;j++){
               document.getElementById("text").innerHTML+="&nbsp;&nbsp;&nbsp;&nbsp;- " + obj[i].tutorias[j] + "<br>";
            }
        document.getElementById("text").innerHTML+="&nbsp;&nbsp;&nbsp;Asignaturas:<br>";
            for(j=0;j<obj[i].asignaturas.length;j++){
               document.getElementById("text").innerHTML+="&nbsp;&nbsp;&nbsp;&nbsp;- " + obj[i].asignaturas[j] + "<br>";
            }
        }
        }   
        </script>
        <title>JSP Page</title>
    </head>
    <body onLoad="lista();">
        <form>
            <input type="text" name="firstname" id="nom">
            <input type="button" name="search" value="Buscar" onclick="escribir()">
        </form>
        <p><%=json%></p>
        <p id="text"></p>
    </body>
</html>
