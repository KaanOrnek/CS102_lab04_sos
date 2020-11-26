import javax.swing.*;
import cs101.sosgame.SOS;

public class PlaySOS {

    public static void main(String [] args){

        String q1 = JOptionPane.showInputDialog("Board Size:");
        String q2 = JOptionPane.showInputDialog("Please enter first player's name:");
        String q3 = JOptionPane.showInputDialog("Please enter second player's name:");
        int dimension;
        JFrame frame;


        dimension = Integer.parseInt(q1);
        SOS game = new SOS(dimension);
        SOSGUIPanel panel = new SOSGUIPanel(game,q2,q3);
        frame = new JFrame("SOS Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setSize( panel.getSOSCanvas().getPreferableFrameWidth(),panel.getSOSCanvas().getPreferableFrameWidth()+100);
        frame.setVisible(true);
    }
}
