package com.EducacaoApps.InstantFormulas;

import java.util.ArrayList;
import java.util.List;

/*
  Esta classe é responsável por converter os dados de uma String para Double e vice-versa.
  Ela é usada na activity principal quando é obtido um dado a partir do Intent qeu o item enviou
  ao iniciar a activity.
*/
public class ConvertStringtoData {

    // TODO Tentar armazenar uma string vazia
    public static String DataToString(Double[] data){
        // Variável que armazena o dado convertido
        String convert = "";

        // Adiciona cada valor na String
        for(int i = 0;i < data.length; i++){
            if (data[i] != null){
                convert += Double.valueOf(data[i]);
            }
            else{
                convert += "";
            }

            convert += "-";
        }

        return convert;
    }

    // Método somente usado pela formula regra_de_tres
    public static List<Double> StringToData(String data){
        // Variável que armazena os valores
        List<Double> convert = new ArrayList<>();

        // String dividida
        String[] split_string = data.split("-");

        // Loop que adiciona cada novo dado dentro da variável
        for (int i = 0;i < split_string.length; i++){
            convert.add(Double.valueOf(split_string[i]));
        }

        return convert;
    }

    // Método que somente divide a string, retornando uma String vazia quando necessário
    public static String[] SplitString(String data){
        // Variável que armazena a string dividida
        String[] split = data.split("-");

        return split;
    }
}
