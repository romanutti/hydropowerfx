package ch.fhnw.oop2.hydropowerfx.domain;

public class PowerStation {

    enum Type {L, P, S, U}

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
    private double status;
    private double waterbodies;
    private double imageUrl;

    public PowerStation(int id, String name, Type type, String site, Canton canton, Double maxWaterVolume, Double maxPowerMw, Double startOfOperationFirst, Double startOfOperationLast, Double latitude, Double longitude, Double status, Double waterbodies, Double imageUrl) {
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

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    public double getWaterbodies() {
        return waterbodies;
    }

    public void setWaterbodies(double waterbodies) {
        this.waterbodies = waterbodies;
    }

    public double getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(double imageUrl) {
        this.imageUrl = imageUrl;
    }
}