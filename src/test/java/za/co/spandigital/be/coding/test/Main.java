package za.co.spandigital.be.coding.test;

import za.co.spandigital.be.coding.model.Match;
import za.co.spandigital.be.coding.utils.Constants;
import za.co.spandigital.be.coding.utils.Utilities;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * @author Sam Rabophala - sam.rabophala@gmail.com
 * @version 0.0-SNAPSHOT
 * @since JDK1.8
 */
public class Main {
    public static void main(String[] args) {
        Utilities util = new Utilities();
        String input  = args[Constants.ZERO];
        List<Match> buildMatches = util.buildMatches(input);
        Map<String, Integer> zeroPointsTable = util.zeroPointsTable(util.teamList);
        AtomicInteger i = new AtomicInteger(Constants.ONE);
        Map<String, Integer> pointsTable = util.pointsTable(buildMatches,zeroPointsTable);
        Map<String, Integer> SortedTeams = new LinkedHashMap<>();
        pointsTable.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> SortedTeams.put(x.getKey(), x.getValue()));
        Map<String, Integer> SortedPoints = new LinkedHashMap<>();
        SortedTeams.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> SortedPoints.put(x.getKey(), x.getValue()));
        SortedPoints.forEach((x,y)-> {
            System.out.println(i.getAndIncrement() + Constants.DOT_WITH_SINGLE_SPACE+x+Constants.COMMA_WITH_SINGLE_SPACE+y+Constants.POINTS_ABBREVIATION);
        });
    }
}

