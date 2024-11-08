/*
• Solve problem on 79 Project Euler.

• The key is to figure out what is node and what is an edge. (what things have relationships in this problem and what defines their relationships?)

• Then use topological sort to solve the problem.

• Create an account on site to confirm your answer.

• Key to solving the problem is finding the indegree of each node (indegree of a node in a directed graph is the number of edges that have that node as a destination,1 or the number of edges that lead into the nodeº

• JUNG can’t make two edges with the same value. So you can’t have two edges with the value of "A".

• The indegree and outdegree of a undirected graph are the same thing, so it’s just called the degree

*/

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PasswordCracking {

    public static void main(String[] args) {
        DirectedSparseGraph<Integer, String> g = new DirectedSparseGraph<>();
        String fileName = "keylog.txt";
        Map<Integer, Integer> indegrees = new HashMap<>();

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                int firstDigit = Character.getNumericValue(line.charAt(0));

                int secondDigit = Character.getNumericValue(line.charAt(1));

                int thirdDigit = Character.getNumericValue(line.charAt(2));

                addEdge(g, indegrees, firstDigit, secondDigit);
                addEdge(g, indegrees, secondDigit, thirdDigit);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Passcode: " + topologicalSort(g, indegrees));
    }

    private static void addEdge(DirectedSparseGraph<Integer, String> graph, Map<Integer, Integer> indegrees, int from, int to) {
        String edge = from + "-" + to;
        if (!graph.containsEdge(edge)) {
            graph.addEdge(edge, from, to);
            indegrees.put(to, indegrees.getOrDefault(to, 0) + 1);
            indegrees.putIfAbsent(from, 0);
        }
    }

    private static String topologicalSort(DirectedSparseGraph<Integer, String> graph, Map<Integer, Integer> indegrees) {
        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : indegrees.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            int current = queue.remove();
            result.append(current);
            for (Integer neighbor : graph.getSuccessors(current)) {
                int newIndegree = indegrees.get(neighbor) - 1;
                indegrees.put(neighbor, newIndegree);
                if (newIndegree == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return result.toString();
    }
}
