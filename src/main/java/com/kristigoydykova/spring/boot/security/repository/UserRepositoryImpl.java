package com.kristigoydykova.spring.boot.security.repository;


import com.kristigoydykova.spring.boot.security.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {

        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {

        entityManager.persist(user);
    }


    @Override
    public User getUserById(long id) {


         return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User updateUser) {

        entityManager.merge(updateUser);
   }

    @Override
    public void deleteUser(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user); // если объединить, то в данном случае,
        // теряется легкочитаемость кода, как мне кажется)
    }

    @Override
    public User findUserByName(String username) {
        return (User) entityManager
                .createQuery("from User u  inner JOIN FETCH u.roles as roles WHERE u.username = :username")
                .setParameter("username", username)
                .getSingleResult();
    }

}
