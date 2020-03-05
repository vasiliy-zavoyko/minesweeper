package sweeper;

public enum Box {

    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,

    OPENED,
    CLOSED,
    FLAGGED,
    BOMBED,
    NOBOMB;

    private Object image;

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    Box nextNumberBox() {
        return Box.values()[this.ordinal() + 1];
    }
}
