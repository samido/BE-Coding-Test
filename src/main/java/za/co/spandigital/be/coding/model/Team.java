package za.co.spandigital.be.coding.model;
/**
 * @author Sam Rabophala - sam.rabophala@gmail.com
 * @version 0.0-SNAPSHOT
 * @since JDK1.8
 */
public class Team {
    private String name;
    private Integer score;

    public Team(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
