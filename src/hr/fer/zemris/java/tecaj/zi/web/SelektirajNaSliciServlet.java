package hr.fer.zemris.java.tecaj.zi.web;

import hr.fer.zemris.java.tecaj.zi.ModelCrtezaImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/selektirajNaSlici" })
public class SelektirajNaSliciServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ModelCrtezaImpl model = (ModelCrtezaImpl) req.getServletContext()
                .getAttribute("model");

        String coord = req.getQueryString();
        String[] params = coord.split(",");

        int index = model.najblizi(Integer.parseInt(params[0]),
                Integer.parseInt(params[1]));
        if (index != -1) {
            model.postaviSelektirani(index);
        }

        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
    }

}
