package ru.flametaichou.quest.core.dao;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import ru.flametaichou.quest.core.domain.Account;

@Repository
public class AccountDaoImpl extends HibernateDaoSupport implements AccountDao {

    @Resource
    public void configure(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void saveOrUpdate(Account account) {
        try {
            getHibernateTemplate().saveOrUpdate(account);
        } catch (Exception e) {
            logger.error("Error saveOrUpdate account!");
            logger.error("Error message: {}", e.getCause());
        }

    }

    @Override
    public Account findByUserName(String username) {
        SessionFactory sessionFactory = this.getSessionFactory();

        return getHibernateTemplate().execute(session -> {
            List<Account> accounts = new ArrayList<Account>();
            accounts = sessionFactory.getCurrentSession()
                    .createQuery("from Account user where user.username = :username and user.enabled = true")
                    .setString("username", username)
                    .list();

            if (accounts.size() > 0) {
                return accounts.get(0);
            } else {
                return null;
            }
        });
    }

    @Override
    public List<Account> listUsers() {
        SessionFactory sessionFactory = this.getSessionFactory();

        return getHibernateTemplate().execute(session -> {
            List<Account> accounts = new ArrayList<Account>();

            accounts = sessionFactory.getCurrentSession()
                    .createQuery("from Account user where user.enabled = true")
                    .list();

            return accounts;
        });
    }

    @Override
    public void delete(Account account) {
        SessionFactory sessionFactory = this.getSessionFactory();

        getHibernateTemplate().execute(new HibernateCallback<Account>() {
            @Override
            public Account doInHibernate(Session session) throws HibernateException {
                sessionFactory.getCurrentSession().delete(account);
                return null;
            }
        });
    }
}
