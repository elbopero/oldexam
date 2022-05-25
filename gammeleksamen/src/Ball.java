public class Ball extends Equipment{
    BallType ballType;
    private boolean needsAir;

    public Ball(int id, String locker, boolean needsReplacement, BallType ballType, boolean needsAir) {
        super(id, locker, needsReplacement);
        this.ballType = ballType;
        this.needsAir = needsAir;
    }

    public BallType getBallType() {
        return ballType;
    }

    public void setBallType(BallType ballType) {
        this.ballType = ballType;
    }

    public boolean isNeedsAir() {
        return needsAir;
    }

    public void setNeedsAir(boolean needsAir) {
        this.needsAir = needsAir;
    }

    @Override
    public String toString() {
        return  "Ball with id:" + getId() + ", Location: " + getLocker()
                + ", Type of ball: " + ballType + ", must be replaced: " + isNeedsReplacement() +", needs air: " + needsAir;
    }
}
