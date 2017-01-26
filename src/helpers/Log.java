package helpers;

public class Log {
    private Integer round;
    private String content;
    
    public Log(int round, String content){
        this.round = round;
        this.content = content;
    }
    public Integer getRound(){
        return round;
    }
    public String getContent(){
        return content;
    }
    
}
