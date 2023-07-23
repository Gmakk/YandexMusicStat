package API.entities;

/**
 * Класс для отображения отдельного плейлиста из JSON
 */
public class Playlist {
    private String title;//название
    private String ownersID;//"owner":"uid"
    private String ownersName;//"owner":"name"
    private String ownersSex;//"owner":"sex"
    private String playlistUuid;
    private Integer kind;
    private Integer trackCount;
    private String visibility;
    private String created;
    private Integer durationMs;

    public Playlist(){
    }

    public Playlist(String title, String ownersID, String ownersName, String ownersSex, String playlistUuid,
                    Integer kind, Integer trackCount, String visibility, String created, Integer durationMs){
        this.title=title;
        this.ownersID = ownersID;
        this.ownersName = ownersName;
        this.ownersSex = ownersSex;
        this.playlistUuid = playlistUuid;
        this.kind = kind;
        this.trackCount = trackCount;
        this.visibility = visibility;
        this.created = created;
        this.durationMs = durationMs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnersID() {
        return ownersID;
    }

    public void setOwnersID(String ownersID) {
        this.ownersID = ownersID;
    }

    public String getOwnersName() {
        return ownersName;
    }

    public void setOwnersName(String ownersName) {
        this.ownersName = ownersName;
    }

    public String getOwnersSex() {
        return ownersSex;
    }

    public void setOwnersSex(String ownersSex) {
        this.ownersSex = ownersSex;
    }

    public String getPlaylistUuid() {
        return playlistUuid;
    }

    public void setPlaylistUuid(String playlistUuid) {
        this.playlistUuid = playlistUuid;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Integer durationMs) {
        this.durationMs = durationMs;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "ownersID='" + ownersID + '\'' +
                ", ownersName='" + ownersName + '\'' +
                ", ownersSex='" + ownersSex + '\'' +
                ", playlistUuid='" + playlistUuid + '\'' +
                ", kind=" + kind +
                ", trackCount=" + trackCount +
                ", visibility='" + visibility + '\'' +
                ", created='" + created + '\'' +
                ", durationMs=" + durationMs +
                '}';
    }
}
