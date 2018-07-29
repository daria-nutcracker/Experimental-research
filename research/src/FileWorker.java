//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей
import java.io.*;

public class FileWorker {
    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }

    public static TaskSequence read(String fileName) throws FileNotFoundException {
        TaskSequence a = new TaskSequence();
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
                    a.Create(Integer.parseInt(n1),Integer.parseInt(n2),Integer.parseInt(n3));
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Возвращаем полученный текст с файла
        return a;
    }

}