package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import services.empleadoService;
import models.EmpleadosEntity;

import static java.lang.System.out;

@WebServlet(name = "empleados", urlPatterns = {"/empleados", "/empleados/create", "/empleados/update", "/empleados/delete", "/empleados/save", "/empleados/edit" , "/empleados/destroy"})
public class empleados extends HttpServlet {
    empleadoService empleadoService = new empleadoService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/empleados/create":
                showNewForm(request, response);
                break;
            case "/empleados/edit":
                showEditForm(request, response);
                break;
            case "/empleados/delete":
                deleteEmpleado(request, response);
                break;
            default:
                listEmpleados(request, response);
                break;
        }
    }

    private void listEmpleados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "views/empleado/EmpleadoList.jsp";
        List<EmpleadosEntity> empleados = null;
        try {
            empleados = empleadoService.listar();
        } catch (Exception e) {
            String error = "No se pudo obtener la lista de empleados";
            request.setAttribute("error", error);
            request.getRequestDispatcher(view).forward(request, response);
        }
        request.setAttribute("empleados", empleados);
        try {
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            sendErrorToHome(request, response);
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/empleado/EmpleadoForm.jsp";
        try {
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            sendErrorToHome(request, response);
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/empleado/EmpleadoUpdate.jsp";
        searchEmpleado(request, response, view);
    }
    private void deleteEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/empleado/EmpleadoDelete.jsp";
        searchEmpleado(request, response, view);
    }
    private void searchEmpleado(HttpServletRequest request, HttpServletResponse response, String view) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        EmpleadosEntity empleado = null;
        try {
            empleado = (EmpleadosEntity) empleadoService.buscar(id);
        } catch (Exception e) {
            String error = "No se pudo obtener el empleado";
            request.setAttribute("error", error);
            try {
                request.getRequestDispatcher(view).forward(request, response);
            } catch (ServletException e1) {
                sendErrorToHome(request, response);
            } catch (IOException e1) {
                sendErrorToHome(request, response);
            }
        }
        request.setAttribute("empleado", empleado);
        try {
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            sendErrorToHome(request, response);
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    private void sendErrorToHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String error = "Ha ocurrido un error inesperado";
        request.setAttribute("error", error);
        response.sendRedirect(request.getContextPath() + "/");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/empleados/save":
                try {
                    saveEmpleado(request, response);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/empleados/destroy":
                destroyEmpleado(request, response);
                break;
            case "/empleados/update":
                try {
                    updateEmpleado(request, response);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                listEmpleados(request, response);
                break;
        }
    }

    private void saveEmpleado(HttpServletRequest request, HttpServletResponse response) throws  ServletException, ParseException, IOException {
        String view = "../views/empleado/EmpleadoList.jsp";
        try {
            EmpleadosEntity empleado = new EmpleadosEntity();
            empleado.setNombre(request.getParameter("nombre"));
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFormateada = formato.parse(request.getParameter("fecha").toString());
            empleado.setFechacontratacion(dateFormateada);
            empleado.setSalario(BigDecimal.valueOf(Double.parseDouble(request.getParameter("salario"))));
            empleadoService.insertar(empleado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/empleados");
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    private void updateEmpleado(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        String view = "../views/empleado/EmpleadoList.jsp";
        EmpleadosEntity empleado = new EmpleadosEntity();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFormateada = formato.parse(request.getParameter("fecha").toString());
            String nombre = request.getParameter("nombre");
            BigDecimal salario = BigDecimal.valueOf(Double.parseDouble(request.getParameter("salario")));
            out.println(salario);

            empleado = (EmpleadosEntity) empleadoService.buscar(id);
            empleado.setNombre(nombre);
            empleado.setFechacontratacion(dateFormateada);
            empleado.setSalario(salario);
            empleadoService.actualizar(empleado);
        }catch (Exception e) {
            throw new RuntimeException();
        }
        try {
            response.sendRedirect(request.getContextPath() + "/empleados");
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    private void destroyEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/empleado/EmpleadoDlete.jsp";
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            empleadoService.eliminar(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        try {
            response.sendRedirect(request.getContextPath() + "/empleados");
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }
}
