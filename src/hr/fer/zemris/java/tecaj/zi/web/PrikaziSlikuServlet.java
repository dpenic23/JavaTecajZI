package hr.fer.zemris.java.tecaj.zi.web;

import hr.fer.zemris.java.tecaj.zi.ModelCrtezaImpl;

import java.awt.Dimension;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/slika" })
public class PrikaziSlikuServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final int IMAGE_SIZE = 500;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("eeeeeeeeeeeeeeeeeee");
        ModelCrtezaImpl model = (ModelCrtezaImpl) req.getServletContext()
                .getAttribute("model");

        byte[] bytes = model.generirajSliku(new Dimension(IMAGE_SIZE,
                IMAGE_SIZE));

        // Show image
        resp.setContentType("image/png");
        resp.setContentLength(bytes.length);

        resp.getOutputStream().write(bytes);
    }

}
