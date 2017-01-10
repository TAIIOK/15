package barley_break;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;


public class GamePanel extends JFrame {
    
    private JPanel fieldPanel = new JPanel();
    


    private JMenuBar menu = null;
    private final String fileItems[] = new String []{"New", "Exit"};
    
    private final int CELL_SIZE = 50;
    private final int TITLE_HEIGHT = 40;
    
    private GameModel _model = new GameModel();

    public GamePanel() {
        super();

        this.setTitle("");

        
        // Меню
        createMenu();
        setJMenuBar(menu);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Box mainBox = Box.createVerticalBox();


        // Игровое поле
        mainBox.add(Box.createVerticalStrut(10));
        fieldPanel.setDoubleBuffered(true);
        _model.start();
        createField();
        setEnabledField(false);
        mainBox.add(fieldPanel);
        
        setContentPane(mainBox);
        pack();
        setResizable(false);
    }
    

// --------------------------- Отрисовываем поле ------------------------------    
    
    private void createField(){
        
        fieldPanel.setDoubleBuffered(true);
        fieldPanel.setLayout(new GridLayout(_model.field().height(), _model.field().width()));
        
        Dimension fieldDimension = new Dimension(CELL_SIZE*_model.field().height(), CELL_SIZE*_model.field().width());
        
        fieldPanel.setPreferredSize(fieldDimension);
        fieldPanel.setMinimumSize(fieldDimension);
        fieldPanel.setMaximumSize(fieldDimension);

        repaintField();
    }
    
    public void repaintField() {
        
        fieldPanel.removeAll();

        List<Cell> cells =  _model.field().getCells();

        for (int row = 1; row <= _model.field().height(); row++) 
        {

            for (int col = 1; col <= _model.field().width(); col++) 
            {
                Cell temp = getCell(cells , col , row);

                if(temp != null) {

                    try {
                        Image img = ImageIO.read(getClass().getResource(temp.image));
                        Image newimg = img.getScaledInstance(60, 55,  Image.SCALE_SMOOTH);
                        JButton button = new JButton(temp.label().getNumber(),new ImageIcon( newimg ));
                        button.setFocusable(false);

                        if (temp instanceof  BrokenCell)
                        {
                            button.setEnabled(false);
                        }
                        button.setHorizontalTextPosition(SwingConstants.CENTER);

                        fieldPanel.add(button);
                        button.addActionListener(new ClickListener());
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }


                }

            }
        }

        fieldPanel.validate();
    }

    public Cell getCell(List<Cell> cells , double x , double y)
    {
        for (Cell item : cells) {
            if (item.position().getX() == x &&  item.position().getY() == y) {
                return item;
            }
        }
        return  null;
    }
    private Point buttonPosition(JButton btn){
        
        int index = 0;
        for(Component widget: fieldPanel.getComponents())
        {
            if(widget instanceof JButton)
            {
                if(btn.equals((JButton)widget))
                {
                    break;
                }
                
                index++;
            }
         }
        
        int fieldWidth = _model.field().width();
        return new Point(index%fieldWidth + 1, index/fieldWidth + 1);
    }
        
   private JButton getButton(Point pos) {

       int index = _model.field().width()*(pos.y-1) + (pos.x-1);
       
        for(Component widget: fieldPanel.getComponents())
        {
            if(widget instanceof JButton)
            {
                if(index == 0)
                {
                    return (JButton)widget;
                }
                index--;
            }
         }
        
        return null;
    }


    
    private void setEnabledField(boolean on){

        Component comp[] = fieldPanel.getComponents();
        for(Component c : comp)
        {    c.setEnabled(on);   }
    }
    
// ----------------------------- Создаем меню ----------------------------------  
    
    private void createMenu() {
 
        menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        for (int i = 0; i < fileItems.length; i++) {
           
            JMenuItem item = new JMenuItem(fileItems[i]);
            item.setActionCommand(fileItems[i].toLowerCase());
            item.addActionListener(new NewMenuListener());
            fileMenu.add(item);
        }
        fileMenu.insertSeparator(1);

        menu.add(fileMenu);
    }

    public class NewMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("exit".equals(command)) {
                System.exit(0);
            }
            if ("new".equals(command)) {
                _model.start();
                _model.shuffleField();
                createField();

            }  
        }
    }
    
// ------------------------- Реагируем на действия игрока ----------------------

    private boolean determinateWin()
    {
        int start = 1;
        for (int row = 1; row <= _model.field().height(); row++) {

            for (int col = 1; col <= _model.field().width(); col++) {
                Point position = new Point(col,row);
                String number = _model.field().cell(position).label().getNumber();
                if(number.isEmpty())
                {
                    if(col != 4 & row != 4)
                    return false;
                }
                else if(start != Integer.parseInt(number)) {
                    return false;
                }
                start++;

            }
        }

        return true;
    }

    private class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           
            JButton button = (JButton) e.getSource();

            
            // Ставим на поле метку текущего игрока
            Point p = buttonPosition(button);


            if(_model.field().move(p))
            {
                repaintField();
                if(determinateWin())
                {
                    JOptionPane.showMessageDialog(null, "Вы выиграли", "Победа!", JOptionPane.PLAIN_MESSAGE);
                    setEnabledField(false);
                }
            }

          //  _model.activePlayer().setLabelTo(p);
        }
    }
    

}
