package ch.fhnw.oop2.hydropowerfx.domain;

import javafx.beans.property.*;

public class PowerStation {

    public enum Type {L, P, S, U}

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<Type> type = new SimpleObjectProperty<>();
    private final StringProperty site = new SimpleStringProperty();
    private final ObjectProperty<Canton> canton = new SimpleObjectProperty<>();
    private final DoubleProperty maxWaterVolume= new SimpleDoubleProperty();
    private final DoubleProperty maxPowerMw = new SimpleDoubleProperty();
    private final DoubleProperty startOfOperationFirst = new SimpleDoubleProperty();
    private final DoubleProperty startOfOperationLast = new SimpleDoubleProperty();
    private final DoubleProperty latitude = new SimpleDoubleProperty();
    private final DoubleProperty longitude = new SimpleDoubleProperty();
    // TODO: Status als Enum?
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty waterbodies = new SimpleStringProperty();
    private final StringProperty imageUrl = new SimpleStringProperty();

    public PowerStation(String[] line) {
        setId(Integer.parseInt(line[0]));
        setName(line[1]);
        setType(Type.valueOf(line[2]));
        setSite(line[3]);
        setCanton(Canton.valueOf(line[4]));
        setMaxWaterVolume(Double.parseDouble(line[5]));
        setMaxPowerMw(Double.parseDouble(line[6]));
        setStartOfOperationFirst(Double.parseDouble(line[7]));
        setStartOfOperationLast(Double.parseDouble(line[8]));
        setLatitude(Double.parseDouble(line[9]));
        setLongitude(Double.parseDouble(line[10]));
        setStatus(line[11]);
        setWaterbodies(line[12]);
        setImageUrl(line[13]);
    }

    // TODO: Methode infoAsLine
    public String infoAsLine(String delimiter) {
        return null;
    }

    //getters and setters
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Type getType() {
        return type.get();
    }

    public ObjectProperty<Type> typeProperty() {
        return type;
    }

    public void setType(Type type) {
        this.type.set(type);
    }

    public String getSite() {
        return site.get();
    }

    public StringProperty siteProperty() {
        return site;
    }

    public void setSite(String site) {
        this.site.set(site);
    }

    public Canton getCanton() {
        return canton.get();
    }

    public ObjectProperty<Canton> cantonProperty() {
        return canton;
    }

    public void setCanton(Canton canton) {
        this.canton.set(canton);
    }

    public double getMaxWaterVolume() {
        return maxWaterVolume.get();
    }

    public DoubleProperty maxWaterVolumeProperty() {
        return maxWaterVolume;
    }

    public void setMaxWaterVolume(double maxWaterVolume) {
        this.maxWaterVolume.set(maxWaterVolume);
    }

    public double getMaxPowerMw() {
        return maxPowerMw.get();
    }

    public DoubleProperty maxPowerMwProperty() {
        return maxPowerMw;
    }

    public void setMaxPowerMw(double maxPowerMw) {
        this.maxPowerMw.set(maxPowerMw);
    }

    public double getStartOfOperationFirst() {
        return startOfOperationFirst.get();
    }

    public DoubleProperty startOfOperationFirstProperty() {
        return startOfOperationFirst;
    }

    public void setStartOfOperationFirst(double startOfOperationFirst) {
        this.startOfOperationFirst.set(startOfOperationFirst);
    }

    public double getStartOfOperationLast() {
        return startOfOperationLast.get();
    }

    public DoubleProperty startOfOperationLastProperty() {
        return startOfOperationLast;
    }

    public void setStartOfOperationLast(double startOfOperationLast) {
        this.startOfOperationLast.set(startOfOperationLast);
    }

    public double getLatitude() {
        return latitude.get();
    }

    public DoubleProperty latitudeProperty() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude.set(latitude);
    }

    public double getLongitude() {
        return longitude.get();
    }

    public DoubleProperty longitudeProperty() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude.set(longitude);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getWaterbodies() {
        return waterbodies.get();
    }

    public StringProperty waterbodiesProperty() {
        return waterbodies;
    }

    public void setWaterbodies(String waterbodies) {
        this.waterbodies.set(waterbodies);
    }

    public String getImageUrl() {
        return imageUrl.get();
    }

    public StringProperty imageUrlProperty() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl.set(imageUrl);
    }
}