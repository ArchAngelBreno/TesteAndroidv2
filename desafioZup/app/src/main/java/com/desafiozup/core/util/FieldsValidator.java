package com.desafiozup.core.util;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldsValidator {

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";

    private static boolean isValidEmail(String email) {
        try {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean isValidLogin(String user){
        return isValidEmail(user) || isValidCPF(user);
    }


    public static boolean isValidPassword(String password) {
        return isValidPattern(PASSWORD_PATTERN, password);

    }

    //criado pelo DevMedia
    private static boolean isValidCPF(String cpf) {
        try {
            int d1, d2;
            int digito1, digito2, resto;
            int digitoCPF;
            String nDigResult;

            d1 = d2 = 0;
            digito1 = digito2 = resto = 0;

            for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
                digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

                d1 = d1 + (11 - nCount) * digitoCPF;

                d2 = d2 + (12 - nCount) * digitoCPF;
            }

            resto = (d1 % 11);

            if (resto < 2)
                digito1 = 0;
            else
                digito1 = 11 - resto;

            d2 += 2 * digito1;

            resto = (d2 % 11);

            if (resto < 2)
                digito2 = 0;
            else
                digito2 = 11 - resto;

            String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

            nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

            return nDigVerific.equals(nDigResult);
        } catch (Exception e) {
            return false;
        }

    }

    private static boolean isValidPattern(String stringPattern, String value) {
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher passwordMatcher = pattern.matcher(value);

        return passwordMatcher.matches();
    }

}
