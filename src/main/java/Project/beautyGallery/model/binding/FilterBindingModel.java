package Project.beautyGallery.model.binding;

public class FilterBindingModel {

    private int type;
    private int style;

    public FilterBindingModel() {
    }

    public int getType() {
        return type;
    }

    public FilterBindingModel setType(int type) {
        this.type = type;
        return this;
    }

    public int getStyle() {
        return style;
    }

    public FilterBindingModel setStyle(int style) {
        this.style = style;
        return this;
    }
}
