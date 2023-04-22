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
import java.util.List;

@WebServlet(name = "ventas", urlPatterns = {"/ventas", "/ventas/create", "/ventas/update", "/ventas/delete", "/ventas/save", "/ventas/edit" , "/ventas/destroy"})
public class ventas extends HttpServlet {

    empleadoService empleadoService = new empleadoService();
    ventaService ventaService = new ventaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/ventas/create":
                showNewForm(request, response);
                break;
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
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void deleteVenta(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
