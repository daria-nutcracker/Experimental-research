//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей

import javax.swing.*;
import java.awt.*;


public class Display
{
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        EventQueue.invokeLater(new Runnable()
        {public void run()
        {
            ButtonFrame frame = new ButtonFrame();
            frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            }
        });
    }
}
