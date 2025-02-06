import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class CommentPom {
    public static void main(String[] args) throws IOException {
        // Change
        String filePath = "D:\\UOB - Finastra\\Automation\\Batch Pom xml Jenkins deploy test automation\\pom.xml";
        // Change
        String commentedLinesPath = "D:\\UOB - Finastra\\Automation\\Batch Pom xml Jenkins deploy test automation\\commented_lines.txt";
        int lastCommentedLine = 0;
        File commentedLinesFile = new File(commentedLinesPath);
        if (commentedLinesFile.exists()) {
            try (Scanner scanner = new Scanner(commentedLinesFile)) {
                if (scanner.hasNextInt()) {
                    lastCommentedLine = scanner.nextInt();
                }
            }
        }

        int nextLine = lastCommentedLine + 1;
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        if (nextLine <= lines.size()) {
            lines.set(nextLine - 1, "<!-- " + lines.get(nextLine - 1) + " -->");
        }
        Files.write(Paths.get(filePath), lines);
        try (PrintWriter writer = new PrintWriter(commentedLinesFile)) {
            writer.println(nextLine);
        }
        System.out.println("Line " + nextLine + " commented out in " + filePath + ".");
    }
}
