//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


/**
 * Created by User on 16.05.2017.
 */
public class WorkInFile
{
    public static void write(String fileName, int diapazonR1,int diapazonR2,int diapazonD1,int diapazonD2,int diapazonP1,int diapazonP2, int kolvo) {
        //Определяем файл
        File file = new File(fileName);

        try {
            //проверяем, что если файл не существует то создаем его
            if(!file.exists()){
                file.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                Random r = new Random();
                int a=0;
                String text="";
                int b=0;
                int k=0;
                //Записываем текст в файл
                for (int i=0;i<kolvo;i++)
                {
                    //время поступления b
                    //время окончания к
                   b = diapazonR1 + r.nextInt(diapazonR2-diapazonR1+1);
                   k=diapazonD1+r.nextInt(diapazonD2-diapazonD1+1);
                   a=diapazonP1+r.nextInt(diapazonP2-diapazonP1+1);
                   text=""+b+";"+a+";"+k+";\n";
                    out.print(text);
                }
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

}
