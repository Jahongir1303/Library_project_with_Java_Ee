package com.company.library.servlets.forfiles;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@WebServlet("/download")
public class DownloadImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestedImage = req.getParameter("img");
        Path path = Paths.get("/home/jahongir/IdeaProjects/Library_project_with_Java_Ee/", requestedImage);
        ServletOutputStream outputStream = resp.getOutputStream();
        Files.copy(path,outputStream);
    }
}
