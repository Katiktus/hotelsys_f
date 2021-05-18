package ua.edu.sumdu.j2ee.pohorila.hotelsys.model;

import java.util.Objects;

public class Room {
    int roomNumber;
    String roomType;
    int capacity;
    int price;
    int customerID;
    int hotelID;

    public Room(int roomNumber, String roomType, int capacity, int price, int customerID, int hotelID) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.capacity = capacity;
        this.price = price;
        this.customerID = customerID;
        this.hotelID = hotelID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", roomType='" + roomType + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber && capacity == room.capacity && price == room.price && Objects.equals(roomType, room.roomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, roomType, capacity, price);
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }
}
