package servlet;

import entities.Apartment;
import repository.ApartmentRepository;
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/deleteApartment")
public class DeleteApartmentServlet extends HttpServlet {
    private static final String HEADER_HTML = "<html><body><h2>Удаление квартиры</h2>\n";
    private static final String FORM_HTML = "<form method='post' action='deleteApartment' target='_self'>"
            + "ID квартиры: <input name='apartmentId' type='number'><br>"
            + "<input type='submit' value='Удалить'>"
            + "</form></body></html>";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handleRequest(response, null, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String apartmentIdStr = request.getParameter("apartmentId");
        if (apartmentIdStr != null) {
            try {
                int apartmentId = Integer.parseInt(apartmentIdStr);

                if (isValidApartmentId(apartmentId)) {
                    ApartmentRepository.deleteApartment(apartmentId);
                    handleRequest(response, apartmentIdStr, null);
                } else {
                    handleRequest(response, null, "Квартиры с данным ID не существует.");
                }
            } catch (NumberFormatException e) {
                handleRequest(response, null, "Некорректный ID. Пожалуйста, введите число.");
            }
        } else {
            handleRequest(response, null, "ID квартиры не был введён.");
        }
    }

    private void handleRequest(HttpServletResponse response, String apartmentId, String errorMessage) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(HEADER_HTML);

        if (errorMessage != null) {
            response.getWriter().write("<p style='color:red;'>" + errorMessage + "</p>");
        } else if (apartmentId != null) {
            response.getWriter().write("<p>Квартира с ID " + apartmentId + " успешно удалена.</p>");
        }

        response.getWriter().write("<h3>Список доступных квартир:</h3>");
        if (ApartmentRepository.getApartments().isEmpty()) {
            response.getWriter().write("<p>Нет доступных квартир.</p>");
        } else {
            for (int i = 0; i < ApartmentRepository.getApartments().size(); i++) {
                Apartment apartment = ApartmentRepository.getApartments().get(i);
                response.getWriter().write("<p>ID: " + i + ", Город: " + apartment.getCity()
                        + ", Адрес: " + apartment.getAddress() + ", Цена: " + apartment.getPrice() + " рублей/мес</p>");
            }
        }

        response.getWriter().write(FORM_HTML);
    }

    private boolean isValidApartmentId(int id) {
        return id >= 0 && id < ApartmentRepository.getApartments().size();
    }
}
