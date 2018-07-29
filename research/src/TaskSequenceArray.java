//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей
import java.util.ArrayList;

public class TaskSequenceArray {

    //всевозможные варианты
  private static ArrayList<TaskSequence> all;
private static int time;

    private static int N;

   public static String TSArray(int startTime, int n, TaskSequence s)
    {
        //заведем переменную, которая будет считать количество итераций
        int kolvoIter=0;

        all = new ArrayList<TaskSequence>();

        String str="";

        //создаем все возможные варианты из 1 требования
        for (int i=0;i<s.l.size();i++) {

            TaskSequence t = new TaskSequence();

            t.l.add (s.l.get(i));
            //все остальные требования записываем в anotherL - их еще нет в расписании
            t.anotherL.addAll(0, s.l);
            t.anotherL.remove(i);
            kolvoIter = kolvoIter + s.l.size()+1;

            if (startTime<=s.l.get(i).getR())
                t.fine = s.l.get(i).getP()+s.l.get(i).getR()-s.l.get(i).getD();
            else
                t.fine = s.l.get(i).getP()+startTime-s.l.get(i).getD();

            all.add(t);
            //на этом этапе получаем n расписаний, где каждое расписание состоит из одного требования
        }
        //находим минимальный штраф

        double minFine = all.get(0).fine;
        int numberOfMin = 0;

        for (int i = 1; i < all.size(); i++)
        {
            if ((minFine > all.get(i).fine)||((minFine==all.get(i).fine)&&(all.get(i).l.size()<all.get(numberOfMin).l.size())))
            {
                minFine = all.get(i).fine;
                numberOfMin = i;
            }
        }

        //делаем пока изначальное количество требований не совпадает
        //с количеством требований с минимальным штрафом
        while (all.get(numberOfMin).l.size()<s.l.size()) {
            TheChange(numberOfMin,s);
            numberOfMin=ChooseTheMin();

        }


        for (int i=0;i<s.l.size();i++) {
            str = str+all.get(numberOfMin).l.get(i).number+" ";
        }
        str = str + " F(П)=" + all.get(numberOfMin).fine;
        return str;
    }

    //нахождение минимума

    private static int ChooseTheMin () {

        double minFine = all.get(0).fine;
        int numberOfMin = 0;

        for (int i = 1; i < all.size(); i++)
        {
            if ((minFine > all.get(i).fine)||((minFine==all.get(i).fine)&&(all.get(i).l.size()<all.get(numberOfMin).l.size())))
            {
                minFine = all.get(i).fine;
                numberOfMin = i;
            }
        }
    //возвращаем номер - минимум в all
    return numberOfMin;
    }

    private static void TheChange (int numberOfMin,TaskSequence s){

       TaskSequence t = new TaskSequence();
      //  t = all.get(numberOfMin);
        t.l.addAll(all.get(numberOfMin).l);
        t.anotherL.addAll(all.get(numberOfMin).anotherL);

       all.remove(numberOfMin);
       //находим все возможные расписания, если часть расписания уже построена
        for (int i=0;i<t.anotherL.size();i++) {
            Task tr = new Task(t.anotherL.get(i).getR(),t.anotherL.get(i).getP(),t.anotherL.get(i).getD());
            tr.number = t.anotherL.get(i).number;
            all.add(ADD(t.anotherL.get(i),tr, t));
        }
    }


//t - новый элемент, который хотим добавить в расписание
    //j -  расписание, которое будем менять. старое расписание надо сохранить!!
    public static TaskSequence ADD (Task deleteT, Task t, TaskSequence j)
    {
        Task newTask = new Task(t.getR(),t.getP(),t.getD());
       newTask.number = t.number;
        //newTask=t;
        //будем возвращать
        TaskSequence newT = new TaskSequence();

        newT.l.addAll(0, j.l);

        newT.anotherL.addAll(0,j.anotherL);
        newT.anotherL.remove(deleteT);


            if (newTask.getR()<=newT.l.get(newT.l.size()-1).getDd())
                newTask.setDd(newT.l.get(newT.l.size()-1).getDd()+newTask.getP());
            else
                newTask.setDd(newTask.getR()+newTask.getP());

        //в штраф будет записываться последнее требование
        newT.fine = newTask.getDd_d();
        newT.l.add(newTask);
        return newT;
    }






//алгоритм пузырек - сортирует в порядке возрастания по поступлению
    private static void AlgVial(TaskSequence s){
        int TheNamberOf = s.l.size();
        Task task = new Task();
        for (int j=0;j<TheNamberOf;j++){
            for (int i=TheNamberOf-1;i>j;i--)
                if (s.l.get(i-1).getR()>s.l.get(i).getR())
                {
                    task = s.l.get(i);
                    s.l.remove(i);
                    s.l.add(i-1,task);
                }}
    }


    //Алгоритм с прерыванием
    public static TaskSequence NewAlgPrer (int startTime, int n, TaskSequence s)
    {
        TaskSequence result = new TaskSequence();
        String str = "";
        //алгоритм пузырьком - расставим требования по возрастанию моментов поступления
        AlgVial(s);

        //определим время начала работы
        if (startTime<s.l.get(0).getR())
            startTime = s.l.get(0).getR();

        //будем выполнять, пока не удалим все требования - добавим их в результат
        while (s.l.size()>0) {
            //будем хранить с какого элемента учитывается r
         //   int ychitivR = -1;
            int ychitivR = 0;
            for (int i = 0; i < s.l.size(); i++){
                if (s.l.get(i).getR() <= startTime)
                    ychitivR = i + 1;
                 else
                    break;
            }
        //найдем требование с мин директив сроком из выбранных
            int numberOfChosenTask = 0;
            for(int i=1;i<ychitivR;i++)
                if (s.l.get(numberOfChosenTask).getD()>s.l.get(i).getD())
                    numberOfChosenTask = i;
            if (ychitivR!=s.l.size())
            {
            //сравним с временем поступления следующего неучтенного т к требования расставленны в порядке возрастнания

                if ((startTime+s.l.get(numberOfChosenTask).getP())>s.l.get(ychitivR).getR())
                {
                int p = s.l.get(numberOfChosenTask).getP();
                s.l.get(numberOfChosenTask).changeP(s.l.get(ychitivR).getR()- s.l.get(numberOfChosenTask).getR());
                startTime = s.l.get(ychitivR).getR();
                s.l.get(numberOfChosenTask).setDd(startTime);
                result.l.add(s.l.get(numberOfChosenTask));
                s.l.get(numberOfChosenTask).changeP(p - s.l.get(numberOfChosenTask).getP());
                }
                else
                    {
                        //надо или нет переходить к следующему требованию
                    if (ychitivR!=1)
                        startTime = s.l.get(numberOfChosenTask).getR()+s.l.get(numberOfChosenTask).getP();
                    else
                        startTime = s.l.get(ychitivR).getR();
                    s.l.get(numberOfChosenTask).setDd(startTime);
                    result.l.add(s.l.get(numberOfChosenTask));
                    s.l.remove(numberOfChosenTask);
                    }
            }
            else
            {
                startTime = s.l.get(numberOfChosenTask).getR()+s.l.get(numberOfChosenTask).getP();
                s.l.get(numberOfChosenTask).setDd(startTime);
                result.l.add(s.l.get(numberOfChosenTask));
                s.l.remove(numberOfChosenTask);
            }
        }


        return result;
    }


    
public static String Alg3 (ArrayList<Integer> s,TaskSequence res)
{
    String str = "";
    //продолжительность
    ArrayList<Integer> ss = new ArrayList<Integer>();
    for (int i=s.size()-1;i>-1;i--)
        ss.add(s.get(i));

//удаляем повторяющиеся требования
 int i = res.l.size()-2;
 while(i>-1) {
     for (int j = i + 1; j < res.l.size(); j++)
         if (res.l.get(i) == res.l.get(j)) {
             res.l.remove(i);
             j = i + 1;
         }
         i--;
 }
 //меняем продолжительность на изначальную
    for (i = 0;i<res.l.size();i++)
    {
        for (int j=0;j<ss.size();j++) {
            if (res.l.get(i).number == (j+1)) {
                res.l.get(i).changeP(ss.get(j));
                break;
            }
        }
//считаем время окончания кажд требования
        if ((i==0)||(res.l.get(i).getR()>res.l.get(i-1).getDd()))
            res.l.get(i).setDd(res.l.get(i).getR()+res.l.get(i).getP());
        else
            res.l.get(i).setDd(res.l.get(i-1).getDd()+res.l.get(i).getP());

        str = str + res.l.get(i).number + " " ;

    }

    str = str + " F(П)="+res.l.get(res.l.size()-1).getDd_d();
    return str;
}


}
