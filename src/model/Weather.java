package model;

public final class Weather {
	public int pressure;
	public float temperature;
	public int weather;
	public Unit pressureUnit;
	public Unit temperatureUnit;

	public Weather(long pressure, long temperature, long weather,
			String unitPressure, String temperatureUnit) {
		this.pressure = (int) pressure;
        this.temperature = temperature;
		this.weather = (int) weather;
		this.pressureUnit = Unit.getUnit(unitPressure);
		this.temperatureUnit = Unit.getUnit(temperatureUnit);
    }
}
