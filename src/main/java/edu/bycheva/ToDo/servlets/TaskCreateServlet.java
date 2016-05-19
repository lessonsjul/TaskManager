package edu.bycheva.ToDo.servlets;

import edu.bycheva.ToDo.model.TaskPriority;
import edu.bycheva.ToDo.store.TaskCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskCreateServlet extends HttpServlet {
    private final AtomicInteger ids = new AtomicInteger();
    private static final TaskCache TASK_CACHE = TaskCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("priorities",TaskPriority.values());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/TaskCreate.jsp");
        dispatcher.forward(req,resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.TASK_CACHE.add(
//                new Task(
//                        Integer.valueOf(ids.incrementAndGet()),
//                        req.getParameter("name"),
//                        TaskPriority.valueOf(req.getParameter("taskPriority")),
//                        req.getParameter("description"),
//                        Date.valueOf(req.getParameter("termEnd"))
//                )
//        );
//        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/"));
//    }
}
