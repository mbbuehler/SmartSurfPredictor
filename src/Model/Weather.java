package Model;

public final class Weather {
    public final long pressure;
    public final long temperature;
    public final long weather;
    public final String unitPressure;
    public final String unit;

    public Weather(long pressure, long temperature, long weather, String unitPressure, String unit){
        this.pressure = pressure;
        this.temperature = temperature;
        this.weather = weather;
        this.unitPressure = unitPressure;
        this.unit = unit;
    }
}
