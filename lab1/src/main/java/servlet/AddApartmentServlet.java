package servlet;

import entities.Apartment;
import repository.ApartmentRepository;
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/addApartment")
public class AddApartmentServlet extends HttpServlet {
    private static final String HEADER_HTML = "<html><body><h2>Добавить квартиру для аренды</h2>\n";
    private static final String FORM_HTML = "<form method='post' action='addApartment' target='_self'>"
            + "Город: <input name='city'><br>"
            + "Адрес: <input name='address'><br>"
            + "Стоимость, рублей/мес: <input name='price' type='number'><br>"
            + "<input type='submit' value='Отправить'>"
            + "</form></body></html>";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        renderResponse(response, null, null, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String price = request.getParameter("price");

        if (isValidInput(city, address, price)) {
            Apartment apartment = new Apartment(city, address, price);
            ApartmentRepository.addApartment(apartment);
        }

        renderResponse(response, city, address, price);
    }

    private void renderResponse(HttpServletResponse response, String city, String address, String price) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(HEADER_HTML);

        if (city != null && address != null && price != null) {
            response.getWriter().write("<p>Вы добавили квартиру:</p>");
            response.getWriter().write("<p>Город: " + city + "</p>");
            response.getWriter().write("<p>Адрес: " + address + "</p>");
            response.getWriter().write("<p>Цена: " + price + " рублей/мес</p>");
        }

        response.getWriter().write("<h3>Список доступных квартир:</h3>");
        if (ApartmentRepository.getApartments().isEmpty()) {
            response.getWriter().write("<p>Квартиры не найдены.</p>");
        } else {
            ApartmentRepository.getApartments().forEach(apartment -> {
                try {
                    response.getWriter().write("<p>Город: " + apartment.getCity()
                            + ", Адрес: " + apartment.getAddress()
                            + ", Стоимость: " + apartment.getPrice() + " руб/мес</p>");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        response.getWriter().write(FORM_HTML);
    }

    private boolean isValidInput(String city, String address, String price) {
        return city != null && address != null && price != null;
    }
}
