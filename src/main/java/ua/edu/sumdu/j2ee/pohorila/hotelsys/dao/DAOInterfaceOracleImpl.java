package ua.edu.sumdu.j2ee.pohorila.hotelsys.dao;

import ua.edu.sumdu.j2ee.pohorila.hotelsys.model.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class DAOInterfaceOracleImpl implements DAOInterface {

    private final static DAOInterfaceOracleImpl instance = new DAOInterfaceOracleImpl();

    public DAOInterfaceOracleImpl(){
        super();
    }

    private static DAOInterfaceOracleImpl getInstance(){
        return instance;
    }

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private DataSource dataSource;
    private Context context;
    private Hashtable hashtable = new Hashtable();

    @Override
    public void connect(){
        try{
            hashtable.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            hashtable.put(Context.PROVIDER_URL, "t3://localhost:7001");
            context = new InitialContext(hashtable);
            dataSource = (DataSource) context.lookup("DataSourceHotel");
            connection = dataSource.getConnection();
            if(!connection.isClosed()){
                System.out.println("Connection successful...");
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect(){
        try{
            if(connection != null)
                connection.close();
            if(context != null)
                context.close();
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addCustomer(String name, String phoneNumber) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("insert into LAB3_EP_CUSTOMER values (null, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    @Override
    public void addRoom(String roomType, int capacity, int price, int customerID, int hotelID) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("insert into LAB3_EP_ROOM values (null, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, roomType);
            preparedStatement.setInt(2, capacity);
            preparedStatement.setInt(3, price);
            preparedStatement.setInt(4, customerID);
            preparedStatement.setInt(5, hotelID);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();

    }

    @Override
    public void addUser(String name, int managerId, int roleId, String phoneNum, int hotelId) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO LAB3_EP_USER values (?,?,?,null,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, roleId);
            preparedStatement.setInt(3, managerId);
            preparedStatement.setString(4, phoneNum);
            preparedStatement.setInt(5, hotelId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    @Override
    public CustomerList getAllCustomer() {
        connect();
        CustomerList customerList = new CustomerList();
        try {
            preparedStatement = connection.prepareStatement("select * from LAB3_EP_CUSTOMER");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                customerList.add(parseCustomer(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return null;
    }

    private Customer parseCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = null;
        int customerid = resultSet.getInt("LAB3_EP_CUSTOMER_ID");
        String name = resultSet.getString("LAB3_EP_CUSTOMER_NAME");
        String phoneNumber = resultSet.getString("LAB3_EP_CUSTOMER_PHONE_NUMBER");
        customer = new Customer(customerid, name, phoneNumber);
        return customer;
    }

    private Room parseRoom(ResultSet resultSet) throws SQLException {
        Room room = null;
        int roomNumber = resultSet.getInt("LAB3_EP_ROOM_NUMBER");
        String roomType = resultSet.getString("LAB3_EP_CUSTOMER_NAME");
        int capacity = resultSet.getInt("LAB3_EP_ROOM_NUMBER");
        int price = resultSet.getInt("LAB3_EP_ROOM_NUMBER");
        int cus_id = resultSet.getInt("LAB3_EP_ROOM_CUSTOMER_ID");
        int hotel_id = resultSet.getInt("LAB3_EP_ROOM_HOTEL_ID");
        room = new Room(roomNumber,roomType, capacity, price, cus_id, hotel_id);
        return room;
    }

    private User parseUser(ResultSet resultSet) throws SQLException {
        User user = null;
        int managerid = resultSet.getInt("LAB3_EP_USER_ID");
        String name = resultSet.getString("LAB3_EP_CUSTOMER_NAME");
        int userid = resultSet.getInt("LAB3_EP_USER_ID");
        int roleid = resultSet.getInt("LAB3_EP_USER_ID");
        String phoneNumber = resultSet.getString("LAB3_EP_CUSTOMER_PHONE_NUMBER");
        int hotelid = resultSet.getInt("LAB3_EP_HOTEL_ID");
        user = new User(name, managerid, userid, roleid, phoneNumber, hotelid);
        return user;
    }

    @Override
    public RoomList getAllRooms() {
        connect();
        RoomList roomList = new RoomList();
        try {
            preparedStatement = connection.prepareStatement("select * from LAB3_EP_ROOM");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                roomList.add(parseRoom(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return roomList;
    }

    @Override
    public UserList getAllUsers() {
        connect();
        UserList userList = new UserList();
        try {
            preparedStatement = connection.prepareStatement("select * from LAB3_EP_USER");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                userList.add(parseUser(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return userList;

    }

    @Override
    public void updateCustomerPhone(int id, String phone) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("update LAB3_EP_CUSTOMER set LAB3_EP_CUSTOMER_PHONE_NUMBER = ? where " +
                    "LAB3_EP_CUSTOMER_ID = ? ");
            preparedStatement.setString(1, phone);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    @Override
    public void updateRoomPrice(int id, int price) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("update LAB3_EP_ROOM set LAB3_EP_ROOM_PRICE = ? where " +
                    "LAB3_EP_ROOM_NUMBER = ? ");
            preparedStatement.setInt(1, price);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    @Override
    public void updateRoomCustomerId(int id, int customerId) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("update LAB3_EP_ROOM set LAB3_EP_ROOM_CUSTOMER_ID = ? where " +
                    "LAB3_EP_ROOM_NUMBER = ? ");
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();

    }

    @Override
    public void updateUserMgr(int id, int mgrId) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("update LAB3_EP_USER set LAB3_EP_MGR_ID = ? where " +
                    "LAB3_EP_USER_ID = ? ");
            preparedStatement.setInt(1, mgrId);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    @Override
    public void updateUserRole(int id, int roleId) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("update LAB3_EP_USER set LAB3_EP_ROLE_ID = ? where " +
                    "LAB3_EP_USER_ID = ? ");
            preparedStatement.setInt(1, roleId);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    @Override
    public void updateUserPhone(int id, String phoneNum) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("update LAB3_EP_USER set LAB3_EP_USER_PHONE_NUMBER = ? where " +
                    "LAB3_EP_USER_ID = ? ");
            preparedStatement.setString(1, phoneNum);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    @Override
    public void removeCustomer(int id) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("delete from LAB3_EP_CUSTOMER where LAB3_EP_CUSTOMER_ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    @Override
    public void removeRoom(int id) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("delete from LAB3_EP_ROOM where LAB3_EP_ROOM_NUMBER = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    @Override
    public void removeUser(int id) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("delete from LAB3_EP_USER where LAB3_EP_USER_ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

}
