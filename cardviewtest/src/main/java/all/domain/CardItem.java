package all.domain;

/**
 * Created by Administrator on 2016/7/13.
 */
public class CardItem {
   private String desStr;
    private int imageRes;

    public CardItem(String desStr, int imageRes) {
        this.desStr = desStr;
        this.imageRes = imageRes;
    }

    public String getDesStr() {
        return desStr;
    }

    public void setDesStr(String desStr) {
        this.desStr = desStr;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
