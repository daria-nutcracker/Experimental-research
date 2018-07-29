//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей
import java.util.ArrayList;

public class TaskSequenceArrayKursovaia {
     static ArrayList<Task> result;
    static ArrayList<Integer> res;
    //всевозможные варианты
  private static ArrayList<TaskSequenceKursovaia> all;
  //  static int Min;
    private static int N;

   public static String TSArray(int n, TaskSequenceKursovaia s)
    {
        N=n;
        result=new ArrayList<Task>();
        all=new ArrayList<TaskSequenceKursovaia>();
        showPermutations(s);
        for (int i=0;i<all.size();i++)
        {
            TaskSequenceKursovaia.Maximize(all.get(i).max,0,n-1);
        }

        for (int i=0;i<n;i++)
            ChooseTheMin();

        String str="";
        TaskSequenceKursovaia sl = new TaskSequenceKursovaia(0);
       for (int u=result.size()-1;u>=0;u--) {
           str=str+""+(result.get(u).number+1)+" ";
           sl.Create(result.get(u).getR(),result.get(u).getP(), result.get(u).getD(), result.get(u).number);
       }
       int ts=sl.l.get(sl.l.size()-1).getDd_d();
        str = str+" F(П)="+ts;
        return str;
    }

    //добавить все возможные варианты
    private static void showPermutations( TaskSequenceKursovaia s) {
        TaskSequenceKursovaia.Minimize(s.l, 0, s.l.size() - 1);
        all.add(s);
        for (int i = s.l.size()-2; i >=0;i--) {
            all.add(TaskSequenceKursovaia.OneOption(i, s));
        }
    }

    //нахождение минимума
    private static void ChooseTheMin ()
    {
        //само значение
        int min=all.get(0).max.get(0).getZnach();
        int ii=0;
        for (int i=1;i<all.size();i++)
           if (all.get(i).max.get(0).getZnach()<min) {
            //мин среди максимумов
               min = all.get(i).max.get(0).getZnach();
               ii=i;
           }
           // в нулевом он должен оказаться на j месте в l
        int tt=all.get(ii).max.get(0).getNumber();
        for (int i=0;i<all.get(ii).l.size();i++)
            if (all.get(ii).l.get(i).number==tt)
                AddToResult(all.get(ii).l.get(i));
    }
    //добавление результата
   private static void AddToResult(Task k){
        //добавление результата
        result.add(k);
        //удаление этого значения в каждой перестановке
        for (int i=0;i<all.size();i++)
            all.get(i).delete(k.number);
    }
}
