//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by User on 05.06.2018.
 */
public class ButtonFrameCreationFile extends JFrame  {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 400;
    TaskSequence Sequence;
    ButtonFrame b;



    JTextField makeTextFields(String txt, int x, int y, int width, int height)
    {
        JTextField a = new JTextField(txt, 20);
        a.setBounds(x,y,width,height);
        a.setEditable(true);
        return a;

    }
    JButton makeButton(String name, JTextField a1, JTextField a2, JTextField a3, JTextField a4, JTextField a5, JTextField a6, JTextField a7, JTextField a8, ButtonFrame frame, String str, int x, int y, int width, int height)
    {
        JButton button = new JButton (name);
        button.setBounds(x, y, width, height);
        //тут должно все считываться
        button.addActionListener(new ActionListener()
        { public void actionPerformed (ActionEvent event)
        {
            String str="";
            if (name=="Создать файл") {
                try {
                    WorkInFile.write(a1.getText(), Integer.parseInt(a2.getText()),Integer.parseInt(a3.getText()), Integer.parseInt(a6.getText()), Integer.parseInt(a7.getText()), Integer.parseInt(a4.getText()), Integer.parseInt(a5.getText()), Integer.parseInt(a8.getText()));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.print("Не число!");
                }
                JOptionPane.showMessageDialog(null, "Файл создан");
            }
            if (name=="Сгенерировать 50 результатов") {
                for (int i=0;i<50;i++)
                {
                try {
                    WorkInFile.write(a1.getText(), Integer.parseInt(a2.getText()),Integer.parseInt(a3.getText()), Integer.parseInt(a6.getText()), Integer.parseInt(a7.getText()), Integer.parseInt(a4.getText()), Integer.parseInt(a5.getText()), Integer.parseInt(a8.getText()));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.print("Не число!");
                }
                //JOptionPane.showMessageDialog(null, "Файл создан");

                    try {
                        Sequence=FileWorker.read(a1.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.print("Не удалось считать!!");
                    }


                    TaskSequence SequenceTSArray = new TaskSequence();
                    for (int j=0;j<Sequence.l.size();j++){
                        SequenceTSArray.Create(Sequence.l.get(j).getR(),Sequence.l.get(j).getP(),Sequence.l.get(j).getD());
                    }

                    //str=TaskSequenceArray.TSArray(0,SequenceTSArray.l.size(),SequenceTSArray);
                    str = TaskTM.TSArray(0);
                    ArrayList<Integer> seq = new ArrayList<Integer>();
                    for (int j=0;j<Sequence.l.size();j++){
                        seq.add(0,Sequence.l.get(j).getP());
                    }
                    TaskSequenceKursovaia Seq = new TaskSequenceKursovaia(0);
                    for (int k=0;k<Sequence.l.size();k++)
                        Seq.Create(Sequence.l.get(k).getR(),Sequence.l.get(k).getP(),Sequence.l.get(k).getD(),k);
                    TaskSequenceKursovaia.Maximize(Seq.max,0,Seq.max.size()-1);
                    String str3=TaskSequenceArrayKursovaia.TSArray(Seq.l.size(),Seq);
                    int kolvo = Sequence.l.size();
                    String str2 = TaskSequenceArray.Alg3(seq, TaskSequenceArray.NewAlgPrer(0,Sequence.l.size(),Sequence));

                    String filename="C://b.txt";
                    WriteToFile.write(filename,str,str2,str3,kolvo);
                }
                JOptionPane.showMessageDialog(null, "Данные сгенерированы, результат получен");
            }

            if (name=="Закончить") {
                setVisible(false);
                frame.setVisible(true);
            }
        }
        });
        return button;
    }
    public ButtonFrameCreationFile(ButtonFrame frame)
    {
        setTitle("Дипломная работа");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
       // setContentPane(new JLabel(new ImageIcon("C://Users//User//Pictures/lKoY59CoEFo.jpg")));
        setLayout(null);
       // mainPanel = new JPanel();
        //mainPanel.setBackground(Color.BLUE);
        JTextField a1 = makeTextFields("C:\\a.txt",5,10,250,20);
        JTextField a2 = makeTextFields("Минимальное время поступления",5,40,250,20);
        JTextField a3 = makeTextFields("Максимальное время поступления",5,70,250,20);
        JTextField a4 = makeTextFields("Минимальная длительность",5,100,250,20);
        JTextField a5 = makeTextFields("Максимальная длительность",5,130,250,20);
        JTextField a6 = makeTextFields("Минимальный директивный срок",5,160,250,20);
        JTextField a7 = makeTextFields("Максимальный директивный срок",5,190,250,20);
        JTextField a8 = makeTextFields("Количество требований",5,220,250,20);
        add(a1);
        add(a2);
        add(a3);
        add(a4);
        add(a5);
        add(a6);
        add(a7);
        add(a8);
        String str="";
        add(makeButton("Создать файл",a1,a2,a3,a4,a5,a6,a7,a8, frame,  str,5,250,120,30));
       // add(makeButton("Сгенерировать 50 результатов",a1,a2,a3,a4,a5,a6,a7,a8, frame,  str,5,350,120,30));
        add(makeButton("Закончить", a1,a2,a3,a4,a5,a6,a7,a8, frame,  str,145,250,110,30));
        //add(mainPanel);

    }
}
