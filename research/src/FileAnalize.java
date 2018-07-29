//Экспериментальное исследование алгоритмов решения задачи ми-нимизации максимального штрафа
//Выполнено Щелоковой Дарьей

import java.io.*;


public class FileAnalize {
    public static void ReadingAnalizeWhiriting(String fileName) throws FileNotFoundException{

        //соответсвует точному
        Integer n=0;
        Integer n0=0;
        Integer kolvo=0;
        // от 1 до 1,1
        Integer n11=0;
        // от 1,1 до 1,2
        Integer n12=0;
        // от 1,2 до 1,3
        Integer n13=0;
        // от 1,3 до 1,4
        Integer n14=0;

        Integer n10=0;
        //больше 1,5
        Integer n2=0;
        //больше или = 2
        Integer n3=0;


        Integer nn=0;
        Integer nn0=0;
        Integer kkolvo=0;
        // от 1 до 1,5
        Integer nn10=0;
        Integer nn11=0;
        Integer nn12=0;
        Integer nn13=0;
        Integer nn14=0;


        //больше 1,5
        Integer nn2=0;
        //больше или = 2
        Integer nn3=0;

        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);

        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                //В цикле по буквам построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    if (s.isEmpty()) {
                    } else {
                        if (s.charAt(0) == 'F') {
                            if (s.charAt(7) == '1') {
                                if ((s.charAt(9) == '0') && (s.charAt(10) == ' '))
                                    n0++;
                                else if (Integer.parseInt("" + s.charAt(9)) >= 5)
                                    n2++;
                                else if (Integer.parseInt("" + s.charAt(9)) == 0)
                                    n10++;
                                else if (Integer.parseInt("" + s.charAt(9)) == 1)
                                    n11++;
                                else if (Integer.parseInt("" + s.charAt(9)) == 2)
                                    n12++;
                                else if (Integer.parseInt("" + s.charAt(9)) == 3)
                                    n13++;
                                else
                                    n14++;


                            } else
                                n3++;


                            int j = 0;
                            for (int i = s.length() - 1; i > -1; i--)
                                if (s.charAt(i) == '.') {
                                    j = i;
                                    break;
                                }
                            if (s.charAt(j - 1) == '1') {
                                if ((s.charAt(j + 1) == '0') && (s.charAt(j + 2) == ' '))
                                    nn0++;
                                else if (Integer.parseInt("" + s.charAt(j + 1)) >= 5)
                                    nn2++;
                                else if (Integer.parseInt("" + s.charAt(j + 1)) == 0)
                                    nn10++;
                                else if (Integer.parseInt("" + s.charAt(j + 1)) == 1)
                                    nn11++;
                                else if (Integer.parseInt("" + s.charAt(j + 1)) == 2)
                                    nn12++;
                                else if (Integer.parseInt("" + s.charAt(j + 1)) == 3)
                                    nn13++;
                                else
                                    nn14++;
                            } else
                                nn3++;


                        }
                        if ((s.charAt(0) == 'Н') && (nn0 > nn)) {
                            if ((s.charAt(s.length() - 1) == '0') && (s.charAt(s.length() - 2) == ' '))
                                kkolvo++;
                            nn = nn0;
                        }

                        if ((s.charAt(0) == 'Н') && (n0 > n)) {
                            if (s.charAt(66) == '0')
                                kolvo++;
                            n = n0;
                        }
                    }

                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        try(FileWriter writer = new FileWriter(fileName, true))//файл не будет пересоздаваться
        {
            writer.append("\r\n");
            writer.append("\r\n");
            writer.append("Для алгоритма 2");
            writer.append("\r\n");
            writer.append("Количество штрафных функций, совпадающих с точным результатом "+n+"; из них количество совпадающих расписаний "+kolvo);
            writer.append("\r\n");
            writer.append("Количество расписаний в диапазоне от 1,0 до 1,1: "+n10);
            writer.append("\r\n");
            writer.append("Количество расписаний в диапазоне от 1,1 до 1,2: "+n11);
            writer.append("\r\n");
            writer.append("Количество расписаний в диапазоне от 1,2 до 1,3: "+n12);
            writer.append("\r\n");
            writer.append("Количество расписаний в диапазоне от 1,3 до 1,4: "+n13);
            writer.append("\r\n");
            writer.append("Количество расписаний в диапазоне от 1,4 до 1,5: "+n14);
            writer.append("\r\n");
            writer.append("Количество расписаний в диапазоне от 1,5 до 2: "+n2);
            writer.append("\r\n");
            writer.append("Количество расписаний в диапазоне больше 2: "+n3);
            writer.append("\r\n");
            writer.append("\r\n");
            writer.append("Для алгоритма 1");
            writer.append("\r\n");
            writer.append("Количество штрафных функций, совпадающих с точным результатом "+nn+"; из них количество совпадающих расписаний "+kkolvo);
            writer.append("\r\n");
            writer.append("Количество расписаний в диапазоне от 1,0 до 1,1: "+nn10);
            writer.append("\r\n");
            writer.append("Количество расписаний в диапазоне от 1,1 до 1,2: "+nn11);
            writer.append("\r\n");
            writer.append("Количество расписаний в диапазоне от 1,2 до 1,3: "+nn12);
            writer.append("\r\n");
            writer.append("Количество расписаний в диапазоне от 1,3 до 1,4: "+nn13);
            writer.append("\r\n");
            writer.append("Количество расписаний в диапазоне от 1,4 до 1,5: "+nn14);

            writer.append("\r\n");
            writer.append("Количество расписаний в диапазоне от 1,5 до 2: "+nn2);
            writer.append("\r\n");
            writer.append("Количество расписаний в диапазоне больше 2: "+nn3);
            writer.append("\r\n");
            writer.append("\r\n");
            writer.append("\r\n");
            if (nn>n) {
                writer.append("Количество совпавших штрафных функций у первого алгоритма больше, чему у второго");
                if (kkolvo>kolvo)
                    writer.append(" как и количество совпавших расписаний.");
                else
                    if (kkolvo<kolvo)
                        writer.append(", хотя количество совпавших расписаний у алгоритма 2 больше. ");
                else
                        writer.append(", хотя количество совпавших расписаний у алгоритма 1 и алгоритма 2 совпало. ");
            }

            else {
                writer.append("Количество совпавших штрафных функций у второго алгоритма больше, чему у первого");
                if (kkolvo>kolvo)
                    writer.append(", хотя количество совпавших расписаний у алгоритма 1 больше. ");
                else
                if (kkolvo<kolvo)
                    writer.append(" как и количество совпавших расписаний.");
                else
                    writer.append(", хотя количество совпавших расписаний у алгоритма 1 и алгоритма 2 совпало. ");

            }
            if (nn==n) {
                writer.append("Количество совпавших штрафных функций у второго алгоритма и у первого одинаково");
                if (kkolvo>kolvo)
                    writer.append(", хотя количество совпавших расписаний у алгоритма 1 больше. ");
                else
                    if (kkolvo==kolvo)
                        writer.append(" как и количество совпавших расписаний.");
                else
                    writer.append(", хотя количество совпавших расписаний у алгоритма 2 больше. ");

            }
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
