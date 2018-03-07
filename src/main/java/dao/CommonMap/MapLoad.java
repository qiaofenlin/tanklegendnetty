package dao.CommonMap;

/**
 * @Created by  qiao
 * @date 18-3-6 下午3:01
 */

public class MapLoad {
    private int id;
    private String type;
    private String mapid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMapid() {
        return mapid;
    }

    public void setMapid(String mapid) {
        this.mapid = mapid;
    }

    @Override
    public String toString() {
        return "MapLoad{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", mapid='" + mapid + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapLoad mapLoad = (MapLoad) o;

        if (id != mapLoad.id) return false;
        if (type != null ? !type.equals(mapLoad.type) : mapLoad.type != null) return false;
        return mapid != null ? mapid.equals(mapLoad.mapid) : mapLoad.mapid == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (mapid != null ? mapid.hashCode() : 0);
        return result;
    }
}
