package edu.bycheva.ToDo.servlets;

import edu.bycheva.ToDo.model.TaskPriority;
import edu.bycheva.ToDo.store.TaskCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TaskEditServlet extends HttpServlet {

    private static final TaskCache TASK_CACHE = TaskCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("task", this.TASK_CACHE.get(Integer.valueOf(req.getParameter("id"))));
        req.setAttribute("priorities",TaskPriority.values());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/TaskEdit.jsp");
        dispatcher.forward(req,resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.TASK_CACHE.edit(new Task(
//                Integer.valueOf(req.getParameter("id")),
//                req.getParameter("name"),
//                TaskPriority.valueOf(req.getParameter("taskPriority")),
//                req.getParameter("description"),
//                Date.valueOf(req.getParameter("termEnd"))
//            )
//        );
//        resp.sendRedirect(String.format("%s%s",req.getContextPath(),"/"));
//    }
}
