//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    public static void write(String fileName,String txt1,String txt2,String txt3, int kolvo) {
        try(FileWriter writer = new FileWriter(fileName, true))//файл не будет пересоздаваться
        {
            int sovpadenia1=0;//сколько несовпадений с алг3
            String fp1="";
            String fp2="";
            String fp3="";
            int sovpadenia2=0;//сколько несовпадений с алг из курсовой
            for (int i=0;i<txt1.length();i++) {
                if (i < 2*kolvo+6) {
                    if (txt1.charAt(i) != txt2.charAt(i))
                        sovpadenia1 = sovpadenia1 + 1;
                    if (txt1.charAt(i) != txt3.charAt(i))
                        sovpadenia2 = sovpadenia2 + 1;
                }
                else
                    {
                fp1 = fp1 + txt1.charAt(i);
                fp2 = fp2 + txt2.charAt(i);
                fp3 = fp3 + txt3.charAt(i);
                    }

            }

            writer.append("\r\n");
          //  writer.append("Метод ветвей и границ: "+txt1);
            writer.append("\r\n");
            writer.append("Алгоритм 2: "+txt2);
            writer.append("\r\n");
            writer.append("Алгоритм 1: "+txt3);
            writer.append("\r\n");
//            String str1 = ""+(Double.parseDouble(fp2)/Double.parseDouble(fp1));
  //          String str2 = ""+(Double.parseDouble(fp3)/Double.parseDouble(fp1));
         /*   writer.append("F2/F*= "+str1+" F1/F*= "+str2+" ");
            writer.append("\r\n");
            writer.append("На сколько не совпадает расписание с оптимальным для алгоритма 2: "+sovpadenia1+" для алгоритма 1: "+sovpadenia2+" ");
            writer.append("\r\n");*/
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
