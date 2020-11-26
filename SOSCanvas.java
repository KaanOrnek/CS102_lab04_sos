import javax.swing.*;
import cs101.sosgame.*;

import java.awt.*;

public class SOSCanvas extends JPanel {
    private final int LENGTH_OF_EACH_SQUARE = 60;
    private final int XTOP = 30;
    private final int YTOP = 30;
    private SOS game;

    private int boardSize;

    public SOSCanvas( SOS game) {
        boardSize = game.getDimension();
        this.game = game;
        this.setPreferredSize(new Dimension( this.getPreferableFrameWidth(),this.getPreferableFrameWidth()));
    }

    @Override
    public void paintComponent(Graphics g) {

        int xLine=XTOP;
        int yLine=YTOP;

        for( int i = 0; i <= boardSize; i++)
        {
            g.drawLine(XTOP,yLine,LENGTH_OF_EACH_SQUARE*boardSize+XTOP,yLine);
            yLine = yLine+LENGTH_OF_EACH_SQUARE;
        }

        for( int k = 0; k <= boardSize; k++)
        {
            g.drawLine(xLine,YTOP,xLine,LENGTH_OF_EACH_SQUARE*boardSize+YTOP);
            xLine = xLine+LENGTH_OF_EACH_SQUARE;
        }

        g.setFont( new Font( "TimesRoman", Font.PLAIN,30));
        for(int a = 0; a < boardSize; a++){
            for( int b = 0; b < boardSize; b++){

                if( game.getCellContents(a,b) == 's'){
                    g.setColor(Color.orange);
                    g.drawString("S",LENGTH_OF_EACH_SQUARE*(b+1)-10,LENGTH_OF_EACH_SQUARE*(a+1)+10);
                }
                if( game.getCellContents(a,b) == 'o'){
                    g.setColor(Color.GREEN);
                    g.drawString("O",LENGTH_OF_EACH_SQUARE*(b+1)-10,LENGTH_OF_EACH_SQUARE*(a+1)+10);
                }
            }
        }
    }
    public int getPreferableFrameWidth(){
        return 60+(LENGTH_OF_EACH_SQUARE*boardSize);
    }
    public int getLengthOfSquares(){
        return LENGTH_OF_EACH_SQUARE;
    }
    public int getGap(){
        return XTOP;
    }
    public void played(){
        repaint();
    }
}

