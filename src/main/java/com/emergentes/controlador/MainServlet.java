package com.emergentes.controlador;

import com.emergentes.modelo.Tarea;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String op=request.getParameter("op");
            Tarea objtarea=new Tarea();
            int id,pos;
            HttpSession ses =request.getSession();
            ArrayList<Tarea>lista=(ArrayList<Tarea>)ses.getAttribute("listatarea");
            switch(op){
                case "nuevo":
                    //enviar un objeto vacio
                    request.setAttribute("miobjtarea", objtarea);
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                    
                    break;
                case "editar":
                    // enviar un objeto a editar pero con contenido
                    id=Integer.parseInt(request.getParameter("id"));
                    pos=buscarPorIndice(request,id);
                    //obtenr objeto
                    objtarea=lista.get(pos);
                    request.setAttribute("miobjtarea", objtarea);
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                    break;
                case "eliminar":
                    //eliminar el registro de la coleccion segun el id
                     id=Integer.parseInt(request.getParameter("id"));
                    pos=buscarPorIndice(request,id);
                    if(pos>=0){
                        lista.remove(pos);
                    }
                    request.setAttribute("listatarea", lista);
                    response.sendRedirect("index.jsp");
                    break;
                default:
                
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       int id=Integer.parseInt(request.getParameter("id"));
        HttpSession ses=request.getSession();
        ArrayList<Tarea> lista=(ArrayList<Tarea>)ses.getAttribute("listatarea");
        Tarea objtarea=new Tarea();
        objtarea.setId(id);
        objtarea.setTarea(request.getParameter("tarea"));
        objtarea.setCompletado(request.getParameter("completado"));
              
        if(id==0){
            //Nuevo registro
            int idNuevo=obtenerId(request);
            objtarea.setId(idNuevo);
            lista.add(objtarea);
            
        }else{
            // edicion de registro
            int pos=buscarPorIndice(request,id);
            lista.set(pos, objtarea);
            
        }
        request.setAttribute("listatarea", lista);
        response.sendRedirect("index.jsp");
    }
    
    public int buscarPorIndice(HttpServletRequest request, int id){
        HttpSession ses=request.getSession();
        ArrayList<Tarea>lista=(ArrayList<Tarea>)ses.getAttribute("listatarea");
        int pos=-1;
        if(lista !=null){
            for(Tarea ele:lista){
                ++pos;
                if(ele.getId()==id){
                    break;
                }
            }
        }
        return pos;
        
    }
    
    public int obtenerId(HttpServletRequest request){
        HttpSession ses=request.getSession();
        ArrayList<Tarea>lista=(ArrayList<Tarea>)ses.getAttribute("listatarea");
        // bucar el ultimo id
        int idn=0;
        for(Tarea ele:lista){
            idn=ele.getId();
            
        }
        return idn +1;
    }

}
