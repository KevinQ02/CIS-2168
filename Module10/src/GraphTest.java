import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//sources: Youtube: Professor Rosen, GitHub: abrosen & CraigFox0, JUNG offical website, Stack Overflow
public class GraphTest {

    public static void main(String[] args) {
        // V's are ints and E's are string opp. of the demo
        SparseGraph<Integer, String> g = new SparseGraph<>();
        g.addEdge("12", 1, 2);
        g.addEdge("13", 1, 3);
        g.addEdge("34", 3, 4);
        g.addEdge("27", 2, 7);


        System.out.println("BFS: " + BreadthFirstTraversal(g, 1));
        System.out.println("DFS: " + DepthFirstTraversal(g, 1));

        // Setup the visualization using a CircleLayout
        Layout<Integer, String> layout = new CircleLayout<>(g);
        layout.setSize(new Dimension(600, 600));

        // Setup up visualization server
        BasicVisualizationServer<Integer, String> vv =
                new BasicVisualizationServer<>(layout);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());

        // Setup the JFrame to display the visualization
        JFrame frame = new JFrame("Graph Test using JUNG 2.0.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }
//BFS
    public static ArrayList<Integer> BreadthFirstTraversal(Graph<Integer, String> g, int startingPoint) {
        ArrayList<Integer> visited = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startingPoint);
        while (!queue.isEmpty()) {
            int current = queue.remove();
            if (!visited.contains(current)) {
                visited.add(current);
                for (int neighbor : g.getNeighbors(current)) {
                    if (!visited.contains(neighbor) && !queue.contains(neighbor))
                        queue.add(neighbor);
                }
            }
        }
        return visited;
    }
//DFS
    public static ArrayList<Integer> DepthFirstTraversal(Graph<Integer, String> g, int startingPoint) {
        ArrayList<Integer> visited = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startingPoint);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                for (int neighbor : g.getNeighbors(current)) {
                    if (!visited.contains(neighbor) && !stack.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        return visited;
    }
}
