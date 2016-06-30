package hr.fer.zemris.java.tecaj.zi.web;

import hr.fer.zemris.java.tecaj.zi.ModelCrtezaImpl;
import hr.fer.zemris.java.tecaj.zi.naredbe.Diselektiraj;
import hr.fer.zemris.java.tecaj.zi.naredbe.Dodaj;
import hr.fer.zemris.java.tecaj.zi.naredbe.Naredba;
import hr.fer.zemris.java.tecaj.zi.naredbe.Obrisi;
import hr.fer.zemris.java.tecaj.zi.naredbe.Selektiraj;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/izvrsi")
public class IzvrsiServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static Map<String, Naredba> naredbe = new HashMap<>();

    static {
        naredbe.put("obrisi", new Obrisi());
        naredbe.put("dodaj", new Dodaj());
        naredbe.put("selektiraj", new Selektiraj());
        naredbe.put("diselektiraj", new Diselektiraj());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        obradi(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        obradi(req, resp);
    }

    protected void obradi(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ModelCrtezaImpl model = (ModelCrtezaImpl) req.getServletContext()
                .getAttribute("model");
        
        req.setCharacterEncoding("UTF-8");

        FormularForm f = new FormularForm();
        f.popuniIzHttpRequesta(req);
        f.validiraj();

        if (f.imaPogresaka()) {
            req.setAttribute("zapis", f);
            req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(
                    req, resp);
            return;
        }

        Record r = new Record();
        f.popuniURecord(r);

        String[] params = r.getRedak().split("\\s+", 2);
        String imeNaredbe = params[0].trim().toLowerCase();
        String parametriNaredbe = "";

        if (params.length == 2) {
            parametriNaredbe = params[1].trim();
        }

        Naredba naredba = naredbe.get(imeNaredbe);

        try {
            naredba.izvrsiNaredbu(parametriNaredbe, model);
        } catch (Exception ex) {
        }
        
        resp.sendRedirect(req.getServletContext().getContextPath() + "/");
    }
}
