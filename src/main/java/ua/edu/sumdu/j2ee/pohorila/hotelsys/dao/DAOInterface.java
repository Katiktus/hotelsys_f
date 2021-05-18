package ua.edu.sumdu.j2ee.pohorila.hotelsys.dao;

import ua.edu.sumdu.j2ee.pohorila.hotelsys.model.*;

public interface DAOInterface {
    void connect();
    void disconnect();
    void addCustomer(String name, String phoneNumber);
    void addRoom(String roomType, int capacity, int price, int customerID, int hotelID);
    void addUser(String name, int managerId, int roleId, String phoneNum, int hotelId);
    CustomerList getAllCustomer();
    RoomList getAllRooms();
    UserList getAllUsers();
    void updateCustomerPhone(int id, String phone);
    void updateRoomPrice(int id, int price);
    void updateRoomCustomerId(int id, int customerId);
    void updateUserMgr(int id, int mgrId);
    void updateUserRole(int id, int roleId);
    void updateUserPhone(int id, String phoneNum);
    void removeCustomer(int id);
    void removeRoom(int id);
    void removeUser(int id);

}
