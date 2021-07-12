package com.epat.build;

/**
 * @author 李涛
 * @date : 2021/7/12 10:01
 */
public interface Builder {

    void setCarType(CarType type);
    void setSeats(int seats);
    void setEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);

}
