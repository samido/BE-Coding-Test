package za.co.spandigital.be.coding;

import za.co.spandigital.be.coding.model.Match;
import za.co.spandigital.be.coding.utils.Constants;
import za.co.spandigital.be.coding.utils.Utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * @author Sam Rabophala - sam.rabophala@gmail.com
 * @version 0.0-SNAPSHOT
 * @since JDK1.8
 */
public class Main {
    public static void main(String[] args) {

        final Logger logger = Logger.getLogger(String.valueOf(Main.class));

        if(args.length == 0){
            System.out.println("Enter input file");
            return;
        }

        logger.info("Input file is : " + args[Constants.ZERO]);
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
        String outputFile = args[Constants.ZERO].substring(0,args[Constants.ZERO].lastIndexOf("\\"))+Constants.OUT_PUT_FILE;
        Path path = Paths.get(outputFile);

        if(!Files.exists(path)){
        try {
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        try {
            Files.write(path, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SortedPoints.forEach((x,y)-> {
            String line = i.getAndIncrement() + Constants.DOT_WITH_SINGLE_SPACE+x+Constants.COMMA_WITH_SINGLE_SPACE+y+Constants.POINTS_ABBREVIATION;
            System.out.println(line);
            try (BufferedWriter writer = new BufferedWriter( new FileWriter(String.valueOf(path), true))) {
                writer.write(line);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        logger.info("Output file is : " + outputFile);
    }
}
