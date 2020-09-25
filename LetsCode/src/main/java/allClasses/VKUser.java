package allClasses;

public class VKUser {
    private int id;
    private String first_name;
    private String last_name;
    private String deactivated;
    private boolean is_closed;
    private boolean can_access_closed;

    @Override
    public String toString() {
        return "VKUser{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", deactivated='" + deactivated + '\'' +
                ", is_closed=" + is_closed +
                ", can_access_closed=" + can_access_closed +
                '}';
    }

    public VKUser() {
    }

    public VKUser(int id, String first_name, String last_name, String deactivated, boolean is_closed, boolean can_access_closed) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.deactivated = deactivated;
        this.is_closed = is_closed;
        this.can_access_closed = can_access_closed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(String deactivated) {
        this.deactivated = deactivated;
    }

    public boolean isIs_closed() {
        return is_closed;
    }

    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }

    public boolean isCan_access_closed() {
        return can_access_closed;
    }

    public void setCan_access_closed(boolean can_access_closed) {
        this.can_access_closed = can_access_closed;
    }
}
