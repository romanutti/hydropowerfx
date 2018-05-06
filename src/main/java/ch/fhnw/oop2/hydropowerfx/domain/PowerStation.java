package ch.fhnw.oop2.hydropowerfx.domain;

public class PowerStation {

    // TODO: Enum als eigene Klasse, da in PowerStationDetailPM benötigt?
    public enum Type {L, P, S, U}

    private int id;
    private String name;
    private Type type;
    private String site;
    private Canton canton;
    private double maxWaterVolume;
    private double maxPowerMw;
    private double startOfOperationFirst;
    private double startOfOperationLast;
    private double latitude;
    private double longitude;
    // TODO: Status als Enum?
    private String status;
    private double waterbodies;
    private String imageUrl;

    public PowerStation(int id, String name, Type type, String site, Canton canton, double maxWaterVolume, double maxPowerMw, double startOfOperationFirst, double startOfOperationLast, double latitude, double longitude, String status, double waterbodies, String imageUrl) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.site = site;
        this.canton = canton;
        this.maxWaterVolume = maxWaterVolume;
        this.maxPowerMw = maxPowerMw;
        this.startOfOperationFirst = startOfOperationFirst;
        this.startOfOperationLast = startOfOperationLast;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.waterbodies = waterbodies;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Canton getCanton() {
        return canton;
    }

    public void setCanton(Canton canton) {
        this.canton = canton;
    }

    public double getMaxWaterVolume() {
        return maxWaterVolume;
    }

    public void setMaxWaterVolume(double maxWaterVolume) {
        this.maxWaterVolume = maxWaterVolume;
    }

    public double getMaxPowerMw() {
        return maxPowerMw;
    }

    public void setMaxPowerMw(double maxPowerMw) {
        this.maxPowerMw = maxPowerMw;
    }

    public double getStartOfOperationFirst() {
        return startOfOperationFirst;
    }

    public void setStartOfOperationFirst(double startOfOperationFirst) {
        this.startOfOperationFirst = startOfOperationFirst;
    }

    public double getStartOfOperationLast() {
        return startOfOperationLast;
    }

    public void setStartOfOperationLast(double startOfOperationLast) {
        this.startOfOperationLast = startOfOperationLast;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getWaterbodies() {
        return waterbodies;
    }

    public void setWaterbodies(double waterbodies) {
        this.waterbodies = waterbodies;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}