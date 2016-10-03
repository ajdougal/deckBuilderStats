import java.util.List;

public class Card {
    private String type;
    private String title;
    private List<String> colors;
    private Integer totalMana;
    private Boolean tapped;

    public Card(String type, String title, List<String> colors, Integer totalMana){
        this.type = type;
        this.title = title;
        this.colors = colors;
        this.totalMana = totalMana;
        this.tapped = false;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getColors() {
        return colors;
    }

    public Integer getTotalMana() {
        return totalMana;
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public void setTotalMana(Integer totalMana) {
        this.totalMana = totalMana;
    }
}
