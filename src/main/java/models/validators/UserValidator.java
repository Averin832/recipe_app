package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import models.User;
import utils.DBUtil;
import utils.EncryptUtil;

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

         if (name.length() > 12) {
             return "ユーザーネームは12文字以内にしてください";
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

    public static Boolean validateLogin(String mail, String plainPass, String pepper) {

        boolean isValidUser = false;
        if (mail != null && !mail.equals("") && plainPass != null && !plainPass.equals("")) {

            User u = findOne(mail, plainPass, pepper);
            if (u != null && u.getId() != null) {

                isValidUser = true;
            }
        }

        return isValidUser;
    }

    public static User findOne(String mail, String plainPass, String pepper) {

        User u = null;

        try {

            EntityManager em = DBUtil.createEntityManager();

            String password = EncryptUtil.getPasswordEncrypt(plainPass, pepper);
            u = em.createNamedQuery("getByMailAndPass", User.class)
                    .setParameter("mail", mail)
                    .setParameter("password", password)
                    .getSingleResult();

            em.close();

        } catch (NoResultException ex) {
        }

        return u;
    }
}
