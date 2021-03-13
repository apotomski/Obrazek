package rysunek;
import java.awt.EventQueue;
import javax.swing.JFrame;
public class Main extends JFrame{

    public Main() {

        initUI();
    }

    private void initUI() {

        add(new surface());

        setTitle("rys");
        setSize(525, 525);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Main ex = new Main();
                ex.setVisible(true);
            }
        });
    }
}
