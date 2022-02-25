package za.co.spandigital.be.coding.model;
/**
 * @author Sam Rabophala - sam.rabophala@gmail.com
 * @version 0.0-SNAPSHOT
 * @since JDK1.8
 */
public class Match {
   private Team teamA;
   private Team teamB;

   public Match(Team teamA, Team teamB) {
      this.teamA = teamA;
      this.teamB = teamB;
   }

   public Team getTeamA() {
      return teamA;
   }

   public void setTeamA(Team teamA) {
      this.teamA = teamA;
   }

   public Team getTeamB() {
      return teamB;
   }

   public void setTeamB(Team teamB) {
      this.teamB = teamB;
   }

   @Override
   public String toString() {
      return "Match{" +
              "teamA=" + teamA +
              ", teamB=" + teamB +
              '}';
   }
}
