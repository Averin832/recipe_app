package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidator {


    public static List<String> validate(
            User u, Boolean mailDuplicateCheckFlag, Boolean passwordCheckFlag
            ){

        List<String> errors = new ArrayList<String>();

        String mailError = validateMail(u.getMail(), mailDuplicateCheckFlag);
        if (!mailError.equals("")) {
            errors.add(mailError);
        }

        String nameError = validateName(u.getName());
            if (!nameError.equals("")) {
                errors.add(nameError);
            }

        String passError = validatePassword(u.getPassword(), passwordCheckFlag);
        if (!passError.equals("")) {
            errors.add(passError);
        }

        return errors;

    }

    private static String validateMail(String mail, Boolean mailDuplicateCheckFlag) {

        if (mail == null || mail.equals("")){
            return "メールアドレスを入力してください";
        }

        if (mailDuplicateCheckFlag) {

            long userCount = isDuplicateUser(mail);

            if (userCount > 0) {
                return "入力されたメールアドレスは既に存在しています。";
            }
        }

        return "";
    }

    private static long isDuplicateUser(String mail) {

        EntityManager em = DBUtil.createEntityManager();

        long users_count = (long) em.createNamedQuery("countRegisteredByMail", Long.class)
                .setParameter("mail", mail)
                .getSingleResult();

        em.close();

        return users_count;

    }

    private static String validateName(String name) {
         if (name == null || name.equals("")) {
             return "ユーザーネームを入力してください";
         }

         return "";
    }

    private static String validatePassword(String password, Boolean passwordCheckFlag) {

        if (passwordCheckFlag && (password == null || password.equals(""))) {
            return "パスワードを入力してください";
        } else {

        return "";
        }
    }

}
