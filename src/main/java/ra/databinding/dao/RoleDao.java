package ra.databinding.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.databinding.model.entity.Role;
import ra.databinding.model.entity.RoleName;

import javax.transaction.Transactional;

@Component
@Transactional
public class RoleDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Role  loadByRoleName(RoleName roleName){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Role where roleName=:roleName",Role.class)
                .setParameter("roleName",roleName)
                .getSingleResult();
    }
}
