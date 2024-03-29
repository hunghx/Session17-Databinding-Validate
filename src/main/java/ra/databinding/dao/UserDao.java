package ra.databinding.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.databinding.model.entity.User;

import javax.transaction.Transactional;

@Component
@Transactional
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void register(User user){
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    public User getUserByUsername(String name){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User where username = :username", User.class)
                .setParameter("username",name)
                .getSingleResult();
    }
}
