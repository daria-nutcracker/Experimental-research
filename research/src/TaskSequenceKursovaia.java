//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей
import java.util.ArrayList;

public class TaskSequenceKursovaia {
    //порядок элементов
    ArrayList<Task> l;
    //порядок по максимумам
    ArrayList<SaveMax> max;
    //дополнительная переменная. даст количество
    int N;
    public TaskSequenceKursovaia(int t) {
        l = new ArrayList<Task>();
        max = new ArrayList<SaveMax>();
        N=0;
    }

  public void delete(int i){
      //мне передается номер порядковый
        int k=0;
        int n=l.size();
      for (int j=0;j<n;j++) {
          if (max.get(j).getNumber() == i) {
              max.remove(j);
              break;
          }
      }
      for (int j=0;j<n;j++){
          if (l.get(j).number==i) {
              l.remove(j);
              k = j;
              break;
          }
      }
      for (int j=k;j<n-1;j++) {
          if (j != 0) {
              if (l.get(j - 1).getDd() >= l.get(j).getR())
                  l.get(j).setDd(l.get(j - 1).getDd() + l.get(j).getP());
              else
                  l.get(j).setDd(l.get(j).getR()+l.get(j).getP());
          }
          else
              l.get(j).setDd(l.get(j).getR()+l.get(j).getP());
          for (int a=0;a<max.size();a++)
              if(max.get(a).getNumber()==l.get(j).number)
                  max.get(a).setZnach(l.get(j).getDd_d());
      }

  }
    public void Create(int r, int p, int d, int n)
    {

        //создаем новую задачу
        Task t = new Task(r,p,d);
        //записываем ее уникальный номер
        t.number=n;

        if(N!=0){
            if (t.getR()<=l.get(N-1).getDd())
                t.setDd(l.get(N-1).getDd()+t.getP());
            else
                t.setDd(t.getR()+t.getP());
        }
        else
            t.setDd(t.getR()+t.getP());
        l.add(t);
        SaveMax save = new SaveMax(t.getDd_d(),n);
        max.add(save);
        N++;
    }
    public static void Maximize(ArrayList<SaveMax> max, int low, int high)
    {
        //первоначально int low=0;
        //int high=N;
        int i = low;
        int j = high;
        int x = max.get(low+(high-low)/2).getZnach();
        do {
            while(max.get(i).getZnach() > x) ++i;
            while(max.get(j).getZnach() < x) --j;
            if(i <= j){
                SaveMax temp = new SaveMax(max.get(i).getZnach(),max.get(i).getNumber());
                max.set(i, max.get(j));
                max.set(j, temp);
                i ++ ; j --;
            }
        } while(i <= j);
        //рекурсивные вызовы функции Maximize
        if(low < j) Maximize(max, low, j);
        if(i < high) Maximize(max, i, high);
    }
    public static void Minimize(ArrayList<Task> l, int low, int high)
    {
        //первоначально int low=0;
        //int high=N;
        int i = low;
        int j = high;
        int x = l.get(low+(high-low)/2).getR();
        do {
            while(l.get(i).getR() < x) ++i;
            while(l.get(j).getR() > x) --j;
            if(i <= j){
                Task temp = new Task(l.get(i).getR(),l.get(i).getP(),l.get(i).getD());
                temp.setDd(l.get(i).getDd());
                temp.number=l.get(i).number;
                l.set(i, l.get(j));
                l.set(j, temp);
                i ++ ; j --;
            }
        } while(i <= j);
        //рекурсивные вызовы функции Minimize
        if(low < j) Minimize(l, low, j);
        if(i < high) Minimize(l, i, high);
    }
    public static TaskSequenceKursovaia OneOption (int i, TaskSequenceKursovaia s)
    {
        TaskSequenceKursovaia ss=new TaskSequenceKursovaia(0);
        for (int j=0;j<s.l.size();j++) {
            if (j != i) {
                ss.Create(s.l.get(j).getR(), s.l.get(j).getP(), s.l.get(j).getD(),s.l.get(j).number);
            }
        }
        ss.Create(s.l.get(i).getR(),s.l.get(i).getP(),s.l.get(i).getD(),s.l.get(i).number);
       return ss;
    }

}
