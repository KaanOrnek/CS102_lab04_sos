import cs101.sosgame.SOS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SOSGUIPanel extends JPanel {

    //instance data members
    private SOSCanvas canvas;
    private JLabel user1;
    private JLabel user2;
    private String u1;
    private String u2;
    private JComboBox selection;
    private JPanel userGui;
    private SOS game;
    private SOSListener listener;

    /*
    constructor that takes user names and the game as input

     */
    public SOSGUIPanel(SOS game, String user1, String user2){
        this.game = game;
        canvas = new SOSCanvas( game );
        listener = new SOSListener();
        canvas.addMouseListener(listener);
        u1 = user1;
        u2 = user2;

        this.user1 = new JLabel(u1 + ": " + game.getPlayerScore1());
        this.user2 = new JLabel( u2 + ": " + game.getPlayerScore2() );
        this.user1.setForeground(Color.RED);

        selection = new JComboBox();
        selection.addItem("S");
        selection.addItem("O");

        userGui = new JPanel();
        userGui.setPreferredSize( new Dimension( canvas.getPreferableFrameWidth(), 60 ));

        userGui.add(this.user1);
        userGui.add(selection);
        userGui.add(this.user2);
        this.setLayout(new BorderLayout());
        add(canvas,BorderLayout.NORTH);
        add(userGui,BorderLayout.SOUTH);


    }
    //returns the canvas that created in the panel so that it can be accessed from the main class as well.
    public SOSCanvas getSOSCanvas(){ return canvas; }

    /*
    By the overwritten mousePressed() method, it gets the pressed location
    checks which row and column has that location and plays SOS with the priorly selected char S or O.
    If game is over, it let the user know who won.
    If game isn't over yet, changes the label's color to indicate whose turn is now.
     */
    class SOSListener implements MouseListener {

        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            int row=-1;
            int column=-1;

            for(int i = 0; i< game.getDimension();i++ )
            {
                if( x > canvas.getGap() + (canvas.getLengthOfSquares()*i) && x < canvas.getGap() + (canvas.getLengthOfSquares()*(i+1)))
                {
                    column = i+1;
                }
            }
            for( int k = 0; k< game.getDimension();k++)
            {
                if( y > canvas.getGap() + (canvas.getLengthOfSquares()*k) && y < canvas.getGap() + (canvas.getLengthOfSquares()*(k+1)) )
                {
                    row = k+1;
                }

            }
            if(((String) selection.getSelectedItem()).equals("S") && !game.isGameOver())
                game.play('s',row,column);

            else if( ((String) selection.getSelectedItem()).equals("O") && !game.isGameOver())
                game.play('o',row,column);
            canvas.played();
            user1.setText(u1 + ": " + game.getPlayerScore1());
            user2.setText(u2 + ": " + game.getPlayerScore2());
            if( game.isGameOver()) {

                if( game.getPlayerScore1() == game.getPlayerScore2())
                    JOptionPane.showMessageDialog(null,"It's a draw!","Game Over!", JOptionPane.PLAIN_MESSAGE );

                else if( game.getPlayerScore1() < game.getPlayerScore2())
                    JOptionPane.showMessageDialog(null,"Winner is " + u2+"!","Game Over!",JOptionPane.PLAIN_MESSAGE );

                else if( game.getPlayerScore1() > game.getPlayerScore2())
                    JOptionPane.showMessageDialog(null,"Winner is " + u1+"!","Game Over!",JOptionPane.PLAIN_MESSAGE );

            }

            if( game.getTurn()==1) {
                user2.setForeground(Color.BLACK);
                user1.setForeground(Color.RED);
            }
            else if( game.getTurn()==2) {
                user2.setForeground(Color.RED);
                user1.setForeground(Color.BLACK);
            }

        }

        //empty methods not to get error for overwriting
        public void mouseClicked(MouseEvent e) { }
        public void mouseEntered(MouseEvent e) { }
        public void mouseExited(MouseEvent e) { }
        public void mouseReleased(MouseEvent e) { }
    }

}
