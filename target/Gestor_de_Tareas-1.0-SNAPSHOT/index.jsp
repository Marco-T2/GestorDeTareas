<%@page import="com.emergentes.modelo.Tarea"%>
<%@page import="java.util.ArrayList"%>
<% 
    if(session.getAttribute("listatarea")==null){
        ArrayList<Tarea>lisaux=new ArrayList<Tarea>();
        session.setAttribute("listatarea", lisaux);
    }
    ArrayList<Tarea>lista=(ArrayList<Tarea>)session.getAttribute("listatarea");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>

       <h1>GESTOR DE TAREAS</h1>
       <h2>Nombre: Marco Antonio Tarqui Alanoca</h2>
        <a href="MainServlet?op=nuevo">Nuevo</a>
        <br> <br>
        <table border="5">
            <tr>
                <th>Id</th>
                <th>Tarea</th>
                <th>Completado</th>
                <th colspan="2">Opciones</th>
                
            </tr>
            <%
                if(lista !=null){
                    for(Tarea item: lista){ 
            %>
            <tr>
                <td><%=item.getId()%></td>
                <td><%=item.getTarea()%></td>
                <td> 
                    <input name="cbilibros" type="checkbox"<%if(item.getCompletado().equals("si")){%> checked="checked" <%}%>" onclick="return false;"/>
                </td>
                <td>
                    <a href="MainServlet?op=editar&id=<%=item.getId()%>">Editar</a>
                </td>
                <td>
                    <a href="MainServlet?op=eliminar&id=<%=item.getId()%>" onclick="return(confirm('Esta seguro de eliminar??'))">Eliminar</a>
                </td>
            </tr>
            <%  
                    }
                }
            %>
        </table>
        
        </center>
    </body>
</html>
