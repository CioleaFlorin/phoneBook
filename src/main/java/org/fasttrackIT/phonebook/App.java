package org.fasttrackIT.phonebook;

import org.fasttrackIT.phonebook.domain.PhoneContact;
import org.fasttrackIT.phonebook.persistance.PhoneContactRepository;
import org.fasttrackIT.phonebook.transfer.CreatePhoneContactRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, IOException, ClassNotFoundException {
        //CreatePhoneContactRequest request=new CreatePhoneContactRequest();
        //request.setFirst_name("Daniel");
        //request.setLast_name("Ciolea");
        //request.setPhoneNumber("0738265888");
        //phoneContactRepository.deletePhoneContact(3);
        PhoneContactRepository phoneContactRepository=new PhoneContactRepository();
        //phoneContactRepository.createPhoneContact(request);
        //List<PhoneContact> phoneContacts= phoneContactRepository.getPhoneContactByName("Daniel", "Daniel");
        //System.out.println(phoneContacts);
        //phoneContactRepository.editPhoneContact(2,"Delia","Ciolea","0735368131");
        //long []arr=new long[10];
        //arr[0]=4;
        //arr[1]=5;
        //phoneContactRepository.deleteMorePhoneContacts(arr);
        List<PhoneContact> phoneContacts = phoneContactRepository.getPhoneContacts();
        System.out.println(phoneContacts);
    }
}
