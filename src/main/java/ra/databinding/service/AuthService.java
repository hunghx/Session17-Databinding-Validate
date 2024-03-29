package ra.databinding.service;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.databinding.dao.RoleDao;
import ra.databinding.dao.UserDao;
import ra.databinding.model.dto.request.FormLogin;
import ra.databinding.model.dto.request.FormRegister;
import ra.databinding.model.entity.Role;
import ra.databinding.model.entity.RoleName;
import ra.databinding.model.entity.User;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private HttpSession session;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ModelMapper mapper;

    public  void register(FormRegister formRegister){
        // thuc hien dang ki
        // chuyen doi
        User user = mapper.map(formRegister,User.class);
//        User user = User.builder().bod(formRegister.getBod())
//                .build();
        user.setPassword(BCrypt.hashpw(formRegister.getPassword(),BCrypt.gensalt(5)));

        Set<Role> set = new HashSet<>();
        // them quyen user
        set.add(roleDao.loadByRoleName(RoleName.ROLE_USER));
        user.setRoleSet(set);

        // luu vao db
        userDao.register(user);
    }
    public boolean login(FormLogin formLogin){
        try{
            User user = userDao.getUserByUsername(formLogin.getUsername());
            if (user!=null){
                if (BCrypt.checkpw(formLogin.getPassword(),user.getPassword())){
                    // luu thong tin nguoi dung vao session
                    session.setAttribute("userLogin",user);
                    return true;
                }
            }
            return false;
        }catch (NoResultException e){
            return false;
        }
    }
}
