package org.fasttrackIT.phonebook.service;

import org.fasttrackIT.phonebook.domain.PhoneContact;
import org.fasttrackIT.phonebook.persistance.PhoneContactRepository;
import org.fasttrackIT.phonebook.transfer.CreatePhoneContactRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PhoneContactService {
    private PhoneContactRepository phoneContactRepository=new PhoneContactRepository();

    public void createPhoneContact(CreatePhoneContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating phone contact: "+request);
        phoneContactRepository.createPhoneContact(request);
    }

    public List<PhoneContact> getPhoneContacts() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retriving phone contacts...");
        return phoneContactRepository.getPhoneContacts();
    }

    public void deletePhoneContact(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting phone contact: "+id);
        phoneContactRepository.deletePhoneContact(id);

    }

    public List<PhoneContact> getPhoneContactsByName(String first_name, String last_name) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retriving phone contacts...");
        return phoneContactRepository.getPhoneContactByName(first_name,last_name);

    }

    public void updatePhoneContact(long id, String first_name, String last_name, String phone_number) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating phone contact: "+id);
        phoneContactRepository.updatePhoneContact(id,first_name,last_name,phone_number);
    }

    public void deleteMorePhoneContacts(long[] id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting phone contacts...");
        phoneContactRepository.deleteMorePhoneContacts(id);
    }


}
