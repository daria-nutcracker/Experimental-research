//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonFrame2 extends JFrame {
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 300;
    private JPanel mainPanel;
    TaskSequence Sequence;

    JTextField makeTextFields(String txt,int x, int y, int width, int height ) {
        JTextField a = new JTextField(txt, 20);
        a.setBounds(x,y,width,height);
        a.setEditable(true);
        //mainPanel.add(a);
        return a;
    }

    JButton makeButton(String name, ButtonFrame frame, JTextField a1, JTextField a2, JTextField a3, String str, int x, int y, int width, int height ) {
        JButton button = new JButton(name);
        button.setBounds(x, y, width, height);
        //mainPanel.add(button);
        //тут должно все считываться
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String str="";
                if (name == "Считать")
                {
                    try {
                        Sequence.Create(Integer.parseInt(a1.getText()), Integer.parseInt(a2.getText()), Integer.parseInt(a3.getText()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.print("Не число!");
                    }
                }
                if (name=="Закончить")
                {
                    setVisible(false);
                    frame.setVisible(true);
                }
                if (name=="Алгоритм ветвей и границ")
                {

                    str="Алгоритм ветвей и границ: "+ TaskSequenceArray.TSArray(0,Sequence.l.size(),Sequence);
                    setVisible(false);
                    ButtonFrame3 frame1 = new ButtonFrame3(frame,str);
                    frame1.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
                    frame1.setVisible(true);
                }
                if (name=="Алгоритм 2")
                {

                    TaskSequenceKursovaia Seq = new TaskSequenceKursovaia(0);
                    for (int k=0;k<Sequence.l.size();k++)
                        Seq.Create(Sequence.l.get(k).getR(),Sequence.l.get(k).getP(),Sequence.l.get(k).getD(),k);
                    TaskSequenceKursovaia.Maximize(Seq.max,0,Seq.max.size()-1);
                    str="Алгоритм 2"+ TaskSequenceArrayKursovaia.TSArray(Seq.l.size(),Seq);
                    setVisible(false);
                    ButtonFrame3 frame1 = new ButtonFrame3(frame,str);
                    frame1.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
                    frame1.setVisible(true);
                }
                if (name=="Алгоритм с прерываниями")
                {

                    TaskSequence result = TaskSequenceArray.NewAlgPrer(0,Sequence.l.size(),Sequence);
                    for (int i = 0;i<result.l.size();i++)
                        str = str+ " Алгоритм с прерываниями " + result.l.get(i).number;
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
                if (name=="Алгоритм 3")
                {
                    ArrayList<Integer> seq = new ArrayList<Integer>();
                    for (int i=0;i<Sequence.l.size();i++){
                        seq.add(0,Sequence.l.get(i).getP());
                        //str = str+ " "+ Sequence.l.get(i).getP();
                    }
                    str=str+" Алгоритм 3 "+ TaskSequenceArray.Alg3(seq, TaskSequenceArray.NewAlgPrer(0,Sequence.l.size(),Sequence));
                    setVisible(false);
                    ButtonFrame3 frame1 = new ButtonFrame3(frame,str);
                    frame1.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
                    frame1.setVisible(true);
                }
                if (name=="Выгрузить")
                {

                    TaskSequence SequenceTSArray = new TaskSequence();
                    for (int i=0;i<Sequence.l.size();i++){
                        SequenceTSArray.Create(Sequence.l.get(i).getR(),Sequence.l.get(i).getP(),Sequence.l.get(i).getD());
                    }

                   str= TaskSequenceArray.TSArray(0,SequenceTSArray.l.size(),SequenceTSArray);

                    ArrayList<Integer> seq = new ArrayList<Integer>();
                    for (int i=0;i<Sequence.l.size();i++){
                        seq.add(0,Sequence.l.get(i).getP());
                    }
                    TaskSequenceKursovaia Seq = new TaskSequenceKursovaia(0);
                    for (int k=0;k<Sequence.l.size();k++)
                        Seq.Create(Sequence.l.get(k).getR(),Sequence.l.get(k).getP(),Sequence.l.get(k).getD(),k);
                    TaskSequenceKursovaia.Maximize(Seq.max,0,Seq.max.size()-1);
                    String str3= TaskSequenceArrayKursovaia.TSArray(Seq.l.size(),Seq);
                    int kolvo = Sequence.l.size();
                    String str2 = TaskSequenceArray.Alg3(seq, TaskSequenceArray.NewAlgPrer(0,Sequence.l.size(),Sequence));

                   String filename="C://b.txt";
                    WriteToFile.write(filename,str,str2,str3, kolvo);
                    JOptionPane.showMessageDialog(null, "Данные добавлены в файл");

                }
            }
        });
        return button;
    }

    public ButtonFrame2(ButtonFrame frame) {
       Sequence = new TaskSequence();
        setTitle("Дипломная работа");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      //  setContentPane(new JLabel(new ImageIcon("C://Users//User//Pictures/60R6ZhxKAz0.jpg")));
        setLayout(null);

       // mainPanel = new JPanel();
       //mainPanel.setBackground(Color.YELLOW);
        //   JTextField[] a = new JTextField[3];
        JTextField a1 = makeTextFields("Время поступления",5,10,205,25);
        JTextField a2 = makeTextFields("Длительность",5,50,205,25);
        JTextField a3 = makeTextFields("Желательное время завершения",5,90,205,25);
        add(a1);
        add(a2);
        add(a3);
        String str="";
        add(makeButton("Считать", frame, a1, a2, a3,  str,5,140,100,30));
        add(makeButton("Алгоритм ветвей и границ", frame, a1, a2, a3,  str,300,10,200,30));
        add(makeButton("Алгоритм 1", frame, a1, a2, a3,  str,300,50,200,30));
        add(makeButton("Алгоритм с прерываниями", frame, a1, a2, a3,  str,300,90,200,30));
        add(makeButton("Алгоритм 2", frame, a1, a2, a3,  str,300,130,200,30));
        add(makeButton("Выгрузить", frame, a1, a2, a3,  str,110,140,100,30));
        add(makeButton("Анализ", frame, a1, a2, a3,  str,5,180,100,30));
        add(makeButton("Закончить", frame, a1, a2, a3,  str,110,180,100,30));
       // add(mainPanel);
    }
}
