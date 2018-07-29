/**
 * Created by User on 21.05.2017.
 */
//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей
public class SaveMax {
    private int number;
    private int znach;
    public SaveMax(int zn, int n){
      number=n;
      znach=zn;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getZnach() {
        return znach;
    }

    public void setZnach(int znach) {
        this.znach = znach;
    }
}
