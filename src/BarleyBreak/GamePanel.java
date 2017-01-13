package BarleyBreak;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import BarleyBreak.Bones.*;

public class GamePanel extends JFrame {
    
    private JPanel fieldPanel = new JPanel();
    private JMenuBar menu = null;
    private final String fileItems[] = new String []{"New", "Exit"};
    
    private final int CELL_SIZE = 50;
    private final int TITLE_HEIGHT = 40;
    
    private GameModel _model = new GameModel();

    public GamePanel() {
        super();

        this.setTitle("Пятнашки");

        
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

        List<Bone> bones =  _model.field().getBones();

        for (int row = 1; row <= _model.field().height(); row++) 
        {

            for (int col = 1; col <= _model.field().width(); col++) 
            {
                Bone temp = getBone(bones , col , row);

                if(temp != null) {

                    try {

                        Image img = ImageIO.read(getClass().getResource(temp.GetImage()));
                        Image newimg = img.getScaledInstance(60, 55,  Image.SCALE_SMOOTH);
                        JButton button = new JButton();
                        if(temp instanceof  EmptyBone) {
                             button.setIcon(new ImageIcon(newimg));
                        }
                        else {
                            if(temp instanceof  SimpleBone )   {
                                button = new JButton(((SimpleBone) temp).getLabel(), new ImageIcon(newimg));
                            }
                            if(temp instanceof  FixedBone )   {
                                button = new JButton(((FixedBone) temp).getLabel(), new ImageIcon(newimg));
                            }
                            if(temp instanceof  StickyBone )   {
                                button = new JButton(((StickyBone) temp).getLabel(), new ImageIcon(newimg));
                            }

                        }
                        button.setFocusable(false);

                        if (temp instanceof  FixedBone ||  temp instanceof  EmptyBone)
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

    public Bone getBone(List<Bone> bones , double x , double y)
    {
        for (Bone item : bones) {
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
                Bone current = _model.field().bone(position);//.label().getNumber();
                int Number = -1;
                if(current instanceof  EmptyBone)
                {
                    if(col != 4 & row != 4)
                    return false;
                }
                else  {
                    if (current instanceof  FixedBone)
                    {
                        Number =  Integer.parseInt(((FixedBone ) current).getLabel());
                    }

                    if (current instanceof  SimpleBone)
                    {
                        Number =  Integer.parseInt(((SimpleBone ) current).getLabel());
                    }

                    if (current instanceof  StickyBone)
                    {
                        Number =  Integer.parseInt(((StickyBone ) current).getLabel());
                    }

                    if(start != Number )
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

            Point p = buttonPosition(button);

            if(_model.field().move(p))
            {
                repaintField();
                if(determinateWin())
                {
                    declareVictory();
                }
            }else
            {
                reportCanNotBeMoved();
            }

        }
    }
    
    private void declareVictory(){
        JOptionPane.showMessageDialog(null, "Вы выиграли", "Победа!", JOptionPane.PLAIN_MESSAGE);
        setEnabledField(false);
    }
    
    private void reportCanNotBeMoved(){
        JOptionPane.showMessageDialog(null, "Кость не может быть перемещена!", "Упс!", JOptionPane.PLAIN_MESSAGE);
    }
    

}
