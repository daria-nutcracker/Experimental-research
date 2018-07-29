//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей
import java.util.ArrayList;

public class Task {
    public int number;
    //момент поступления
    private int r;
    //продолжительность
    private int p;
    //время, после кот будем платить
    private int d;
    //время, когда закончится на самом деле
    private int Dd;
    //разница между заданным временем и реальным
    private int Dd_d;
    //числа для штрафной функции
    ArrayList<Integer> constants;
    public Task (int r, int p, int d,ArrayList<Integer> constants)
    {
        this.r = r;
        this.p = p;
        this.d = d;
        //  Dd=d;
        // Dd_d=0;
        Dd = r+p;
        if (Dd>d)
            Dd_d=Dd-d;
        else
            Dd_d=0;
        number=-1;
        this.constants = constants;
    }
    public Task (int r, int p, int d)
    {
        this.r = r;
        this.p = p;
        this.d = d;
        //Dd=d;
        Dd = r+p;
        if (Dd>d)
            Dd_d=Dd-d;
        else
            Dd_d=0;
        number=-1;
    }
    public Task ()
    {
        this.r = 0;
        this.p = 0;
        this.d = 0;
    }
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

        //проверка на штраф - штраф будет, если время завершения превышает директивный срок
        Dd = dd;
        //   Dd_d =0;
        if (Dd-d>=0)
            Dd_d = Dd-d;
        else
            Dd_d =0;
      /*  Dd_d =0;
        if (Dd-d>=0){
            //тогда считаем штраф функцию
            for (int i=0;i<constants.size();i++)
                Dd_d=Dd_d+constants.get(i)*(int)Math.pow(dd,i);
        }*/
    }

    public int getDd_d() {
        return Dd_d;
    }

    @Override
    public String toString() {
        return "начало "+r+" продолжительность "+p+" ожид окончание "+d+" реальное окончание "+ Dd+" разница "+ Dd_d+" номер по порядку "+ number;

    }

    //для алгоритма с прерываниями
    public void changeP(int k){
        p = k;
    }
}
