# Obrazek
Program rysuje obrazek fraktalo podobny wykorzystując jave oraz swing i awt.
Obrazek tworzony jest z kół które są rysowane przy wykorzystaniu funkcji rekurencyjnej która rysuje koła w tym samym kolorze co poprzednie ale zmniejsza je i odpowiednio przesuwa.
Od każdego koła są rysowane 2 dodatkowe jedno po prawej stronie drugie po lewej koła, mają one zmniejszony promień o połowę i środek na granicy poprzedniego koła.
Następnie program rysuje koła w takim samym schemacie tylko w górę i w dół od głównego koła.

Dodatkowo wszystkie koła zmieniają stopniowo kolor wypełnienia według zapętlonego schematu. 


Opis kodu:

klasa surface

przyjmuje jako argumenty obiekt klasy Graphics potrzebny do rysowania oraz zmienne x i y które są miejscem rysowania koła, r jest promieniem koła zaś  k1,k2,k3 są odpowiednio wartościami składników kolor rysowanego koła. Zadaniem funkcji jest narysowanie kół w poziomie poprzez rekurencyjne wywołanie funkcji przesuwając pozycję kół zmniejszając ich promień.

       private void maluj_w_poziomie(Graphics g, int x, int y, int r,int k1,int k2,int k3){
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
 
 ta funkcja działa tak samo jak poprzednia tylko zamiast rozmieszczać koła po poziomie to rozmieszcza je w pionie
 
     private void maluj_w_pionie(Graphics g, int x, int y, int r,int k1,int k2,int k3){
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

funkcja paint odpowiada za czyszczenie ekranu oraz wywołanie obu funkcji rysujących rekurencyjnie koła

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,500,500);

        maluj_w_poziomie(g,w,h,200,k1,k2,k3);
        maluj_w_pionie(g,w,h,200,k1,k2,k3);

        g.dispose();
    }

funkcja actionPerformed obsługuje zdarzenia, zawiera w sobie zegar i cały czas zmienia wartości zmiennych k1,k2,k3 aby rysowane kółka zmieniały na bieżąco kolor.

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


W klasie Main
Ustawienie parametrów dla okna

        private void initUI() {

        add(new surface());

        setTitle("rys");
        setSize(525, 525);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
 Wywołanie okna w main
 
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Main ex = new Main();
                ex.setVisible(true);
            }
        });
