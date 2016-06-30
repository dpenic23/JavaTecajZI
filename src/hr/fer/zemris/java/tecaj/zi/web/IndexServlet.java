package hr.fer.zemris.java.tecaj.zi.web;

import hr.fer.zemris.java.tecaj.zi.Krug;
import hr.fer.zemris.java.tecaj.zi.ModelCrtezaImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@code IndexServlet} is base {@code HttpServlet} of this web application. It
 * provides initial list of {@code Polls} available from {@code DAOProvider} and
 * sets them as "polls" attribute. Request is being forwarded to another page
 * which task is to show these data.
 * 
 * @author Domagoj Penic
 * @version 1.0
 * 
 */
@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {

    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ModelCrtezaImpl model = (ModelCrtezaImpl) req.getServletContext()
                .getAttribute("model");
        
        List<Zapis> zapisi = new ArrayList<>();
        
        int n = model.brojKrugova();
        for(int i = 0 ; i < n; i++){
            Krug krug = model.dohvati(i);
            Zapis zapis = new Zapis(i, krug.toString());
            zapisi.add(zapis);
        }

        req.setAttribute("zapisi", zapisi);
        req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);

    }

    public static class Zapis {
        private int index;
        private String definicija;

        public Zapis(int index, String definicija) {
            this.index = index;
            this.definicija = definicija;
        }

        public int getIndex() {
            return index;
        }

        public String getDefinicija() {
            return definicija;
        }

    }

}
