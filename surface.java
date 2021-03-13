package rysunek;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class surface extends JPanel implements ActionListener {
    private int w=150,h=150;
    private int k1 = 0,k2 = 0,k3 = 0;
    private Timer timer;
    public surface() {
        timer = new Timer(20, this);
        timer.start();
    }

    private void maluj_w_poziomie(Graphics g, int x, int y, int r,int k1,int k2,int k3) {
        Graphics2D g2d = (Graphics2D) g;
        float alpha2 = 10 * 0.1f;//     wyłączenie przezroczystosci od{
        AlphaComposite alcom2 = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha2);
        g2d.setComposite(alcom2);//         }do
        g2d.setPaint(Color.black);
        Shape kolo = new Ellipse2D.Double(x, y, r, r);//rysowanie obramówki
        g2d.draw(kolo);
        g2d.setColor(new Color(k1, k2, k3));
        float alpha = 3 * 0.1f;//   ustawienie przezroczystosci od{
        AlphaComposite alcom = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alcom);//          }do
        g2d.fillOval(x, y, r, r);//     rysowanie kolorowego kola
        if(r > 1) {//   rekurencyjne wywolanie rysoania kolejnych kulek
            maluj_w_poziomie(g,x + r - r / 4, y + r / 4, r / 2,k1,k2,k3);
            maluj_w_poziomie(g,x - r / 2 + r / 4 , y + r / 4, r / 2,k1,k2,k3);
        }
    }
    private void maluj_w_pionie(Graphics g, int x, int y, int r,int k1,int k2,int k3) {
            Graphics2D g2d = (Graphics2D) g;
            float alpha2 = 10 * 0.1f;//     wyłączenie przezroczystosci od{
            AlphaComposite alcom2 = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, alpha2);
            g2d.setComposite(alcom2);//         }do
            g2d.setPaint(Color.black);
            Shape kolo = new Ellipse2D.Double(x, y, r, r);//rysowanie obramówki
            g2d.draw(kolo);
            g2d.setColor(new Color(k1, k2, k3));
            float alpha = 3 * 0.1f;//   ustawienie przezroczystosci od{
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(alcom);//          }do
            g2d.fillOval(x, y, r, r);//     rysowanie kolorowego kola
            if (r > 1) {//   rekurencyjne wywolanie rysoania kolejnych kulek
                maluj_w_pionie(g, x + r / 4, y + r - r / 4, r / 2,k1,k2,k3);
                maluj_w_pionie(g, x + r / 4, y - r / 2 + r / 4, r / 2,k1,k2,k3);
            }

    }
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,500,500);

        maluj_w_poziomie(g,w,h,200,k1,k2,k3);
        maluj_w_pionie(g,w,h,200,k1,k2,k3);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(k1 < 250 && k2 == 0 && k3 == 0) k1 += 2;
        else if(k1 == 250 && k2 < 250 && k3 == 0) k2 += 2;
        else if(k1 > 0 && k2 == 250 && k3 == 0) k1 -= 2;
        else if(k1 == 0 && k2 == 250 && k3 < 250) k3 += 2;
        else if(k1 == 0 && k2 > 0 && k3 == 250) k2 -= 2;
        else if(k1 < 250 && k2 == 0 && k3 == 250) k1 += 2;
        else if(k1 == 250 && k2 == 0 && k3 > 0) k3 -= 2;





        repaint();
    }

}
