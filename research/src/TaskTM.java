//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей
import java.io.*;
import java.util.ArrayList;

public class TaskTM {
    //метод ветвей и границ, реализованный в одном классе

    public int number;
    //момент поступления
    public int r;
    //продолжительность
    public int p;
    // директивный срок
    public int d;
    //время, когда закончится на самом деле
    public int Dd;
    //разница между заданным временем и реальным
    public int Dd_d;
    //числа для штрафной функции

    public TaskTM(int r, int p, int d)
    {
        this.r = r;
        this.p = p;
        this.d = d;

        Dd = r+p;
        if (Dd>d)
            Dd_d=Dd-d;
        else
            Dd_d=0;
        number=-1;
    }
   /* public TaskTM()
    {
        this.r = 0;
        this.p = 0;
        this.d = 0;
    }*/
    public int getR() {
        return r;
    }


    public int getD() {
        return d;
    }

    public int getP() {
        return p;
    }

    public int getDd() {
        return Dd;
    }



    public void setDd(int dd) {
        Dd = dd;
        if (Dd>d)
            Dd_d=Dd-d;
        else
            Dd_d=0;
    }


    public int getDd_d() {
        return Dd_d;
    }

    @Override
    public String toString() {
        return "начало "+r+" продолжительность "+p+" ожид окончание "+d+" реальное окончание "+ Dd+" разница "+ Dd_d+" номер по порядку "+ number;

    }

    //в l хранятся все начальные требования
    public static ArrayList<TaskTM> l = new ArrayList<>();

    public static int N=1;

    public static void Creation(int r, int p, int d)
    {
        TaskTM t = new TaskTM(r,p,d);
        t.number = N;
        l.add(t);
        N++;
    }

    public static ArrayList<ArrayList<TaskTM>> all = new ArrayList<ArrayList<TaskTM>>();
    public static ArrayList<Integer> fine = new ArrayList<Integer>();


    public static String TSArray(int startTime)
    {
        String str="";

        //создаем все возможные варианты из 1 требования
        for (int i=0;i<l.size();i++) {

            ArrayList<TaskTM> lnew = new  ArrayList<TaskTM>();

            lnew.add (l.get(i));

            if (startTime>l.get(i).getR())
                lnew.get(0).setDd(l.get(i).getP() + startTime);



            //проверить совпадают или нет

            fine.add(lnew.get(0).getDd_d());
            all.add(lnew);

            //на этом этапе получаем n расписаний, где каждое расписание состоит из одного требования
        }

        if ((fine.size()==all.size())&&(fine.size()==l.size()))
            System.out.println("совпало");

        //находим минимальный штраф

        double minFine = fine.get(0);
        int numberOfMin = 0;

        for (int i = 1; i < fine.size(); i++)
            //тут пока у всех одинааковое количество
            if (minFine > fine.get(i))
            {
                minFine = fine.get(i);
                numberOfMin = i;
            }


        //делаем пока изначальное количество требований не совпадает
        //с количеством требований с минимальным штрафом
        while (all.get(numberOfMin).size()<l.size()) {
            TheChange(numberOfMin);
            numberOfMin=ChooseTheMin();

        }


        for (int i=0;i<l.size();i++) {
            str = str+all.get(numberOfMin).get(i).number+" ";
        }
        str = str + " F(П)=" + fine.get(numberOfMin);
        return str;
    }

    //нахождение минимума

    private static int ChooseTheMin () {

        double minFine = fine.get(0);
        int numberOfMin = 0;
        for (int i = 1; i < fine.size(); i++)
        {

            if (minFine > fine.get(i))
             //   if ((minFine > fine.get(i))||((minFine==fine.get(i))&&(all.get(i).size()<all.get(numberOfMin).size())))
                {
                    minFine = fine.get(i);
                    numberOfMin = i;
                }
        }
        //возвращаем номер - минимум в all
        return numberOfMin;
    }

    private static void TheChange (int numberOfMin){

        ArrayList<TaskTM> t = new ArrayList<TaskTM>();

        t.addAll(all.get(numberOfMin));

        all.remove(numberOfMin);

        fine.remove(numberOfMin);
        //находим все возможные расписания, если часть расписания уже построена

        for (int i=0;i<l.size();i++) {
            int k=0;
            for (int j=0;j<t.size();j++)
                if (l.get(i).number==t.get(j).number)
                    k++;
            if (k==0)
            {
                TaskTM tr = new TaskTM(l.get(i).getR(), l.get(i).getP(), l.get(i).getD());
                tr.number = l.get(i).number;
                all.add(ADD(tr, t));
            }
        }
    }


    //t - новый элемент, который хотим добавить в расписание
    //j -  расписание, которое будем менять. старое расписание надо сохранить!!
    public static ArrayList<TaskTM> ADD (TaskTM t, ArrayList<TaskTM> j)
    {
        TaskTM newTask = new TaskTM(t.getR(),t.getP(),t.getD());
        newTask.number = t.number;
        //будем возвращать
        ArrayList<TaskTM> newT = new ArrayList<TaskTM>();

        newT.addAll(0, j);


        if (newTask.getR()<=newT.get(newT.size()-1).getDd())
            newTask.setDd(newT.get(newT.size()-1).getDd()+newTask.getP());
        else
            newTask.setDd(newTask.getR()+newTask.getP());

        fine.add(newTask.getDd_d());

        newT.add(newTask);
        return newT;

    }


    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }

    public static void read(String fileName) throws FileNotFoundException {
        //TaskSequence a = new TaskSequence();
        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);
        exists(fileName);

        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                //В цикле по буквам построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    int k=0;
                    String n1="";
                    String n2="";
                    String n3="";
                    //  String n4="";
                    for (int i=0;i<s.length();i++) {
                        if ((s.charAt(i) ==';')||(s.charAt(i) ==' '))
                            k++;
                        else {
                            if (k == 0)
                                n1 = n1 + "" + s.charAt(i);
                            if (k == 1)
                                n2 = n2 + "" + s.charAt(i);
                            if (k == 2)
                                n3 = n3 + "" + s.charAt(i);

                        }

                    }
                    System.out.println(n1+" "+n2+" "+n3);
                    TaskTM.Creation(Integer.parseInt(n1),Integer.parseInt(n2),Integer.parseInt(n3));
                  //  System.out.println(TaskTM.l.get(l.size()-1).number);
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Возвращаем полученный текст с файла
    }





    public static void main(String args[])
    {
        //TaskTM Sequence = new TaskSequence();
        try {
            TaskTM.read("C:\\a.txt");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.print("Не удалось считать!!");
        }
        System.out.println("Hello, World!");
       // ArrayList<TaskSequence> all = new ArrayList<TaskSequence>();
        String str = ""+ TaskTM.TSArray(0)+"\n";
        System.out.println(str);
    }
}
