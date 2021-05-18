package ua.edu.sumdu.j2ee.pohorila.hotelsys.model;

import java.util.ArrayList;

public class CustomerList {
    ArrayList<Customer> customers;


    public void add(Customer parseCustomer) {
        customers.add(parseCustomer);
    }
}
