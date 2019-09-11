import java.io.Serializable;

public class Recipe implements Serializable {
    private String img;
    private String title;
    private String url;
    public Recipe(String img, String title, String url){
        this.img=img;
        this.title=title;
        this.url=url;
    }

    public String getImg(){
        return this.img;
    }

    public String getTitle(){
        return this.title;
    }

    public String getUrl(){ return this.url; }
}
