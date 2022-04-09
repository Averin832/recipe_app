package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.User;
import services.UserService;

public class UserValidator {

    public static List<String> validate(
            UserService service, User u, Boolean mailDuplicateCheckFlag, Boolean passwordCheckFlag
            ){

        List<String> errors = new ArrayList<String>();

        String mailError = validateMail(service, u.getMail(), mailDuplicateCheckFlag);
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

    private static String validateMail(UserService service, String mail, Boolean mailDuplicateCheckFlag) {

        if (mail == null || mail.equals("")){
            return "メールアドレスを入力してください";
        }

        if (mailDuplicateCheckFlag) {

            long userCount = isDuplicateUser(service, mail);

            if (userCount > 0) {
                return "入力されたメールアドレスは既に存在しています。";
            }
        }

        return "";
    }

    private static long isDuplicateUser(UserService service, String mail) {

        long userCount = service.countByMail(mail);
        return userCount;
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
        }

        return "";
    }


}
