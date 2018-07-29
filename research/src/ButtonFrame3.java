//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 23.05.2017.
 */
public class ButtonFrame3 extends JFrame
{  public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 200;

    JLabel makeLabel(String str)
    {
        JLabel label = new JLabel();
        String[] strArr=str.split("\\n");
        for (int i=0;i<strArr.length;i++)
        label.setText(str + "\r\n");
        Font font = new Font("Verdana", Font.PLAIN, 14);
        label.setFont(font);
        label.setForeground(Color.BLACK);
        label.setBounds(0,0,1000,30);
        return label;
    }
    JButton makeButton(String name, ButtonFrame frame, int x, int y, int width, int height)
    {
        JButton button = new JButton (name);
        button.setBounds(x, y, width, height);

        button.addActionListener(new ActionListener()
        { public void actionPerformed (ActionEvent event)
        {
            if (name=="Закончить") {
                setVisible(false);
                frame.setVisible(true);
            }
        }
        });
        return button;
    }
    public ButtonFrame3(ButtonFrame frame, String str)
    {

        setTitle("Результат");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //setContentPane(new JLabel(new ImageIcon("C://Users//User//Pictures//Новая папка (3)/IMG_20170722_182808.jpg")));
        setLayout(null);
        add(makeLabel(str));
        add(makeButton("Закончить", frame, 287, 132, 100, 30));


    }

}
