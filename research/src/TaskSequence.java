//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей
import java.util.ArrayList;

public class TaskSequence {
    //порядок элементов
    ArrayList<Task> l;
    ArrayList<Task> anotherL;
    //дополнительная переменная. даст количество
    int N;
    //штраф
    Integer fine;
    public TaskSequence() {
        l = new ArrayList<Task>();
        anotherL = new ArrayList<Task>();
        N=1;
        //изначально штаф равен нулю
        fine = 0;
    }


    public void Create(int r, int p, int d)
    {
        //штрафная функция  - многочлен
        //Random rnd = new Random(System.currentTimeMillis());
        // Получаем случайное число в диапазоне от 0 до 10 (включительно) - макс степень многочлена
        //int kolvo = 0 + rnd.nextInt(10-0+1);
        //ArrayList<Integer> constants = new ArrayList();
       // for (int i=0;i<kolvo;i++){
            //числа могут варьироваться от 0 до 10
       //     int tn = 0+rnd.nextInt(10-0+1);
       //     constants.add(tn);
            //  System.out.println(tn);
      //  }
        //создаем новую задачу
       // Task t = new Task(r,p,d,constants);
        Task t = new Task(r,p,d);
        //записываем ее уникальный номер
        t.number=N;
        l.add(t);
        //   anotherL.add(t);
        N++;
    }

}
