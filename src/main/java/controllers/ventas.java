package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import services.empleadoService;
import models.EmpleadosEntity;
import services.ventaService;
import models.VentasEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;

@WebServlet(name = "ventas", urlPatterns = {"/ventas", "/ventas/create", "/ventas/update", "/ventas/delete", "/ventas/save", "/ventas/edit" , "/ventas/destroy"})
public class ventas extends HttpServlet {

    empleadoService empleadoService = new empleadoService();
    ventaService ventaService = new ventaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            /*case "/ventas/create":
                showNewForm(request, response);
                break;*/
            case "/ventas/edit":
                showEditForm(request, response);
                break;
            case "/ventas/delete":
                deleteVenta(request, response);
                break;
            default:
                listVentas(request, response);
                break;
        }
    }

    private void listVentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "views/venta/VentaList.jsp";
        List<VentasEntity> ventas = null;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ventas = ventaService.listar(id);
        } catch (Exception e) {
            String error = "No se pudo obtener la lista de ventas";
            request.setAttribute("error", error);
            request.getRequestDispatcher(view).forward(request, response);
        }
        request.setAttribute("ventas", ventas);
        request.getRequestDispatcher(view).forward(request, response);
    }

    /*private void showNewForm(HttpServletRequest request, HttpServletResponse response) {

    }*/

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/venta/VentaUpdate.jsp";
        searchVenta(request, response, view);
    }

    private void deleteVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/venta/VentaDelete.jsp";
        searchVenta(request, response, view);
    }

    private void searchVenta(HttpServletRequest request, HttpServletResponse response, String view) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        out.println(id);
        VentasEntity venta = null;
        try {
            venta = (VentasEntity) ventaService.buscar(id);
        } catch (Exception e) {
            String error = "No se pudo obtener la venta";
            request.setAttribute("error", error);
            try {
                request.getRequestDispatcher(view).forward(request, response);
            } catch (ServletException e1) {
                sendErrorToHome(request, response);
            } catch (IOException e1) {
                sendErrorToHome(request, response);
            }
        }
        request.setAttribute("venta", venta);
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
            case "/ventas/save":
                saveVenta(request, response);
                break;
            case "/ventas/update":
                updateVenta(request, response);
                break;
            case "/ventas/destroy":
                destroyVenta(request, response);
                break;
        }
    }

    private void saveVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "views/venta/VentaList.jsp";
        VentasEntity venta = new VentasEntity();
        try {
            int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
            BigDecimal total = new BigDecimal(request.getParameter("total"));
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFormateada = formato.parse(request.getParameter("fecha").toString());

            venta.setIdEmpleado(idEmpleado);
            venta.setFechaventa(dateFormateada);
            venta.setTotalventa(total);
            ventaService.insertar(venta);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/ventas?id=" + venta.getIdEmpleado());
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }

    }

    private void updateVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/venta/VentaList.jsp";
        VentasEntity venta = null;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            BigDecimal total = new BigDecimal(request.getParameter("total"));
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFormateada = formato.parse(request.getParameter("fecha").toString());

            venta = (VentasEntity) ventaService.buscar(id);
            venta.setTotalventa(total);
            venta.setFechaventa(dateFormateada);
            ventaService.actualizar(venta);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/ventas?id=" + venta.getIdEmpleado());
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    private void destroyVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/venta/VentaList.jsp";
        VentasEntity venta = null;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            venta = (VentasEntity) ventaService.buscar(id);
            ventaService.eliminar(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/ventas?id=" + venta.getIdEmpleado());
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }
}
