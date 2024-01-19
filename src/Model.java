public class Model {
    private String zone;
    private String district;
    private String localName;
    public Model(String zone, String district, String localName) {
        this.zone = zone;
        this.district = district;
        this.localName = localName;
    }
    public void setZone(String zone) {
        this.zone = zone;
    }
    public String getZone() {
        return zone;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getLocalName() {
        return localName;
    }
    public void setLocalName(String localName) {
        this.localName = localName;
    }
    public String toString() {
        return "Model{" +
                "zone='" + zone + '\'' +
                ", district='" + district + '\'' +
                ", localName='" + localName + '\'' +
                '}';
    }
}
