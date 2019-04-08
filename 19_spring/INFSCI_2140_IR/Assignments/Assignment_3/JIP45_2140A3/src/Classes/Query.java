package Classes;

public class Query {
    //you can modify this class

    private String queryContent;
    private String topicId;
    private String queryTitle;
    private String queryDesc;
    private String queryNarr;

    public Query(String topicId, String queryContent) {
        this.topicId = topicId;
        this.queryContent = queryContent;
        this.queryTitle = null;
        this.queryDesc = null;
        this.queryNarr = null;
    }

    public Query() {
        this.queryContent = null;
        this.topicId = null;
        this.queryTitle = null;
        this.queryDesc = null;
        this.queryNarr = null;
    }

    public String GetQueryContent() {
        return this.queryContent;
    }

    public String GetTopicId() {
        return this.topicId;
    }

    public String GetQueryTitle() {
        return this.queryTitle;
    }

    public String GetQueryDesc() {
        return this.queryDesc;
    }

    public String GetQueryNarr() {
        return this.queryNarr;
    }

    public void SetQueryContent(String content) {
        this.queryContent = content;
    }

    public void SetTopicId(String id) {
        this.topicId = id;
    }

    public void SetQueryTitle(String queryTitle){
        this.queryTitle = queryTitle;
    }

    public void SetQueryDesc(String queryDesc){
        this.queryDesc = queryDesc;
    }

    public void SetQueryNarr(String queryNarr){
        this.queryNarr = queryNarr;
    }

}
