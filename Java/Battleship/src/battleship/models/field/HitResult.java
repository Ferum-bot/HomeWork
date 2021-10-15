package battleship.models.field;

public enum HitResult {

    MISSED (null),

    TORPEDO_MESSED (null),

    HIT (null),

    SUNK (null),

    TORPEDO_SUNK (null),

    NO_AVAILABLE_TORPEDO (null);

    private String extraInformation;

    HitResult(String extraInformation) {
        this.extraInformation = extraInformation;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }
}
