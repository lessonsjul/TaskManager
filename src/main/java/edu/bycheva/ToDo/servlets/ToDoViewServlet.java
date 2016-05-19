package edu.bycheva.ToDo.servlets;

import edu.bycheva.ToDo.store.TaskCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToDoViewServlet extends HttpServlet{

    private static final TaskCache TASK_CACHE = TaskCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tasks",this.TASK_CACHE.values());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/TaskView.jsp");
        dispatcher.forward(req,resp);
    }
}
