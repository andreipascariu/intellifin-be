package ro.happydevs.intellifin.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.models.Car;
import ro.happydevs.intellifin.utils.constants.CONSTANTS;
import ro.happydevs.intellifin.utils.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class CarRepository implements IRepository {

    private static Logger logger = LoggerFactory.getLogger(CarRepository.class);


    @Override
    public ArrayList<?> getAll() {
        return null;
    }

    @Override
    public ArrayList<?> getAllByStringColumn(String column, String value) {
        return null;
    }

    @Override
    public ArrayList<?> getAllByNumericColumn(String column, int value) {
        return null;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override //deletes car
    public boolean delete(int id) {
        Connection con = DBConnection.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE " + CONSTANTS.CAR_TABLE + " SET deleted = 1 WHERE id = ? ");
            ps.setInt(1,id);
            ps.executeUpdate();
            logger.info("[CarRepository] - delete Car success!");
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public boolean update(Object newObject, String token) {
        return false;
    }

    @Override
    public boolean create(Object object) {
        Connection con = DBConnection.getConnection();
        Car car = (Car) object;
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + CONSTANTS.CAR_TABLE + "(car_vin,car_manufacturer,car_year,car_model,car_color,car_fueltype,userID,car_id,deleted) VALUES(?,?,?,?,?,?,?,?,?)");
            ps.setString(1,car.getCarVIN());
            ps.setString(2,car.getCarManufacturer());
            ps.setInt(3,car.getCarYear());
            ps.setString(4,car.getCarModel  ());
            ps.setString(5,car.getCarColor());
            ps.setString(6,car.getCarFuelType());
            ps.setInt(7,car.getUserID());
            ps.setInt(8,car.getCarID());
            ps.setBoolean(9,false);
            ps.executeUpdate();
            logger.info("[Car Repository Create] - Success");
            return true;

        }catch(Exception e){
        e.printStackTrace();
            logger.error("[Car Repository Create] - Error");
        }
        return false;
    }

}
