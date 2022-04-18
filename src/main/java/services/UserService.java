package services;

import java.util.List;

import models.User;
import models.validators.UserValidator;
import utils.EncryptUtil;

public class UserService extends ServiceBase{

    public List<User> getPerPage(int page){

        List<User> users = em.createNamedQuery("getAllUsers", User.class)
                .setFirstResult(25 * (page - 1))
                .setMaxResults(25)
                .getResultList();

        return users;
    }

    public long countAll() {
        long userCount = (long)em.createNamedQuery("getUsersCount", Long.class)
                .getSingleResult();

        return userCount;
    }





    public User findOne(int id) {

        User u = findOneInternal(id);

        return u;
    }

    private User findOneInternal(int id ) {

        User u = em.find(User.class, id);

        return u;
    }

    public List<String> create(User u, String pepper) {

        //パスワードをハッシュ化して設定
        String pass = EncryptUtil.getPasswordEncrypt(u.getPassword(), pepper);
        u.setPassword(pass);

        //登録内容のバリデーションを行う
        List<String> errors = UserValidator.validate(u, true, true);

        //バリデーションエラーがなければデータを登録する
        if (errors.size() == 0) {
            create(u);
        }

        //エラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    public List<String> update(User u, String pepper){

        User savedU = findOne(u.getId());

        boolean validateMail = false;
        if (!savedU.getMail().equals(u.getMail())) {
            validateMail = true;
            savedU.setMail(u.getMail());
        }

        boolean validatePassword = false;
        if  (u.getPassword() != null && !u.getPassword().equals("")) {
            validatePassword = true;
            savedU.setPassword(EncryptUtil.getPasswordEncrypt(u.getPassword(), pepper));
        }

        savedU.setName(u.getName());

        List<String> errors = UserValidator.validate(savedU, validateMail, validatePassword);

        if(errors.size() == 0) {
            update(savedU);
        }

        return errors;


    }



    private void create(User u) {

        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }

    private void update(User u) {

        em.getTransaction().begin();
        em.getTransaction().commit();

    }

}
