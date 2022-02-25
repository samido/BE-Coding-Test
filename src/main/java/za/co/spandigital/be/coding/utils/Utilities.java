package za.co.spandigital.be.coding.utils;

import za.co.spandigital.be.coding.model.Match;
import za.co.spandigital.be.coding.model.Team;
import za.co.spandigital.be.coding.service.Calculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Sam Rabophala - sam.rabophala@gmail.com
 * @version 0.0-SNAPSHOT
 * @since JDK1.8
 */
public class Utilities {
    public static Set<String> teamList = new TreeSet<>();

    public static List<Match> buildMatches(String filePath){
        List<Match> matchList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(line ->{
                List<Team>  match = twoTeams(line);
                matchList.add(new Match(match.get(Constants.ZERO),match.get(Constants.ONE)));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchList;
    }

    protected static List<Team> twoTeams(String line){
        List<Team> teams = new ArrayList<>();
        Stream.of(line.split(Constants.COMMA_WITH_SINGLE_SPACE)).forEach(team ->{
            String teamName = team.substring(Constants.ZERO,team.lastIndexOf(Constants.SINGLE_SPACE));
            Integer teamScore = Integer.parseInt(team.substring(team.lastIndexOf(Constants.SINGLE_SPACE)+Constants.ONE));
            Team team1 = new Team(teamName, teamScore);
            teams.add(team1);
            teamList.add(teamName);
        });
        return teams;
    }

    public static Map<String, Integer> pointsTable(List<Match> matchList,  Map<String, Integer> zeroPointsTable){
        Map<String, Integer> updatedPointsTable = zeroPointsTable;
        Calculator calculator = new Calculator();
        matchList.forEach(match -> {
           if(match.getTeamA().getScore() == match.getTeamB().getScore()){
               updatedPointsTable.put(match.getTeamA().getName(), calculator.drawPoint(updatedPointsTable.get(match.getTeamA().getName())));
               updatedPointsTable.put(match.getTeamB().getName(), calculator.drawPoint(updatedPointsTable.get(match.getTeamB().getName())));
           }
            if(match.getTeamA().getScore() > match.getTeamB().getScore()){
                updatedPointsTable.put(match.getTeamA().getName(), calculator.winPoints(updatedPointsTable.get(match.getTeamA().getName())));

            }
            if(match.getTeamA().getScore() < match.getTeamB().getScore()){
                updatedPointsTable.put(match.getTeamB().getName(), calculator.winPoints( updatedPointsTable.get(match.getTeamB().getName())));
            }
        });
        return updatedPointsTable;
    }

    public static Map<String, Integer> zeroPointsTable(Set<String> teamList){
        Map<String, Integer> zeroPointsTable = new TreeMap<>();
        teamList.forEach(team ->{
            zeroPointsTable.put(team,Constants.ZERO);
        });
        return zeroPointsTable;
    }
}
