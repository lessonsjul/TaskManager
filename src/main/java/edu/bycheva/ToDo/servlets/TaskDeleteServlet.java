package edu.bycheva.ToDo.servlets;

import edu.bycheva.ToDo.store.TaskCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TaskDeleteServlet extends HttpServlet {

    private static final TaskCache TASK_CACHE = TaskCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.TASK_CACHE.delete(Integer.valueOf(req.getParameter("id")));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(),"/"));
    }
}
