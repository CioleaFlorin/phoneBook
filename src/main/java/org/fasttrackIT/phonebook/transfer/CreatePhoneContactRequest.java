package org.fasttrackIT.phonebook.transfer;

public class CreatePhoneContactRequest {
    private String first_name;
    private String last_name;
    private String phoneNumber;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "CreatePhoneContactRequest{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
