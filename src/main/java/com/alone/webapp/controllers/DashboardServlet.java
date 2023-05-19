package com.alone.webapp.controllers;

import com.alone.webapp.DAO.DetalleOrdenDAO;
import com.alone.webapp.DAO.OrdenDAO;
import com.alone.webapp.models.Orden;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DashboardServlet", value = "/dashboard")
public class DashboardServlet extends HttpServlet {
    private OrdenDAO ordenDAO = new OrdenDAO();
    private DetalleOrdenDAO detalleOrdenDAO = new DetalleOrdenDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.defaultAction(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            ProductosServlet.sendInternalError(response, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void defaultAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        BigDecimal ganancias = detalleOrdenDAO.findMonthlyWealth();
        request.setAttribute("ganancias",ganancias);

        Integer clientes = ordenDAO.findYearlyCustomers();
        request.setAttribute("clientes",clientes);

        Integer ordenes = ordenDAO.findMonthlyOrders();
        request.setAttribute("ordenes",ordenes);

        List<Orden> ordenesDiarias = ordenDAO.findDaily();
        request.setAttribute("ordenesDiarias",ordenesDiarias);

        getServletContext().getRequestDispatcher("/WEB-INF/dashboard/index.jsp").forward(request,response);
    }
}
