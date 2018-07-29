/**
 * Created by User on 30.04.2017.
 * из файла
 */
//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonFrame1 extends JFrame
{  public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 300;

    TaskSequence Sequence;
    ButtonFrame b;
    JLabel makeLabel()
    {

        JLabel label = new JLabel();
        Font font = new Font("Verdana", Font.PLAIN, 14);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setText("Bведите адрес файла, с которого нужно считать данные");
        label.setBounds(40,0,600,30);
        return label;
    }

    JTextField makeTextFields(String txt)
    {
        JTextField a = new JTextField(txt, 20);
        a.setBounds(190,40,100,30);
        a.setEditable(true);
        return a;

    }
    JButton makeButton(String name, JTextField a1, ButtonFrame frame, String str, int x, int y, int width, int height)
    {
        JButton button = new JButton (name);
        button.setBounds(x, y, width, height);
        button.addActionListener(new ActionListener ()
        { public void actionPerformed (ActionEvent event)
        {
            String str="";
            if (name=="Считать") {
                try {
                    Sequence=FileWorker.read(a1.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.print("Не удалось считать!!");
                }



                TaskSequence SequenceTSArray = new TaskSequence();
                for (int i=0;i<Sequence.l.size();i++){
                    SequenceTSArray.Create(Sequence.l.get(i).getR(),Sequence.l.get(i).getP(),Sequence.l.get(i).getD());
                }

                ArrayList<Integer> seq = new ArrayList<Integer>();
                for (int i=0;i<Sequence.l.size();i++){
                    seq.add(0,Sequence.l.get(i).getP());
                }
                TaskSequenceKursovaia Seq = new TaskSequenceKursovaia(0);
                for (int k=0;k<Sequence.l.size();k++)
                    Seq.Create(Sequence.l.get(k).getR(),Sequence.l.get(k).getP(),Sequence.l.get(k).getD(),k);
                TaskSequenceKursovaia.Maximize(Seq.max,0,Seq.max.size()-1);


                //str = "Метод ветвей и границ: "+ TaskSequenceArray.TSArray(0,SequenceTSArray.l.size(),SequenceTSArray)+"\n";
                str = str + " Метод из курсовой работы: " +TaskSequenceArrayKursovaia.TSArray(Seq.l.size(),Seq)+ "\n";
                str = str + " Алгоритм 3: "+ TaskSequenceArray.Alg3(seq, TaskSequenceArray.NewAlgPrer(0,Sequence.l.size(),Sequence))+ "\n";
                setVisible(false);
                ButtonFrame3 frame1 = new ButtonFrame3(frame,str);
                frame1.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
                frame1.setVisible(true);
            }
            if (name=="Анализ") {
                try {
                    FileAnalize.ReadingAnalizeWhiriting("C://b.txt");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.print("Файл не найден!!");
                }
                JOptionPane.showMessageDialog(null, "Данные проанализированы и добавлены в файл");
            }
            if (name=="Выгрузить"){
                try {
                    Sequence=FileWorker.read(a1.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.print("Не удалось считать!!");
                }


               // TaskSequence SequenceTSArray = new TaskSequence();
                //for (int i=0;i<Sequence.l.size();i++){
                 //   SequenceTSArray.Create(Sequence.l.get(i).getR(),Sequence.l.get(i).getP(),Sequence.l.get(i).getD());
                //}

                //str=TaskSequenceArray.TSArray(0,SequenceTSArray.l.size(),SequenceTSArray);
               // str=TaskTM.TSArray(0);

                ArrayList<Integer> seq = new ArrayList<Integer>();
                for (int i=0;i<Sequence.l.size();i++){
                    seq.add(0,Sequence.l.get(i).getP());
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
                JOptionPane.showMessageDialog(null, "Данные добавлены в файл");

            }
            if (name=="Закончить") {
                setVisible(false);
                frame.setVisible(true);
            }
        }
        });
        return button;
    }
    public ButtonFrame1(ButtonFrame frame)
    {
        setTitle("Дипломная работа");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);


       // setContentPane(new JLabel(new ImageIcon("C://Users//User//Pictures/ad.jpg")));
        setLayout(null);


        add(makeLabel());
        JTextField a1 = makeTextFields("C:\\a.txt");
        String str="";
        add(a1);
        add( makeButton("Считать",a1, frame, str,120,80,100,30));
        add(makeButton("Выгрузить", a1, frame, str,240,80,100,30));
        add(makeButton("Анализ", a1, frame, str,120,120,100,30));
        add(makeButton("Закончить", a1, frame, str,240,120,100,30));



    }
}


