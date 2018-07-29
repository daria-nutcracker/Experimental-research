/**
 * Created by User on 30.04.2017.
 */
//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonFrame extends JFrame
{  public static final int DEFAULT_WIDTH = 390;
    public static final int DEFAULT_HEIGHT = 300;

    JLabel makeLabel(String txt, int x, int y)
    {
        JLabel label = new JLabel();
        Font font = new Font("Verdana", Font.PLAIN, 14);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setText(txt);
        label.setBounds(x,y,300,30);
        return label;
    }
    JButton makeButton(String name,int x, int y, int width, int height)
    {
        JButton button = new JButton (name);
        button.setBounds(x, y, width, height);

        //тут должно все считываться
        ButtonFrame f=this;
        button.addActionListener(new ActionListener ()
        { public void actionPerformed (ActionEvent event)
        {

            setVisible(false);
            if (name == "Считать с файла")
            {
                ButtonFrame1 frame1 = new ButtonFrame1(f);
                frame1.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
                frame1.setVisible(true);
            }
            if (name == "Вводить самому")
            {
                ButtonFrame2 frame2 = new ButtonFrame2(f);
                frame2.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
                frame2.setVisible(true);
            }
            if (name == "Сгенерировать файл")
            {
                ButtonFrameCreationFile frame1 = new ButtonFrameCreationFile(f);
                frame1.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
                frame1.setVisible(true);
            }
        }
        });
        return button;
    }
    public ButtonFrame()
    {
        setTitle("Дипломная работа");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
       // mainPanel = new JPanel();
       // mainPanel.setBackground(Color.WHITE);
       // setContentPane(new JLabel(new ImageIcon("C://Users//User//Pictures/b3.jpg")));
        setLayout(null);
        add(makeLabel("Будете вводить данные?",100,0));
        add(makeLabel("Или можете создать файл",100,100));
        add(makeButton("Сгенерировать файл",110,150,170,30));
        add(makeButton("Считать с файла",20,50,150,30));
        add(makeButton("Вводить самому",200,50,150,30));
       // add(mainPanel);

    }

}
