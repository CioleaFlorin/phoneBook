package org.fasttrackIT.phonebook.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackIT.phonebook.domain.PhoneContact;
import org.fasttrackIT.phonebook.service.PhoneContactService;
import org.fasttrackIT.phonebook.transfer.CreatePhoneContactRequest;
import org.fasttrackIT.phonebook.transfer.UpdatePhoneContactRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/phone-contacts")
public class PhoneContactServlet extends HttpServlet {

    private PhoneContactService phoneContactService = new PhoneContactService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        CreatePhoneContactRequest request =
                objectMapper.readValue(req.getReader(), CreatePhoneContactRequest.class);
        try {
            phoneContactService.createPhoneContact(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal error:" + e.getMessage());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        try {
            phoneContactService.deletePhoneContact(Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        ObjectMapper objectMapper = new ObjectMapper();
        UpdatePhoneContactRepository request = objectMapper.readValue(req.getReader(), UpdatePhoneContactRepository.class);

        try {
            phoneContactService.updatePhoneContact(Long.parseLong(id), request.getFirst_name(), request.getLast_name(), request.getPhone_number());
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }
    }

   // @Override
   // protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
   //     try {
   //         List<PhoneContact> phoneContacts = phoneContactService.getPhoneContacts();
   //         ObjectMapper objectMapper=new ObjectMapper();
   //         String response = objectMapper.writeValueAsString(phoneContacts);
   //         resp.getWriter().print(response);
   //     } catch (SQLException | ClassNotFoundException e) {
   //         resp.sendError(500, "Internal error: " + e.getMessage());
   //     }
   // }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String first_name = req.getParameter("first_name");
        String last_name = req.getParameter("last_name");
        try {
            List<PhoneContact> response = phoneContactService.getPhoneContactsByName(first_name, last_name);
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.writeValueAsString(response);
            resp.getWriter().print(response);

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500,"Internal error "+ e.getMessage());

        }

    }

    //@Override
    //protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //    String[] id = req.getParameterValues("id");
    //    long[]ids=new long[100];
    //    for(int i=0; i<=id.length;i++)
    //    {
    //        ids[i]=Long.parseLong(id[i]);
    //    }
    //    try {
    //        phoneContactService.deleteMorePhoneContacts(ids);
    //    } catch (SQLException | ClassNotFoundException e) {
    //        resp.sendError(500,"Internal error: " + e.getMessage());
    //    }
    //}
}

