package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import javafx.beans.property.*;

public class PowerStationPM {

    public enum Type {L, P, S, U}

    private final IntegerProperty id                            = new SimpleIntegerProperty();
    private final StringProperty name                           = new SimpleStringProperty();
    private final ObjectProperty<Type> type                     = new SimpleObjectProperty<>();
    private final StringProperty site                           = new SimpleStringProperty();
    private final ObjectProperty<Canton> canton                 = new SimpleObjectProperty<>();
    private final DoubleProperty maxWaterVolume                 = new SimpleDoubleProperty();
    private final DoubleProperty maxPowerMw                     = new SimpleDoubleProperty();
    private final SimpleIntegerProperty startOfOperationFirst   = new SimpleIntegerProperty();
    private final SimpleIntegerProperty startOfOperationLast    = new SimpleIntegerProperty();
    private final DoubleProperty latitude                       = new SimpleDoubleProperty();
    private final DoubleProperty longitude                      = new SimpleDoubleProperty();
    private final ObjectProperty<Status> status                 = new SimpleObjectProperty();
    private final StringProperty waterbodies                    = new SimpleStringProperty();
    private final StringProperty imageUrl                       = new SimpleStringProperty();

    private PowerStationPM() {
    }

    public PowerStationPM(String[] line) {
        setId(                                           Integer.parseInt(line[0]));
        setName(                                                          line[1]);
        setType(                                             Type.valueOf(line[2]));
        setSite(                                                          line[3]);
        setCanton((line[4].length() != 2) ? Canton.OTHER : Canton.valueOf(line[4])); // Sets empty cantons as "OTHERS"
        setMaxWaterVolume(                             Double.parseDouble(line[5]));
        setMaxPowerMw(                                 Double.parseDouble(line[6]));
        setStartOfOperationFirst(                        Integer.parseInt(line[7]));
        setStartOfOperationLast(                         Integer.parseInt(line[8]));
        setLatitude(                                   Double.parseDouble(line[9]));
        setLongitude(                                  Double.parseDouble(line[10]));
        setStatus(                                           defineStatus(line[11]));
        setWaterbodies(                                                   line[12]);
        setImageUrl(                                                      line[13]);
    }

    public static PowerStationPM getDefaultPowerStationPM() {
        PowerStationPM defaultPowerStation = new PowerStationPM();

        // set default values
        defaultPowerStation.setId(99);
        defaultPowerStation.setName("");
        defaultPowerStation.setType(Type.L);
        defaultPowerStation.setSite("");
        defaultPowerStation.setCanton(Canton.OTHER);
        defaultPowerStation.setMaxWaterVolume(0.0);
        defaultPowerStation.setMaxPowerMw(0.0);
        defaultPowerStation.setStartOfOperationFirst(0);
        defaultPowerStation.setStartOfOperationLast(0);
        defaultPowerStation.setLatitude(0);
        defaultPowerStation.setLongitude(0);
        defaultPowerStation.setStatus(Status.NORMAL);
        defaultPowerStation.setWaterbodies("");
        defaultPowerStation.setImageUrl("");

        return defaultPowerStation;
    }

    public String infoAsLine(String delimiter) {
        return String.join(delimiter,
                Integer.toString(getId()),
                getName(),
                getType().name(),
                getSite(),
                getCanton().name(),
                Double.toString(getMaxWaterVolume()),
                Double.toString(getMaxPowerMw()),
                Integer.toString(getStartOfOperationFirst()),
                Integer.toString(getStartOfOperationLast()),
                Double.toString(getLatitude()),
                Double.toString(getLongitude()),
                getStatus().name(),
                getWaterbodies(),
                getImageUrl()
        );
    }

    public Status defineStatus(String status) {
        Status result;
        switch (status) {
            case "im Normalbetrieb":
                result = Status.NORMAL;
                break;
            case "im Bau":
                result = Status.BAU;
                break;
            case "stillgelegt":
                result = Status.STILL;
                break;
            default:
                result = Status.NORMAL;
                break;
        }
        return result;
    }

    // getters and setters
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

    public Integer getStartOfOperationFirst() {
        return startOfOperationFirst.get();
    }

    public IntegerProperty startOfOperationFirstProperty() {
        return startOfOperationFirst;
    }

    public void setStartOfOperationFirst(Integer startOfOperationFirst) {
        this.startOfOperationFirst.set(startOfOperationFirst);
    }

    public Integer getStartOfOperationLast() {
        return startOfOperationLast.get();
    }

    public IntegerProperty startOfOperationLastProperty() {
        return startOfOperationLast;
    }

    public void setStartOfOperationLast(Integer startOfOperationLast) {
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

    public Status getStatus() {
        return status.get();
    }

    public ObjectProperty<Status> statusProperty() {
        return status;
    }

    public void setStatus(Status status) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PowerStationPM) {
            if (((PowerStationPM) obj).getId() == this.getId()) {
                return true;
            }
        }
        return false;
    }
}